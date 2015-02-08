package wordsearch;

/**
 * Created by nirajthapaliya on 2/8/15.
 */
public class Game {
    public char[][] Board;
    public Player Player1;
    public Player Player2;
    public Letters LetterBag;

    public Game(String playerName, String playerName2) {
        Player1 = new Player(playerName);
        Player2 = new Player(playerName);
        Board = new char[15][15];
        LetterBag = new Letters();
    }
}
