package board;

import javax.swing.*;
import java.awt.*;

public class BoardLayout extends JPanel {
    private SidePanel sp;
    private JPanel pieceHolder;
    private JPanel sidePanel;

    public BoardLayout() {
        setBackground(Color.WHITE);
        JPanel board = new ScrabbleBoard(this);
        sp = new SidePanel();
        add(board);
        add(sp);
    }
}
