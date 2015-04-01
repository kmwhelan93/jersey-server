package sqlTableObjects;

public class AttackObj {
	public String attacker;
	public BaseObj attackerBase;
	public int attackerWormholeId;
	public String defender;
	public BaseObj defenderBase;
	public int defenderWormholeId;
	public long timeInitiated;
	public long timeAttackLands;
	public long lastUpdate;
	public int numUnits;
	
	
	public AttackObj() {
		
	}


	public AttackObj(String attacker, BaseObj attackerBase, int attackerWormholeId, String defender, BaseObj defenderBase, int defenderWormholeId, long timeInitiated, long timeAttackLands,
			long lastUpdate, int numUnits) {
		super();
		this.attacker = attacker;
		this.attackerBase = attackerBase;
		this.attackerWormholeId = attackerWormholeId;
		this.defender = defender;
		this.defenderBase = defenderBase;
		this.defenderWormholeId = defenderWormholeId;
		this.timeInitiated = timeInitiated;
		this.timeAttackLands = timeAttackLands;
		this.lastUpdate = lastUpdate;
		this.numUnits = numUnits;
	}


	@Override
	public String toString() {
		return "AttackObj [attacker=" + attacker + ", attackerBase=" + attackerBase + ", attackerWormholeId=" + attackerWormholeId + ", defender=" + defender + ", defenderBase=" + defenderBase
				+ ", defenderWormholeId=" + defenderWormholeId + ", timeInitiated=" + timeInitiated + ", timeAttackLands=" + timeAttackLands + ", lastUpdate=" + lastUpdate + ", numUnits=" + numUnits
				+ "]";
	}

	
	





	
	
	
}
