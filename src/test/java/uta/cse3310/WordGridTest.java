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
    public void testPlaceWord() 
    {
        // INCOMPLETE
        int size = 25;

        ArrayList<String> words = new ArrayList<>(size);
        words.add("Terra");
        words.add("Gaia");
        words.add("Geb");


        //no error
        assertTrue(words.size() == 4);
    }
    @Test
    public void testDensity()
    {

    }
    @Test
    public void testRandomness()
    {

    }
    @Test
    public void testGridTime()
    {

    }

}
