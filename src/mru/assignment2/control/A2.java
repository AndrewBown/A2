package mru.assignment2.control;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import mru.assignment2.model.*;
import mru.assignment2.view.PrintWordLists;


/**
 * This class is the main class for the program. It reads in the file and then
 * calls the methods to print the results to the output file.
 */
public class A2 {
	private Scanner fileReader;
	private File text;
	private PrintWordLists printWords;
	private SLL<Token> alphabeticalNodeList;
	private SLL<Token> mostUsedNodeList;
	private SLL<Token> leastUsedNodeList;

	private ArrayList<String> stopWords;
	private int totalWordCount;
	private int stopWordWordCount;
	private int uniqueWordCount;
	private final String FILE_PATH = "res/input.txt";

	
	/**
	 * Constructor for the A2 class. It initializes the fileReader, printWords, wordList, and stopWords.
	 * It also reads in the file.
	 */
	public A2() {
		fileReader = new Scanner(System.in);
		printWords = new PrintWordLists();
		alphabeticalNodeList = new SLL<Token>();
		mostUsedNodeList = new SLL<Token>();
		leastUsedNodeList = new SLL<Token>();

		stopWords = new ArrayList<String>(Arrays.asList("a", "about", 
		"all", "am", "an", "and", "any", "are", "as", "at", "be", "been", "but",
		"by", "can", "cannot", "could", "did", "do", "does", "else", "for", "from",
		"get", "got", "had", "has", "have", "he", "her", "hers", "him", "his", "how",
		"i", "if", "in", "into", "is", "it", "its", "like", "more", "me", "my", "no",
		"now", "not", "of", "on", "one", "or", "our", "out", "said", "say", "says",
		"she", "so", "some", "than", "that", "the", "their", "them", "then", "there",
		"these", "they", "this", "to", "too", "us", "upon", "was", "we", "were", "what",
		"with", "when", "where", "which", "while", "who", "whom", "why", "will", "you", "your"));

		try {
			text = new File(FILE_PATH);
			fileReader = new Scanner(text);
		} catch (Exception e) {
			System.out.println("File not found");
		}
	}
	

	/**
	 * Main method for the program. It creates an instance of the A2 class and then calls the checkWords method, and then the printResults method.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		A2 A2Manager = new A2();
		A2Manager.sortAlphabetically();
		A2Manager.sortMostUsedWords();
		A2Manager.sortLeastUsedWords();
		A2Manager.printResults();
	}


	/**
	 * This method calls the print methods from the PrintWordLists class.
	 */
	public void printResults() {
		printWords.totalWordCount(totalWordCount);
		printWords.uniqueWordCount(uniqueWordCount);
		printWords.stopWordCount(stopWordWordCount);

		printWords.mostUsedWords(mostUsedNodeList);
		printWords.leastUsedWords(leastUsedNodeList);
		printWords.allWords(alphabeticalNodeList);		


		// Collections.sort(wordList, new mostUsedWords());

		// Collections.sort(wordList, new LeastUsedWords());
		
		// Collections.sort(wordList);
		// printWords.allWords(wordList);
	}
		
	/**
	 * This method reads the file and checks each word. For each word it updates the totalWordCount, stopWordWordCount, and uniqueWordCount
	 */
    // public void checkWords() {
	// 	SLL<String> allWordsString = new SLL<String>(); //a list of all the words in the file, used to check if the word is already in wordList or not
	// 	SLL<Token> allWordsToken = new SLL<Token>();

	// 	// goes through each line in the file and checks each word in the line to see if it is a stop word or not and then updates the word counts
    // 	while(fileReader.hasNextLine()) {
	// 		String currLine = fileReader.nextLine().strip().toLowerCase().replaceAll("[^A-Za-z ]", "").replaceAll("\s+", " "); 		// used https://stackoverflow.com/questions/7233447/a-regex-to-match-strings-with-alphanumeric-spaces-and-punctuation for help with regex
	// 	if(!(currLine.equals("") || currLine.equals(" "))) {
	// 			for(String individualWordsInLine: currLine.strip().split(" ")) {
	// 				Token word;
	// 				word = new Token(individualWordsInLine, totalWordCount, stopWordWordCount, uniqueWordCount); 

	// 				if(!(allWordsString.contains(word.getWord()))) { // if the word is not in the list of all words then it adds it to the list and updates the word counts

