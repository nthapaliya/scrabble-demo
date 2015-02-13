package board;

import wordsearch.Letters;
import wordsearch.Tiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tile extends JPanel {
    final int TILE_SIZE = 45;

    Tiles tile;
    private boolean permanent, hover;
    String[] tileText;
    char c;

    public static Tile selected;

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

    void put(Tile t) {
        this.c = t.c;
        repaint();
    }

    void select() {
        selected = this;
        selected.repaint();
    }
    void remove(Tile t) {
        t.c = 0;
        t.repaint();
    }
    void deselect() {
        Tile t = selected;
        selected = null;
        t.repaint();
    }
    boolean isEmpty(){ return c ==0; }
    boolean isSelected() { return selected == this; }
    boolean isPermanent() { return permanent; }

    void makePermanent() {
        permanent = true;
        repaint();
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
                    put(selected);
                    remove(selected);
                    deselect();
                } else if (!isEmpty() && selected == null) {
                    select();
                } else if (!isEmpty() && selected != null) {
                    deselect();

                }
            }
        }
    }
}