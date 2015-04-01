package code;

import java.util.ArrayList;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import sqlTableObjects.AttackObj;
import sqlTableObjects.BaseObj;
import sqlTableObjects.Portal;
import sqlTableObjects.WormHoleObj;

import com.google.common.collect.Lists;

import jooq.generated.tables.BaseOwners;
import jooq.generated.tables.Bases;
import jooq.generated.tables.records.BasesRecord;
import jsonObjects.GoldInfo;
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
				r.getValue(b.NUM_UNITS),
				r.getValue(b.UNITS_TO_ADD),
				r.getValue(b.LAST_UPDATED));
	}
	
	private static Point getBaseWorldLoc(String username, int baseId) {
		Result<Record> results = create.select()
				.from(BASE_OWNERS)
				.join(BASES)
					.on(BASES.BASE_ID.equal(BASE_OWNERS.BASE_ID))
				.where(BASE_OWNERS.USERNAME.equal(username))
				.and(BASE_OWNERS.BASE_ID.equal(baseId)).fetch();
		return new Point(results.get(0).getValue(BASE_OWNERS.WORLD_X), 
				results.get(0).getValue(BASE_OWNERS.WORLD_Y));
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
		return captureBase(b.username, b.baseId, b.world.x, b.world.y, b.local.x, b.local.y, b.units, b.lastUpdated);
	}
	
	public static boolean captureBase(
			String username,
			int baseId,
			int worldX,
			int worldY,
			int localX,
			int localY,
			int units,
			long lastUpdated) {
		disownBase(baseId);
		int result = create.insertInto(BASE_OWNERS, BASE_OWNERS.USERNAME, BASE_OWNERS.BASE_ID, BASE_OWNERS.WORLD_X, BASE_OWNERS.WORLD_Y, BASE_OWNERS.LOCAL_X, BASE_OWNERS.LOCAL_Y, BASE_OWNERS.NUM_UNITS, BASE_OWNERS.LAST_UPDATED)
			.values(username, baseId, worldX, worldY, localX, localY, units, lastUpdated)
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
	
	public static int[] getValidBaseIds(String username, int base1Id) {
		Point refBaseWorldLoc = getBaseWorldLoc(username, base1Id);
		Result<Record> records = create.select()
			.from(BASE_OWNERS)
			.join(BASES)
				.on(BASES.BASE_ID.equal(BASE_OWNERS.BASE_ID))
			.where(BASE_OWNERS.USERNAME.equal(username))
			.and(BASE_OWNERS.WORLD_X.minus(refBaseWorldLoc.x).abs().lessOrEqual(1))
			.and(BASE_OWNERS.WORLD_Y.minus(refBaseWorldLoc.y).abs().lessOrEqual(1))
			.and(BASE_OWNERS.BASE_ID.notEqual(base1Id)).fetch();
		int[] validBaseIds = new int[records.size() + 1];
		int i = 0;
		for (Record r : records) {
			validBaseIds[i] = r.getValue(BASE_OWNERS.BASE_ID);
			i++;
		}
		validBaseIds[i] = base1Id;
		return validBaseIds;
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
				r.getValue(PORTALS.TIME_FINISHED),
				r.getValue(PORTALS.FLOW_RATE),
				r.getValue(PORTALS.TROOPS_TO_MOVE),
				r.getValue(PORTALS.LAST_MOVE_UPDATE));
	}
	
	private static Portal getPortalById(String username, int portalId) {
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
			.where(PORTALS.USERNAME.equal(username))
			.and(PORTALS.PORTAL_ID.equal(portalId))
			.fetch();
		return getPortal(records.get(0), bases1, baseOwner1, bases2, baseOwner2);
	}
	
	public static Portal getAndUpdatePortal(String username, int baseId1, int baseId2, int numTroops) {
		// Get portal
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
				.where(PORTALS.USERNAME.equal(username).and(PORTALS.BASE_ID1.equal(baseId1).and(PORTALS.BASE_ID2.equal(baseId2))))
				.or(PORTALS.USERNAME.equal(username).and(PORTALS.BASE_ID1.equal(baseId2).and(PORTALS.BASE_ID2.equal(baseId1))))
				.and(PORTALS.TIME_FINISHED.lessOrEqual(System.currentTimeMillis()))
				.fetch();
		Portal p = getPortal(records.get(0), bases1, baseOwner1, bases2, baseOwner2);
		
		// Update "troops_to_move" and "last_move_update" values
		if (p.base1.baseId == baseId2) {
			numTroops *= -1;
		}
		updatePortalForMove(username, p.portalId, numTroops);
		return p;
	}
	
	public static void updatePortalForMove(String username, int portalId, int numTroops) {
		create.update(PORTALS)
				.set(PORTALS.TROOPS_TO_MOVE, numTroops)
				.set(PORTALS.LAST_MOVE_UPDATE, System.currentTimeMillis())
				.where(PORTALS.USERNAME.equal(username).and(PORTALS.PORTAL_ID.equal(portalId)))
				.execute();
	}
	
	public static Portal[] getMovePortals(String username) {
		// Get portals
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
			.where(PORTALS.USERNAME.equal(username))
			.and(PORTALS.TROOPS_TO_MOVE.notEqual(0))
			.fetch();
		Portal[] p = new Portal[records.size()];
		for (int i = 0; i < records.size(); i++) {
			p[i] = getPortal(records.get(i), bases1, baseOwner1, bases2, baseOwner2);
		}
		return p;
	}
	
	public static boolean createPortal(String username, int baseId1, int baseId2, long timeFinished) {
		try {
			int result = create.insertInto(PORTALS, PORTALS.USERNAME, PORTALS.BASE_ID1, PORTALS.BASE_ID2, PORTALS.TIME_FINISHED, PORTALS.FLOW_RATE, PORTALS.LAST_MOVE_UPDATE)
					.values(username, baseId1, baseId2, timeFinished, 10, System.currentTimeMillis())
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
			.and(PORTALS.TIME_FINISHED.lessOrEqual(System.currentTimeMillis()))
			.fetch();
			
		if (results.isNotEmpty()) {
			return true;
		}
		return false;
	}
	
	///////////////////////////
	///////// WORMHOLES////////
	///////////////////////////
	
	public static List<WormHoleObj> getWormholes(String username) {
		Result<Record> results = create.select()
			.from(WORMHOLES)
			.join(BASES)
						.on(BASES.BASE_ID.equal(WORMHOLES.BASE_ID))
					.join(BASE_OWNERS)
						.on(BASE_OWNERS.BASE_ID.equal(WORMHOLES.BASE_ID))
			.where(BASE_OWNERS.USERNAME.equal(username)).fetch();
		List<WormHoleObj> wormholes = Lists.newArrayList();
		for (Record r : results) {
			wormholes.add(getWormHole(r));
		}
		return wormholes;
	}
	
	public static WormHoleObj getWormHole(Record r) {
		return new WormHoleObj(
				r.getValue(WORMHOLES.WORMHOLE_ID),
				getBase(r, BASES, BASE_OWNERS),
				new Point(r.getValue(WORMHOLES.RELATIVE_COORD_X), r.getValue(WORMHOLES.RELATIVE_COORD_Y)),
				r.getValue(WORMHOLES.CONNECTED_WORMHOLE_ID));
	}
	
	public static int persistNewWormHole(WormHoleObj wormhole) {
		int wormholeId = create.insertInto(WORMHOLES, WORMHOLES.BASE_ID, WORMHOLES.RELATIVE_COORD_X, WORMHOLES.RELATIVE_COORD_Y, WORMHOLES.CONNECTED_WORMHOLE_ID)
			.values(wormhole.b.baseId, wormhole.relativeCoords.x, wormhole.relativeCoords.y, wormhole.connectedWormholeId)
			.returning(WORMHOLES.WORMHOLE_ID)
			.fetchOne().getWormholeId();
		return wormholeId;
	}
	
	// TROOPS
	public static void startAddTroops(String username, int baseId, int numTroops) {
		create.update(BASE_OWNERS)
			.set(BASE_OWNERS.UNITS_TO_ADD, numTroops)
			.set(BASE_OWNERS.LAST_UPDATED, System.currentTimeMillis())
			.where(BASE_OWNERS.BASE_ID.equal(baseId))
			.and(BASE_OWNERS.USERNAME.equal(username))
			.execute();
	}
	
	public static List<BaseObj> getAddTroopsBases(String username) {
		Result<Record> results = create.select()
				.from(BASE_OWNERS)
				.join(BASES)
					.on(BASES.BASE_ID.equal(BASE_OWNERS.BASE_ID))
				.where(BASE_OWNERS.USERNAME.equal(username))
				.and(BASE_OWNERS.UNITS_TO_ADD.notEqual(0)).fetch();
		List<BaseObj> baseLocations = Lists.newArrayList();
		for (Record r : results) {
			baseLocations.add(getBase(r, BASES, BASE_OWNERS));
		}
		return baseLocations;
	}
	
	public static void finishAddTroops(String username, int baseId) {
		int numTroops = getNumTroopsToAdd(username, baseId);
		create.update(BASE_OWNERS)
			.set(BASE_OWNERS.NUM_UNITS, BASE_OWNERS.NUM_UNITS.plus(numTroops))
			.set(BASE_OWNERS.UNITS_TO_ADD, 0)
			.set(BASE_OWNERS.LAST_UPDATED, System.currentTimeMillis())
			.where(BASE_OWNERS.BASE_ID.equal(baseId))
			.and(BASE_OWNERS.USERNAME.equal(username))
			.execute();
	}
	
	public static int getNumTroops(String username, int baseId) {
		Result<Record> results = create.select()
				.from(BASE_OWNERS)
				.join(BASES)
					.on(BASES.BASE_ID.equal(BASE_OWNERS.BASE_ID))
				.where(BASE_OWNERS.USERNAME.equal(username))
				.and(BASES.BASE_ID.equal(baseId)).fetch();
		if (results.size() == 1) {
			return results.get(0).getValue(BASE_OWNERS.NUM_UNITS);
		}
		return -1;
	}
	
	public static int getNumTroopsToAdd(String username, int baseId) {
		Result<Record> results = create.select()
				.from(BASE_OWNERS)
				.join(BASES)
					.on(BASES.BASE_ID.equal(BASE_OWNERS.BASE_ID))
				.where(BASE_OWNERS.USERNAME.equal(username))
				.and(BASES.BASE_ID.equal(baseId)).fetch();
		if (results.size() == 1) {
			return results.get(0).getValue(BASE_OWNERS.UNITS_TO_ADD);
		}
		return -1;
	}
	
	public static void finishMoveTroops(String username, int portalId) {
		// Get portal
		Portal p = getPortalById(username, portalId);
		moveTroops(username, p.base1.baseId, p.base2.baseId, p.troopsToMove);
		updatePortalForMove(username, portalId, 0);
	}
	
	public static void moveTroops(String username, int baseId1, int baseId2, int numTroops) {
		create.update(BASE_OWNERS)
			.set(BASE_OWNERS.NUM_UNITS, BASE_OWNERS.NUM_UNITS.minus(numTroops))
			.where(BASE_OWNERS.BASE_ID.equal(baseId1))
			.and(BASE_OWNERS.USERNAME.equal(username))
			.execute();
		create.update(BASE_OWNERS)
			.set(BASE_OWNERS.NUM_UNITS, BASE_OWNERS.NUM_UNITS.plus(numTroops))
			.where(BASE_OWNERS.BASE_ID.equal(baseId2))
			.and(BASE_OWNERS.USERNAME.equal(username))
			.execute();
	}
	
	// USER
	public static void decrementGold(String username, double amount) {
		// No need to update USERS.LAST_UPDATE (just used for accumulation of gold over time, hasn't changed)
		create.update(USERS)
			.set(USERS.GOLD, USERS.GOLD.minus(amount))
			.where(USERS.USERNAME.equal(username))
			.execute();
	}
	
	public static GoldInfo getGold(String username) {
		Record r = create.select()
			.from(USERS)
			.where(USERS.USERNAME.equal(username)).fetchOne();
		return new GoldInfo(r.getValue(USERS.GOLD), r.getValue(USERS.LAST_UPDATE));
	}
	
	public static void syncGold(String username, double gold, long lastUpdate) {
		create.update(USERS)
			.set(USERS.GOLD, gold)
			.set(USERS.LAST_UPDATE, lastUpdate)
			.where(USERS.USERNAME.equal(username))
			.execute();
	}
	
	
	///////////////////////////////////////
	/////////////// ATTACKS ///////////////
	///////////////////////////////////////
	public static List<AttackObj> getAttacks(String username) {
		List<AttackObj> attacks = Lists.newArrayList();
		Bases attackerBase = BASES.as("attackerBase");
		BaseOwners attackerBaseOwner = BASE_OWNERS.as("attackerBaseOwner");
		Bases defenderBase = BASES.as("defenderBase");
		BaseOwners defenderBaseOwner = BASE_OWNERS.as("defenderBaseOwner");
		Result<Record> records = create.select()
			.from(ATTACKS
					.join(attackerBaseOwner)
						.on(attackerBaseOwner.BASE_ID.equal(ATTACKS.ATTACKER_BASE_ID))
					.join(attackerBase)
						.on(attackerBase.BASE_ID.equal(ATTACKS.ATTACKER_BASE_ID))
					.join(defenderBase)
						.on(defenderBase.BASE_ID.equal(ATTACKS.DEFENDER_BASE_ID))
					.join(defenderBaseOwner)
						.on(defenderBaseOwner.BASE_ID.equal(ATTACKS.DEFENDER_BASE_ID))
					)
			.where(ATTACKS.ATTACKER.equal(username))
			.fetch();
		for (Record r : records) {
			attacks.add(QueryService.getAttackObj(r, attackerBase, attackerBaseOwner, defenderBase, defenderBaseOwner));
		}
		return attacks;
	}
	
	public static List<AttackObj> getAttacksDefending(String username) {
		List<AttackObj> attacks = Lists.newArrayList();
		Bases attackerBase = BASES.as("attackerBase");
		BaseOwners attackerBaseOwner = BASE_OWNERS.as("attackerBaseOwner");
		Bases defenderBase = BASES.as("defenderBase");
		BaseOwners defenderBaseOwner = BASE_OWNERS.as("defenderBaseOwner");
		Result<Record> records = create.select()
			.from(ATTACKS
					.join(attackerBaseOwner)
						.on(attackerBaseOwner.BASE_ID.equal(ATTACKS.ATTACKER_BASE_ID))
					.join(attackerBase)
						.on(attackerBase.BASE_ID.equal(ATTACKS.ATTACKER_BASE_ID))
					.join(defenderBase)
						.on(defenderBase.BASE_ID.equal(ATTACKS.DEFENDER_BASE_ID))
					.join(defenderBaseOwner)
						.on(defenderBaseOwner.BASE_ID.equal(ATTACKS.DEFENDER_BASE_ID))
					)
			.where(ATTACKS.DEFENDER.equal(username))
			.fetch();
		for (Record r : records) {
			attacks.add(QueryService.getAttackObj(r, attackerBase, attackerBaseOwner, defenderBase, defenderBaseOwner));
		}
		return attacks;
	}
	
	public static AttackObj getAttackObj(Record r, Bases attackerBase, BaseOwners attackerBaseOwner, Bases defenderBase, BaseOwners defenderBaseOwner) {
		return new AttackObj(
				r.getValue(ATTACKS.ATTACKER),
				getBase(r, attackerBase, attackerBaseOwner),
				r.getValue(ATTACKS.ATTACKER_WORMHOLE_ID),
				r.getValue(ATTACKS.DEFENDER),
				getBase(r, defenderBase, defenderBaseOwner),
				r.getValue(ATTACKS.DEFENDER_WORMHOLE_ID),
				r.getValue(ATTACKS.TIME_INIATED),
				r.getValue(ATTACKS.TIME_ATTACK_LANDS),
				r.getValue(ATTACKS.LAST_UPDATE),
				r.getValue(ATTACKS.NUM_UNITS));
	}
	
	
	
	
	public static void main (String[] args) {
		System.out.println(getAttacks("kmw8sf"));
	}
}
