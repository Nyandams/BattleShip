import java.util.ArrayList;

public class Player {
	
	private ArrayList<Ship> ships;
	private ShotsGrid shotsGrid;
	
	public Player() {
		this.ships     = new ArrayList<Ship>();	
		this.shotsGrid = new ShotsGrid();
	}
	
	/**
	 * Add a Carrier (length : 5) to the player's fleet
	 * @param startCoord - String of the top left coordinate
	 * @param endCoord - String of the bottom right coordinate
	 * @throws Exception
	 */
	public void addCarrier(String startCoord, String endCoord) throws Exception {
		try {
			Ship carrier =  new Ship(startCoord, endCoord);
			
			if(carrier.shipSize() == 5) {
				if (this.freeLocation(carrier)){
					this.ships.add(carrier);
				}else {
					throw new Exception("the ship would be surimposed with another ship of the fleet");
				}
			}else {
				throw new Exception("invalid size of the ship (should be 5)");
			}
		}catch(Exception e) {
			throw(e);
		}
	}
	
	/**
	 * Add a BattleShip (length : 4) to the player's fleet
	 * @param startCoord - String of the top left coordinate
	 * @param endCoord - String of the bottom right coordinate
	 * @throws Exception
	 */
	public void addBattleship(String startCoord, String endCoord) throws Exception {
		try {
			Ship battleship =  new Ship(startCoord, endCoord);
			
			if(battleship.shipSize() == 4) {
				if (this.freeLocation(battleship)){
					this.ships.add(battleship);
				}else {
					throw new Exception("the ship would be surimposed with another ship of the fleet");
				}
			}else {
				throw new Exception("invalid size of the ship (should be 4)");
			}
		}catch(Exception e) {
			throw(e);
		}
	}
	
	/**
	 * Add a Cruiser (length : 3) to the player's fleet
	 * @param startCoord - String of the top left coordinate
	 * @param endCoord - String of the bottom right coordinate
	 * @throws Exception
	 */
	public void addCruiser(String startCoord, String endCoord) throws Exception {
		try {
			Ship cruiser =  new Ship(startCoord, endCoord);
			
			if(cruiser.shipSize() == 3) {
				if (this.freeLocation(cruiser)){
					this.ships.add(cruiser);
				}else {
					throw new Exception("the ship would be surimposed with another ship of the fleet");
				}
			}else {
				throw new Exception("invalid size of the ship (should be 3)");
			}
		}catch(Exception e) {
			throw(e);
		}
	}
	
	/**
	 * Add a Submarine (length : 3) to the player's fleet
	 * @param startCoord - String of the top left coordinate
	 * @param endCoord - String of the bottom right coordinate
	 * @throws Exception
	 */
	public void addSubmarine(String startCoord, String endCoord) throws Exception {
		try {
			Ship submarine =  new Ship(startCoord, endCoord);
			
			if(submarine.shipSize() == 3) {
				if (this.freeLocation(submarine)){
					this.ships.add(submarine);
				}else {
					throw new Exception("the ship would be surimposed with another ship of the fleet");
				}
			}else {
				throw new Exception("invalid size of the ship (should be 3)");
			}
		}catch(Exception e) {
			throw(e);
		}
	}
	
	/**
	 * Add a Destroyer (length : 2) to the player's fleet
	 * @param startCoord - String of the top left coordinate
	 * @param endCoord - String of the bottom right coordinate
	 * @throws Exception
	 */
	public void addDestroyer(String startCoord, String endCoord) throws Exception {
		try {
			Ship destroyer =  new Ship(startCoord, endCoord);
			
			if(destroyer.shipSize() == 2) {
				if (this.freeLocation(destroyer)){
					this.ships.add(destroyer);
				}else {
					throw new Exception("the ship would be surimposed with another ship of the fleet");
				}
			}else {
				throw new Exception("invalid size of the ship (should be 2)");
			}
		}catch(Exception e) {
			throw(e);
		}
	}
	
