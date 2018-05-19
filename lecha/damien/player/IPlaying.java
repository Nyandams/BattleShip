package lecha.damien.player;

import lecha.damien.battleship.intern.ShotState;

public interface IPlaying {
	/**
	 * place a ship for the player which length is shipLength
	 * @param shipLength - length of the ship
	 */
	public abstract void placeShip(int shipLength);
	
	/**
	 * make the current Player attack another Player
	 * @param player
	 * @throws Exception
	 */
	public abstract ShotState attack(Player player);
	
	/**
	 * 
	 * @return true if the Player has boat that aren't destroyed in his fleet
	 */
	public abstract boolean isAlive();
	
	/**
	 * The coordinate that the player want to Attack will be returned
	 * @return  String
	 */
	public abstract String choseTarget();
}
