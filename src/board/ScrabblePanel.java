package board;

import wordsearch.Game;
import wordsearch.Letters;
import wordsearch.Tiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScrabblePanel extends JPanel {
    static private ScrabblePanel sp;
    private ScorePanel scorePanel;
    private Game game;
    private Tile selected;
    private RackTile[] rack;
    private Tile[][] tiles;

    public ScrabblePanel() {
        sp = this;
        game = new Game("Player 1", "Player 2");
        rack = new RackTile[7];

        setBackground(Color.WHITE);
        add(new Board());
        add(new SidePanel());
    }

    void putUnfixedBack() {
        char[] unfixed = new char[7];
        int index = 0;
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (!tile.isEmpty() && !tile.isPermanent()) {
                    unfixed[index++] = tile.c;
                    tile.c = 0;
                    tile.repaint();
                }
            }
        }
        int rackIndex = 0;
        RackTile rackTile = rack[rackIndex];

        for (int i = 0; i < index; i++) {
            while (!rackTile.isEmpty()) {
                rackTile = rack[++rackIndex];
            }
            rackTile.put(unfixed[i]);
        }
    }

    void refillRack() {
        for (int i = 0; i < 7; i++) {
            RackTile racktile = rack[i];
            if (racktile.isEmpty()) {
                char c = game.DrawRandom();
                racktile.put(c);
            }
        }
    }

    void fixAll() {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (!tile.isEmpty() && !tile.isPermanent()) {
                    tile.makePermanent();
                    tile.repaint();
                }
            }
        }
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

    class Tile extends JPanel {
        final int TILE_SIZE = 45;
        final Tiles tile;

        private boolean permanent, hover;
        final String[] tileText;
        char c;

        Tile(int row, int col) {
            tile = Tiles.GetType(row, col);
            tileText = tile.toString().split("\\s");

            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
            addMouseListener(new HoverListener());
        }

        Color getColor() {
            if (isEmpty()) {
                if (tile == Tiles.DOUBLE_WORD) {
                    return Color.PINK;
                } else if (tile == Tiles.TRIPLE_WORD) {
                    return Color.RED;
                } else if (tile == Tiles.DOUBLE_LETTER) {
                    return Color.BLUE.brighter();
                } else if (tile == Tiles.TRIPLE_LETTER) {
                    return Color.CYAN;
                } else if (tile == Tiles.CENTER_TILE) {
                    return Color.YELLOW;
                } else {
                    return new Color(255, 204, 102);
                }
            } else {
                if (isPermanent()) return Color.ORANGE;
                else if (isSelected()) return Color.GREEN;
            }
            return Color.YELLOW.brighter();
        }

        void put(char tileC) {
            this.c = tileC;
            this.repaint();
        }

        void select() {
            selected = this;
            selected.repaint();
        }

        void remove() {
            c = 0;
            repaint();
        }

        void deselect() {
            Tile t = selected;
            selected = null;
            t.repaint();
        }

        boolean isEmpty() {
            return c == 0;
        }

        boolean isSelected() {
            return selected == this;
        }

        boolean isPermanent() {
            return permanent;
        }

        void makePermanent() {
            permanent = true;
        }

        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint
                    (RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Color color = getColor();
            if (hover && !permanent & !isSelected()) {
                color = color.darker();
            }

            if (isEmpty()) {
                // Draws the appropriate square (double word in blue, etc)
                g.setColor(Color.GRAY);
                g.fillRoundRect(0, 0, TILE_SIZE - 1, TILE_SIZE - 1, 10, 10);
                g.setColor(color);
                g.fillRoundRect(0, 0, TILE_SIZE - 3, TILE_SIZE - 3, 10, 10);

                if (tile != Tiles.CENTER_TILE && tile != Tiles.PLAIN) {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Courier Sans", Font.PLAIN, 10));
                    g.drawString(tileText[0], (TILE_SIZE - tileText[0].length() * 6) / 2, 20);
                    g.drawString(tileText[1], (TILE_SIZE - tileText[1].length() * 6) / 2, 35);
                }
            } else {
                // puts in a letter
                g.setColor(Color.BLACK);
                g.fillRoundRect(0, 0, TILE_SIZE - 1, TILE_SIZE - 1, 10, 10);
                g.setColor(color);
                g.fillRoundRect(0, 0, TILE_SIZE - 3, TILE_SIZE - 3, 10, 10); //fill in square

                g.setColor(Color.BLACK);       // fill in text
                g.drawRoundRect(1, 1, TILE_SIZE - 4, TILE_SIZE - 4, 10, 10);
                g.setFont(new Font("Courier Sans", Font.PLAIN, 36));
                g.drawString(("" + c).toUpperCase(), 7, 35);

                g.setFont(new Font("Courier Sans", Font.BOLD, 10));
                g.drawString(Letters.Value(c) + "", 31, 44);
            }
        }

        private class HoverListener extends MouseAdapter {

            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }

            public void mouseExited(MouseEvent e) {
                hover = false;
                repaint();
            }

            public void mousePressed(MouseEvent e) {
                if (!isPermanent()) {
                    if (isEmpty() && selected != null) {
                        put(selected.c);
                        selected.remove();
                        deselect();
                    } else if (!isEmpty() && selected != null) {
                        deselect();
                    } else if (!isEmpty()) {
                        select();
                    }
                }
            }
        }
    }

    class RackTile extends Tile {
        RackTile(char c) {
            super(0, 0);
            this.c = c;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (isEmpty()) {
                g.setColor(Color.BLACK);
                g.fillRoundRect(0, 0, TILE_SIZE - 1, TILE_SIZE - 1, 10, 10);
                g.setColor(Color.GRAY);
                g.fillRoundRect(0, 0, TILE_SIZE - 3, TILE_SIZE - 3, 10, 10);
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
        class Rack extends JPanel {
            public Rack() {
                setLayout(new GridLayout(7, 1, 0, 5));
                setBackground(Color.WHITE);

                for (int i = 0; i < 7; i++) {
                    char c = game.DrawRandom();
                    rack[i] = new RackTile(c);
                    add(rack[i]);
                }
            }
        }

    }

    class ScorePanel extends JPanel {
        ScorePanel() {
            scorePanel = this;
            setPreferredSize(new Dimension(200, 150));
            setBackground(Color.WHITE);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint
                    (RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);


            g.setColor(Color.BLACK);
            g.drawRoundRect(1, 1, 195, 140, 10, 10);
            g.setFont(new Font("Courier Sans", Font.PLAIN, 12));

            g.drawString("Pieces remaining: " + game.remainingLetters() + " ", 8, 20);
            g.drawString("Player Score:  " + game.Player1Score(), 8, 40);
            g.drawString("Comp Score:  " + game.Player2Score(), 8, 60);

            String lastWord = game.LastWord();
            int lastScore = game.LastScore();

            if (lastWord != null) {
                g.drawString("Latest word(s) played", 8, 80);
                g.setFont(new Font("Courier Sans", Font.BOLD, 12));
                g.drawString(lastWord, 20, 100);
                g.setFont(new Font("Courier Sans", Font.PLAIN, 12));
                g.drawString("for a score: " + lastScore, 8, 120);

            }
        }
    }

    class ButtonPanel extends JPanel implements ActionListener {
        final JButton goButton;
        final JButton loseButton;
        final JButton reset;

        ButtonPanel() {
            setBackground(Color.WHITE);
            goButton = new JButton("Go!");
            loseButton = new JButton("Lose Turn");
            reset = new JButton("Reset");

            goButton.addActionListener(this);
            loseButton.addActionListener(this);
            reset.addActionListener(this);

            add(goButton);
            add(loseButton);
            add(reset);

        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(goButton)) {
                System.out.println("go button");
                sp.fixAll();
                sp.refillRack();

            } else if (e.getSource().equals(loseButton)) {
                System.out.println("lose button");

            } else { // resetButton;
                System.out.println("reset button");
                sp.putUnfixedBack();
            }
            sp.scorePanel.repaint();
//            sp.repaint();
        }
    }

}