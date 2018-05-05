public class Configuration {
	public static final char LetterMin  = 'A';
	public static final char LetterMax  = 'J';
	public static final int  IntMin 	= 1;
	public static final int  IntMax 	= 10;
	
	public static final int  LineLength   = (IntMax - IntMin + 1);
	public static final int  ColumnLength = ((int)LetterMax - (int)LetterMin + 1);
}