	// 					if(stopWords.contains(word.getWord())) { // if the word is a stop word then it updates the word counts
	// 						word.increaseIndividualWordCount();
	// 						word.increaseStopWordWordCount();
	// 						word.increaseTotalWordCount();
	// 						allWordsString.add(word.getWord());
	// 						allWordsToken.add(word);
	// 					} else { // if the word is not a stop word then it updates the word counts
	// 						word.increaseIndividualWordCount();
	// 						word.increaseTotalWordCount();
	// 						word.increaseUniqueWordCount();
	// 						wordList.add(word);
	// 						allWordsString.add(word.getWord());
	// 						allWordsToken.add(word);
	// 					}
	// 				} else { // if the word is in the list of all words then it updates the word counts
	// 					for(Token wordToCheck: allWordsToken) {
	// 						if(wordToCheck.getWord().equals(word.getWord())) { // if the word is in the list of all words then it updates the word counts
	// 							word.setIndividualWordCount(wordToCheck.getIndividualWordCount() + 1);
	// 							wordToCheck.setIndividualWordCount(word.getIndividualWordCount());
	// 							break;
	// 						}
	// 					}
	// 					word.increaseTotalWordCount();
	// 					if(stopWords.contains(word.getWord())) { // if the word is a stop word then it updates the word counts
	// 						word.increaseStopWordWordCount();
	// 					} 
	// 				}
	// 				totalWordCount = word.getTotalWordCount();
	// 				stopWordWordCount = word.getStopWordWordCount();
	// 				uniqueWordCount = word.getUniqueWordCount();
	// 			}
	// 		}
    // 	}	
	// }

	public void sortAlphabetically() {
		// goes through each line in the file and checks each word in the line to see if it is a stop word or not and then updates the word counts
    	while(fileReader.hasNextLine()) {

			String currLine = fileReader.nextLine().strip().toLowerCase().replaceAll("[^A-Za-z ]", "").replaceAll("\s+", " "); 		// used https://stackoverflow.com/questions/7233447/a-regex-to-match-strings-with-alphanumeric-spaces-and-punctuation for help with regex
			
			if(!(currLine.equals("") || currLine.equals(" "))) {
				for(String individualWordInLine: currLine.strip().split(" ")) {

					if(alphabeticalNodeList.isEmpty()) {

						if(stopWords.contains(individualWordInLine)) {
							stopWordWordCount += 1;
							totalWordCount += 1;
						} else {
							Token wordToken = new Token(individualWordInLine); 
							Node<Token> wordTokenNode = new Node<Token>(wordToken);

							alphabeticalNodeList.add(wordTokenNode.getData());
							uniqueWordCount += 1;
							totalWordCount += 1;
						}
					} else {
						compareWords(individualWordInLine);
					}
				}
			}
    	}
	}


	private void sortMostUsedWords() {
		for(int i = 0; i < alphabeticalNodeList.size(); i++) {
			Node<Token> wordNode = new Node<Token>(alphabeticalNodeList.get(i));
			mostUsedNodeList.sortMostToLeastUsedWords(wordNode);
		}
	}


	private void sortLeastUsedWords() {
		for(int i = 0; i < alphabeticalNodeList.size(); i++) {
			Node<Token> wordNode = new Node<Token>(alphabeticalNodeList.get(i));
			leastUsedNodeList.sortLeastToMostUsedWords(wordNode);
		}
	}


	public void compareWords(String individualWord) {
		Boolean wordMatched = false;

		if(stopWords.contains(individualWord)) {
			stopWordWordCount += 1;
			totalWordCount += 1;
		} else {
			for(int i = 0; i < alphabeticalNodeList.size(); i++) {

				if(alphabeticalNodeList.get(i).getWord().equals(individualWord)) {
					alphabeticalNodeList.get(i).increaseIndividualWordCount();
					alphabeticalNodeList.set(i, alphabeticalNodeList.get(i));
					wordMatched = true;

					break;
				}
			}
			if (!wordMatched) {
				Token wordToken = new Token(individualWord); 
				Node<Token> wordTokenNode = new Node<Token>(wordToken);

				alphabeticalNodeList.addInOrder(wordTokenNode);
				uniqueWordCount += 1;
			}
			totalWordCount += 1;
		}
	}
}
