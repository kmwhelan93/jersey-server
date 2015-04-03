package jsonObjects;

public class AddTroopsCommand {
	public int bId;
	public int troopsToAdd;
	
	public AddTroopsCommand() {
		
	}
	
	public AddTroopsCommand (int baseId, int troopsToAdd) {
		this.bId = baseId;
		this.troopsToAdd = troopsToAdd;
	}
}
