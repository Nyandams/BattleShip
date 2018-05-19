package lecha.damien.player.human;

import java.util.Scanner;

import lecha.damien.battleship.intern.ShotState;
import lecha.damien.player.Player;

public class Human extends Player {
	
	public Human(String name) {
		super(name);
	}

	@Override
	public void placeShip(int shipLength) {
		Scanner scPlace = new Scanner(System.in);
		String inputStart, inputEnd;
		boolean testScan = false;

		while (!testScan) {
			System.out.print("Start Coordinates : ");
			inputStart = scPlace.next();
			System.out.print("End Coordinates : ");
			inputEnd = scPlace.next();

			try {
				this.gameBoard.addShip(inputStart, inputEnd, shipLength);
				testScan = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public ShotState attack(Player opponent){
		boolean testScan = false;
		ShotState shot = null;
		while (!testScan) {
			try {
				String coord = choseTarget();
				shot = this.gameBoard.attack(opponent.getGameBoard(), coord);
				testScan = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return shot;
	}

	@Override
	public String choseTarget() {
		Scanner scChose = new Scanner(System.in);
		System.out.print("choose where you want to attack : ");
		String coord = scChose.next();
		return coord;
	}
	
	public String toString() {
		return this.gameBoard.toString();
	}
}
