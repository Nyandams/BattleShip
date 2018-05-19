package lecha.damien.battleship.player.ai;

import java.util.ArrayList;
import java.util.Random;

import lecha.damien.battleship.config.Configuration;

public class AI1 extends AI{
	private ArrayList<String> shotsPossibility;
	
	public AI1(String name) {
		super(name);
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
	public String choseTarget() {
		Random rand = new Random();
		return this.shotsPossibility.remove(rand.nextInt(this.shotsPossibility.size()));		
	}
}
