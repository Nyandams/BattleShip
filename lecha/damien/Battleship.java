package lecha.damien;

import java.util.Scanner;

import lecha.damien.battleship.ai.AI;
import lecha.damien.battleship.ai.AI0;
import lecha.damien.battleship.ai.AI1;
import lecha.damien.battleship.ai.AI2;
import lecha.damien.battleship.config.Configuration;

public class Battleship {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
			
		/* AI VS AI */ 
		
		AI[] tabAI = new AI[2];
		boolean testInput = false;
		String inputLvl1 = "0";
		String inputLvl2 = "0";
		while(!testInput) {
			System.out.print("Chose the 1st AI lvl : ");
			inputLvl1 = sc.next();
			
			System.out.print("Chose the 2nd AI lvl : ");
			inputLvl2 = sc.next();
			
			
			switch(inputLvl1) {
				case "0": 	tabAI[0] = new AI0();
							testInput = true;
							break;
						
					
				case "1": 	tabAI[0] = new AI1();
							testInput = true;
							break;
				
				case "2":	tabAI[0] = new AI2();
							testInput = true;
							break;
							
				default :	break;
			}
			
			switch(inputLvl2) {
				case "0": 	tabAI[1] = new AI0();
							break;
					
				case "1": 	tabAI[1] = new AI1();
							break;
				
				case "2":	tabAI[1] = new AI2();
							break;
							
				default :	break;
			}
		}
		
		int cptWin1 = 0;
		int cptWin2 = 0;
		int i;
		
		for(int run = 0; run <1000; run++) {
			i = 0;
			
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
			
		}
			
		System.out.println("AI 1 wins : " + cptWin1);
		System.out.println("AI 2 wins : " + cptWin2);
		
		
		sc.close();
	}
	
	
	
}