	/***
	 * 
	 * @param missileCoord - String of a Coordinate
	 * @return 0 - Miss |
	 * 		   1 - Hit |
	 * 		   2 - Sink |
	 */
	public int hit(String missileCoord) {
		boolean hit = false;
		int res = 0;
		int i = 0;
		
		while(!hit && i<this.ships.size()) {
			hit = this.ships.get(i).hit(missileCoord);
			if (hit) {
				if(this.ships.get(i).isDestroyed()) {
					res = 2;
				}else {
					res = 1;
				}
			}
			
			i++;
		}
		
		
		return res;
	}

	/**
	 * check if the ship is allowed to be placed or not
	 * @return true if the ship's coordinates are different from those of other ships of the fleet
	 */
	public boolean freeLocation(Ship s) {
		boolean superimposed = true;
		int j = 0;

		while(superimposed && j<this.ships.size()) {
			superimposed = !this.ships.get(j).hasCoord(s);
			j++;
		}
		return superimposed;
	}
	
	/**
	 * current player attack p2 player
	 * @param p2 - player attacked
	 * @param coord
	 * @return 0 if it's a Miss, 1 if it's a Hit, 2 if the ship has been sunk, -1 if already launched
	 * @throws Exception 
	 */
	public int attack(Player p2, String coord) throws Exception {
		
		if(StringConvert.isOk(coord)) {
			if (this.shotsGrid.hasCoord(coord)) {
				return -1;
			}else {
				int shot = p2.hit(coord);
				switch(shot) {
					case 0: this.shotsGrid.addMiss(coord);
							break;
						
					case 1:	this.shotsGrid.addHit(coord);
							break;
						
					case 2:	this.shotsGrid.addSink(coord);
							break;
				}
				return shot;
			}
		}else {
			throw new Exception("invalid coordinates (not in the grid)");
		}
	}
	
	
	/**
	 * @return String
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.fleetToString());
		sb.append("\n");
		sb.append(this.shotsGrid.toString());
		
		return sb.toString();
	}
	
	public String fleetToString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("Bateau : \n");
		
		int[][] shipsGrid = new int[Configuration.LineLength][Configuration.ColumnLength];
		
		for(int i = 0; i<Configuration.LineLength; i++) {
			for(int j = 0; j<Configuration.ColumnLength; j++) {
				shipsGrid[i][j] = 0;
			}
		}
		
		
		for (Ship s : this.ships) {
			ArrayList<CaseCoord> shipCoord = s.getShipCoord();
			for (CaseCoord c : shipCoord) {
				IntCoord intCoord;
				try {
					intCoord = new IntCoord(c.getCaseCoord());
					if(c.isTouched()) {
						shipsGrid[intCoord.getLine()][intCoord.getColumn()] = 2;
					}else {
						shipsGrid[intCoord.getLine()][intCoord.getColumn()] = 1;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
	sb.append("   ");
		
		for(int i = 0; i < Configuration.ColumnLength; i++) {
			sb.append((char)(Configuration.LetterMin + i) + " ");
		}
		sb.append("\n");
		
		
		for(int i = 0; i<Configuration.LineLength; i++) {
			if((Configuration.IntMin+i) < 10) {
				sb.append((Configuration.IntMin+i) + "  " );
			}else {
				sb.append((Configuration.IntMin+i) + " " );
			}
			
			
			for(int j = 0; j<Configuration.ColumnLength; j++) {
				if(shipsGrid[i][j] == 1) {       //safe
					sb.append("\u25c9");
				}else if(shipsGrid[i][j] == 2) { //touched
					sb.append("\u2605");
				}else{
					sb.append("\u25cb");         //nothing
				}
				sb.append(" ");
			}
			sb.append("\n");
		}	
		return sb.toString();
	}
	
	/**
	 * 
	 * @return true if all the ship of the player aren't destroyed, else return false
	 */
	public boolean alive() {
		boolean alive = false;
		
		for (Ship s: this.ships) {
			if(!s.isDestroyed()) {
				alive = true;
			}
		}
		
		return alive;
	}
	

	public static void main(String[] args) throws Exception {
		Player p1 = new Player();
		Player p2 = new Player();
		
		p1.addBattleship("A1", "D1");
		p1.addCarrier("B2", "B6");
		p2.addBattleship("A1", "A4");
		p2.addCarrier("B2", "B6");
		
		p1.attack(p2, "A2");
		
		System.out.println("P1 : \n" + p1.toString());
		System.out.println("P2 : \n" + p2.toString());
	}
}