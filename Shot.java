
public class Shot {
	private String  shotCoord;
	private int state;
	
	/**
	 * -1 - no information
	 *  0 - A miss
	 *  1 - A hit
	 *  2 - The ship has been sunk
	 */
	
	public Shot(String shotCoord) {
		this.shotCoord = shotCoord;
	}
}
