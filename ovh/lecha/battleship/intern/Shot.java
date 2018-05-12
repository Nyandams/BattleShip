package ovh.lecha.battleship.intern;

public class Shot {
	private String  shotCoord;
	private ShotState state;
	
	public Shot(String shotCoord) {
		this.shotCoord = shotCoord;
	}

	
	
	public String getShotCoord() {
		return shotCoord;
	}
	
	public boolean isMiss() {
		return (this.state == ShotState.MISS);
	}

	public boolean isHit() {
		return (this.state == ShotState.HIT);
	}
	
	public boolean isSink() {
		return (this.state == ShotState.SINK);
	}
	
	public Shot setMiss() {
		this.state = ShotState.MISS;
		return this;
	}
	
	public Shot setHit() {
		this.state = ShotState.HIT;
		return this;
	}
	
	public Shot setSink() {
		this.state = ShotState.SINK;
		return this;
	}
}
