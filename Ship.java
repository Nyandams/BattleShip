import java.util.ArrayList;

public class Ship {
	
	private ArrayList<CaseCoord> shipCoord;
	 
	/**
	 * 
	 * @param startCoord - String of the top left coordinate
	 * @param endCoord - String of the bottom right coordinate
	 * @throws Exception
	 * 
	 */
	public Ship(String startCoord, String endCoord) throws Exception {
		//Coordinates conversion into Char and Integer
		char startLetter = StringConvert.getCorrespondingLetter(startCoord);
		char endLetter   = StringConvert.getCorrespondingLetter(endCoord);
		int startInt 	 = StringConvert.getCorrespondingInt(startCoord);
		int endInt		 = StringConvert.getCorrespondingInt(endCoord);
		
		
		//verification about the validity of the coordinates in the grid
		if( (int)startLetter >= (int)Configuration.LetterMin && (int)startLetter <= (int)Configuration.LetterMax &&
			(int)endLetter >= (int)Configuration.LetterMin && (int)endLetter <= (int)Configuration.LetterMax &&
			startInt >= Configuration.IntMin && startInt <= Configuration.IntMax &&
			endInt >= Configuration.IntMin && endInt <= Configuration.IntMax) {
			
			// verification about the verticality or horizontality of the ship
			if(startLetter == endLetter) {
				if(endInt < startInt) {
					int tmp  = endInt;
					endInt   = startInt;
					startInt = tmp;
				}
				this.shipCoord = new ArrayList<CaseCoord>();
				int i = startInt;
				while(i <= endInt) {
					String coord = startLetter + String.valueOf(i);
					this.shipCoord.add(new CaseCoord(coord));
					i++;
				}
				
			}else if(startInt == endInt) {
				if((int)endLetter < (int)startLetter) {
					char tmp    = endLetter;
					endLetter   = startLetter;
					startLetter = tmp;
				}
				this.shipCoord = new ArrayList<CaseCoord>();
				int i = (int)startLetter;
				while(i <= (int)endLetter) {
					String coord = String.valueOf((char)i) + String.valueOf(startInt);
					this.shipCoord.add(new CaseCoord(coord));
					i++;
				}
				
			}else {
				throw new Exception("invalid coordinates (verticality-horizontality)");
			}
			
		}else {
			throw new Exception("invalid coordinates (not in the grid)");
		}
	}
		

	/**
	 * return true if the ship is hit by the missile
	 * @param missileCoord - String
	 * @return - boolean
	 */
	public boolean isHit(String missileCoord) {
		boolean hit = false;
		int i = 0;
		while(!hit && i<this.shipCoord.size()) {
			if(this.shipCoord.get(i).getCaseCoord().equals(missileCoord)) {
				this.shipCoord.get(i).setTouched(true);
			}
			i++;
		}
		return hit;
	}
	
	/**
	 * return true if the ship is hit by the missile and set it true if it touch
	 * @param missileCoord - String
	 * @return - boolean
	 */
	public boolean hit(String missileCoord) {
		boolean hit = false;
		int i = 0;
		while(!hit && i<this.shipCoord.size()) {
			if(this.shipCoord.get(i).getCaseCoord().equals(missileCoord)) {
				hit = true;
				this.shipCoord.get(i).setTouched(true);
			}
			i++;
		}
		return hit;
	}
	
	
	/**
	 * return true if the ship is superimposed with the ship s
	 * @param coord - String
	 * @return - boolean
	 */
	public boolean hasCoord(Ship s) {
		boolean has = false;
		int i;
		int j = 0;
		String coord;
		
		while(!has && j<s.shipSize()) {
			coord = s.shipCoord.get(j).getCaseCoord();
							
			i = 0;
			while(!has && i<this.shipCoord.size()) {
				if(this.shipCoord.get(i).getCaseCoord().equals(coord)) {
					has = true;
				}
				i++;
			}
			j++;
		}
			
			
		
		return has;
	}
	
	public ArrayList<CaseCoord> getShipCoord() {
		return shipCoord;
	}


	/**
	 * 
	 * @return true if the ship is destroyed, false otherwise
	 */
	public boolean isDestroyed() {
		boolean destroyed = true;
		int i = 0;
		while(destroyed && i<this.shipCoord.size()) {
			if(!this.shipCoord.get(i).isTouched()) {
				destroyed = false;
			}
			i++;
		}
		
		return destroyed;
	}
	
	/**
	 * 
	 * @return int - the size of the Ship
	 */
	public int shipSize() {
		return this.shipCoord.size();
	}
	
	/**
	 * toString method of the Class Ship
	 * @return String
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Ship = [");
		
		for (CaseCoord c : this.shipCoord) {
			 sb.append(" " + c.toString());
		}
		
		sb.append(" ]");
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		Ship s1 = new Ship("A10", "F10");
		System.out.println(s1.toString());	
	}

}