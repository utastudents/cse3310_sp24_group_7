package uta.cse3310;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordBank {
    private List<String> wordList;
    private char[][] grid;
    private List<String> wordsPlaced; // To store words successfully placed in the grid
    private List<WordLocation> wordLocations;                  //This is causing errors
    //private int[] start

    public WordBank() {
        wordList = new ArrayList<>();
        wordsPlaced = new ArrayList<>();
        wordLocations = new ArrayList<>();
    }

    public void addWordsFromFile(String fileName) {
        Random random = new Random();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() >= 4 && random.nextDouble() < 0.025 && wordList.size() < 100) {
                    wordList.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateGrid(int rows, int cols) {
        if (wordList.isEmpty()) {
            System.out.println("Word list is empty. Please add words before generating the grid.");
            return;
        }

        grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = '.';
            }
        }

        int overlapCount = 0;
        boolean overlapLetter = false;
        int[] start = new int[2];
        int[] end = new int[2];
        Random random = new Random();
        while (overlapCount < 5) { // Continue generating grid until at least 5 words are overlapped
            wordsPlaced.clear();
            for (String word : wordList) {
                boolean wordPlaced = false;
                int attempts = 0;
                while (!wordPlaced && attempts < 10) {
                    int row = random.nextInt(rows);
                    int col = random.nextInt(cols);
                    
                    int direction = random.nextInt(5); // 0: vertical, 1: horizontal, 2: reverse vertical, 3: reverse horizontal, 4: diagonal down-right

                    if (direction == 0 && row + word.length() <= rows) {
                        // Vertical placement

                        boolean fits = true;
                        overlapLetter = false;
                        for (int i = 0; i < word.length(); i++) {
                            if (grid[row + i][col] != '.' && grid[row + i][col] != word.charAt(i)) {
                                fits = false;
                                break;
                            }
                            if (grid[row + i][col] == word.charAt(i)) {
                                overlapLetter = true;
                            }
                        }
                        if (fits) {
                            for (int i = 0; i < word.length(); i++) {
                                grid[row + i][col] = word.charAt(i);
                            }
                            wordPlaced = true;
                            wordsPlaced.add(word); // Add the successfully placed word to wordsPlaced list
                            if (overlapLetter) {
                                overlapCount++;
                            }
                            start[0] = row;
                            start[1] = col;

                            end[0] = row+word.length()-1;
                            end[1] = col; 
                            wordLocations.add(new WordLocation(word, start[0], start[1], end[0], end[1], direction));     //This is causing errors 
                        }
                    } else if (direction == 1 && col + word.length() <= cols) {
                        // Horizontal placement
                        end[0] = row;
                        end[1] = col+word.length();

                        boolean fits = true;
                        overlapLetter = false;
                        for (int i = 0; i < word.length(); i++) {
                            if (grid[row][col + i] != '.' && grid[row][col + i] != word.charAt(i)) {
                                fits = false;
                                break;
                            }
                            if (grid[row][col + i] == word.charAt(i)) {
                                overlapLetter = true;
                            }
                        }
                        if (fits) {
                            for (int i = 0; i < word.length(); i++) {
                                grid[row][col + i] = word.charAt(i);
                            }
                            wordPlaced = true;
                            wordsPlaced.add(word); // Add the successfully placed word to wordsPlaced list
                            if (overlapLetter) {
                                overlapCount++;
                            }
                            start[0] = row;
                            start[1] = col;

                            end[0] = row;
                            end[1] = col+word.length()-1; 
                            wordLocations.add(new WordLocation(word, start[0], start[1], end[0], end[1], direction));
                            //ArrayList.add(new wordLocation(word,start,end,direction));     This is causing errors 
                        }
                    } else if (direction == 2 && row - word.length() >= -1) {
                        // Reverse vertical placement
                        end[0] = row-word.length();
                        end[1] = col;

                        boolean fits = true;
                        overlapLetter = false;
                        for (int i = 0; i < word.length(); i++) {
                            if (grid[row - i][col] != '.' && grid[row - i][col] != word.charAt(i)) {
                                fits = false;
                                break;
                            }
                            if (grid[row - i][col] == word.charAt(i)) {
                                overlapLetter = true;
                            }
                        }
                        if (fits) {
                            for (int i = 0; i < word.length(); i++) {
                                grid[row - i][col] = word.charAt(i);
                            }
                            wordPlaced = true;
                            wordsPlaced.add(word);
                            if (overlapLetter) {
                                overlapCount++;
                            }
                            start[0] = row;
                            start[1] = col;

                            end[0] = row-word.length()+1;
                            end[1] = col;
                            wordLocations.add(new WordLocation(word, start[0], start[1], end[0], end[1], direction));
                            //ArrayList.add(new wordLocation(word,start,end,direction));     This is causing errors 
                        }
                    } else if (direction == 3 && col - word.length() >= -1) {
                        // Reverse horizontal placement
                        end[0] = row;
                        end[1] = col-word.length();

                        boolean fits = true;
                        overlapLetter = false;
                        for (int i = 0; i < word.length(); i++) {
                            if (grid[row][col - i] != '.' && grid[row][col - i] != word.charAt(i)) {
                                fits = false;
                                break;
                            }
                            if (grid[row][col - i] == word.charAt(i)) {
                                overlapLetter = true;
                            }
                        }
                        if (fits) {
                            for (int i = 0; i < word.length(); i++) {
                                grid[row][col - i] = word.charAt(i);
                            }
                            wordPlaced = true;
                            wordsPlaced.add(word);
                            if (overlapLetter) {
                                overlapCount++;
                            }
                            start[0] = row;
                            start[1] = col;

                            end[0] = row;
                            end[1] = col-word.length()+1;
                            wordLocations.add(new WordLocation(word, start[0], start[1], end[0], end[1], direction));
                            //ArrayList.add(new wordLocation(word,start,end,direction));     This is causing errors 
                        }
                    } else if (direction == 4 && row + word.length() <= rows && col + word.length() <= cols) {
                        // Diagonal down-right placement
                        
                        boolean fits = true;
                        overlapLetter = false;
                        for (int i = 0; i < word.length(); i++) {
                            if (grid[row + i][col + i] != '.' && grid[row + i][col + i] != word.charAt(i)) {
                                fits = false;
                                break;
                            }
                            if (grid[row + i][col + i] == word.charAt(i)) {
                                overlapLetter = true;
                            }
                        }
                        if (fits) {
                            for (int i = 0; i < word.length(); i++) {
                                grid[row + i][col + i] = word.charAt(i);
                            }
                            wordPlaced = true;
                            wordsPlaced.add(word); // Add the successfully placed word to wordsPlaced list
                            if (overlapLetter) {
                                overlapCount++;
                            }
                            start[0] = row;
                            start[1] = col;

                            end[0] = row+word.length()-1;
                            end[1] = col+word.length()-1;
                            wordLocations.add(new WordLocation(word, start[0], start[1], end[0], end[1], direction));
                            //ArrayList.add(new wordLocation(word,start,end,direction));     This is causing errors 
                        }
                    }
                    attempts++;
                }
            }
            if (overlapCount < 5) {
                clearGrid();
            }
        }

        // Print the grid
        printGrid();

        // Print the words placed
        System.out.println("Overlapped Words: " + overlapCount);
        System.out.println("Words Placed:");
        for (String word : wordsPlaced) {
            System.out.println(word);
        }

        fillEmptyCells(rows, cols);
        printGrid();

        System.out.println("Word Location:");
        //for (String word : wordsPlaced) {                             //print for wordLocation after error is fixed 
            //System.out.println(wordLocation);
       // }
    }

    public void printGrid() {
        if (grid == null) {
            System.out.println("Grid not generated yet.");
            return;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void fillEmptyCells(int rows, int cols) {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '.') {
                    grid[i][j] = (char) (random.nextInt(26) + 'a');
                }
            }
        }
    }

    public List<String> getWordList() {
        return wordList;
    }

    public String getRandomWord() {
        // Return a random word from wordList
        return null;
    }

    public boolean containsWord(String word) {
        return wordList.contains(word);
    }

    public boolean isValidWord(String word) {
        return wordList.contains(word);
    }

    private void clearGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = '.';
            }
        }
    }

    public static void main(String[] args) {
        WordBank wordBank = new WordBank();
        wordBank.addWordsFromFile("words.txt");
        wordBank.generateGrid(25, 25);
    }

    public List<WordLocation> getWordLocations()
    {
        return wordLocations;
    }
}
