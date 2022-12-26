public class WordTransformer {
	
	static String makeWord (String theWord){
		return theWord.trim();
	}

	static String makeCapital (String theWord){
		return theWord.substring(0,1).toUpperCase()+theWord.substring(1);
	}

	static String lowerCase (String theWord){
		return theWord.substring(0,1).toLowerCase()+theWord.substring(1);
	}

	static String upperCaser (String thePhrase) {
		return thePhrase.toUpperCase();
	}

	static String lowerCaser (String thePhrase) {
		return thePhrase.toLowerCase();
	}
	
	static String leftPadder(String theWord){
		return " "+theWord;
	}
	
	static String rightPadder(String theWord, String padding){
		return theWord+padding;
	}

	static String reverseWord (String theWord) {
		String newString="";
		theWord=theWord.trim();
		int i=theWord.length();
		while (i>0) {
			newString=newString+theWord.substring(i-1);
			theWord=theWord.substring(0,i-1);
			i=theWord.length();
		}
		return newString;
	}
		
}
