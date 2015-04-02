package jsonObjects;

public class MoveTroopsCommand {
	public int pId;
	public int troopsToMove;
	
	public MoveTroopsCommand() {
		
	}
	
	public MoveTroopsCommand(int pId, int troopsToMove) {
		this.pId = pId;
		this.troopsToMove = troopsToMove;
	}
}
