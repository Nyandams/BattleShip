
public class ShotsGrid {
	/**
	 * -1 - no information
	 *  0 - A miss
	 *  1 - A hit
	 *  2 - The ship has been sunk
	 */
	
	private int[][] shotsGrid;

	
	public ShotsGrid() {
		this.shotsGrid = new int[10][10];
		
		for(int i = 0; i<10; i++) {
			for(int j = 0; j<10; j++) {
				this.shotsGrid[i][j] = -1;
			}
		}
	}
	
	
	public int getShot (String coord) throws Exception {
		IntCoord intCoord = new IntCoord(coord);
		
		return this.shotsGrid[intCoord.getLine()][intCoord.getColumn()];
	}
	
	public void setHit(String coord) throws Exception {
		IntCoord intCoord = new IntCoord(coord);;
		System.out.println("line :" + intCoord.getLine() + "column :" + intCoord.getColumn());//System.exit(0);
		this.shotsGrid[intCoord.getLine()][intCoord.getColumn()] = 1;
	}
	
	public void setMiss(String coord) throws Exception {
		IntCoord intCoord = new IntCoord(coord);
		
		this.shotsGrid[intCoord.getLine()][intCoord.getColumn()] = 0;
	}
	
	public void setSink(String coord) throws Exception {
		IntCoord intCoord = new IntCoord(coord);
		
		this.shotsGrid[intCoord.getLine()][intCoord.getColumn()] = 2;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("TIRS :\n");
		sb.append("  1 2 3 4 5 6 7 8 9 10" + "\n");
		
		for(int i = 0; i<10; i++) {
			sb.append((char)(Configuration.LetterMin+i) + " " );
			for(int j = 0; j<10; j++) {
				if(this.shotsGrid[i][j]  == 0) {
					sb.append("\u25ce");
				}else if(this.shotsGrid[i][j] == 1){
					sb.append("\u25c9");
				}else if(this.shotsGrid[i][j] == 2) {
					sb.append("\u2605");
				}else if(this.shotsGrid[i][j] == -1) {
					sb.append("\u25cb");
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
		
		s.setHit("A5");
		s.setHit("A6");
		s.setHit("A10");
		
		s.setMiss("A10");
		
		s.setSink("A8");
		System.out.println(s.toString());

	}
}
