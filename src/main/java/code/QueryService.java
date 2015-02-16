package code;

import java.util.ArrayList;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import sqlTableObjects.Base;
import sqlTableObjects.Portal;

import com.google.common.collect.Lists;

import jooq.generated.tables.BaseOwners;
import jooq.generated.tables.records.BasesRecord;
import jsonObjects.Point;
import static jooq.generated.Tables.*;

public class QueryService {
	public static DSLContext create = DSL.using(DBConn.getInstance().getConnection(), SQLDialect.MYSQL);
	private static int DEFAULT_PROD_RATE = 200;
	public static List<Base> getUserBases(String username) {
		Result<Record> results = create.select().from(BASE_OWNERS).where(BASE_OWNERS.USERNAME.equal(username)).fetch();
		List<Base> baseLocations = Lists.newArrayList();
		for (Record r : results) {
			baseLocations.add(getBase(r, BASE_OWNERS));
		}
		return baseLocations;
	}
	
	private static Base getBase(Record r, BaseOwners b) {
		return new Base(r.getValue(b.USERNAME),
				r.getValue(b.COLOR_ID),
				r.getValue(b.BASE_ID),
				new Point(r.getValue(b.WORLD_X), r.getValue(b.WORLD_Y)),
				new Point(r.getValue(b.LOCAL_X), r.getValue(b.LOCAL_Y)));
	}
	
	public static int createBase(int prodRate) {
		try {
			BasesRecord basesRecord =  create.insertInto(BASES, BASES.PROD_RATE)
				.values(prodRate)
				.returning(BASES.BASE_ID)
				.fetchOne();
			return basesRecord.getBaseId();
		} catch (Exception e) {
			return -1;
		}
	}
	
	public static boolean captureBase(Base b) {
		return captureBase(b.username, b.baseId, b.world.x, b.world.y, b.local.x, b.local.y);
	}
	
	public static boolean captureBase(
			String username,
			int baseId,
			int worldX,
			int worldY,
			int localX,
			int localY) {
		disownBase(baseId);
		int result = create.insertInto(BASE_OWNERS, BASE_OWNERS.USERNAME, BASE_OWNERS.BASE_ID, BASE_OWNERS.WORLD_X, BASE_OWNERS.WORLD_Y, BASE_OWNERS.LOCAL_X, BASE_OWNERS.LOCAL_Y)
			.values(username, baseId, worldX, worldY, localX, localY)
			.execute();
		return result == 0;
	}
	
	public static void persistNewBase(Base b) {
		try {
			if (b.baseId == -1) {
				b.baseId = createBase(DEFAULT_PROD_RATE);
			}
			captureBase(b);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void disownBase(int baseId) {
		create.delete(BASE_OWNERS)
			.where(BASE_OWNERS.BASE_ID.equal(baseId))
			.execute();
	}
	
	//////////// PORTALS ///////////////
	
	public static List<Portal> getPortals(String username) {
		ArrayList<Portal> retVal = Lists.newArrayList();
		BaseOwners base1 = BASE_OWNERS.as("base1");
		BaseOwners base2 = BASE_OWNERS.as("base2");
		Result<Record> records = create.select()
			.from(PORTALS
					.join(base1)
						.on(base1.BASE_ID.equal(PORTALS.BASE_ID1))
					.join(base2)
						.on(base2.BASE_ID.equal(PORTALS.BASE_ID2))
					)
			.fetch();
		for (Record r : records) {
			retVal.add(getPortal(r, base1, base2));
		}
		return retVal;
	}
	
	private static Portal getPortal(Record r, BaseOwners base1, BaseOwners base2) {
		return new Portal(r.getValue(PORTALS.PORTAL_ID), 
				r.getValue(PORTALS.USERNAME),
				getBase(r, base1),
				getBase(r, base2),
				r.getValue(PORTALS.FLOW_RATE));
	}
	
	public static boolean createPortal(String username, int baseId1, int baseId2) {
		try {
			int result = create.insertInto(PORTALS, PORTALS.USERNAME, PORTALS.BASE_ID1, PORTALS.BASE_ID2, PORTALS.FLOW_RATE)
					.values(username, baseId1, baseId2, 10)
					.execute();
			return result == 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void disownPortals(String username) {
		create.delete(PORTALS)
			.where(PORTALS.USERNAME.equal(username))
			.execute();
	}
	
	public static void main (String[] args) {
		System.out.println(getPortals("kmw8sf"));
	}
}
