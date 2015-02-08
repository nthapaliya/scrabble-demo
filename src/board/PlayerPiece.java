package board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayerPiece extends JPanel {

    public static PlayerPiece selected;
    private Piece piece;
    public int value;
    public boolean isEmpty;
    private PlayerPiece currentPiece;
    Color color;
    String text;

    PlayerPiece(Piece p) {
        currentPiece = this;
        color = Color.YELLOW;
        piece = p;
        isEmpty = false;

        setPreferredSize(new Dimension(60, 60));

        setBackground(Color.WHITE);

        text = piece.toString();
        value = piece.getValue();

        addMouseListener(new HoverListener());
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        Graphics2D g2d = (Graphics2D) g; // smooth fonts
        g2d.setRenderingHint
                (RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (value != -1) {
            g.setColor(Color.BLACK);
            g.fillRect(1, 1, 60, 60);

            g.setColor(color);

            g.fillRect(0, 0, 58, 58);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Courier Sans", Font.PLAIN, 36));
            g.drawString(text, 13, 40);

            g.setFont(new Font("Courier Sans", Font.BOLD, 12));
            g.drawString(value + "", 42, 50);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 60, 60);
            g.setColor(Color.GRAY);
            g.fillRect(2, 2, 58, 58);
        }
    }

    public void remove() {
        value = -1;
        text = null;
        repaint();
        isEmpty = true;
    }

    public String toString() {
        return text;
    }


    private class HoverListener extends MouseAdapter {
        public void mouseEntered(MouseEvent e) {
            if (selected != currentPiece) color = Color.YELLOW.darker();
            repaint();
        }

        public void mouseExited(MouseEvent e) {
            if (selected != currentPiece) color = Color.YELLOW;
            repaint();

        }

        public void mousePressed(MouseEvent e) {
            if (isEmpty) {
                if (selected == null && Tile.boardClicked == null) {
                    System.out.println("s Case 1");
                } else if (selected != null && Tile.boardClicked == null) {
                    text = selected.text;
                    value = selected.value;
                    repaint();
                    selected.remove();

                    isEmpty = false;
                    selected = null;
                    //System.out.println("s Case 2");
                } else if (selected == null && Tile.boardClicked != null) {
                    text = Tile.boardClicked.tileLetter;
                    value = Tile.boardClicked.tileValue;

                    Tile.boardClicked.clearTile();
                    Tile.boardClicked.repaint();
                    Tile.boardClicked = null;

                    isEmpty = false;
                    repaint();

                    //System.out.println("s Case 3");
                } else if (selected != null && Tile.boardClicked != null) {
                    System.out.println("s Case 4");
                }
            } else {
                if (selected == null && Tile.boardClicked == null) {
                    selected = currentPiece;
                    color = Color.GREEN.darker();
                    repaint();
                    //System.out.println("s Case 5");
                } else if (selected != null && Tile.boardClicked == null) {
                    selected.color = Color.YELLOW;
                    selected.repaint();
                    selected = null;

                    //System.out.println("s Case 6");
                } else if (selected == null && Tile.boardClicked != null) {
                    Tile.boardClicked.thisClicked = false;
                    Tile.boardClicked.repaint();
                    Tile.boardClicked = null;

                    //System.out.println("s Case 7");
                } else if (selected != null && Tile.boardClicked != null) {
                    System.out.println("s Case 8");
                }
            }
        }

    }
}
