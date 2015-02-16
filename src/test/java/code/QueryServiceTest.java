package code;


import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.Before;
import org.junit.Test;

import static jooq.generated.Tables.*;
import code.DBConn;
import code.HelloWorld;

public class QueryServiceTest {
	public HelloWorld hw;
	public Connection conn;

	@Before
	public void setUp() throws SQLException {
		hw = new HelloWorld();
		DBConn mockedConn = mock(DBConn.class);
		hw.dbConn = mockedConn;
		conn = this.createKevinDBConnection();
		when(mockedConn.getConnection()).thenReturn(conn);
		this.configureTables();
	}
	
	public void configureTables() throws SQLException {
		DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
		create.deleteQuery(PORTALS).execute();
		create.deleteQuery(BASE_OWNERS).execute();
		create.deleteQuery(BASES).execute();
		create.deleteQuery(USERS).execute();
		
		create.insertInto(USERS, USERS.USERNAME, USERS.PASSWORD).values("kevin", "kevin").execute();
		
		create.insertInto(BASES, BASES.BASE_ID, BASES.PROD_RATE).values(1, 10).execute();
		create.insertInto(BASES, BASES.BASE_ID, BASES.PROD_RATE).values(2, 10).execute();
		create.insertInto(BASES, BASES.BASE_ID, BASES.PROD_RATE).values(3, 10).execute();
		create.insertInto(BASES, BASES.BASE_ID, BASES.PROD_RATE).values(4, 10).execute();
		
		create.insertInto(BASE_OWNERS, BASE_OWNERS.USERNAME, BASE_OWNERS.BASE_ID, BASE_OWNERS.COLOR_ID, BASE_OWNERS.WORLD_X, BASE_OWNERS.WORLD_Y, BASE_OWNERS.LOCAL_X, BASE_OWNERS.LOCAL_Y, BASE_OWNERS.NUM_UNITS)
			.values("kevin", 1, 1, 0, 0, 1, 0, 10).execute();
		create.insertInto(BASE_OWNERS, BASE_OWNERS.USERNAME, BASE_OWNERS.BASE_ID, BASE_OWNERS.COLOR_ID, BASE_OWNERS.WORLD_X, BASE_OWNERS.WORLD_Y, BASE_OWNERS.LOCAL_X, BASE_OWNERS.LOCAL_Y, BASE_OWNERS.NUM_UNITS)
			.values("kevin", 2, 2, 1, 0, -1, -1, 10).execute();
		create.insertInto(BASE_OWNERS, BASE_OWNERS.USERNAME, BASE_OWNERS.BASE_ID, BASE_OWNERS.COLOR_ID, BASE_OWNERS.WORLD_X, BASE_OWNERS.WORLD_Y, BASE_OWNERS.LOCAL_X, BASE_OWNERS.LOCAL_Y, BASE_OWNERS.NUM_UNITS)
			.values("kevin", 3, 3, 1, 1, 0, -1, 10).execute();
		create.insertInto(BASE_OWNERS, BASE_OWNERS.USERNAME, BASE_OWNERS.BASE_ID, BASE_OWNERS.COLOR_ID, BASE_OWNERS.WORLD_X, BASE_OWNERS.WORLD_Y, BASE_OWNERS.LOCAL_X, BASE_OWNERS.LOCAL_Y, BASE_OWNERS.NUM_UNITS)
			.values("kevin", 4, 4, -1, 0, 0, 0, 10).execute();
		
		create.insertInto(PORTALS, PORTALS.PORTAL_ID, PORTALS.USERNAME, PORTALS.BASE_ID1, PORTALS.BASE_ID2, PORTALS.FLOW_RATE)
			.values(1, "kevin", 3, 2, 10).execute();
		
		QueryService.create = create;
		
	}

	@Test
	public void test() {
		System.out.println(QueryService.getUserBases("kevin"));
	}

	public Connection createKevinDBConnection() {
		Connection conn = null;

		String userName = "root";
		String password = "";
		String url = "jdbc:mysql://localhost:3306/maimon";

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, userName, password);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException ignore) {

			}
			return null;

		}
	}
}

