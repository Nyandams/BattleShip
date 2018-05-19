package lecha.damien;

import java.util.Scanner;

import lecha.damien.battleship.config.Configuration;
import lecha.damien.battleship.player.Player;
import lecha.damien.battleship.player.ai.AI0;
import lecha.damien.battleship.player.ai.AI1;
import lecha.damien.battleship.player.ai.AI2;
import lecha.damien.battleship.player.human.Human;

public class BattleShip {

	public static void main(String[] args) {
		boolean play = true;

		Player[] tabPlayer = new Player[2];
		tabPlayer[0] = new Human("Player 1");
		Scanner sc = new Scanner(System.in);

		System.out.print("Chose the type of the second player (HUMAN/AI0/AI1/AI2) : ");
		String inputPlayerType = sc.next();
		boolean testInput = false;
		
		while (!testInput) {
			if (inputPlayerType.equals("HUMAN")) {
				tabPlayer[1] = new Human("Player 2");
				testInput = true;
			} else if (inputPlayerType.equals("AI0")) {
				tabPlayer[1] = new AI0("Player 2");
				testInput = true;

			} else if (inputPlayerType.equals("AI1")) {
				tabPlayer[1] = new AI1("Player 2");
				testInput = true;
			} else if (inputPlayerType.equals("AI2")) {
				tabPlayer[1] = new AI2("Player 2");
				testInput = true;
			} else {
				System.out.print("Chose the type of the second player (HUMAN/AI0/AI1/AI2) : ");
				inputPlayerType = sc.next();
			}
		}
		testInput = false;

		int cptGame = 0;
		while (play) {

			System.out.println("\n");

			for (int i = 0; i < tabPlayer.length; i++) {
				System.out.println(tabPlayer[i].getName() + ", you have to place your ships");
				for (int shipSize : Configuration.shipLength) {
					System.out.println("Place a ship which the size is : " + shipSize);
					tabPlayer[i].placeShip(shipSize);
					System.out.println("placed !");
				}
				System.out.println("fleet in place !" + "\n");
			}

			int i = 0;

			Player current = tabPlayer[i % 2];
			Player opponent = tabPlayer[(i + 1) % 2];

			while (current.isAlive()) {
				System.out.println("\n" + "___________________________________");
				System.out.println(current.getName() + " : It's your turn to play ! ");

				if (current instanceof Human) {
					System.out.println(current.toString());
				}

				switch (current.attack(opponent)) {
				case ALREADY:
					System.out.println("you already attacked here");
					break;

				case MISS:
					System.out.println("MISS !");
					break;

				case HIT:
					System.out.println("HIT !");
					break;

				case SINK:
					System.out.println("SINK !");
					break;
				}

				i++;
				current = tabPlayer[i % 2];
				opponent = tabPlayer[(i + 1) % 2];
			}

			System.out.println(opponent.getName() + " WON !" + "\n");

			System.out.println("Do you want to replay ? (Y/N)");
			String inputWant = sc.next();

			while (!testInput) {
				if (inputWant.equals("Y")) {
					if((cptGame%2) == 0) {
						tabPlayer[1] = new Human("Player 1");
						if (inputPlayerType.equals("HUMAN")) {
							tabPlayer[0] = new Human("Player 2");
						} else if (inputPlayerType.equals("AI0")) {
							tabPlayer[0] = new AI0("Player 2");
						} else if (inputPlayerType.equals("AI1")) {
							tabPlayer[0] = new AI1("Player 2");
						} else if (inputPlayerType.equals("AI2")) {
							tabPlayer[0] = new AI2("Player 2");
						}
					}else {
						tabPlayer[0] = new Human("Player 1");
						if (inputPlayerType.equals("HUMAN")) {
							tabPlayer[1] = new Human("Player 2");
						} else if (inputPlayerType.equals("AI0")) {
							tabPlayer[1] = new AI0("Player 2");
						} else if (inputPlayerType.equals("AI1")) {
							tabPlayer[1] = new AI1("Player 2");
						} else if (inputPlayerType.equals("AI2")) {
							tabPlayer[1] = new AI2("Player 2");
						}
					}
					
					
					
					testInput = true;
					cptGame++;

				} else if (inputWant.equals("N")) {
					play = false;
					testInput = true;

				} else {
					System.out.print("Do you want to replay ? (Y/N)");
					inputPlayerType = sc.next();
				}
			}
		}
		System.out.println("END OF THE GAME !");
		sc.close();
	}

}
