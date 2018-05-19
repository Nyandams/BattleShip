package lecha.damien.battleship.player.ai;

import java.util.Random;

import lecha.damien.battleship.config.Configuration;

public class AI0 extends AI{
	
	public AI0(String name) {
		super(name);
	}

	@Override
	public String choseTarget() {
		Random rn = new Random();
		
		char coordLetter	= 	(char)((int)Configuration.LetterMin + rn.nextInt(Configuration.ColumnLength));
		int coordInt 		=  	Configuration.IntMin + rn.nextInt(Configuration.LineLength);
		String coord		=	new StringBuilder().append(coordLetter).append(coordInt).toString();
		return coord;
	}

}
