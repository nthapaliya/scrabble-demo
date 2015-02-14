package wordsearch;

/**
 * Created by nirajthapaliya on 2/8/15.
 */
class Player {
    private int[] rack;
    private int score;
    private char[][] board;
    private String name;

    Player(String name) {
        rack = new int[26];
        score = 0;
        board = new char[15][15];
        this.name = name;
    }

    int Score() {
        return score;
    }
}
