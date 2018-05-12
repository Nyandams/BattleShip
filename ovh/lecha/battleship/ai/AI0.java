package ovh.lecha.battleship.ai;

import java.util.Random;
import ovh.lecha.battleship.config.Configuration;

public class AI0 extends AI{

	@Override
	public String choseTarget() {
		Random rn = new Random();
		
		char coordLetter	= 	(char)((int)Configuration.LetterMin + rn.nextInt(Configuration.ColumnLength));
		int coordInt 		=  	Configuration.IntMin + rn.nextInt(Configuration.LineLength);
		String coord		=	new StringBuilder().append(coordLetter).append(coordInt).toString();
		return coord;
	}

	public static void main(String[] args) throws Exception {
		//AI0 ai = new AI0();
		AI0 ai1 = new AI0();
		AI0 ai2 = new AI0();
		//System.out.println(ai.player.fleetToString());
		/*ai.placeShip(3);
		
		ai.placeShip(4);
		
		ai.placeShip(3);
		
		ai.placeShip(5);
		
		ai.placeShip(2);*/
		ai2.getPlayer().addShip("A1", "A2", 2);
		
		String atck;
		/*
		ai1.getPlayer().attack(ai2.getPlayer(), "A1");
		ai1.getPlayer().attack(ai2.getPlayer(), "A2");
		ai1.getPlayer().attack(ai2.getPlayer(), "A1");
		ai1.getPlayer().attack(ai2.getPlayer(), "A1");
		*/
		for(int i =0; i<1000; i++) {
			ai1.attack(ai2);
		}
		System.out.println(ai1.player.toString());
		
		/*for(int i =0; i<100; i++) {
			System.out.println(ai.choseTarget());
		}*/
	}

}
