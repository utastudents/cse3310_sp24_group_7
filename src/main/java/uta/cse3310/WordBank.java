package uta.cse3310;

import java.util.ArrayList;
import java.util.List;

public class WordBank {
    private List<String> wordList;

    public WordBank() {
        // Constructor \\
        wordList = new ArrayList<>();
    }

    public void addWord(String word) {
        
    }

    public void removeWord(String word) {
        
    }

    public List<String> getWordList() {
        // Method to get the list of words in the word bank \\
        return wordList;
    }

    public String getRandomWord() {
        // Method to get a random word from the word bank \\
        return null;
    }

    public boolean containsWord(String word) {
        // Method to check if a word is in the word bank \\
        return wordList.contains(word);
    }

    public boolean isValidWord(String word) {
        // Method to check if the selected word is in the word bank \\
        return wordList.contains(word);
    }
}
