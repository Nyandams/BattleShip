package ovh.lecha.battleship;

import java.util.Scanner;
import ovh.lecha.battleship.ai.AI;
import ovh.lecha.battleship.ai.AI0;
import ovh.lecha.battleship.ai.AI1;
import ovh.lecha.battleship.ai.AI2;
import ovh.lecha.battleship.config.Configuration;

public class Battleship {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		
		
		/* AI VS AI */ 
		
		AI[] tabAI = new AI[2];
		
		System.out.print("Chose the 1st AI lvl : ");
		String inputLvl1 = sc.next();
		
		System.out.print("Chose the 2nd AI lvl : ");
		String inputLvl2 = sc.next();
		

		int cptWin1 = 0;
		int cptWin2 = 0;
		int i;
		for(int run = 0; run <100; run++) {	
			i = 0;
			
			switch(inputLvl1) {
				case "0": 	tabAI[0] = new AI0();
							break;
						
					
				case "1": 	tabAI[0] = new AI1();
							break;
				
				case "2":	tabAI[0] = new AI2();
							break;
			}
			
			switch(inputLvl2) {
				case "0": 	tabAI[1] = new AI0();
							break;
						
					
				case "1": 	tabAI[1] = new AI1();
							break;
				
				case "2":	tabAI[1] = new AI2();
							break;
			}
		
			for(AI ai : tabAI) {
				for(int s : Configuration.shipLength) {
					ai.placeShip(s);
				}
			}
			
			
			
			AI current  = tabAI[i%2];
			AI opponent = tabAI[(i+1)%2];
			
			while(current.getPlayer().alive()) {
				//System.out.println("AI " + (((i)%2)+1) );
				try {
					current.attack(opponent);
				} catch (Exception e) {
					e.getMessage();
				}
				
				//System.out.println(current.getPlayer().toString());
				
				i++;
				current  = tabAI[i%2];
				opponent = tabAI[(i+1)%2];
			}
			//System.out.println(opponent.getPlayer().toString());
			
			if((((i+1)%2)+1) == 1) {
				cptWin1++;
			}else {
				cptWin2++;
			}
			
			System.out.println("AI " + (((i+1)%2)+1) + " WON in " + i/2 + " shots");
			
		}
			
		System.out.println("AI 1 wins : " + cptWin1);
		System.out.println("AI 2 wins : " + cptWin2);
		
		
		sc.close();
	}
	
	
	
}
