package ovh.lecha.battleship.ai;

import java.util.Random;
import ovh.lecha.battleship.config.Configuration;

public class AI0 extends AI{

	@Override
	protected String choseTarget() {
		Random rn = new Random();
		
		char coordLetter	= 	(char)((int)Configuration.LetterMin + rn.nextInt(Configuration.ColumnLength));
		int coordInt 		=  	Configuration.IntMin + rn.nextInt(Configuration.LineLength);
		String coord		=	new StringBuilder().append(coordLetter).append(coordInt).toString();
		return coord;
	}

}
