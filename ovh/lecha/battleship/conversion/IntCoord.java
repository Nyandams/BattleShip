package ovh.lecha.battleship.conversion;
import ovh.lecha.battleship.config.Configuration;

public class IntCoord {
	private int line;
	private int column;
	
	public IntCoord(String stringCoord) throws Exception {
		char coordLetter;
		int  coordInt;
		
		if(stringCoord.length() == 2) {
			coordLetter = Character.toUpperCase(stringCoord.charAt(0));
			coordInt	= Integer.parseInt(String.valueOf(stringCoord.charAt(1)));
			
		}else if(stringCoord.length() == 3) {
			coordLetter = Character.toUpperCase(stringCoord.charAt(0));
			coordInt	=  Integer.parseInt(new StringBuilder().append(stringCoord.charAt(1)).append(stringCoord.charAt(2)).toString());
			
		}else {
			throw new Exception("invalid coordinates (length)");
		}
		this.column   = getCorrespondingLine(coordLetter);
		this.line = getCorrespondingColumn(coordInt);
		
	}
	
	
	private static int getCorrespondingLine(char line) {
		return (char)(line - Configuration.LetterMin);
	}
	
	private static int getCorrespondingColumn(int column) {
		return column - Configuration.IntMin;
	}


	public int getLine() {
		return line;
	}


	public int getColumn() {
		return column;
	}
	
}
