package uta.cse3310;
import org.junit.Test;
import static org.junit.Assert.*;

public class WordGridTest
{
    @Test
    public void toTestGenerateWordList()
    {
        WordGrid wordGrid = new WordGrid(10);
        String[] wordList = wordGrid.generateWordList();
        assertNotNull(wordList);
        assertTrue(wordList.length > 0);
    }
    @Test
    public void toTestSelectionValidation()
    {
        WordGrid wordGrid = new WordGrid(10);
        String[] wordList = wordGrid.generateWordList();
        String selectedWord = wordList[0];
        assertTrue(wordGrid.SelectionValidation(selectedWord));
    }
    @Test
    public void toTestCheckHorizontal()
    {
        WordGrid wordGrid = new WordGrid(10);
        char[][] grid = wordGrid.grid;
        String word = "testit";
        for (int i = 0; i < word.length(); i++)
        {
            grid[0][i] = word.charAt(i);
        }
        assertTrue(wordGrid.checkHorizontal(word));
    }
    @Test
    public void toTestCheckVertical()
    {
        WordGrid wordGrid = new WordGrid(10);
        char[][] grid = wordGrid.grid;
        String word = "testit";
        for (int i = 0; i < word.length(); i++)
        {
            grid[i][0] = word.charAt(i);
        }
        assertTrue(wordGrid.checkVertical(word));
    }
    @Test
    public void toTestCheckDiagonal()
    {
        WordGrid wordGrid = new WordGrid(10);
        char[][] grid = wordGrid.grid;
        String word = "testit";
        for (int i = 0; i < word.length(); i++)
        {
            grid[i][i] = word.charAt(i);
        }
        assertTrue(wordGrid.checkDiagonal(word));
    }
    @Test
    public void toTestFillGrid()
    {
        WordGrid wordGrid = new WordGrid(10);
        char[][] grid = wordGrid.fillGrid();
        assertNotNull(grid);
    }
}
