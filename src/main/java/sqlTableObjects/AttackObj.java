package sqlTableObjects;

public class AttackObj {
	public int attackId;
	public String attacker;
	public int attackerBaseId;
	public int attackerWormholeId;
	public String defender;
	public int defenderBaseId;
	public int defenderWormholeId;
	public long timeInitiated;
	public long timeAttackLands;
	public long lastUpdate;
	public int numUnits;
	
	
	public AttackObj() {
		
	}


	public AttackObj(int attackId, String attacker, int attackerBaseId, int attackerWormholeId, String defender, int defenderBaseId, int defenderWormholeId, long timeInitiated, long timeAttackLands,
			long lastUpdate, int numUnits) {
		super();
		this.attackId = attackId;
		this.attacker = attacker;
		this.attackerBaseId = attackerBaseId;
		this.attackerWormholeId = attackerWormholeId;
		this.defender = defender;
		this.defenderBaseId = defenderBaseId;
		this.defenderWormholeId = defenderWormholeId;
		this.timeInitiated = timeInitiated;
		this.timeAttackLands = timeAttackLands;
		this.lastUpdate = lastUpdate;
		this.numUnits = numUnits;
	}


	@Override
	public String toString() {
		return "AttackObj [attackId=" + attackId + ", attacker=" + attacker + ", attackerBase=" + attackerBaseId + 
				", attackerWormholeId=" + attackerWormholeId + ", defender=" + defender + ", defenderBase=" + defenderBaseId + 
				", defenderWormholeId=" + defenderWormholeId + ", timeInitiated=" + timeInitiated + ", timeAttackLands=" + 
				timeAttackLands + ", lastUpdate=" + lastUpdate + ", numUnits=" + numUnits + "]";
	}

	
	





	
	
	
}
