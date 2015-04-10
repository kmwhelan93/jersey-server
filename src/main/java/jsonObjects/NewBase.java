package jsonObjects;

import sqlTableObjects.BaseObj;
import sqlTableObjects.Portal;

public class NewBase {
	public BaseObj b;
	public Portal p;
	
	public NewBase() {
		
	}
	
	public NewBase(BaseObj bObj, Portal p) {
		super();
		this.b = bObj;
		this.p = p;
	}
}
