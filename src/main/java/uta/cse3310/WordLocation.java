package uta.cse3310;

public class WordLocation {
    private String word;
    private int startRow;
    private int startCol;
    private int endRow;
    private int endCol;
    private int direction;

    public WordLocation(String word, int startRow, int startCol, int endRow, int endCol, int direction) {
        this.word = word;
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;

        this.direction = direction;
    }

    // Getters and setters
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public void setStartCol(int startCol) {
        this.startCol = startCol;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getEndCol() {
        return endCol;
    }

    public void setEndCol(int endCol) {
        this.endCol = endCol;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Word: " + word +
                ", Start: (" + startRow + ", " + startCol + ")" +
                ", End: (" + endRow + ", " + endCol + ")" +
                ", Direction: " + direction;
    }
}
