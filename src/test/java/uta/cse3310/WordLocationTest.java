package uta.cse3310;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WordLocationTest
{
    @Test
    public void toTestWordLocationConstructor()
    {
        WordLocation wordLocation = new WordLocation("test", 1, 2, 3, 4, 0);

        assertEquals("test", wordLocation.getWord());
        assertEquals(1, wordLocation.getStartRow());
        assertEquals(2, wordLocation.getStartCol());
        assertEquals(3, wordLocation.getEndRow());
        assertEquals(4, wordLocation.getEndCol());
        assertEquals(0, wordLocation.getDirection());
    }

    @Test
    public void toTestWordLocationSetter()
    {
        WordLocation wordLocation = new WordLocation("", 0, 0, 0, 0, 0);
        wordLocation.setWord("fixit");
        wordLocation.setStartRow(1);
        wordLocation.setStartCol(2);
        wordLocation.setEndRow(3);
        wordLocation.setEndCol(4);
        wordLocation.setDirection(1);

        assertEquals("fixit", wordLocation.getWord());
        assertEquals(1, wordLocation.getStartRow());
        assertEquals(2, wordLocation.getStartCol());
        assertEquals(3, wordLocation.getEndRow());
        assertEquals(4, wordLocation.getEndCol());
        assertEquals(1, wordLocation.getDirection());
    }

    @Test
    public void toTestWordLocationToString()
    {
        WordLocation wordLocation = new WordLocation("test", 1, 2, 3, 4, 0);
        String expected = "Word: test, Start: (1, 2), End: (3, 4), Direction: 0";
        assertEquals(expected, wordLocation.toString());
    }
}

