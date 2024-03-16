package uta.cse3310;

public class WordGrid{
	
	public char[][] grid;
	public String[] wordList;
    	public int gridSize;
    	public Random random;
	
	public WordGrid(){

        // Constructor \\

	}

    	public void initializeGrid() {

        // Method to Generate Grid w/ spaces. \\

   	}

    	public void displayGrid() {

        // Method to display Grid. \\

    	}
	
	public boolean SelectionValidation(){

        // Method to ensure the Word selected is in the WordBank/WordList. \\
		return false;
	}
	
	public boolean checkHorizontal(){

        // Method for checking words Horizontally. \\
		return false;
	}
	
	public boolean checkVertical(){

        // Method for checking words Vertically. \\
		return false;
	}
	
	public boolean checkDiagonal(){

        // Method for checking words Diagonally. \\
		return false;
	}
	
	public String[] generateWordList(){

        // Method for generating the Word Bank. \\
		return wordList;
	}
	
	public char[][] fillGrid(){

        // Method for filling in the Grid w/ random letters \\
		return grid;
	}
	
}
