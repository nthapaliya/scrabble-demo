package board;

import java.awt.*;

/**
 * Created by nirajthapaliya on 2/14/15.
 */
class Board extends PanelTemplate {
    Board(BoardLogic bl) {
        bl.tiles = new Tile[15][15];
        setLayout(new GridLayout(15, 15, 1, 1));

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                bl.tiles[i][j] = new Tile(i, j);
                add(bl.tiles[i][j]);
            }
        }
    }
}
