package code;


import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import jsonObjects.Point;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.junit.Before;
import org.junit.Test;

import sqlTableObjects.BaseObj;

import com.google.common.collect.Lists;

import static jooq.generated.Tables.*;
import code.DBConn;
import code.HelloWorld;
import static org.hamcrest.Matchers.*;

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
		List<String> toDrop = Lists.newArrayList("portals", "base_owners", "bases", "users");
		for (String table : toDrop) {
			try {
				create.dropTable(table).execute();
			} catch(Exception e) {
				System.out.println("Could not drop table " + table);
				e.printStackTrace();
			}
		}
		
		create.execute("CREATE TABLE users (\r\n" + 
				"  username varchar(255) PRIMARY KEY,\r\n" + 
				"  password varchar(255),\r\n" + 
				"  gold float,\r\n" + 
				"  last_update BIGINT\r\n" + 
				")ENGINE=InnoDB;");
		
		create.execute("CREATE TABLE bases (\r\n" + 
				"  base_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\r\n" + 
				"  prod_rate INT NOT NULL\r\n" + 
				")ENGINE=InnoDB;");
		
		create.execute("CREATE TABLE base_owners (\r\n" + 
				"  username varchar(255) NOT NULL,\r\n" + 
				"  color_id INT NOT NULL AUTO_INCREMENT,\r\n" + 
				"  base_id INT NOT NULL,\r\n" + 
				"  world_x INT NOT NULL,\r\n" + 
				"  world_y INT NOT NULL,\r\n" + 
				"  local_x INT NOT NULL,\r\n" + 
				"  local_y INT NOT NULL,\r\n" + 
				"  num_units INT DEFAULT 0,\r\n" + 
				"  units_to_add INT DEFAULT 0,\r\n" + 
				"  last_updated BIGINT,\r\n" + 
				"  PRIMARY KEY (username, color_id),\r\n" + 
				"  FOREIGN KEY (username) REFERENCES users(username),\r\n" + 
				"  FOREIGN KEY (base_id) REFERENCES bases(base_id)\r\n" + 
				")ENGINE=MyIsam;");
		
		create.execute("CREATE TABLE portals (\r\n" + 
				"  portal_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\r\n" + 
				"  username varchar(255),\r\n" + 
				"  base_id1 INT NOT NULL,\r\n" + 
				"  base_id2 INT NOT NULL,\r\n" + 
				"  flow_rate INT NOT NULL,\r\n" + 
				"  troops_to_move INT DEFAULT 0,\r\n" + 
				"  last_move_update BIGINT,\r\n" + 
				"  FOREIGN KEY(username) REFERENCES users(username),\r\n" + 
				"  FOREIGN KEY(base_id1) REFERENCES bases(base_id),\r\n" + 
				"  FOREIGN KEY(base_id2) REFERENCES bases(base_id)\r\n" + 
				") ENGINE=InnoDB;");

		
		create.insertInto(USERS, USERS.USERNAME, USERS.PASSWORD, USERS.LAST_UPDATE).values("kevin", "kevin", System.currentTimeMillis()).execute();
		
		create.insertInto(BASES, BASES.BASE_ID, BASES.PROD_RATE).values(1, 10).execute();
		create.insertInto(BASES, BASES.BASE_ID, BASES.PROD_RATE).values(2, 11).execute();
		create.insertInto(BASES, BASES.BASE_ID, BASES.PROD_RATE).values(3, 12).execute();
		create.insertInto(BASES, BASES.BASE_ID, BASES.PROD_RATE).values(4, 13).execute();
		
		create.insertInto(BASE_OWNERS, BASE_OWNERS.USERNAME, BASE_OWNERS.BASE_ID, BASE_OWNERS.COLOR_ID, BASE_OWNERS.WORLD_X, BASE_OWNERS.WORLD_Y, BASE_OWNERS.LOCAL_X, BASE_OWNERS.LOCAL_Y, BASE_OWNERS.NUM_UNITS, BASE_OWNERS.LAST_UPDATED)
			.values("kevin", 1, 1, 0, 0, 1, 0, 100, System.currentTimeMillis()).execute();
		create.insertInto(BASE_OWNERS, BASE_OWNERS.USERNAME, BASE_OWNERS.BASE_ID, BASE_OWNERS.COLOR_ID, BASE_OWNERS.WORLD_X, BASE_OWNERS.WORLD_Y, BASE_OWNERS.LOCAL_X, BASE_OWNERS.LOCAL_Y, BASE_OWNERS.NUM_UNITS, BASE_OWNERS.LAST_UPDATED)
			.values("kevin", 2, 2, 1, 0, -1, -1, 101, System.currentTimeMillis()).execute();
		create.insertInto(BASE_OWNERS, BASE_OWNERS.USERNAME, BASE_OWNERS.BASE_ID, BASE_OWNERS.COLOR_ID, BASE_OWNERS.WORLD_X, BASE_OWNERS.WORLD_Y, BASE_OWNERS.LOCAL_X, BASE_OWNERS.LOCAL_Y, BASE_OWNERS.NUM_UNITS, BASE_OWNERS.LAST_UPDATED)
			.values("kevin", 3, 3, 1, 1, 0, -1, 102, System.currentTimeMillis()).execute();
		create.insertInto(BASE_OWNERS, BASE_OWNERS.USERNAME, BASE_OWNERS.BASE_ID, BASE_OWNERS.COLOR_ID, BASE_OWNERS.WORLD_X, BASE_OWNERS.WORLD_Y, BASE_OWNERS.LOCAL_X, BASE_OWNERS.LOCAL_Y, BASE_OWNERS.NUM_UNITS, BASE_OWNERS.LAST_UPDATED)
			.values("kevin", 4, 4, -1, 0, 0, 0, 103, System.currentTimeMillis()).execute();
		
		create.insertInto(PORTALS, PORTALS.PORTAL_ID, PORTALS.USERNAME, PORTALS.BASE_ID1, PORTALS.BASE_ID2, PORTALS.FLOW_RATE, PORTALS.LAST_MOVE_UPDATE)
			.values(1, "kevin", 3, 2, 10, System.currentTimeMillis()).execute();
		
		QueryService.create = create;
		
	}

	@Test
	public void testGetUserBases() {
		// todo fill this out
		List<BaseObj> bases = Lists.newArrayList(new BaseObj("kevin", 1, 1, new Point(0, 0), new Point(1, 0), 10, 100),
				new BaseObj("kevin", 2, 2, new Point(1, 0), new Point(-1, -1), 11, 101),
				new BaseObj("kevin", 3, 3, new Point(1, 1), new Point(0, -1), 12, 102),
				new BaseObj("kevin", 4, 4, new Point(-1, 0), new Point(0, 0), 13, 103));
		assertThat(QueryService.getUserBases("kevin"), equalTo(bases));
	}
	
	@Test
	public void testCreateBase() {
		int baseId = QueryService.createBase(10);
		assertThat(baseId, greaterThan(0));
	}
	
	@Test
	public void testCaptureBase() {
		int baseId = QueryService.createBase(14);
		QueryService.captureBase("kevin", baseId, -1, -1, 1, 1, 104, 0);
				
		List<BaseObj> bases = Lists.newArrayList(new BaseObj("kevin", 1, 1, new Point(0, 0), new Point(1, 0), 10, 100),
				new BaseObj("kevin", 2, 2, new Point(1, 0), new Point(-1, -1), 11, 101),
				new BaseObj("kevin", 3, 3, new Point(1, 1), new Point(0, -1), 12, 102),
				new BaseObj("kevin", 4, 4, new Point(-1, 0), new Point(0, 0), 13, 103),
				new BaseObj("kevin", 5, baseId, new Point(-1, -1), new Point(1, 1), 14, 104));
		
		assertThat(QueryService.getUserBases("kevin"), equalTo(bases));
	}
	
	@Test
	public void testPersistNewBase() {
		BaseObj newBase = new BaseObj("kevin", -1, -1, new Point(-1, -1), new Point(1, 1), 14, 10);
		int baseId = QueryService.persistNewBase(newBase);
		assertThat(baseId, greaterThan(0));
		assertThat(QueryService.getUserBases("kevin").get(4), equalTo(new BaseObj("kevin", 5, baseId, new Point(-1, -1), new Point(1, 1), 14, 10)));
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

