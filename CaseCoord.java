
public class CaseCoord {
	private String  caseCoord;
	private boolean touched;
	
	public CaseCoord(String caseCoord) {
		this.caseCoord = caseCoord;
		this.touched	= false;
	}

	/**
	 * Getter of the attribute caseCoord
	 * @return the String of the Coordinate
	 */
	public String getCaseCoord() {
		return caseCoord;
	}

	/**
	 * Setter of the attribute caseCoord
	 * @param caseCoord - the String of the coordinate 
	 */
	public void setCaseCoord(String caseCoord) {
		this.caseCoord = caseCoord;
	}

	/**
	 * Getter of the boolean attribute touched
	 * @return boolean - True if the case is touched, else False
	 */
	public boolean isTouched() {
		return touched;
	}

	/**
	 * Setter of the attributer touched
	 * @param touched - a boolean specifying the state of the case
	 */
	public void setTouched(boolean touched) {
		this.touched = touched;
	}
	
	/**
	 * toString method of CaseCoord
	 * Display :  <case>:<state>
	 * @return : String
	 */
	public String toString() {
		String touchedString;
		if (this.isTouched()) {
			touchedString = "touched";
		}else {
			touchedString = "safe";
		}
		return this.caseCoord + ":" + touchedString;
	}
	
}