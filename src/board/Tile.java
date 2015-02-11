package board;

import wordsearch.Letters;
import wordsearch.Tiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

public class Tile extends JPanel {
    public static Tile selected;

    final int TILE_SIZE = 45;
    Tiles tile;
    boolean permanent, empty, clicked;
    char c;
    String tileText;
    int tileValue;

    String text1, text2;
    Color color;

    HoverListener hl;

    Tile(int row, int col) {
        permanent = false;
        empty = true;
        tile = Tiles.GetType(row, col);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
        setColorsToDefaultState(); // sets the colors to their natural state
        addMouseListener(new HoverListener());
        getTileText();
    }

    void getTileText() {
        if (text1 == null && text2 == null) {
            Scanner s = new Scanner(tile.toString());
            text1 = s.next();
            if (s.hasNext()) text2 = s.next();
        }
    }

    void setColorsToDefaultState() {
        if (empty) {
            if (tile == Tiles.DOUBLE_WORD) {
                color = Color.PINK;
            } else if (tile == Tiles.TRIPLE_WORD) {
                color = Color.RED;
            } else if (tile == Tiles.DOUBLE_LETTER) {
                color = Color.BLUE.brighter();
            } else if (tile == Tiles.TRIPLE_LETTER) {
                color = Color.CYAN;
            } else if (tile == Tiles.CENTER_TILE) {
                color = Color.YELLOW;
            } else {
                color = new Color(255, 204, 102);
            }
        } else {
            if (permanent) color = Color.ORANGE;
            else if (clicked) color = Color.GREEN;
            else color = Color.YELLOW.brighter();
        }
    }

    void refreshValues(char c) {
        empty = false;
        this.c = c;
        tileText = ("" + c).toUpperCase();
        tileValue = Letters.Value(c);
    }

    void put(char c) {
        refreshValues(c);
        setColorsToDefaultState();
        repaint();
        selected.empty = true;
        selected.deselect();
    }

    void select() {
        selected = this;
        selected.clicked = true;
        setColorsToDefaultState();
        repaint();
    }

    void deselect() {
        selected.clicked = false;
        setColorsToDefaultState();
        selected.repaint();
        selected = null;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint
                (RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);


        if (empty) {
            // Draws the appropriate square (double word in blue, etc)
            g.setColor(Color.GRAY);
            g.fillRoundRect(0, 0, TILE_SIZE - 1, TILE_SIZE - 1, 10, 10);

            g.setColor(color);
            g.fillRoundRect(0, 0, TILE_SIZE - 3, TILE_SIZE - 3, 10, 10);

            if (tile != Tiles.CENTER_TILE && tile != Tiles.PLAIN) {
//                getTileText();
                g.setColor(Color.BLACK);
                g.setFont(new Font("Courier Sans", Font.PLAIN, 10));
                g.drawString(text1, (TILE_SIZE - text1.length() * 6) / 2, 20);
                g.drawString(text2, (TILE_SIZE - text2.length() * 6) / 2, 35);
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
            g.drawString(tileValue + "", 31, 44);
        }
    }

    private class HoverListener extends MouseAdapter {

        public void mouseEntered(MouseEvent e) {
            if (!clicked || !permanent) {
                color = color.darker();
                repaint();
            }
        }

        public void mouseExited(MouseEvent e) {
            setColorsToDefaultState();
            repaint();
        }

        public void mousePressed(MouseEvent e) {
            if (!permanent) {
                if (empty && selected != null) {
                    put(selected.c);
                } else if (!empty && selected != null) {
                    deselect();
                } else if (!empty && selected == null) {
                    select();
                }
            }
        }
    }

}