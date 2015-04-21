package sqlTableObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jsonObjects.NewBase;

public class AttackResultObj {
	public int attackId;
	public String winnerUsername;
	public int numUnitsLeft;
	public BaseObj newBase;
	public Portal newPortal;
	public int[] lostPortalIds;
	//public boolean winnerHasViewed;
	//public boolean loserHasViewed;
	
	public AttackResultObj() {
		
	}

	public AttackResultObj(int attackId, String winnerUsername,
			int numUnitsLeft, BaseObj newBase, Portal newPortal, int[] lostPortalIds
			/*, boolean winnerHasViewed, boolean loserHasViewed*/) {
		super();
		this.attackId = attackId;
		this.winnerUsername = winnerUsername;
		this.numUnitsLeft = numUnitsLeft;
		this.newBase = newBase;
		this.newPortal = newPortal;
		this.lostPortalIds = lostPortalIds;
		//this.winnerHasViewed = winnerHasViewed;
		//this.loserHasViewed = loserHasViewed;
	}

	@Override
	public String toString() {
		return "AttackResultObj [attackId=" + attackId + ", winnerUsername="
				+ winnerUsername + ", numUnitsLeft=" + numUnitsLeft
				+ ", newBase=" + newBase + ", newPortal=" + newPortal
				+ ", lostPortalIds=" + Arrays.toString(lostPortalIds) + "]";
	}
	
}
