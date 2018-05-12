package ovh.lecha.battleship.ai;

import java.util.Stack;

import ovh.lecha.battleship.config.Configuration;
import ovh.lecha.battleship.conversion.StringConvert;
import ovh.lecha.battleship.intern.Player;
import ovh.lecha.battleship.intern.ShotState;

public class AI2 extends AI{

	private Stack<String> shotsStack;
	
	public AI2() {
		this.shotsStack = new Stack<String>();
		int i = 0;
		int j = 0;

		while(i<Configuration.LineLength){
			while(j<Configuration.LineLength) {
				char coordLetter	= 	(char)((int)Configuration.LetterMin + i);
				int coordInt 		=  	Configuration.IntMin + j;
				shotsStack.push(new StringBuilder().append(coordLetter).append(coordInt).toString());
				j = j + 2;
			}
			
			i++;
			if((i%2) == 0) {
				j = 0;
			}else {
				j = 1;
			}
		}
	}
	
	@Override
	public void attack(Player playerTarget) throws Exception {
		String coordAttack = this.choseTarget();
		ShotState shot = this.player.attack(playerTarget, coordAttack);
		System.out.println(shot);
		if(shot == ShotState.HIT) {
			char coordLetter	= StringConvert.getCorrespondingLetter(coordAttack);
			int  coordInt	 	= StringConvert.getCorrespondingInt(coordAttack);

			//up
			if(StringConvert.gridValidity((char)((int)coordLetter - 1), coordInt)) {
				this.shotsStack.push(new StringBuilder().append((char)((int)coordLetter - 1)).append(coordInt).toString());
			}
			//down
			if(StringConvert.gridValidity((char)((int)coordLetter + 1), coordInt)) {
				this.shotsStack.push(new StringBuilder().append((char)((int)coordLetter + 1)).append(coordInt).toString());
			}
			//left
			if(StringConvert.gridValidity(coordLetter, coordInt -1)) {
				this.shotsStack.push(new StringBuilder().append(coordLetter).append(coordInt - 1).toString());
			}
			//right
			if(StringConvert.gridValidity(coordLetter, coordInt +1)) {
				this.shotsStack.push(new StringBuilder().append(coordLetter).append(coordInt + 1).toString());
			}
		}
	}
	
	@Override
	public void attack(AI aiTarget) throws Exception {
		String coordAttack = this.choseTarget();
		ShotState shot = this.player.attack(aiTarget.player, coordAttack);
		if(shot == ShotState.HIT) {
			char coordLetter	= StringConvert.getCorrespondingLetter(coordAttack);
			int  coordInt	 	= StringConvert.getCorrespondingInt(coordAttack);

			//up
			if(StringConvert.gridValidity((char)((int)coordLetter - 1), coordInt)) {
				this.shotsStack.push(new StringBuilder().append((char)((int)coordLetter - 1)).append(coordInt).toString());
			}
			//down
			if(StringConvert.gridValidity((char)((int)coordLetter + 1), coordInt)) {
				this.shotsStack.push(new StringBuilder().append((char)((int)coordLetter + 1)).append(coordInt).toString());
			}
			//left
			if(StringConvert.gridValidity(coordLetter, coordInt - 1)) {
				this.shotsStack.push(new StringBuilder().append(coordLetter).append(coordInt - 1).toString());
			}
			//right
			if(StringConvert.gridValidity(coordLetter, coordInt + 1)) {
				this.shotsStack.push(new StringBuilder().append(coordLetter).append(coordInt + 1).toString());
			}
		}
	}

	@Override
	protected String choseTarget() {
		return this.shotsStack.pop();
	}
}
