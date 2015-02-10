package sqlTableObjects;

import jsonObjects.Point;

public class Base {
	public String username;
	public int colorId = -1;
	public int baseId = -1;
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
	
	public Base(String username, Point world, Point local) {
		this.username = username;
		this.world = world;
		this.local = local;
	}



	public Base() {
		
	}



	@Override
	public String toString() {
		return "Base [world=" + world + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((world == null) ? 0 : world.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Base other = (Base) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (world == null) {
			if (other.world != null)
				return false;
		} else if (!world.equals(other.world))
			return false;
		return true;
	}
	
	
	
	
}
