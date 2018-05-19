package lecha.damien;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import lecha.damien.battleship.config.Configuration;
import lecha.damien.battleship.player.ai.AI;
import lecha.damien.battleship.player.ai.AI0;
import lecha.damien.battleship.player.ai.AI1;
import lecha.damien.battleship.player.ai.AI2;

public class TestIA {
	public static void main(String[] args) {

		StringBuffer sb = new StringBuffer();
		sb.append("AI Name;score;AI Name2;score2\n");

		/* AI VS AI */
		for (int lvl = 0; lvl < 3; lvl++) {
			AI[] tabAI = new AI[2];

			switch (lvl) {
			case 0:
				tabAI[0] = new AI0("Beginner"); // Beginner
				tabAI[1] = new AI1("Medium"); // Medium
				break;

			case 1:
				tabAI[0] = new AI0("Beginner"); // Beginner
				tabAI[1] = new AI2("Hard"); // Hard
				break;

			case 2:
				tabAI[0] = new AI1("Medium"); // Medium
				tabAI[1] = new AI2("Hard"); // Hard
				break;

			}

			int cptWin1 = 0;
			int cptWin2 = 0;
			int i;

			for (int run = 0; run < 100; run++) {
				i = 0;

				for (AI ai : tabAI) {
					for (int s : Configuration.shipLength) {
						ai.placeShip(s);
					}
				}

				AI current = tabAI[run % 2];
				AI opponent = tabAI[(run + 1) % 2];

				while (current.isAlive()) {

					current.attack(opponent);

					i++;
					current = tabAI[i % 2];
					opponent = tabAI[(i + 1) % 2];
				}

				if ((((i + 1) % 2) + 1) == 1) {
					cptWin1++;
				} else {
					cptWin2++;
				}

				switch (lvl) {
				case 0:
					tabAI[0] = new AI0("Beginner"); // Beginner
					tabAI[1] = new AI1("Medium"); // Medium
					break;

				case 1:
					tabAI[0] = new AI0("Beginner"); // Beginner
					tabAI[1] = new AI2("Hard"); // Hard
					break;

				case 2:
					tabAI[0] = new AI1("Medium"); // Medium
					tabAI[1] = new AI2("Hard"); // Hard
					break;

				}

			}
			switch (lvl) {
			case 0:
				sb.append("AI Level Beginner;" + cptWin1 + ";Level Medium;" + cptWin2 + "\n");
				break;

			case 1:
				sb.append("AI Level Beginner;" + cptWin1 + ";Level Hard;" + cptWin2 + "\n");
				break;

			case 2:
				sb.append("AI Level Medium;" + cptWin1 + ";Level Hard;" + cptWin2 + "\n");
				break;

			}

		}

		try {
			PrintWriter pw = new PrintWriter(new File("ai_proof.csv"));
			pw.write(sb.toString());
			pw.close();
		} catch (FileNotFoundException e1) {
			System.err.println("the csv file failed and wasn't found !");
		}
	}

}
