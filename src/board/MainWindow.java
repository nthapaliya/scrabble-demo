package board;

import javax.swing.*;
import java.awt.*;
import wordsearch.Game;

public class MainWindow extends JPanel {
    public static Tile[][] tiles;
    public static MainWindow mw;
    public Game game;

    public MainWindow() {
        mw = this;
        game = new Game("Player 1", "Player 2");
        setBackground(Color.WHITE);
        add(new Board());
        add(new SidePanel(game));
    }

    class Board extends JPanel {
        Board() {
            tiles = new Tile[15][15];
            setLayout(new GridLayout(15, 15, 1, 1));
            setBackground(Color.WHITE);

            // Build the board
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    tiles[i][j] = new Tile(i, j);
                    add(tiles[i][j]);
                }
            }
        }
    }

    class SidePanel extends JPanel {
        SidePanel(Game game) {
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            setBackground(Color.WHITE);

            add(new ScorePanel(game));
            add(new PieceHolder(game));
            add(new ButtonPanel(game));
        }
    }
}
