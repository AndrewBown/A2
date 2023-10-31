package mru.assignment2.view;

import mru.assignment2.control.SLL;
import mru.assignment2.model.Token;

/**
 * This class is used to print the words and lists of the program.
 */
public class PrintWordLists {
	/**
	 * Prints the total word counts for the specified wordCountType to the console.
	 * @param totalWordCount The total number of words.
	 * @param wordCountType The type of word count to print.
	 */
	public void wordCounts(int totalWordCount, String wordCountType) {
		System.out.println(wordCountType + ": " + totalWordCount);
	}


	/**
	 * Prints the used words for the specified listType and their individual counts to the console.
	 * @param wordList The sorted list to print out.
	 * @param listType The type of list to print.
	 */
    public void usedWordsList(SLL<Token> wordList, String listType) {
		System.out.println();
		if(listType == "Most" || listType == "Least") {
			System.out.println("10 " + listType + " Frequent");

			for(int i = 0; i < 10; i++) {
				if(i < wordList.size()) {			//prints the first 10 words in the list
					System.out.println(wordList.get(i).toString());
				}
			}
		} else if(listType == "All") {
			System.out.println(listType);
			for(int i = 0; i < wordList.size(); i++) {		//prints all the words in the list
				System.out.println(wordList.get(i).toString());
			}
		}
	}
}
