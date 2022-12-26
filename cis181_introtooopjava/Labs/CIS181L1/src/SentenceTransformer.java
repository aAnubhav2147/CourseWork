public class SentenceTransformer {
	
	public static void main(String[] args) {
		String inString0="Let's ask that dude Fred.";
		String inString1="Who said it is my turn to look for one?";
		String inString2 = "Take the computer from Janet!";
		String randomString = "kool";
		
		//Output expected - Janet said Fred is one kool dude!
		
		String temp1 = SentenceTransformer.depunctuate(inString2);
		for (int i=1;i<6;i++) temp1=SentenceTransformer.lastWords(temp1);
		//System.out.println(temp1); //Janet
		
		String temp2 = SentenceTransformer.depunctuate(inString0);
		for (int i=1;i<5;i++) temp2=SentenceTransformer.lastWords(temp2);
		temp2 = WordTransformer.leftPadder(temp2);
		//System.out.println(temp2); //Fred
		
		String temp3 = SentenceTransformer.depunctuate(inString0);
		for (int i=1;i<4;i++) temp3=SentenceTransformer.lastWords(temp3);
		temp3 = SentenceTransformer.firstWord(temp3);
		temp3 = WordTransformer.leftPadder(temp3);
		//System.out.println(temp3);//dude
		
		String temp4 = SentenceTransformer.depunctuate(inString1);
		for (int i=1;i<10;i++) temp4=SentenceTransformer.lastWords(temp4);
		//temp4 = SentenceTransformer.firstWord(temp4);
		//System.out.println(temp4);//one
		
		String temp5 = WordTransformer.leftPadder(randomString);
		//System.out.println(temp5);
		
		String temp6 = WordTransformer.rightPadder(temp4, temp5);
		//System.out.println(temp6); //create one kool
		
		String temp7 = WordTransformer.rightPadder(temp6, temp3);
		temp7 = WordTransformer.rightPadder(temp7, "!");
		temp7 = WordTransformer.leftPadder(temp7);
		//System.out.println(temp7); //create " one kool dude!"
		
		String temp8 = SentenceTransformer.depunctuate(inString1);
		for (int i=1;i<2;i++) temp8=SentenceTransformer.lastWords(temp8);
		temp8 = SentenceTransformer.firstWord(temp8);
		temp8 = WordTransformer.leftPadder(temp8);
		//System.out.println(temp8); // said
		
		String temp9 = SentenceTransformer.depunctuate(inString1);
		for (int i=1;i<4;i++) temp9=SentenceTransformer.lastWords(temp9);
		temp9 = SentenceTransformer.firstWord(temp9);
		temp9 = WordTransformer.leftPadder(temp9);
		//System.out.println(temp9); // is
		
		String temp10 = WordTransformer.rightPadder(temp1, temp8);
		//System.out.println(temp10); //Janet said 
		
		String temp11 = WordTransformer.rightPadder(temp10, temp2);
		//System.out.println(temp11);//Janet said Fred
		
		String temp12 = WordTransformer.rightPadder(temp11, temp9);
		//System.out.println(temp12);//Janet said Fred is
		
		String output = WordTransformer.rightPadder(temp12, temp7);
		System.out.println(output);
			
	}
	
	static String firstWord (String thePhrase) {
		int i=thePhrase.indexOf(" ");
		if (i>0) {return thePhrase.substring(0,i);}
		else {return thePhrase;}
	}

	static String lastWords (String thePhrase) {
		int i=thePhrase.indexOf(" ");
		if (i>0) {return thePhrase.substring(i+1,thePhrase.length());}
		else {return thePhrase;}
	}

	static String reverseSentence(String thePhrase){
		return null;
	}
	
	static String depunctuate (String thePhrase){
		thePhrase=thePhrase.replace('.',' ');
		thePhrase=thePhrase.replace('!',' ');
		thePhrase=thePhrase.replace('?',' ');
		thePhrase=thePhrase.substring(0,thePhrase.length()-1);
		return thePhrase;
	}
}


