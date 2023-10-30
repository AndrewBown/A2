package mru.assignment2.model;

/**
 * This class is used to store the information about each word.
 */
public class Token implements Comparable<Token>{

	int individualWordCount;
	//int totalWordCount;
	//int stopWordWordCount;
	//int uniqueWordCount;
	String word;

	/**
	 * Constructor for the Token class. It initializes the word, totalWordCount, stopWordWordCount, and uniqueWordCount.
	 * @param word String representing the word
	 * @param totalWordCount int representing the total number of words
	 * @param stopWordWordCount int representing the number of stop words
	 * @param uniqueWordCount int representing the number of unique words
	 */	
	public Token(String word/*, int totalWordCount, int stopWordWordCount, int uniqueWordCount*/) {
		this.word = word;
		individualWordCount = 1;
		//this.totalWordCount = totalWordCount;
		//this.stopWordWordCount = stopWordWordCount;
		//this.uniqueWordCount = uniqueWordCount;
	}


	/**
	 * This method overrides the equals method. It checks if the word is equal to the word in the Token object.
	 * @param obj Object to be compared
	 * @return boolean representing if the words are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Token)) {
			return false;
		}
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
	 * @param individualWordCount int representing the individual word count
	 */
	public void setIndividualWordCount(int individualWordCount) {
		this.individualWordCount = individualWordCount;
	}
	/**
	 * when called sets the individualWordCount in the Token object
	 */
	public int getIndividualWordCount() {
		return individualWordCount;
	}


	public String toString() {
		return word + " : " + individualWordCount;
	}


	// /**
	//  * when called increases the count of totalWordCount in the Token object
	//  */
	// public void increaseTotalWordCount() {
	// 	totalWordCount += 1;
	// }


	// /**
	//  * when called returns the totalWordCount in the Token object
	//  * @return int representing the total word count
	//  */
	// public int getTotalWordCount() {
	// 	return totalWordCount;
	// }


	// /**
	//  * when called increases the count of stopWordWordCount in the Token object
	//  */
	// public void increaseStopWordWordCount() {
	// 	stopWordWordCount += 1;
	// }


	// /**
	//  * when called returns the stopWordWordCount in the Token object
	//  * @return int representing the stop word count
	//  */
	// public int getStopWordWordCount() {
	// 	return stopWordWordCount;
	// }


	// /**
	//  * when called increases the count of uniqueWordCount in the Token object
	//  */
	// public void increaseUniqueWordCount() {
	// 	uniqueWordCount += 1;
	// }


	// /**
	//  * when called returns the uniqueWordCount in the Token object
	//  * @return int representing the unique word count
	//  */
	// public int getUniqueWordCount() {
	// 	return uniqueWordCount;
	// }
}
