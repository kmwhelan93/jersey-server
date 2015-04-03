package jsonObjects;

public class MoveTroopsCommand {
	public int portalId;
	public int troopsToMove;
	
	public MoveTroopsCommand() {
		
	}
	
	public MoveTroopsCommand(int pId, int troopsToMove) {
		this.portalId = pId;
		this.troopsToMove = troopsToMove;
	}
}
