package sqlTableObjects;

public class Portal {
	public int portalId;
	public String username;
	public Base base1;
	public Base base2;
	int flowRate;

	public Portal(int portalId, String username, Base base1, Base base2,
			int flowRate) {
		super();
		this.portalId = portalId;
		this.username = username;
		this.base1 = base1;
		this.base2 = base2;
		this.flowRate = flowRate;
	}

	@Override
	public String toString() {
		return "Portal [portalId=" + portalId + ", username=" + username
				+ ", base1=" + base1 + ", base2=" + base2 + ", flowRate="
				+ flowRate + "]";
	}
	
	
	
	
}
