package uta.cse3310;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import static org.junit.Assert.assertTrue;;



public class WordBankTest {
    public List<String> wordList;
   public  WordBank wordBank;
   public List<String> wordsPlaced;
   private List<WordLocation> wordLocations;

   @Before
    public void setUp() {
        wordBank = new WordBank();
        wordList = new ArrayList<>();
        wordsPlaced = new ArrayList<>();
       

    }
//This checks if the wordBank is actually being populated, it checks if it is empty first, and after populated it checks to see if it is empty 
    @Test
    public void testAddWordsFromFile() {
        assertTrue(wordList.isEmpty());
        wordBank.addWordsFromFile("words.txt", wordList);
        assertNotNull(wordList);
        assertFalse(wordList.isEmpty());
      
    }
    //This checks if the grid is empty before it is created,and checks if it is created after initalized
    @Test
    public void testGenerateGrid() { 
        assertTrue(wordsPlaced.isEmpty());
        wordBank.generateGrid(25, 25, wordList); 
        System.out.println("Words Placed:");
        wordsPlaced = wordBank.getWordsPlaced();
        assertNotNull(wordsPlaced);
        //assertFalse(wordsPlaced.isEmpty());
    }
    //Test if wordsPlaced is correct 
    @Test
    public void testWordsPlaced() { 
        assertTrue(wordsPlaced.isEmpty());
        //assertTrue(wordLocations.isEmpty());
        wordBank.generateGrid(25, 25, wordList); 

        wordsPlaced = wordBank.getWordsPlaced();
        wordLocations = wordBank.getWordLocations();

        System.out.println("Word Location:");
        System.out.println(wordLocations.size());
        for (WordLocation location : wordLocations) {
            System.out.println(location.toString());
        }
        
        System.out.println("Words Placed:");
        for (String word : wordsPlaced) {
            System.out.println(word);
        }
        System.out.println(wordsPlaced.size());
        
        assertNotNull(wordsPlaced);
        assertTrue(wordsPlaced.size()==wordLocations.size());
        //assertFalse(wordsPlaced.isEmpty());
    }
}
 
