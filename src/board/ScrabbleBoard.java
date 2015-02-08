package board;

import javax.swing.*;
import java.awt.*;

public class ScrabbleBoard extends JPanel {

    public static final int BOARD_SIZE = 700;
    public static Tile[][] tiles = new Tile[15][15];
    public static ScrabbleBoard scrabbleBoard;


    ScrabbleBoard(Component f) {
        scrabbleBoard = this;
        //setPreferredSize(new Dimension(BOARD_SIZE,BOARD_SIZE));
        setLayout(new GridLayout(15, 15, 1, 1));

        setBackground(Color.WHITE);
        buildBoard();

    }

    public static void clearBoard() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (!tiles[i][j].permanent)
                    tiles[i][j].clearTile();

            }
        }
    }

    void buildBoard() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                tiles[i][j] = new Tile(i, j);
                add(tiles[i][j]);
            }
        }
    }
}
