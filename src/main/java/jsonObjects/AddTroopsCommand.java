package jsonObjects;

public class AddTroopsCommand {
	public int baseId;
	public int troopsToAdd;
	
	public AddTroopsCommand() {
		
	}
	
	public AddTroopsCommand (int baseId, int troopsToAdd) {
		this.baseId = baseId;
		this.troopsToAdd = troopsToAdd;
	}
}
