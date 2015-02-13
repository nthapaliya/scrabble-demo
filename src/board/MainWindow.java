package board;

import wordsearch.Game;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JPanel {
    static Tile[][] tiles;
    static Game game;

    public MainWindow() {
        game = new Game("Player 1", "Player 2");
        setBackground(Color.WHITE);
        add(new Board());
        add(new SidePanel());
    }

    class Board extends JPanel {
        Board() {
            tiles = new Tile[15][15];
            setLayout(new GridLayout(15, 15, 1, 1));
            setBackground(Color.WHITE);

            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    tiles[i][j] = new Tile(i, j);
                    add(tiles[i][j]);
                }
            }
        }
    }

    class SidePanel extends JPanel {
        SidePanel() {
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            setBackground(Color.WHITE);

            add(new ScorePanel());
            add(new Rack());
            add(new ButtonPanel());
        }
    }
}
