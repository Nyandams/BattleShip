package ovh.lecha.battleship.ai;

import java.util.Random;
import ovh.lecha.battleship.config.Configuration;
import ovh.lecha.battleship.intern.Player;

public abstract class AI implements AIBehavior {
	protected Player player;
	
	public AI() {
		this.player = new Player();
	}
	
	@Override
	public void placeShip(int shipLength){
		boolean testPlace = false;
		while(!testPlace) {
			Random rn = new Random();
			char startLetter, endLetter;
			int startInt, endInt;
			String startCoord, endCoord;
			
			//vertical
			if(rn.nextBoolean()) {
				startLetter	= (char)((int)Configuration.LetterMin + rn.nextInt(Configuration.ColumnLength - shipLength));
				startInt 		=  Configuration.IntMin + rn.nextInt(Configuration.LineLength);
				startCoord	=  new StringBuilder().append(startLetter).append(startInt).toString();
				endInt 		= startInt;
				endLetter	= (char)((int)startLetter + shipLength - 1);
				endCoord 	= new StringBuilder().append(endLetter).append(endInt).toString();
			//horizontal
			}else {
				startLetter= (char)((int)Configuration.LetterMin + rn.nextInt(Configuration.ColumnLength));
				startInt 	=  Configuration.IntMin + rn.nextInt(Configuration.LineLength - shipLength);
				startCoord	=  new StringBuilder().append(startLetter).append(startInt).toString();
				endLetter	= startLetter;
				endInt		= startInt + shipLength - 1;
				endCoord 	= new StringBuilder().append(endLetter).append(endInt).toString();
			}
			
			try {
				this.player.addShip(startCoord, endCoord, shipLength);
				testPlace = true;
			}catch(Exception e) {
				
			}
		}
			
	}
	
	@Override
	public void attack(Player playerTarget) throws Exception {
		String coordAttack = this.choseTarget();
		this.player.attack(playerTarget, coordAttack);
	}
	
	@Override
	public void attack(AI aiTarget) throws Exception {
		String coordAttack = this.choseTarget();
		this.player.attack(aiTarget.player, coordAttack);
	}
	
	/**
	 * 
	 * @return String of the coordinate the ai wants to attack
	 */
	protected abstract String choseTarget();
	
	
	public Player getPlayer() {
		return this.player;
	}
}
