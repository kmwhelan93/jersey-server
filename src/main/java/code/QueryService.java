package code;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import com.google.common.collect.Lists;

import jsonObjects.BaseLocation;
import jsonObjects.Point;
import static jooq.generated.Tables.*;

public class QueryService {
	private static DSLContext create = DSL.using(DBConn.getInstance().getConnection(), SQLDialect.MYSQL);
	
	public static List<BaseLocation> getBases(String username) {
		Result<Record> results = create.select().from(BASES).where(BASES.USERNAME.equal(username)).fetch();
		List<BaseLocation> baseLocations = Lists.newArrayList();
		for (Record r : results) {
			baseLocations.add(new BaseLocation(
					r.getValue(BASES.BASE_ID),
					new Point(r.getValue(BASES.WORLD_X), r.getValue(BASES.WORLD_Y)),
					new Point(r.getValue(BASES.LOCAL_X), r.getValue(BASES.LOCAL_Y))));
		}
		return baseLocations;
	}
}
