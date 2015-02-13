package board;

import wordsearch.Game;

import javax.swing.*;
import java.awt.*;

class Rack extends JPanel {
    int[] playerRack;
    Game game = MainWindow.game;

    public Rack() {
        setLayout(new GridLayout(7, 1, 10, 5));
        setBackground(Color.WHITE);
        playerRack = new int[26];

        for (int i = 0; i < 7; i++) {
            char c = game.DrawRandom();
            playerRack[c-'a']++;
            add(new RackTile(c));
        }
    }
    /**
     * Created by nirajthapaliya on 2/11/15.
     */
    class RackTile extends Tile {
        RackTile(char c) {
            super(0,0);
            this.c = c;
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (isEmpty()) {
                g.setColor(Color.BLACK);
                g.fillRoundRect(0, 0, TILE_SIZE - 1, TILE_SIZE - 1, 10, 10);
                g.setColor(Color.GRAY);
                g.fillRoundRect(0, 0, TILE_SIZE - 3, TILE_SIZE - 3, 10, 10); //fill in square
            }
        }
    }
}
