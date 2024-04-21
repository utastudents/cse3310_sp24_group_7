package uta.cse3310;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordBank {
    private List<String> wordList;
    private Random random;
    private ArrayList<String> wordBank;


    public WordBank() {
        // Constructor \\
        wordList = new ArrayList<>();
        random = new Random();
    }

    public void addWord(String word) {
        
        try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() >= 4 && random.nextDouble() < 0.01 && wordList.size()<50) {
                    wordList.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
   
    public static void main(String[] args) {
        // Create a WordBank instance
        WordBank wordBank = new WordBank();
        
        // Populate the word bank from a text file
        wordBank.addWord("words.txt");
        
        // Display the words in the word bank
        System.out.println("Words in the Word Bank:");
        for (String word : wordBank.getWordList()) {
            System.out.println(word);
        }
    }
}
