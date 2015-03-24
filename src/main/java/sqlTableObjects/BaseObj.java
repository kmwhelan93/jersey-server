package sqlTableObjects;

import java.util.List;

import jsonObjects.Point;

public class BaseObj {
	public String username;
	public int colorId = -1;
	public int baseId = -1;
	public Point world;
	public Point local;
	public int prodRate;
	public int units;
	public int unitsToAdd;
	public long lastUpdated;
	
	
	public BaseObj(String username, int colorId, int baseId, Point world, Point local, int prodRate, int units,
			int unitsToAdd, long lastUpdated) {
		super();
		this.username = username;
		this.colorId = colorId;
		this.baseId = baseId;
		this.world = world;
		this.local = local;
		this.prodRate = prodRate;
		this.units = units;
		this.unitsToAdd = unitsToAdd;
		this.lastUpdated = lastUpdated;
	}
	
	// for tests
	public BaseObj(String username, int colorId, int baseId, Point world, Point local, int prodRate, int units) {
		super();
		this.username = username;
		this.colorId = colorId;
		this.baseId = baseId;
		this.world = world;
		this.local = local;
		this.prodRate = prodRate;
		this.units = units;
		this.unitsToAdd = 0;
		this.lastUpdated = 0;
	}
	
	public BaseObj(String username, Point world, Point local) {
		this.username = username;
		this.world = world;
		this.local = local;
	}
	
	public boolean isSpaceOccupied(List<BaseObj> bases) {
		for (BaseObj b : bases) {
			if (this.world.equals(b.world))
				return true;
		}
		return false;
	}



	public BaseObj() {
		
	}

	@Override
	public String toString() {
		return "Base [username=" + username + ", colorId=" + colorId
				+ ", baseId=" + baseId + ", world=" + world + ", local="
				+ local + ", prodRate=" + prodRate + ", units=" + units + 
				", unitsToAdd=" + unitsToAdd + ", lastUpdated=" + lastUpdated + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + baseId;
		result = prime * result + colorId;
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result + prodRate;
		result = prime * result + units;
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
		BaseObj other = (BaseObj) obj;
		if (baseId != other.baseId)
			return false;
		if (colorId != other.colorId)
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (prodRate != other.prodRate)
			return false;
		if (units != other.units)
			return false;
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
