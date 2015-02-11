package board;

import wordsearch.Game;
import wordsearch.Tiles;

import javax.swing.*;
import java.awt.*;

class Rack extends JPanel {
    int[] playerRack;
    Game game;

    public Rack(Game game) {
        this.game = game;
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
            super(1, 1);
            tile = null;
            color = Color.YELLOW.brighter();
            refreshValues(c);
            setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
        }
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint
                    (RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            if (!empty) {
                if (permanent) color = Color.ORANGE;
                else if (clicked) color = Color.GREEN;
                else color = Color.YELLOW.brighter();

                g.setColor(Color.BLACK);
                g.fillRoundRect(0, 0, TILE_SIZE - 1, TILE_SIZE - 1, 10, 10);
                g.setColor(color);
                g.fillRoundRect(0, 0, TILE_SIZE - 3, TILE_SIZE - 3, 10, 10); //fill in square

                g.setColor(Color.BLACK);       // fill in text
//                g.drawRoundRect(1, 1, TILE_SIZE - 4, TILE_SIZE - 4, 10, 10);
                g.setFont(new Font("Courier Sans", Font.PLAIN, 36));
                g.drawString(("" + c).toUpperCase(), 7, 35);

                g.setFont(new Font("Courier Sans", Font.BOLD, 12));
                g.drawString(tileValue + "", 31, 44);
            }
        }
    }
}
