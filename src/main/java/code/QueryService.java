package code;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.Table;
import org.jooq.impl.DSL;

import sqlTableObjects.AttackObj;
import sqlTableObjects.AttackResultObj;
import sqlTableObjects.BaseObj;
import sqlTableObjects.Portal;
import sqlTableObjects.WormHoleObj;

import com.google.common.collect.Lists;

import com.example.WorldResources;
import jooq.generated.tables.BaseOwners;
import jooq.generated.tables.Bases;
import jooq.generated.tables.Wormholes;
import jooq.generated.tables.records.BasesRecord;
import jooq.generated.tables.records.PortalsRecord;
import jsonObjects.AddTroopsCommand;
import jsonObjects.GoldInfo;
import jsonObjects.NewBase;
import jsonObjects.Point;
import static jooq.generated.Tables.*;

public class QueryService {
	public static DSLContext create = DSL.using(DBConn.getInstance().getConnection(), SQLDialect.MYSQL);
	private static Random random = new Random();
	private final static Object mutex = new Object();
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
	
	public static NewBase createBase(BaseObj referenceBase) {
		String username = referenceBase.username;
		List<BaseObj> bases = QueryService.getUserBases(username);
		int magnitude = 1;
		BaseObj toAdd = null;
		outer: while (true) {
			int initialDirection = random.nextInt(4);
			for (int i = 0; i < 4; i++) {
				int direction = (i + initialDirection) % 4;
				Point p = Point.getPoint(direction).scale(magnitude).add(referenceBase.world);
				BaseObj newBase = new BaseObj(username, p, Point.getRandomDirection());
				newBase.prodRate = random.nextInt(200) + 10;
				if (!newBase.isSpaceOccupied(bases)) {
					toAdd = newBase;
					break outer;
				}
				
			}
			magnitude++;
		}
		toAdd.lastUpdated = System.currentTimeMillis();
		int baseColorId = QueryService.persistNewBase(toAdd);
		toAdd.colorId = baseColorId;
		Portal p = QueryService.createPortal(referenceBase.username, referenceBase.baseId, toAdd.baseId, System.currentTimeMillis());
		
		List<WormHoleObj> newWormholes = GameLogicService.createNewWormholes(toAdd);
		System.out.println(newWormholes);
		for (WormHoleObj wormhole : newWormholes) {
			QueryService.persistNewWormHole(wormhole);
		}
		NewBase newBase = new NewBase(toAdd, p);
		return newBase;
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
			return getBaseColor(b.username, b.baseId);
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	private static int getBaseColor(String username, int baseId) {
		Record1<Integer> r = create.select(BASE_OWNERS.COLOR_ID)
				.from(BASE_OWNERS)
				.join(BASES)
					.on(BASES.BASE_ID.equal(BASE_OWNERS.BASE_ID))
				.where(BASE_OWNERS.USERNAME.equal(username))
				.and(BASE_OWNERS.BASE_ID.equal(baseId)).fetchOne();
		return r.value1();
	}
	
	private static int getBaseNumUnits(String username, int baseId) {
		Record1<Integer> r = create.select(BASE_OWNERS.NUM_UNITS)
				.from(BASE_OWNERS)
				.join(BASES)
					.on(BASES.BASE_ID.equal(BASE_OWNERS.BASE_ID))
				.where(BASE_OWNERS.USERNAME.equal(username))
				.and(BASE_OWNERS.BASE_ID.equal(baseId)).fetchOne();
		return r.value1();
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
	
	private static void removeUnits(String username, int baseId, int numUnits) {
		create.update(BASE_OWNERS)
				.set(BASE_OWNERS.NUM_UNITS, BASE_OWNERS.NUM_UNITS.minus(numUnits))
				.where(BASE_OWNERS.USERNAME.equal(username))
				.and(BASE_OWNERS.BASE_ID.equal(baseId))
				.execute();
	}
	
	private static BaseObj getBaseById(int baseId) {
		Record r = create.select()
			.from(BASE_OWNERS)
			.join(BASES)
				.on(BASES.BASE_ID.equal(BASE_OWNERS.BASE_ID))
			.where(BASE_OWNERS.BASE_ID.equal(baseId))
			.fetchOne();
		return getBase(r, BASES, BASE_OWNERS);
	}
	
	//////////// PORTALS ///////////////
	
	// Get all portals for the user
	public static List<Portal> getPortals(String username) {
		ArrayList<Portal> retVal = Lists.newArrayList();
		Result<Record> records = create.select()
			.from(PORTALS)
			.where(PORTALS.USERNAME.equal(username))
			.fetch();
		for (Record r : records) {
			retVal.add(getPortal(r));
		}
		return retVal;
	}
	
	// Get portal object from a Record
	private static Portal getPortal(Record r) {
		return new Portal(r.getValue(PORTALS.PORTAL_ID), 
				r.getValue(PORTALS.USERNAME),
				r.getValue(PORTALS.BASE_ID1),
				r.getValue(PORTALS.BASE_ID2),
				r.getValue(PORTALS.TIME_FINISHED),
				r.getValue(PORTALS.FLOW_RATE),
				r.getValue(PORTALS.TROOPS_TO_MOVE),
				r.getValue(PORTALS.LAST_MOVE_UPDATE));
	}
	
	// Get user's portal with a certain id
	private static Portal getPortalById(String username, int portalId) {
		Result<Record> records = create.select()
			.from(PORTALS)
			.where(PORTALS.USERNAME.equal(username))
			.and(PORTALS.PORTAL_ID.equal(portalId))
			.fetch();
		if (records.isEmpty()) {
			return null;
		}
		return getPortal(records.get(0));
	}
	
	// For starting a troops move: return Portal that troops are moving along, update portal's
	// move troops attributes in database
	public static Portal getAndUpdatePortal(String username, int baseId1, int baseId2, int numTroops) {
		// Get portal from baseIds
		Result<Record> records = create.select()
			.from(PORTALS)
				.where(PORTALS.USERNAME.equal(username).and(PORTALS.BASE_ID1.equal(baseId1).and(PORTALS.BASE_ID2.equal(baseId2))))
				.or(PORTALS.USERNAME.equal(username).and(PORTALS.BASE_ID1.equal(baseId2).and(PORTALS.BASE_ID2.equal(baseId1))))
				.and(PORTALS.TIME_FINISHED.lessOrEqual(System.currentTimeMillis()))
				.fetch();
		Portal p = getPortal(records.get(0));
		
		// Update "troops_to_move" and "last_move_update" values
		if (p.base1Id == baseId2) {
			// + if troops are moving from Portal's base1 to base2, - otherwise
			numTroops *= -1;
		}
		updatePortalForMove(username, p.portalId, numTroops);
		return p;
	}
	
	// Update "troops_to_move" and "last_move_update" values
	public static void updatePortalForMove(String username, int portalId, int numTroops) {
		create.update(PORTALS)
				.set(PORTALS.TROOPS_TO_MOVE, numTroops)
				.set(PORTALS.LAST_MOVE_UPDATE, System.currentTimeMillis())
				.where(PORTALS.USERNAME.equal(username).and(PORTALS.PORTAL_ID.equal(portalId)))
				.execute();
	}
	
	// Get all portals for the user that have move troops actions in progress
	public static Portal[] getMovePortals(String username) {
		// Get portals
		Result<Record> records = create.select()
			.from(PORTALS)
			.where(PORTALS.USERNAME.equal(username))
			.and(PORTALS.TROOPS_TO_MOVE.notEqual(0))
			.fetch();
		Portal[] p = new Portal[records.size()];
		for (int i = 0; i < records.size(); i++) {
			p[i] = getPortal(records.get(i));
		}
		return p;
	}
	
	public static Portal createPortal(String username, int baseId1, int baseId2, long timeFinished) {
		try {
			PortalsRecord pr = create.insertInto(PORTALS, PORTALS.USERNAME, PORTALS.BASE_ID1, PORTALS.BASE_ID2, PORTALS.TIME_FINISHED, PORTALS.FLOW_RATE, PORTALS.LAST_MOVE_UPDATE)
					.values(username, baseId1, baseId2, timeFinished, 10, System.currentTimeMillis())
					.returning(PORTALS.PORTAL_ID, PORTALS.USERNAME, PORTALS.BASE_ID1, PORTALS.BASE_ID2, PORTALS.TIME_FINISHED, PORTALS.FLOW_RATE, PORTALS.LAST_MOVE_UPDATE, PORTALS.TROOPS_TO_MOVE)
					.fetchOne();
			return new Portal(pr.getPortalId(), 
					pr.getUsername(), 
					pr.getBaseId1(),
					pr.getBaseId2(),
					pr.getTimeFinished(),
					pr.getFlowRate(),
					pr.getTroopsToMove(),
					pr.getLastMoveUpdate());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Portal();
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
	
	public static Portal getPortalFromBaseId(String username, int baseId) {
		Record r = create.select()
				.from(PORTALS)
				.where(PORTALS.USERNAME.equal(username).and(PORTALS.BASE_ID1.equal(baseId)))
				.or(PORTALS.USERNAME.equal(username).and(PORTALS.BASE_ID2.equal(baseId)))
				.fetchOne();
		return getPortal(r);
	}
	
	public static List<Integer> getLostPortalIds(int baseId) {
		List<Integer> portalIds = new ArrayList<Integer>();
		Result<Record> results = create.select()
				.from(PORTALS)
				.where((PORTALS.BASE_ID1.equal(baseId)).or(PORTALS.BASE_ID2.equal(baseId)))
				.fetch();
		for (Record r : results) {
			Portal p = getPortal(r);
			portalIds.add(p.portalId);
		}
		return portalIds;
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
				r.getValue(WORMHOLES.BASE_ID),
				new Point(r.getValue(WORMHOLES.RELATIVE_COORD_X), r.getValue(WORMHOLES.RELATIVE_COORD_Y)),
				r.getValue(WORMHOLES.CONNECTED_WORMHOLE_ID));
	}
	
	public static int persistNewWormHole(WormHoleObj wormhole) {
		int wormholeId = create.insertInto(WORMHOLES, WORMHOLES.BASE_ID, WORMHOLES.RELATIVE_COORD_X, WORMHOLES.RELATIVE_COORD_Y, WORMHOLES.CONNECTED_WORMHOLE_ID)
			.values(wormhole.baseId, wormhole.relativeCoords.x, wormhole.relativeCoords.y, wormhole.connectedWormholeId)
			.returning(WORMHOLES.WORMHOLE_ID)
			.fetchOne().getWormholeId();
		return wormholeId;
	}
	
	private static WormHoleObj getWormHoleObj(int wormholeId) {
		Record r = create.select()
				.from(WORMHOLES)
				.where(WORMHOLES.WORMHOLE_ID.equal(wormholeId))
				.fetchOne();
		return getWormHole(r);
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
	
	public static List<AddTroopsCommand> getAddTroopsBases(String username) {
		Result<Record> results = create.select()
				.from(BASE_OWNERS)
				.join(BASES)
					.on(BASES.BASE_ID.equal(BASE_OWNERS.BASE_ID))
				.where(BASE_OWNERS.USERNAME.equal(username))
				.and(BASE_OWNERS.UNITS_TO_ADD.notEqual(0)).fetch();
		List<AddTroopsCommand> atcs = Lists.newArrayList();
		for (Record r : results) {
			atcs.add(new AddTroopsCommand(r.getValue(BASES.BASE_ID), r.getValue(BASE_OWNERS.UNITS_TO_ADD)));
		}
		return atcs;
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
		moveTroops(username, p.base1Id, p.base2Id, p.troopsToMove);
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
		// Get all attacks whose results haven't been generated, or whose results haven't been
		// viewed by attacker yet
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
					.leftOuterJoin(ATTACK_RESULTS)
						.on(ATTACKS.ATTACKID.equal(ATTACK_RESULTS.ATTACK_ID))
			.where(ATTACKS.ATTACKER.equal(username))
			.and((ATTACK_RESULTS.ATTACKER_HAS_VIEWED.notEqual((byte)1)).or(ATTACK_RESULTS.ATTACKER_HAS_VIEWED.isNull()))
			.fetch();
		for (Record r : records) {
			attacks.add(QueryService.getAttackObj(r, attackerBase, attackerBaseOwner, defenderBase, defenderBaseOwner));
		}
		return attacks;
	}
	
	public static List<AttackObj> getAttacksDefending(String username) {
		// Get all attacks defending whose results haven't been generated, or whose results haven't 
		// been viewed by defender yet
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
					.leftOuterJoin(ATTACK_RESULTS)
						.on(ATTACKS.ATTACKID.equal(ATTACK_RESULTS.ATTACK_ID))
			.where(ATTACKS.DEFENDER.equal(username))
			.and((ATTACK_RESULTS.DEFENDER_HAS_VIEWED.notEqual((byte)1)).or(ATTACK_RESULTS.DEFENDER_HAS_VIEWED.isNull()))
			.fetch();
		for (Record r : records) {
			attacks.add(QueryService.getAttackObj(r, attackerBase, attackerBaseOwner, defenderBase, defenderBaseOwner));
		}
		return attacks;
	}
	
	public static AttackObj getAttackObj(Record r, Bases attackerBase, BaseOwners attackerBaseOwner, Bases defenderBase, BaseOwners defenderBaseOwner) {
		return new AttackObj(
				r.getValue(ATTACKS.ATTACKID),
				r.getValue(ATTACKS.ATTACKER),
				r.getValue(ATTACKS.ATTACKER_BASE_ID),
				r.getValue(ATTACKS.ATTACKER_WORMHOLE_ID),
				r.getValue(ATTACKS.DEFENDER),
				r.getValue(ATTACKS.DEFENDER_BASE_ID),
				r.getValue(ATTACKS.DEFENDER_WORMHOLE_ID),
				r.getValue(ATTACKS.TIME_INIATED),
				r.getValue(ATTACKS.TIME_ATTACK_LANDS),
				r.getValue(ATTACKS.LAST_UPDATE),
				r.getValue(ATTACKS.NUM_UNITS));
	}
	
	public static AttackObj initiateAttack(String username, int baseId, int wormholeId, int numUnits) {
		int baseUnits = getBaseNumUnits(username, baseId);
		if (numUnits <= baseUnits) {
			// Valid
			removeUnits(username, baseId, numUnits);
			AttackObj attackObj = createAttack(username, baseId, wormholeId, numUnits);
			return attackObj;
		}
		else {
			// Return empty attackObj
			return new AttackObj();
		}
	}
	
	public static AttackObj createAttack(String username, int baseId, int wormholeId, int numUnits) {
		BaseOwners base1 = BASE_OWNERS.as("base1");
		Wormholes wormholes2 = WORMHOLES.as("wormholes2");
		
		Record r = create.select()
			.from(WORMHOLES)
				.join(base1)
					.on(WORMHOLES.BASE_ID.equal(base1.BASE_ID))
				.join(wormholes2)
					.on(WORMHOLES.CONNECTED_WORMHOLE_ID.equal(wormholes2.WORMHOLE_ID))
				.join(BASE_OWNERS)
					.on(wormholes2.BASE_ID.equal(BASE_OWNERS.BASE_ID))
			.where(WORMHOLES.WORMHOLE_ID.equal(wormholeId))
			.and(base1.USERNAME.equal(username))
			.fetchOne();
		
		long curTimeMillis = System.currentTimeMillis();
		Record record = create.insertInto(ATTACKS, ATTACKS.ATTACKER, ATTACKS.ATTACKER_BASE_ID, ATTACKS.ATTACKER_WORMHOLE_ID, ATTACKS.DEFENDER, ATTACKS.DEFENDER_BASE_ID, ATTACKS.DEFENDER_WORMHOLE_ID, ATTACKS.TIME_INIATED, ATTACKS.TIME_ATTACK_LANDS, ATTACKS.LAST_UPDATE, ATTACKS.NUM_UNITS)
			.values(username, baseId, wormholeId, r.getValue(BASE_OWNERS.USERNAME), r.getValue(BASE_OWNERS.BASE_ID), 
					r.getValue(wormholes2.WORMHOLE_ID), curTimeMillis, curTimeMillis + GameSettings.attackTimeInMillis, 
					curTimeMillis, numUnits)
			.returning(ATTACKS.ATTACKID)
			.fetchOne();
		int attackId = record.getValue(ATTACKS.ATTACKID);
		return new AttackObj(attackId, username, baseId, wormholeId, r.getValue(BASE_OWNERS.USERNAME), r.getValue(BASE_OWNERS.BASE_ID), r.getValue(wormholes2.WORMHOLE_ID), curTimeMillis, curTimeMillis + GameSettings.attackTimeInMillis, curTimeMillis, numUnits);
	}
	
	// TODO: clean this up
	public static AttackResultObj attackLanded(String username, int attackId) {
		// Get attack info
		Record r = create.select()
				.from(ATTACKS)
				.where(ATTACKS.ATTACKID.equal(attackId))
				.fetchOne();
		
		// Check if attack has really landed
		if (r.getValue(ATTACKS.TIME_ATTACK_LANDS) <= System.currentTimeMillis()) {
			// Use mutex for modifying AttackResults table
			synchronized(mutex) {
				// Check to see if attack results have already been determined and stored in AttackResults
				Record attackRecord = create.select()
						.from(ATTACK_RESULTS)
						.where(ATTACK_RESULTS.ATTACK_ID.equal(attackId))
						.fetchOne();
				
				// If results not determined yet:
				if (attackRecord == null) {
					// Determine who wins, number of troops left
					Record rec = create.select(BASE_OWNERS.NUM_UNITS)
							.from(BASE_OWNERS)
							.join(ATTACKS)
								.on(ATTACKS.DEFENDER_BASE_ID.equal(BASE_OWNERS.BASE_ID))
							.where(ATTACKS.ATTACKID.equal(attackId))
							.fetchOne();
					int numUnitsDefender = rec.getValue(BASE_OWNERS.NUM_UNITS);
					// TODO: improve how winner is determined
					String winnerUsername = numUnitsDefender >= r.getValue(ATTACKS.NUM_UNITS) ? r.getValue(ATTACKS.DEFENDER) : r.getValue(ATTACKS.ATTACKER);
					boolean isWinner = winnerUsername.equals(username);
					boolean isAttacker = r.getValue(ATTACKS.ATTACKER).equals(username);
					boolean attackerWon = (isWinner && isAttacker) || (!isWinner && !isAttacker);
					int numTroopsLeft = Math.abs(r.getValue(ATTACKS.NUM_UNITS) - numUnitsDefender);
					
					// If attacker wins attack - base ownership changes
					NewBase newBase = null;
					List<Integer> lostPortalIds = null;
					if (attackerWon) {
						// Base ownership changes
						String newUsername = r.getValue(ATTACKS.ATTACKER);
						int baseIdAttacker = r.getValue(ATTACKS.ATTACKER_BASE_ID);
						int baseIdDefender = r.getValue(ATTACKS.DEFENDER_BASE_ID);
						lostPortalIds = getLostPortalIds(baseIdDefender);
						newBase = changeBaseOwnership(newUsername, baseIdAttacker, baseIdDefender, numTroopsLeft);
					} else {
						// Base ownership stays as it was, update number of units on base
						create.update(BASE_OWNERS)
							.set(BASE_OWNERS.NUM_UNITS, numTroopsLeft)
							.where(BASE_OWNERS.BASE_ID.equal(r.getValue(ATTACKS.DEFENDER_BASE_ID)))
							.and(BASE_OWNERS.USERNAME.equal(r.getValue(ATTACKS.DEFENDER)))
							.execute();
					}
					
					int[] idArray = null;
					if (lostPortalIds != null) {
						idArray = new int[lostPortalIds.size()];
						for (int i = 0; i < lostPortalIds.size(); i++) {
							idArray[i] = lostPortalIds.get(i);
						}
					}
					
					// Add results to AttackResults table, with usernameViewed = true
					create.insertInto(ATTACK_RESULTS, ATTACK_RESULTS.ATTACK_ID, ATTACK_RESULTS.WINNER_USERNAME, ATTACK_RESULTS.NUM_UNITS_LEFT, ATTACK_RESULTS.NEW_BASE_ID, ATTACK_RESULTS.ATTACKER_HAS_VIEWED, ATTACK_RESULTS.DEFENDER_HAS_VIEWED)
						.values(attackId, winnerUsername, numTroopsLeft, attackerWon ? newBase.b.baseId : -1, (byte)(isAttacker ? 1 : 0), (byte)(isAttacker ? 0 : 1))
						.execute();
					
					// Return results in AttackResultObj
					BaseObj b = newBase == null ? null : newBase.b;
					Portal p = newBase == null ? null : newBase.p;
					return new AttackResultObj(attackId, winnerUsername, numTroopsLeft, b, p, idArray);
				} else {
					System.out.println("attackId: " + r.getValue(ATTACKS.ATTACKID) + " results already there");
					// If results have already been determined
					updateAttackRecordViewing(attackRecord, username, r.getValue(ATTACKS.ATTACKER).equals(username));
					// If attacker won and username is the attacker
					NewBase newBase = null;
					List<Integer> lostPortalIds = null;
					if (attackRecord.getValue(ATTACK_RESULTS.NEW_BASE_ID) != -1) {
						BaseObj b = getBaseById(attackRecord.getValue(ATTACK_RESULTS.NEW_BASE_ID));
						Portal p = getPortalFromBaseId(username, b.baseId);
						newBase = new NewBase(b, p);
						lostPortalIds = getLostPortalIds(r.getValue(ATTACKS.DEFENDER_BASE_ID));
					}
					
					int[] idArray = null;
					if (lostPortalIds != null) {
						idArray = new int[lostPortalIds.size()];
						for (int i = 0; i < lostPortalIds.size(); i++) {
							idArray[i] = lostPortalIds.get(i);
						}
					}
					
					return new AttackResultObj(attackId, attackRecord.getValue(ATTACK_RESULTS.WINNER_USERNAME), attackRecord.getValue(ATTACK_RESULTS.NUM_UNITS_LEFT), newBase.b, newBase.p, idArray);
				}
			// End mutex
			}
		}
		return new AttackResultObj();
	}
	
	private static void updateAttackRecordViewing(Record attackRecord, String username, boolean isAttacker) {
		if (isAttacker) {
			if (attackRecord.getValue(ATTACK_RESULTS.ATTACKER_HAS_VIEWED) == (byte)0) {
				create.update(ATTACK_RESULTS)
					.set(ATTACK_RESULTS.ATTACKER_HAS_VIEWED, (byte)1)
					.execute();
			}
		}
		else {
			if (attackRecord.getValue(ATTACK_RESULTS.DEFENDER_HAS_VIEWED) == (byte)0) {
				create.update(ATTACK_RESULTS)
					.set(ATTACK_RESULTS.DEFENDER_HAS_VIEWED, (byte)1)
					.execute();
			}
		}
	}
	
	private static NewBase changeBaseOwnership(String newUsername, int baseIdAttacker, int baseIdDefender, int numUnitsLeft) {
		// Add new base for winner
		BaseObj refBase = getBaseById(baseIdAttacker);
		BaseObj defeatedBase = getBaseById(baseIdDefender);
		NewBase newBase = createBase(refBase);
		create.update(BASE_OWNERS.join(BASES).on(BASE_OWNERS.BASE_ID.equal(BASES.BASE_ID)))
			.set(BASE_OWNERS.USERNAME, newUsername)
			.set(BASE_OWNERS.NUM_UNITS, numUnitsLeft)
			.set(BASES.PROD_RATE, defeatedBase.prodRate)
			.where(BASE_OWNERS.BASE_ID.equal(newBase.b.baseId))
			.execute();
		newBase.b.username = newUsername;
		newBase.b.units = numUnitsLeft;
		newBase.b.prodRate = defeatedBase.prodRate;
		
		// Delete all portals connected to old base
		create.delete(PORTALS)
			.where(PORTALS.BASE_ID1.equal(baseIdDefender))
			.or(PORTALS.BASE_ID2.equal(baseIdDefender))
			.execute();
		
		// Delete defender's old base
		create.delete(BASE_OWNERS)
			.where(BASE_OWNERS.BASE_ID.equal(baseIdDefender))
			.execute();
		
		return newBase;
	}
	
	public static void main (String[] args) {
		System.out.println(getAttacks("kmw8sf"));
	}
}
