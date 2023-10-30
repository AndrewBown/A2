package mru.assignment2.sorting;

import java.util.Comparator;

import mru.assignment2.model.Token;

/**
 * This class is used to sort the words by the number of times they appear in the file least to most.
 * @return int representing whether the word is greater than, less than, or equal to the word in the Token object passed
 */
public class LeastUsedWords implements Comparator<Token> {
    
    public int compare(Token t1, Token t2) {

        if (t1.getIndividualWordCount() == t2.getIndividualWordCount()) {
            return t1.getWord().compareTo(t2.getWord());
        }
        
        return t1.getIndividualWordCount() - t2.getIndividualWordCount();
    }
}
