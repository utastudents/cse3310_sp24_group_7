package uta.cse3310;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;

import java.util.ArrayList;

import org.junit.Test;

public class WordGridTest 
{
    int i;
    WordGrid wordGrid = new WordGrid(625);

    @Test
    public void WordGridSelection(int size)
    {

    }
    @Test
    public void testPlaceWord() {
        int rows = 25;
        WordGrid wordGrid = new WordGrid(rows);
        ArrayList<String> words = new ArrayList<>();
        words.add("Terra");
        words.add("Gaia");
        words.add("Geb");


        //no error
        assertTrue(words.size() == 4);
    }
}
