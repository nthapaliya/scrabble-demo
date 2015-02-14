package board;

/**
 * Created by nirajthapaliya on 2/13/15.
 */
public class BoardState {
    static int[] getRack(ScrabblePanel.Tile[] tileRack){
        int[] rack = new int[26];
        for (ScrabblePanel.Tile t: tileRack) {
            if (!t.isEmpty()) {
                rack[t.c - 'a']++;
            }
        }
        return rack;
    }
    static char[][] getBoard(ScrabblePanel.Tile[][] tileBoard){
        char[][] board = new char[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                ScrabblePanel.Tile tile = tileBoard[i][j];
                if (!tile.isEmpty()){
                    board[i][j] = tile.c;
                }
            }
        }
        return board;
    }
}
