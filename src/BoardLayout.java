import javax.swing.*;
import java.awt.*;

class BoardLayout extends JPanel {
private JPanel board, pieceHolder, sidePanel;
SidePanel sp;

BoardLayout(){
								setBackground(Color.WHITE);
								board = new ScrabbleBoard(this);
								sp = new SidePanel();
								add (board);
								add (sp);
}
}
