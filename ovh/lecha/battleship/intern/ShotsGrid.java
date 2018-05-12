package ovh.lecha.battleship.intern;
import java.util.ArrayList;

import ovh.lecha.battleship.config.Configuration;
import ovh.lecha.battleship.conversion.IntCoord;

public class ShotsGrid {
	/**
	 *  0 - A miss
	 *  1 - A hit
	 *  2 - The ship has been sunk
	 */
	
	private ArrayList<Shot> shotsGrid;

	public ShotsGrid() {
		this.shotsGrid = new ArrayList<Shot>();
	}

	/**
	 * 
	 * @param coord - String
	 * @return true if the coord is in the shotsGrid
	 */
	public boolean hasCoord(String coord) {
		boolean has = false;
		for(Shot s : this.shotsGrid) {
			if(s.getShotCoord().equals(coord)) {
				has = true;
			}
		}
		return has;
	}
	/**
	 * add a Hit in the shotsGrid
	 * @param coord - String of the hit
	 */
	public void addHit(String coord) {
		this.shotsGrid.add(new Shot(coord).setHit());
	}
	
	/**
	 * add a Miss in the shotsGrid
	 * @param coord - String of the miss
	 */
	public void addMiss(String coord) {
		this.shotsGrid.add(new Shot(coord).setMiss());
	}
	
	/**
	 * add a Hit in the shotsGrid
	 * @param coord - String of the Sink
	 */
	public void addSink(String coord) {
		this.shotsGrid.add(new Shot(coord).setSink());
	}
	
	
	public String toString() {
		int shotsArray[][] = new int[Configuration.LineLength][Configuration.ColumnLength];
		for(int i = 0; i<Configuration.LineLength; i++) {
			for(int j = 0; j<Configuration.LineLength; j++) {
				shotsArray[i][j] = -1;
			}
		}
		
		
		for (Shot shot : this.shotsGrid) {
			IntCoord intCoord;
			try {
				intCoord = new IntCoord(shot.getShotCoord());
				if(shot.isHit()) {
					shotsArray[intCoord.getLine()][intCoord.getColumn()] = 1;
				}else if(shot.isMiss()) {
					shotsArray[intCoord.getLine()][intCoord.getColumn()] = 0;
				}else if(shot.isSink()) {
					shotsArray[intCoord.getLine()][intCoord.getColumn()] = 2;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("TIRS :\n");
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
				if(shotsArray[i][j]  == 0) {
					sb.append("o");
				}else if(shotsArray[i][j] == 1){
					sb.append("+");
				}else if(shotsArray[i][j] == 2) {
					sb.append("x");
				}else if(shotsArray[i][j] == -1) {
					sb.append(".");
				}
				sb.append(" ");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	

	public static void main(String[] args) throws Exception {
		ShotsGrid s = new ShotsGrid();
		System.out.println(s.toString());
		
		s.addHit("A5");
		System.out.println(s.hasCoord("A5"));
		s.addHit("A6");
		s.addHit("A10");
		
		s.addMiss("A10");
		
		s.addSink("A8");
		System.out.println(s.toString());

	}
}