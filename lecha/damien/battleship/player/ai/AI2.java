package lecha.damien.battleship.player.ai;

import java.util.Stack;

import lecha.damien.battleship.config.Configuration;
import lecha.damien.battleship.conversion.StringConvert;
import lecha.damien.battleship.intern.ShotState;
import lecha.damien.battleship.player.Player;

public class AI2 extends AI{

	private Stack<String> shotsStack;
	
	public AI2(String name) {
		super(name);
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
	public ShotState attack(Player opponent){
		String coordAttack = this.choseTarget();
		this.shotsStack.pop();
		ShotState shot = null;
		
		try {
			shot = this.gameBoard.attack(opponent.getGameBoard(), coordAttack);
			char coordLetter	= StringConvert.getCorrespondingLetter(coordAttack);
			int  coordInt	 	= StringConvert.getCorrespondingInt(coordAttack);
			
			if(shot == ShotState.HIT) {
				

				//up
				if(StringConvert.gridValidity((char)((int)coordLetter - 1), coordInt)) {
					String up = new StringBuilder().append((char)((int)coordLetter - 1)).append(coordInt).toString();
					if(!this.gameBoard.hasAlreadyFired(up)) {
						this.shotsStack.push(up);
					}
				}
				//down
				if(StringConvert.gridValidity((char)((int)coordLetter + 1), coordInt)) {
					String down = new StringBuilder().append((char)((int)coordLetter + 1)).append(coordInt).toString();
					if(!this.gameBoard.hasAlreadyFired(down)) {
						this.shotsStack.push(down);
					}
				}
				//left
				if(StringConvert.gridValidity(coordLetter, coordInt - 1)) {
					String left = new StringBuilder().append(coordLetter).append(coordInt - 1).toString();
					if(!this.gameBoard.hasAlreadyFired(left)) {
						this.shotsStack.push(left);
					}
				}
				//right
				if(StringConvert.gridValidity(coordLetter, coordInt + 1)) {
					String right = new StringBuilder().append(coordLetter).append(coordInt + 1).toString();
					if(!this.gameBoard.hasAlreadyFired(right)) {
						this.shotsStack.push(right);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return shot;
		
	}

	@Override
	public String choseTarget() {
		return this.shotsStack.peek();
	}
}
