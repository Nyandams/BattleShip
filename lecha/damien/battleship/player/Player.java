package lecha.damien.battleship.player;

import lecha.damien.battleship.intern.GameBoard;

public abstract class Player implements IPlaying{
	protected GameBoard gameBoard;
	private String name;
	
	public Player(String name) {
		this.gameBoard = new GameBoard();
		this.name 	   = name;
	}
	
	public boolean isAlive() {
		return this.gameBoard.alive();
	}
	
	public GameBoard getGameBoard() {
		return this.gameBoard;
	}	
	
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return this.gameBoard.toString();
	}
}
