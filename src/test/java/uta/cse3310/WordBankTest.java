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
        wordsPlaced = wordBank.getWordsPlaced();
        assertNotNull(wordsPlaced);
        //assertFalse(wordsPlaced.isEmpty());
    }

}
 
