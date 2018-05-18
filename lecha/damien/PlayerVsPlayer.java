package lecha.damien;

import java.util.Scanner;

import lecha.damien.battleship.intern.Player;

public class PlayerVsPlayer {

	public static void main(String[] args) {
		Player[] tabPlayer = new Player[2];
		tabPlayer[0] = new Player();
		tabPlayer[1] = new Player();
		Scanner sc = new Scanner(System.in);
		
		String inputStart;
		String inputEnd;
		int i = 0;
		boolean testScan = false;
		
		for(Player p : tabPlayer) {
			System.out.println("Player " + (((i)%2)+1) );
			/*System.out.println(p.fleetToString());
			while(!testScan) {
				System.out.println("Place a carrier(length : 5) : ");
				System.out.print("Start Coordinates : ");
				inputStart = sc.next();
				System.out.print("End Coordinates : ");
				inputEnd = sc.next();
				
				try {
					p.addShip(inputStart, inputEnd, 5);
					testScan = true;
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			testScan = false;
			System.out.println(p.fleetToString());
			
			while(!testScan) {
				System.out.println("Place a BattleShip(length : 4) : ");
				System.out.print("Start Coordinates : ");
				inputStart = sc.next();
				System.out.print("End Coordinates : ");
				inputEnd = sc.next();
				
				try {
					p.addShip(inputStart, inputEnd, 4);
					testScan = true;
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			testScan = false;
			System.out.println(p.fleetToString());
			
			while(!testScan) {
				System.out.println("Place a Cruiser(length : 3) : ");
				System.out.print("Start Coordinates : ");
				inputStart = sc.next();
				System.out.print("End Coordinates : ");
				inputEnd = sc.next();
				
				try {
					p.addShip(inputStart, inputEnd, 3);
					testScan = true;
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			testScan = false;
			System.out.println(p.fleetToString());
			
			
			while(!testScan) {
				System.out.println("Place a Submarine(length : 3) : ");
				System.out.print("Start Coordinates : ");
				inputStart = sc.next();
				System.out.print("End Coordinates : ");
				inputEnd = sc.next();
				
				try {
					p.addShip(inputStart, inputEnd, 3);
					testScan = true;
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			testScan = false;*/
			System.out.println(p.fleetToString());
			
			while(!testScan) {
				System.out.println("Place a Destroyer(length : 2) : ");
				System.out.print("Start Coordinates : ");
				inputStart = sc.next();
				System.out.print("End Coordinates : ");
				inputEnd = sc.next();
				
				try {
					p.addShip(inputStart, inputEnd, 2);
					testScan = true;
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			testScan = false;
			System.out.println(p.fleetToString());
			
			i++;
		}
		
		
		
		Player current  = tabPlayer[i%2];
		Player opponent = tabPlayer[(i+1)%2];
		String coord;
		while(current.alive()) {
			System.out.println(current.toString());
			
			
			while(!testScan) {
				System.out.println("Player " + (((i)%2)+1) );
				System.out.print("choose where you want to attack : ");
				coord = sc.next();
				
				try {
					switch(current.attack(opponent, coord)) {
						case ALREADY : 	System.out.println("you already attacked here");
								 		testScan = true;
								 		break;
						
						case MISS : 	System.out.println("MISS !");
								 		testScan = true;
								 		break;
						
						case HIT :		System.out.println("HIT !");
								 		testScan = true;
								 		break;
								 
						case SINK : 	System.out.println("SINK !");
								 		testScan = true;
								 		break;
					}
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			testScan = false;
			

			
			i++;
			current  = tabPlayer[i%2];
			opponent = tabPlayer[(i+1)%2];
		}
		sc.close();
		System.out.println("Player " + (((i+1)%2)+1) + " WON !");
	}
}
