
public class Shot {
	private String  shotCoord;
	private int state;
	
	/**
	 *  0 - A miss
	 *  1 - A hit
	 *  2 - The ship has been sunk
	 */
	
	public Shot(String shotCoord) {
		this.shotCoord = shotCoord;
	}

	
	
	public String getShotCoord() {
		return shotCoord;
	}
	
	public boolean isMiss() {
		return (this.state == 0);
	}

	public boolean isHit() {
		return (this.state == 1);
	}
	
	public boolean isSink() {
		return (this.state == 2);
	}
	
	public Shot setMiss() {
		this.state = 0;
		return this;
	}
	
	public Shot setHit() {
		this.state = 1;
		return this;
	}
	
	public Shot setSink() {
		this.state = 2;
		return this;
	}
}
