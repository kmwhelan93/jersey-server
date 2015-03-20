package sqlTableObjects;

import jsonObjects.Point;

public class WormHoleObj {
	public int wormhole_id;
	public BaseObj base;
	public Point relative_coords;
	public int connected_wormhole_id;
	
	public WormHoleObj() {
		
	}

	public WormHoleObj(int wormhole_id, BaseObj base,
			Point relative_coords, int connected_wormhole_id) {
		super();
		this.wormhole_id = wormhole_id;
		this.base = base;
		this.relative_coords = relative_coords;
		this.connected_wormhole_id = connected_wormhole_id;
	}

	@Override
	public String toString() {
		return "WormHoleObj [wormhole_id=" + wormhole_id + ", base=" + base
				+ ", relative_coords=" + relative_coords
				+ ", connected_wormhole_id=" + connected_wormhole_id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((base == null) ? 0 : base.hashCode());
		result = prime * result + connected_wormhole_id;
		result = prime * result
				+ ((relative_coords == null) ? 0 : relative_coords.hashCode());
		result = prime * result + wormhole_id;
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
		WormHoleObj other = (WormHoleObj) obj;
		if (base == null) {
			if (other.base != null)
				return false;
		} else if (!base.equals(other.base))
			return false;
		if (connected_wormhole_id != other.connected_wormhole_id)
			return false;
		if (relative_coords == null) {
			if (other.relative_coords != null)
				return false;
		} else if (!relative_coords.equals(other.relative_coords))
			return false;
		if (wormhole_id != other.wormhole_id)
			return false;
		return true;
	}
	
	
	
}
