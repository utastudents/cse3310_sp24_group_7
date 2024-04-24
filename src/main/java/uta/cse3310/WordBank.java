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
    private Random random;

    public WordBank() {
        wordList = new ArrayList<>();
        wordsPlaced = new ArrayList<>();
        random = new Random();
    }

    public void addWordsFromFile(String fileName) {
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
        // System.out.println("test");
        while (overlapCount < 5) { // Continue generating grid until at least 5 words are overlapped
            wordsPlaced.clear();
            for (String word : wordList) {
                boolean wordPlaced = false;
                int attempts = 0;
                while (!wordPlaced && attempts < 10) {
                    System.out.println("test" + attempts);
                    int row = random.nextInt(rows);
                    int col = random.nextInt(cols);
                    int direction = random.nextInt(4); // 0: vertical, 1: horizontal, 2: reverse vertical, 3: reverse
                                                       // horizontal

                    if (direction == 0 && row + word.length() <= rows) {
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
                        }
                    } else if (direction == 1 && col + word.length() <= cols) {
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
                        }
                    } else if (direction == 2 && row - word.length() >= -1) {
                        // Reverse vertical placement
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
                        }
                    } else if (direction == 3 && col - word.length() >= -1) {
                        // Reverse horizontal placement
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
}