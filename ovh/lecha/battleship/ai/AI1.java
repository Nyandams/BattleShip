package ovh.lecha.battleship.ai;

import java.util.ArrayList;
import java.util.Random;

import ovh.lecha.battleship.config.Configuration;

public class AI1 extends AI{
	private ArrayList<String> shotsPossibility;
	
	public AI1() {
		this.shotsPossibility = new ArrayList<String>();
		
		for(int i = 0; i < Configuration.LineLength; i++) {
			for(int j = 0; j < Configuration.LineLength; j++) {
				
				char coordLetter	= 	(char)((int)Configuration.LetterMin + i);
				int coordInt 		=  	Configuration.IntMin + j;
				shotsPossibility.add(new StringBuilder().append(coordLetter).append(coordInt).toString());
			}
		}
	}
	
	@Override
	protected String choseTarget() {
		Random rand = new Random();
		return this.shotsPossibility.remove(rand.nextInt(this.shotsPossibility.size()));		
	}
}
