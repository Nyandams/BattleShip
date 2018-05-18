package lecha.damien.battleship.ai;

import java.util.Stack;

import lecha.damien.battleship.config.Configuration;
import lecha.damien.battleship.conversion.StringConvert;
import lecha.damien.battleship.intern.Player;
import lecha.damien.battleship.intern.ShotState;

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
				String up = new StringBuilder().append((char)((int)coordLetter - 1)).append(coordInt).toString();
				if(!this.player.hasAlreadyFired(up)) {
					this.shotsStack.push(up);
				}
			}
			//down
			if(StringConvert.gridValidity((char)((int)coordLetter + 1), coordInt)) {
				String down = new StringBuilder().append((char)((int)coordLetter + 1)).append(coordInt).toString();
				if(!this.player.hasAlreadyFired(down)) {
					this.shotsStack.push(down);
				}
			}
			//left
			if(StringConvert.gridValidity(coordLetter, coordInt - 1)) {
				String left = new StringBuilder().append(coordLetter).append(coordInt - 1).toString();
				if(!this.player.hasAlreadyFired(left)) {
					this.shotsStack.push(left);
				}
			}
			//right
			if(StringConvert.gridValidity(coordLetter, coordInt + 1)) {
				String right = new StringBuilder().append(coordLetter).append(coordInt + 1).toString();
				if(!this.player.hasAlreadyFired(right)) {
					this.shotsStack.push(right);
				}
			}
		}
	}

	@Override
	protected String choseTarget() {
		return this.shotsStack.pop();
	}
}
