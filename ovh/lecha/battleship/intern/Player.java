package ovh.lecha.battleship.intern;
import java.util.ArrayList;

import ovh.lecha.battleship.config.Configuration;
import ovh.lecha.battleship.conversion.IntCoord;
import ovh.lecha.battleship.conversion.StringConvert;

public class Player {
	
	private ArrayList<Ship> ships;
	private ShotsGrid shotsGrid;
	
	public Player() {
		this.ships     = new ArrayList<Ship>();	
		this.shotsGrid = new ShotsGrid();
	}
	
	/**
	 * Add a Ship to the player's fleet
	 * @param startCoord - String of the top left coordinate
	 * @param endCoord - String of the bottom right coordinate
	 * @param shipLength - integer of the length we want for the ship
	 * @throws Exception
	 */
	public void addShip(String startCoord, String endCoord, int shipLength) throws Exception {
		try {
			Ship ship =  new Ship(startCoord, endCoord);
			
			if(ship.shipSize() == shipLength) {
				if (this.freeLocation(ship)){
					this.ships.add(ship);
				}else {
					throw new Exception("the ship would be surimposed with another ship of the fleet");
				}
			}else {
				throw new Exception("invalid size of the ship (should be "+ shipLength +")");
			}
		}catch(Exception e) {
			throw(e);
		}
	}
		
	/***
	 * 
	 * @param missileCoord - String of a Coordinate
	 * @return ShotState
	 */
	public ShotState hit(String missileCoord) {
		boolean hit = false;
		ShotState res = ShotState.MISS;
		int i = 0;
		
		while(!hit && i<this.ships.size()) {
			hit = this.ships.get(i).hit(missileCoord);
			if (hit) {
				if(this.ships.get(i).isDestroyed()) {
					res = ShotState.SINK;
				}else {
					res = ShotState.HIT;
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
	 * @return ShotState
	 * @throws Exception "invalid coordinates (not in the grid)"
	 */
	public ShotState attack(Player p2, String coord) throws Exception {
		
		if(StringConvert.isOk(coord)) {
			ShotState shot;
			
			if (this.shotsGrid.hasCoord(coord)) {
				shot =  ShotState.ALREADY;
			}else {
				shot = p2.hit(coord);
			}
			switch(shot) {
				case MISS :		this.shotsGrid.addMiss(coord);
								break;
						
				case HIT :		this.shotsGrid.addHit(coord);
								break;
						
				case SINK :		this.shotsGrid.addSink(coord);
								break;
								
				case ALREADY: 	break;
				
				default:
								break;		
			}
			return shot;
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
	
	/**
	 * 
	 * @return String of the fleet of the player
	 */
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
					sb.append("o");
				}else if(shipsGrid[i][j] == 2) { //touched
					sb.append("x");
				}else{
					sb.append(".");         //nothing
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
		
		p1.addShip("A1", "A3", 3);
		p1.addShip("B1", "D1", 3);
		p2.addShip("A1", "A3", 3);
		p1.attack(p2, "A1");
		p1.attack(p2,"A2");
		p1.attack(p2, "A3");
		p1.attack(p2, "A2");
		p1.attack(p2, "A1");
		p1.attack(p2,"A2");
		//System.out.println("P2 : \n" + p2.toString());
		System.out.println("P1 : \n" + p1.toString());
		
	}
}