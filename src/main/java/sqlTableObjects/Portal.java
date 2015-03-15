package sqlTableObjects;

public class Portal {
	public int portalId;
	public String username;
	public BaseObj base1;
	public BaseObj base2;
	public long timeFinished;
	public int flowRate;
	public int troopsToMove;
	public long lastMoveUpdate;

	public Portal(int portalId, String username, BaseObj base1, BaseObj base2,
			long timeFinished, int flowRate, int troopsToMove, long lastMoveUpdate) {
		super();
		this.portalId = portalId;
		this.username = username;
		this.base1 = base1;
		this.base2 = base2;
		this.timeFinished = timeFinished;
		this.flowRate = flowRate;
		this.troopsToMove = troopsToMove;
		this.lastMoveUpdate = lastMoveUpdate;
	}

	@Override
	public String toString() {
		return "Portal [portalId=" + portalId + ", username=" + username
				+ ", base1=" + base1 + ", base2=" + base2 + ", timeFinished=" 
				+ timeFinished + ", flowRate=" + flowRate + ", troopsToMove=" 
				+ troopsToMove + ", lastMoveUpdate=" + lastMoveUpdate + "]";
	}
	
	
	
	
}
