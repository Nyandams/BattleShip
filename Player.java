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
	 * @return
	 */
	public boolean hit(String missileCoord) {
		boolean hit = false;
		int i = 0;
		
		while(!hit && i<this.ships.size()) {
			hit = this.ships.get(i).hit(missileCoord);
			i++;
		}
		return hit;
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
	 * 
	 * @param p2- player attacked
	 * @param coord
	 * @return 0 if it's a Miss, 1 if it's a Hit, 2 if the ship has been sunk
	 */
	public int attack(Player p2, String coord) {
		return 0;
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
		
		int[][] shipsGrid = new int[10][10];
		
		for(int i = 0; i<10; i++) {
			for(int j = 0; j<10; j++) {
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
		
		sb.append("  1 2 3 4 5 6 7 8 9 10" + "\n");
		for(int i = 0; i<10; i++) {
			sb.append((char)(Configuration.LetterMin + i) + " " );
			for(int j = 0; j<10; j++) {
				if(shipsGrid[i][j] == 1) {
					sb.append("\u25c9");
				}else if(shipsGrid[i][j] == 2) {
					sb.append("\u2605");
				}else{
					sb.append("\u25cb");
				}
				sb.append(" ");
			}
			sb.append("\n");
		}
		
		
		
		return sb.toString();
	}
	

	public static void main(String[] args) throws Exception {
		Player p1 = new Player();
		p1.addBattleship("A1", "D1");
		p1.hit("B1");
		p1.addCarrier("B2", "B6");
		
		System.out.println(p1.toString());
		
	}
}