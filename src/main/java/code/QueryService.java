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
	
	public static void main (String[] args) {
		System.out.println(getUserBases("kmw8sf"));
	}
}
