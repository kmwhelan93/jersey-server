package sqlTableObjects;

public class Portal {
	public int portalId;
	public String username;
	public int base1Id;
	public int base2Id;
	public long timeFinished;
	public int flowRate;
	public int troopsToMove;
	public long lastMoveUpdate;

	public Portal(int portalId, String username, int base1, int base2,
			long timeFinished, int flowRate, int troopsToMove, long lastMoveUpdate) {
		super();
		this.portalId = portalId;
		this.username = username;
		this.base1Id = base1;
		this.base2Id = base2;
		this.timeFinished = timeFinished;
		this.flowRate = flowRate;
		this.troopsToMove = troopsToMove;
		this.lastMoveUpdate = lastMoveUpdate;
	}
	
	public Portal() {
		
	}

	@Override
	public String toString() {
		return "Portal [portalId=" + portalId + ", username=" + username
				+ ", base1=" + base1Id + ", base2=" + base2Id + ", timeFinished=" 
				+ timeFinished + ", flowRate=" + flowRate + ", troopsToMove=" 
				+ troopsToMove + ", lastMoveUpdate=" + lastMoveUpdate + "]";
	}
	
	
	
	
}
