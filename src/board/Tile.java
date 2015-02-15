package board;

import wordsearch.Letters;
import wordsearch.Tiles;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by nirajthapaliya on 2/14/15.
 */
public class Tile extends PanelTemplate {
    static Tile selected;
    private final Font TEXT_FONT = new Font("Courier Sans", Font.PLAIN, 10);
    private final Font LETTER_FONT = new Font("Courier Sans", Font.PLAIN, 26);
    private final Font VALUE_FONT = new Font("Courier Sans", Font.BOLD, 10);
    private final Color LETTER_COLOR = Color.YELLOW.brighter();
    private final Color LETTER_SELECTED = Color.GREEN;
    private final Color LETTER_PERMANENT = Color.ORANGE;
    private final int TILE_SIZE = 40;
    private final Tiles TILE;
    private final String[] TILE_TEXT;
    private final int[] TILE_TEXT_WIDTH;
    private final Color TILE_COLOR;
    private boolean permanent, hover;
    private char c;

    public Tile() {
        this(-1, -1);
    }

    public Tile(int row, int col) {
        TILE = Tiles.GetType(row, col);
        TILE_COLOR = getColor(TILE);
        TILE_TEXT = TILE.toString().split("\\s");
        TILE_TEXT_WIDTH = new int[]{
                getTextWidth(TILE_TEXT[0], TEXT_FONT),
                getTextWidth(TILE_TEXT[1], TEXT_FONT)
        };

        setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
        addMouseListener(new HoverListener());
    }

    int getTextWidth(String text, Font font) {
        FontMetrics fm = getFontMetrics(font);
        return fm.stringWidth(text);
    }

    int getTextHeight(Font font) {
        FontMetrics fm = getFontMetrics(font);
        return fm.getAscent() - fm.getDescent();
    }

    Color getColor(Tiles tile) {
        switch (tile) {
            case DOUBLE_WORD:
                return Color.PINK;
            case TRIPLE_WORD:
                return Color.RED;
            case DOUBLE_LETTER:
                return new Color(0x52A5FF);
            case TRIPLE_LETTER:
                return Color.CYAN;
            case CENTER_TILE:
                return Color.RED.darker();
            case PLAIN:
                return new Color(0xFFF8AD);
            default:
                return Color.GRAY;
        }
    }

    char getLetter() {
        return c;
    }

    void put(char c) {
        this.c = c;
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
        repaint();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint
                (RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (isEmpty()) {
            // Draws the appropriate square (double word in blue, etc)
            g.setColor(TILE_COLOR);
            if (hover && !isPermanent()) {
                g.setColor(TILE_COLOR.darker());
            }
            g.fillRoundRect(0, 0, TILE_SIZE, TILE_SIZE, 10, 10);

            if (TILE != Tiles.CENTER_TILE && TILE != Tiles.PLAIN && TILE != Tiles.BLANK) {
                g.setColor(Color.BLACK);
                g.setFont(TEXT_FONT);
                int h = getTextHeight(TEXT_FONT);
                int y1, y2;
                int c = 6;
                y1 = (TILE_SIZE - c)/2;
                y2 = (TILE_SIZE + (2 * h) + c)/2;

                g.drawString(TILE_TEXT[0], (TILE_SIZE - TILE_TEXT_WIDTH[0]) / 2, y1);
                g.drawString(TILE_TEXT[1], (TILE_SIZE - TILE_TEXT_WIDTH[1]) / 2, y2);
            }
        } else {
            Color color = LETTER_COLOR;
            if (hover & !isPermanent() & !isSelected())
                color = LETTER_COLOR.darker();
            else if (isSelected())
                color = LETTER_SELECTED;
            else if (isPermanent()) color = LETTER_PERMANENT;

            assert isSelected() != isPermanent();

            g.setColor(color);
            g.fillRoundRect(0, 0, TILE_SIZE, TILE_SIZE, 10, 10); //fill in square

            String letter = ("" + c).toUpperCase();
            String value = ("" + Letters.Value(c));

            g.setColor(Color.BLACK);

            g.setFont(LETTER_FONT);
            int letterX = (TILE_SIZE - getTextWidth(letter, LETTER_FONT)) / 2;
            int letterY = getTextHeight(LETTER_FONT);
            letterY += (TILE_SIZE - letterY) / 2;
            g.drawString(letter, letterX, letterY);

            g.setFont(VALUE_FONT);
            int valueLen = getTextWidth(value, VALUE_FONT);
            g.drawString(value, TILE_SIZE - valueLen - 3, TILE_SIZE - 3);
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
