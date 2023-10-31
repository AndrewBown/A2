package mru.assignment2.model;

/**
 * This class is used to store the information about each word.
 */
public class Token implements Comparable<Token>{

	int individualWordCount;
	String word;

	/**
	 * Constructor for the Token class. It initializes the word and individual word count.
	 * @param word String representing the word
	 */	
	public Token(String word) {
		this.word = word;
		individualWordCount = 1;
	}


	/**
	 * This method overrides the equals method. It checks if the word is equal to the word in the Token object.
	 * @param obj Object to be compared
	 * @return boolean representing if the words are equal
	 */
	@Override
	public boolean equals(Object obj) {
		Token t = (Token) obj;
		return this.word.equals(t.getWord());
	}
	

	/**
	 * This method overrides the compareTo method. It compares the word in the Token object to the word in the Token object passed.
	 * @param t Token object to be compared
	 * @return int representing the comparison
	 */
	@Override
	public int compareTo(Token t) {
        return this.word.compareTo(t.word);
    }
		

	/**
	 * when called returns the String "word" in the Token object
	 * @return String representing the word
	 */
	public String getWord() {
        return word;
    }
	
	/**
	 * when called increases the count of individualWordCount in the Token object
	 */
	public void increaseIndividualWordCount() {
		individualWordCount += 1;
	}
	/**
	 * when called sets the individualWordCount in the Token object
	 */
	public void setIndividualWordCount(int individualWordCount) {
		this.individualWordCount = individualWordCount;
	}
	/**
	 * when called returns the individualWordCount in the Token object
	 * @return int representing the individual word count
	 */
	public int getIndividualWordCount() {
		return individualWordCount;
	}


	/**
	 * when called returns the String "word" and the int "individualWordCount" in the Token object
	 * @return String representing the word and the individual word count
	 */
	public String toString() {
		return word + " : " + individualWordCount;
	}
}
