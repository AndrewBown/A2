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
	 * Constructor for the A2 class. It initializes the fileReader, printWords, alphabeticalNodeList, mostUsedNodeList, leastUsedNodeList, and stopWords.
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
	 * Main method for the program. It creates an instance of the A2 class and then calls the sortAlphabetically, sortMostUsedWords, sortLeastUsedWords, and printResults methods.
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
		printWords.wordCounts(totalWordCount, "Total Words");
		printWords.wordCounts(uniqueWordCount, "Unique Words");
		printWords.wordCounts(stopWordWordCount, "Stop Words");

		printWords.usedWordsList(mostUsedNodeList, "Most");
		printWords.usedWordsList(leastUsedNodeList, "Least");
		printWords.usedWordsList(alphabeticalNodeList, "All");		
	}
		

	/**
	 * This method sorts the words alphabetically and then updates the word counts.
	 */
	public void sortAlphabetically() {
    	while(fileReader.hasNextLine()) {
			String currLine = fileReader.nextLine().strip().toLowerCase().replaceAll("[^A-Za-z ]", "").replaceAll("\s+", " "); 		// used https://stackoverflow.com/questions/7233447/a-regex-to-match-strings-with-alphanumeric-spaces-and-punctuation for help with regex
			
			if(!(currLine.equals("") || currLine.equals(" "))) {
				for(String individualWordInLine: currLine.strip().split(" ")) {		// goes through each word in the line and checks if it is a stop word or not

					if(alphabeticalNodeList.isEmpty()) {		// if the alphabeticalNodeList is empty, add the word to the list

						if(stopWords.contains(individualWordInLine)) {		// check if the word is a stop word
							stopWordWordCount += 1;
							totalWordCount += 1;
						} else {								// if it is not a stop word, create a new node for the word and add it to the alphabetical node list
							Token wordToken = new Token(individualWordInLine); 
							Node<Token> wordTokenNode = new Node<Token>(wordToken);		// turn word into a node so it can be added to the list

							alphabeticalNodeList.add(0, wordTokenNode.getData()); 			// add the word to the alphabeticalNodeList
							uniqueWordCount += 1;
							totalWordCount += 1;
						}
					} else {
						compareWords(individualWordInLine);		// compare the word to the words in the alphabeticalNodeList and add it to the list in the correctly ordered place if it is not already in the list
					}
				}
			}
    	}
	}


	/**
	 * This method sorts the words from most used to least used.
	 */
	private void sortMostUsedWords() {
		for(int i = 0; i < alphabeticalNodeList.size(); i++) {			// goes through the alphabeticalNodeList and adds the nodes to the mostUsedNodeList in order of most used to least used
			Node<Token> wordNode = new Node<Token>(alphabeticalNodeList.get(i));
			mostUsedNodeList.addInOrder(wordNode, "mostUsed");
		}
	}


	/**
	 * This method sorts the words from least used to most used.
	 */
	private void sortLeastUsedWords() {
		for(int i = 0; i < alphabeticalNodeList.size(); i++) {		// goes through each word in the alphabeticalNodeList and adds it to the leastUsedNodeList in order of least used to most used
			Node<Token> wordNode = new Node<Token>(alphabeticalNodeList.get(i));
			leastUsedNodeList.addInOrder(wordNode, "leastUsed");
		}
	}


	/**
	 * This method compares the words to the words in the alphabeticalNodeList and updates the word counts.
	 * @param individualWord String representing the word to be compared
	 */
	public void compareWords(String individualWord) {
		Boolean wordMatched = false;

		if(stopWords.contains(individualWord)) {			// check if the word is a stop word
			stopWordWordCount += 1;
			totalWordCount += 1;
		} else {
			for(int i = 0; i < alphabeticalNodeList.size(); i++) {				// if it is not a stop word, check if it already exists in the alphabetical node list
				if(alphabeticalNodeList.get(i).getWord().equals(individualWord)) {
					alphabeticalNodeList.get(i).increaseIndividualWordCount();
					alphabeticalNodeList.set(i, alphabeticalNodeList.get(i));
					wordMatched = true;			// matched set to true if the word is found
					break;
				}
			}
			if (!wordMatched) {				// if the word is not found, create a new node for the word and add it to the alphabetical node list
				Token wordToken = new Token(individualWord); 
				Node<Token> wordTokenNode = new Node<Token>(wordToken);
				alphabeticalNodeList.addInOrder(wordTokenNode, "alphabetical");
				uniqueWordCount += 1;
			}
			totalWordCount += 1;
		}
	}
}
