package ovh.lecha.battleship.ai;

import ovh.lecha.battleship.intern.Player;

public interface AIBehavior {
	/**
	 * place a ship for the player
	 * @param shipLength - length of the ship
	 */
	public abstract void placeShip(int shipLength);
	
	/**
	 * make the current AI player attack another player
	 * @param player
	 * @throws Exception
	 */
	public abstract void attack(Player player) throws Exception;
	
	/**
	 * make the current AI player attack another AI player
	 * @param player
	 * @throws Exception
	 */
	public abstract void attack(AI ia) throws Exception;
	
	/**
	 * 
	 * @return String of the coordinate the ai wants to attack
	 */
	public abstract String choseTarget();
	
}
