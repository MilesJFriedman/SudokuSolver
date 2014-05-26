package homework1Sudoku;


/**
 * NumberTracker.java
 * 
 * @author Miles Friedman
 * @version 5/26/14
 * 
 * Description: This class is used to create a number tracker object, which basically keeps
 * 				track of whether or not a row/column/block contains the number specified upon
 * 				object creation. It also keeps track of when the object is found and begins to
 * 				be tracked (if necessary).
 */
public class NumberTracker {

	private boolean contains;
	private boolean changed;
	
	public NumberTracker (boolean has) {
		this.contains = has;
		this.changed = false;
	}//constructor

	public void testAndReset () {
		if (this.changed == true)
			this.contains = false;
	}
	
	public void setContains (boolean has) {
		this.contains = has;
	}//setter
	
	public void resetChanged () {
		this.changed = false;
	}
	
	public void markChanged () {
		if (this.contains == false)
			this.changed = true;
	}//setter
	
	public boolean isContained () {
		return this.contains;
	}//getter

	public boolean hasChanged () {
		return this.changed;
	}//getter

}


