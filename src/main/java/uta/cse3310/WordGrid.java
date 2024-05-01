package uta.cse3310;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordGrid{
	
	public char[][] grid;
    	public int gridSize;
    	public Random random;
		public List<String> wordList;
		
	
	public WordGrid(int size){
		gridSize= size;
		wordList = new ArrayList<>();
		random = new Random();
		initializeGrid();
		displayGrid();
		generateWordList();
		fillGrid();
        // Constructor \\

	}

    	public void initializeGrid() {
			grid = generateRandomGrid(gridSize, gridSize);
        // Method to Generate Grid w/ spaces. \\
   	}

    	public void displayGrid() {
			printGrid(grid);
        // Method to display Grid. \\

    	}
	
	public boolean SelectionValidation(String word){
		return wordList.contains(word);

        // Method to ensure the Word selected is in the WordBank/WordList. \\

	}
	
	public boolean checkHorizontal(String word){
        // Method for checking words Horizontally. \\
		for(int i = 0; i < gridSize; i++){
			for(int j = 0; j <= gridSize - word.length(); j++){
				boolean found = true;
				for(int k = 0; k < word.length(); k++){
					if(grid[i][j+k] !=word.charAt(k)){
						found = false;
						break;
					}

				}
				if(found){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkVertical(String word){
		for(int j = 0; j < gridSize; j++){
			for(int i = 0; i <= gridSize - word.length(); i++){
				boolean found = true;
				for(int k = 0; k < word.length(); k++){
					if(grid[i+k][j] !=word.charAt(k)){
						found = false;
						break;
					}
				}
				if(found){
					return true;
				}
			}
		}

        // Method for checking words Vertically. \\
		return false;
	}
	
	public boolean checkDiagonal(String word){
		for(int i = 0; i < gridSize - word.length(); i++){
		     for(int j = 0; j<=gridSize - word.length(); j++){
			boolean found = true;
				for(int k = 0; k < word.length(); k++){
					if (grid[i + k][j + k] != word.charAt(k)) {
						found = false;
						break;
					}

				}
				if(found){
					return true;
				}

			}
		}
	

        // Method for checking words Diagonally. \\
		return false;
	}
	
	public String[] generateWordList(){
		try(BufferedReader reader = new BufferedReader(new FileReader("words.txt"))){
			String word;
			while ((word = reader.readLine()) != null) {
				if(word.length() >= 4 && random.nextDouble() < 0.01 && wordList.size() <50){
					wordList.add(word);
				}
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
        // Method for generating the Word Bank. \\
		return wordList.toArray(new String[0]);
	}
	
	public char[][] fillGrid(){
		for(int i = 0; i< gridSize; i++){
			for(int j =0; j<gridSize; j++){
				if(grid[i][j] == ' '){
					grid[i][j] = (char)(random.nextInt(26) + 'a');
				}
			}
		}

        // Method for filling in the Grid w/ random letters \\
		return grid;
	}
    public char[][] generateRandomGrid(int rows, int cols) {
        char[][] grid = new char[rows][cols];
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = (char) (rand.nextInt(26) + 'a'); // Generating random lowercase letters
            }
        }
        return grid;
    }

    public void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) // populates the columns
            {
                System.out.print(grid[i][j] + " "); // print an array
            }
            System.out.println(); // creates a new row
        }
    }
}
