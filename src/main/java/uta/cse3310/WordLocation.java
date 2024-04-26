package uta.cse3310;

public class WordLocation {
    private String word;
    private int[] start;
    private int[] end;
    private int direction;

    public WordLocation(String word, int[] start, int[] end, int direction) {
        this.word = word;
        this.start = start;
        this.end = end;
        this.direction = direction;
    }

    // Getters and setters
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int[] getStart() {
        return start;
    }

    public void setStart(int[] start) {
        this.start = start;
    }

    public int[] getEnd() {
        return end;
    }

    public void setEnd(int[] end) {
        this.end = end;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
