import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordBank {
    private List<String> wordList;
    private char[][] grid;
    private Random random;

    public WordBank() {
        wordList = new ArrayList<>();
        random = new Random();
    }

    public void addWordsFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() >= 4 && random.nextDouble() < 0.05 && wordList.size() < 50) {
                    wordList.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateGrid(int rows, int cols) {
        grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = '.'; // Initialize grid with empty cells
            }
        }


        System.out.println("test");
        for (String word : wordList) {
            boolean wordPlaced = false;
            int attempts=0;
            while (!wordPlaced && attempts < 10) {
                System.out.println("test" + attempts);
                int row = random.nextInt(rows);
                int col = random.nextInt(cols);
                int direction = random.nextInt(4); // 0: vertical, 1: horizontal, 2: reverse vertical, 3: reverse
                                                   // horizontal

                if (direction == 0 && row + word.length() <= rows) {
                    boolean fits = true;
                    for (int i = 0; i < word.length(); i++) {
                        if (grid[row + i][col] != '.' && grid[row + i][col] != word.charAt(i)) {
                            fits = false;
                            break;
                        }
                    }
                    if (fits) {
                        for (int i = 0; i < word.length(); i++) {
                            grid[row + i][col] = word.charAt(i);
                        }
                        wordPlaced = true;
                    }
                } else if (direction == 1 && col + word.length() <= cols) {
                    boolean fits = true;
                    for (int i = 0; i < word.length(); i++) {
                        if (grid[row][col + i] != '.' && grid[row][col + i] != word.charAt(i)) {
                            fits = false;
                            break;
                        }
                    }
                    if (fits) {
                        for (int i = 0; i < word.length(); i++) {
                            grid[row][col + i] = word.charAt(i);
                        }
                        wordPlaced = true;
                    }
                    
                }
                attempts++;
            }
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
        if (wordList.isEmpty()) {
            return null;
        }
        return wordList.get(random.nextInt(wordList.size()));
    }

    public boolean containsWord(String word) {
        return wordList.contains(word);
    }

    public boolean isValidWord(String word) {
        return wordList.contains(word);
    }

    public static void main(String[] args) {
        WordBank wordBank = new WordBank();
        wordBank.addWordsFromFile("words.txt");
        wordBank.generateGrid(25, 25);
        wordBank.printGrid();
    }
}
