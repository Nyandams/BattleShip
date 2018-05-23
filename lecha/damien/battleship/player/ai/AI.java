package lecha.damien.battleship.player.ai;

import java.util.Random;

import lecha.damien.battleship.config.Configuration;
import lecha.damien.battleship.intern.ShotState;
import lecha.damien.battleship.player.Player;

public abstract class AI extends Player {



	public AI(String name) {
		super(name);
	}


	@Override
	public void placeShip(int shipLength){
		boolean testPlace = false;
		while(!testPlace) {
			Random rn = new Random();
			char startLetter, endLetter;
			int startInt, endInt;
			String startCoord = "";
			String endCoord = "";
			boolean choixDirection;
			if((Configuration.ColumnLength - shipLength) <1 && (Configuration.LineLength - shipLength) <1) {
				throw new Error("Impossibility to place the ship in the grid due to the capacity of the grid");
			} else if((Configuration.LineLength - shipLength) <1) {
				choixDirection = true;
			}else if((Configuration.ColumnLength - shipLength) <1) {
				choixDirection = false;
			}else {
				choixDirection = rn.nextBoolean();
			}
			//vertical
			
			if(choixDirection) {
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
				this.gameBoard.addShip(startCoord, endCoord, shipLength);
				testPlace = true;
			}catch(Exception e) {
				
			}
		}
			
	}
	
	
	public ShotState attack(Player opponent) {
		String coordAttack = this.choseTarget();
		ShotState shot = null;
		try {
			shot = this.gameBoard.attack(opponent.getGameBoard(), coordAttack);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shot;
	}
}
