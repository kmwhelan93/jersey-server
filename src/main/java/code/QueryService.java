package code;

import java.util.ArrayList;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import sqlTableObjects.BaseObj;
import sqlTableObjects.Portal;

import com.google.common.collect.Lists;

import jooq.generated.tables.BaseOwners;
import jooq.generated.tables.Bases;
import jooq.generated.tables.records.BasesRecord;
import jsonObjects.Point;
import static jooq.generated.Tables.*;

public class QueryService {
	public static DSLContext create = DSL.using(DBConn.getInstance().getConnection(), SQLDialect.MYSQL);
	private static int DEFAULT_PROD_RATE = 200;
	public static List<BaseObj> getUserBases(String username) {
		Result<Record> results = create.select()
				.from(BASE_OWNERS)
				.join(BASES)
					.on(BASES.BASE_ID.equal(BASE_OWNERS.BASE_ID))
				.where(BASE_OWNERS.USERNAME.equal(username)).fetch();
		List<BaseObj> baseLocations = Lists.newArrayList();
		for (Record r : results) {
			baseLocations.add(getBase(r, BASES, BASE_OWNERS));
		}
		return baseLocations;
	}
	
	private static BaseObj getBase(Record r, Bases bases, BaseOwners b) {
		return new BaseObj(r.getValue(b.USERNAME),
				r.getValue(b.COLOR_ID),
				r.getValue(b.BASE_ID),
				new Point(r.getValue(b.WORLD_X), r.getValue(b.WORLD_Y)),
				new Point(r.getValue(b.LOCAL_X), r.getValue(b.LOCAL_Y)),
				r.getValue(bases.PROD_RATE),
				r.getValue(b.NUM_UNITS));
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
	
	public static boolean captureBase(BaseObj b) {
		return captureBase(b.username, b.baseId, b.world.x, b.world.y, b.local.x, b.local.y, b.units);
	}
	
	public static boolean captureBase(
			String username,
			int baseId,
			int worldX,
			int worldY,
			int localX,
			int localY,
			int units) {
		disownBase(baseId);
		int result = create.insertInto(BASE_OWNERS, BASE_OWNERS.USERNAME, BASE_OWNERS.BASE_ID, BASE_OWNERS.WORLD_X, BASE_OWNERS.WORLD_Y, BASE_OWNERS.LOCAL_X, BASE_OWNERS.LOCAL_Y, BASE_OWNERS.NUM_UNITS)
			.values(username, baseId, worldX, worldY, localX, localY, units)
			.execute();
		return result == 0;
	}
	
	public static int persistNewBase(BaseObj b) {
		try {
			if (b.baseId == -1) {
				b.baseId = createBase(b.prodRate);
			}
			captureBase(b);
			return b.baseId;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
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
		Bases bases1 = BASES.as("base1");
		BaseOwners baseOwner1 = BASE_OWNERS.as("baseOwner1");
		Bases bases2 = BASES.as("base2");
		BaseOwners baseOwner2 = BASE_OWNERS.as("baseOwner2");
		Result<Record> records = create.select()
			.from(PORTALS
					.join(baseOwner1)
						.on(baseOwner1.BASE_ID.equal(PORTALS.BASE_ID1))
					.join(bases1)
						.on(bases1.BASE_ID.equal(PORTALS.BASE_ID1))
					.join(baseOwner2)
						.on(baseOwner2.BASE_ID.equal(PORTALS.BASE_ID2))
					.join(bases2)
						.on(bases2.BASE_ID.equal(PORTALS.BASE_ID2))
					)
			.fetch();
		for (Record r : records) {
			retVal.add(getPortal(r, bases1, baseOwner1, bases2, baseOwner2));
		}
		return retVal;
	}
	
	private static Portal getPortal(Record r, Bases bases1, BaseOwners baseOwner1, Bases bases2, BaseOwners baseOwner2) {
		return new Portal(r.getValue(PORTALS.PORTAL_ID), 
				r.getValue(PORTALS.USERNAME),
				getBase(r, bases1, baseOwner1),
				getBase(r, bases2, baseOwner2),
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
	
	public static boolean portalExists(String username, int baseId1, int baseId2) {
		Result<Record> results = create.select()
			.from(PORTALS)
			.where(PORTALS.USERNAME.equal(username).and(PORTALS.BASE_ID1.equal(baseId1).and(PORTALS.BASE_ID2.equal(baseId2))))
			.or(PORTALS.USERNAME.equal(username).and(PORTALS.BASE_ID1.equal(baseId2).and(PORTALS.BASE_ID2.equal(baseId1))))
			.fetch();
			
		if (results.isNotEmpty()) {
			return true;
		}
		return false;
	}
	
	public static void main (String[] args) {
		System.out.println(getPortals("kmw8sf"));
	}
}
