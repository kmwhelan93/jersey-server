package code;

import java.util.ArrayList;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import sqlTableObjects.Base;

import com.google.common.collect.Lists;

import jooq.generated.tables.records.BasesRecord;
import jsonObjects.Point;
import static jooq.generated.Tables.*;

public class QueryService {
	private static DSLContext create = DSL.using(DBConn.getInstance().getConnection(), SQLDialect.MYSQL);
	
	public static List<Base> getUserBases(String username) {
		Result<Record> results = create.select().from(BASE_OWNERS).where(BASE_OWNERS.USERNAME.equal(username)).fetch();
		List<Base> baseLocations = Lists.newArrayList();
		for (Record r : results) {
			baseLocations.add(new Base(
					r.getValue(BASE_OWNERS.USERNAME),
					r.getValue(BASE_OWNERS.COLOR_ID),
					r.getValue(BASE_OWNERS.BASE_ID),
					new Point(r.getValue(BASE_OWNERS.WORLD_X), r.getValue(BASE_OWNERS.WORLD_Y)),
					new Point(r.getValue(BASE_OWNERS.LOCAL_X), r.getValue(BASE_OWNERS.LOCAL_Y))));
		}
		return baseLocations;
	}
	
	public static int createBase(int prodRate) {
		BasesRecord basesRecord =  create.insertInto(BASES, BASES.PROD_RATE)
			.values(prodRate)
			.returning(BASES.BASE_ID)
			.fetchOne();
		return basesRecord.getBaseId();
	}
	
	public static boolean captureBase(
			String username,
			int baseId,
			int worldX,
			int worldY,
			int localX,
			int localY) {
		int result = create.insertInto(BASE_OWNERS, BASE_OWNERS.USERNAME, BASE_OWNERS.BASE_ID, BASE_OWNERS.WORLD_X, BASE_OWNERS.WORLD_Y, BASE_OWNERS.LOCAL_X, BASE_OWNERS.LOCAL_Y)
			.values(username, baseId, worldX, worldY, localX, localY)
			.execute();
		return result == 0;
	}
	
	public static void main (String[] args) {
		System.out.println(getUserBases("kmw8sf"));
	}
}
