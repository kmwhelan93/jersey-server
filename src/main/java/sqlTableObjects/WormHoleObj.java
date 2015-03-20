package sqlTableObjects;

import jsonObjects.Point;

public class WormHoleObj {
	public int wormholeId;
	public BaseObj b;
	public Point relativeCoords;
	public int connectedWormholeId;
	
	public WormHoleObj() {
		
	}

	public WormHoleObj(int wormhole_id, BaseObj base,
			Point relative_coords, int connected_wormhole_id) {
		super();
		this.wormholeId = wormhole_id;
		this.b = base;
		this.relativeCoords = relative_coords;
		this.connectedWormholeId = connected_wormhole_id;
	}
	public WormHoleObj(BaseObj base,
			Point relative_coords, int connected_wormhole_id) {
		super();
		this.b = base;
		this.relativeCoords = relative_coords;
		this.connectedWormholeId = connected_wormhole_id;
	}

	@Override
	public String toString() {
		return "WormHoleObj [wormhole_id=" + wormholeId + ", base=" + b
				+ ", relative_coords=" + relativeCoords
				+ ", connected_wormhole_id=" + connectedWormholeId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		result = prime * result + connectedWormholeId;
		result = prime * result
				+ ((relativeCoords == null) ? 0 : relativeCoords.hashCode());
		result = prime * result + wormholeId;
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
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		if (connectedWormholeId != other.connectedWormholeId)
			return false;
		if (relativeCoords == null) {
			if (other.relativeCoords != null)
				return false;
		} else if (!relativeCoords.equals(other.relativeCoords))
			return false;
		if (wormholeId != other.wormholeId)
			return false;
		return true;
	}
	
	
	
}
