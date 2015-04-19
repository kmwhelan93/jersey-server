package sqlTableObjects;

public class AttackResultObj {
	int attackId;
	String winnerUsername;
	int numUnitsLeft;
	boolean winnerHasViewed;
	boolean loserHasViewed;
	
	public AttackResultObj() {
		
	}

	public AttackResultObj(int attackId, String winnerUsername,
			int numUnitsLeft, boolean winnerHasViewed, boolean loserHasViewed) {
		super();
		this.attackId = attackId;
		this.winnerUsername = winnerUsername;
		this.numUnitsLeft = numUnitsLeft;
		this.winnerHasViewed = winnerHasViewed;
		this.loserHasViewed = loserHasViewed;
	}

	@Override
	public String toString() {
		return "AttackResultObj [attackId=" + attackId + ", winnerUsername="
				+ winnerUsername + ", numUnitsLeft=" + numUnitsLeft
				+ ", winnerHasViewed=" + winnerHasViewed + ", loserHasViewed="
				+ loserHasViewed + "]";
	}
	
}
