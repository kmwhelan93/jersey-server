package sqlTableObjects;

public class Portal {
	public int portalId;
	public String username;
	public BaseObj base1;
	public BaseObj base2;
	public int flowRate;
	public long timeFinished;

	public Portal(int portalId, String username, BaseObj base1, BaseObj base2,
			int flowRate, long timeFinished) {
		super();
		this.portalId = portalId;
		this.username = username;
		this.base1 = base1;
		this.base2 = base2;
		this.flowRate = flowRate;
		this.timeFinished = timeFinished;
	}

	@Override
	public String toString() {
		return "Portal [portalId=" + portalId + ", username=" + username
				+ ", base1=" + base1 + ", base2=" + base2 + ", flowRate="
				+ flowRate + ", timeFinished=" + timeFinished + "]";
	}
	
	
	
	
}
