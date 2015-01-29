package sqlTableObjects;

import jsonObjects.Point;

public class Base {
	public String username;
	public int colorId;
	public int baseId;
	public Point world;
	public Point local;
	
	
	
	public Base(String username, int colorId, int baseId, Point world, Point local) {
		super();
		this.username = username;
		this.colorId = colorId;
		this.baseId = baseId;
		this.world = world;
		this.local = local;
	}



	public Base() {
		
	}
}
