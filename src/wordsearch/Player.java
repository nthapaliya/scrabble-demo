package wordsearch;

/**
 * Created by nirajthapaliya on 2/8/15.
 */
public class Player {
    int[] rack;
    int score;
    char[][] board;
    String name;

    Player(String name) {
        rack = new int[26];
        score = 0;
        board = new char[15][15];
        this.name = name;
    }
    
}
