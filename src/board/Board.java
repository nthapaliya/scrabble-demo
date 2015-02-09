package board;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    public Tile[][] tiles = new Tile[15][15];
    public static Board board;


    Board() {
        board = this;
        setLayout(new GridLayout(15, 15, 1, 1));
        setBackground(Color.WHITE);
        buildBoard();

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
