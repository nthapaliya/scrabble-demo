package board;

import wordsearch.Tiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

public class Tile extends JPanel {

    public static Tile boardClicked;
    private final int TILE_SIZE = 45;
    boolean permanent, thisClicked, hover;
    public int tileValue;
    Tiles tile;
    char c;
    String tileLetter, text1, text2; // "DOUBLE" "WORD" etc
    private Color color, col;   // don't know

    Tile(int row, int col) {
        permanent = false;
        tile = Tiles.GetType(row, col);

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
        addMouseListener(new HoverListener());
    }

    void getTileText() {
        Scanner s = new Scanner(tile.toString());
        text1 = s.next();
        if (s.hasNext()) text2 = s.next();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint
                (RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        setDefault();

        g.setColor(Color.GRAY);
        g.fillRoundRect(0, 0, TILE_SIZE - 1, TILE_SIZE - 1, 10, 10);


        if (hover && tileLetter == null) g.setColor(color.darker());
        else g.setColor(color);

        g.fillRoundRect(0, 0, TILE_SIZE - 3, TILE_SIZE - 3, 10, 10);


        if (tileLetter == null) { // empty tile

            if (tile != Tiles.CENTER_TILE &&
                    tile != Tiles.PLAIN) {
                getTileText();

                g.setColor(Color.BLACK);
                g.setFont(new Font("Courier Sans", Font.PLAIN, 11));
                g.drawString(text1, (TILE_SIZE - text1.length() * 6) / 2, 20);
                g.drawString(text2, (TILE_SIZE - text2.length() * 6) / 2, 35);


            }
        } else if (tileLetter != null) {

            if (permanent) col = Color.ORANGE;
            else if (thisClicked) col = Color.GREEN;
            else col = Color.YELLOW.brighter();

            g.setColor(Color.BLACK);
            g.fillRect(2, 2, TILE_SIZE - 1, TILE_SIZE - 1);
            g.drawRect(0, 0, TILE_SIZE - 3, TILE_SIZE - 3);
            g.setColor(col);
            g.fillRect(1, 1, TILE_SIZE - 4, TILE_SIZE - 4); //fill in square


            g.setColor(Color.BLACK);       // fill in text
            g.setFont(new Font("Courier Sans", Font.PLAIN, 36));
            g.drawString(tileLetter, 7, 35);

            g.setFont(new Font("Courier Sans", Font.BOLD, 12));
            g.drawString(tileValue + "", 31, 44);
        }
    }

    void setDefault() {

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
    }

    void putOnBoard(Rack.PlayerPiece p) {
        c = p.c;
        tileLetter = p.text;
        tileValue = p.value;
        thisClicked = false;
        repaint();
        Rack.selected.RemovePiece();
        Rack.selected = null;
    }
    void putOnBoard(Tile tile) {
        c = tile.c;
        tileLetter = tile.tileLetter;
        tileValue = tile.tileValue;
        repaint();
        tile.clearTile();
        tile.repaint();
        boardClicked = null;
    }
    public void clearTile() {
        tileLetter = null;
        tileValue = -1;
        thisClicked = false;
        permanent = false;
        boardClicked = null;
        repaint();
    }

    boolean tileOccupied() {
        return tileLetter != null;
    }

    boolean pieceSelected() {       // playerpiece from sidepanel clicked
        return (Rack.selected != null && !pieceEmpty());
    }

    boolean tileClicked() {     //board is clicked
        return boardClicked != null;
    }

    boolean pieceEmpty() {     // used in piece selected
        return Rack.selected != null && Rack.selected.empty;
    }

    void clickThis() {
        thisClicked = true;
        boardClicked = this;
        repaint();
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


        public void mousePressed(MouseEvent e) {   //truth table approach here
            if (!permanent) {
                boolean TO, PS,TC;
                TO = tileOccupied();
                PS = pieceSelected();
                TC = tileClicked();
                if (TO && PS && TC) {
                    System.out.println("Impossible case. Case 1");
                } else if (!TO && PS && !TC) {
                    System.out.println("Move from clicked on rack to empty on board");
                    putOnBoard(Rack.selected);
                } else if (!TO && !PS && TC) {
                    putOnBoard(boardClicked);
                    System.out.println("Case 3");
                } else if (!TO && !PS && !TC) {
                    System.out.println("Case 4");
                } else if (TO && PS && TC) {
                    System.out.println("Case 5");
                } else if (TO && PS && !TC) {
                    Rack.selected.deselect();
                    System.out.println("Case 6");
                } else if (TO && !PS && TC) {
                    boardClicked.thisClicked = false;
                    boardClicked.repaint();
                    boardClicked = null;
                    System.out.println("Case 7");
                } else if (TO && !PS && !TC) {
                    clickThis();
                    System.out.println("Case 8");
                }
            }
        }
    }
}
