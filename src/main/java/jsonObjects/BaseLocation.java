package jsonObjects;

public class BaseLocation {
	public int baseId;
	public Point world;
	public Point local;
	
	public BaseLocation(
			int baseId,
			Point world,
			Point local) {
		this.baseId = baseId;
		this.world = world;
		this.local = local;
	}
	
	public BaseLocation() {
		
	}
}
