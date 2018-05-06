
public class StringConvert {
	private char letterCoord;
	private int  intCoord;
	
	public StringConvert(String coord) throws Exception {
		if(coord.length() == 2) {
			this.letterCoord = Character.toUpperCase(coord.charAt(0));
			this.intCoord	 = Integer.parseInt(String.valueOf(coord.charAt(1)));
			
		}else if(coord.length() == 3) {
			this.letterCoord = Character.toUpperCase(coord.charAt(0));
			this.intCoord    = Integer.parseInt(new StringBuilder().append(coord.charAt(1)).append(coord.charAt(2)).toString());
		
		}else {
			throw new Exception("invalid coordinates (length)");
		}
	}
	
	public static char getCorrespondingLetter(String coord) throws Exception {
		if(coord.length() == 2) {
			return Character.toUpperCase(coord.charAt(0));
			
		}else if(coord.length() == 3) {
			return Character.toUpperCase(coord.charAt(0));
		
		}else {
			throw new Exception("invalid coordinates (length)");
		}
	}		
	
	
	public static int getCorrespondingInt(String coord) throws Exception {
		if(coord.length() == 2) {
			return Integer.parseInt(String.valueOf(coord.charAt(1)));
			
		}else if(coord.length() == 3) {
			return Integer.parseInt(new StringBuilder().append(coord.charAt(1)).append(coord.charAt(2)).toString());
		
		}else {
			throw new Exception("invalid coordinates (length)");
		}
	}

	public char getLetterCoord() {
		return letterCoord;
	}

	public int getIntCoord() {
		return intCoord;
	}
}
