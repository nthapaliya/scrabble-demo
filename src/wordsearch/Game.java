package wordsearch;

/**
 * Created by nirajthapaliya on 2/8/15.
 */
public class Game {
    Board board;
    Player player1;
    Player player2;
    Letters letterBag;

    String lastWord;
    int lastScore;

    public Game(String playerName, String playerName2) {
        player1 = new Player(playerName);
        player2 = new Player(playerName2);
        board = new Board();
        letterBag = new Letters();
    }

    public int remainingLetters() {
        return letterBag.Sum();
    }
    public int Player1Score() { return player1.Score(); }
    public int Player2Score() { return player2.Score(); }
    public char DrawRandom() { return letterBag.DrawRandom();}
    public void AddToBag(char c) { letterBag.Add(c); }
    public String ErrMsg(char[][] newState) {
        return board.ErrMsg(newState);
    }
    public String LastWord() { return lastWord; }
    public int LastScore() { return lastScore; }
}
