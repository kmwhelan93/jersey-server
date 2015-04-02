package sqlTableObjects;

import jsonObjects.Point;

public class WormHoleObj {
	public int wormholeId;
	public int baseId;
	public Point relativeCoords;
	public int connectedWormholeId;
	
	public WormHoleObj() {
		
	}

	public WormHoleObj(int wormhole_id, int baseId,
			Point relative_coords, int connected_wormhole_id) {
		super();
		this.wormholeId = wormhole_id;
		this.baseId = baseId;
		this.relativeCoords = relative_coords;
		this.connectedWormholeId = connected_wormhole_id;
	}
	public WormHoleObj(int baseId,
			Point relative_coords, int connected_wormhole_id) {
		super();
		this.baseId = baseId;
		this.relativeCoords = relative_coords;
		this.connectedWormholeId = connected_wormhole_id;
	}

	@Override
	public String toString() {
		return "WormHoleObj [wormhole_id=" + wormholeId + ", baseId=" + baseId
				+ ", relative_coords=" + relativeCoords
				+ ", connected_wormhole_id=" + connectedWormholeId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + baseId;
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
		if (baseId != other.baseId)
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
