import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

class SidePanel extends JPanel {

SidePanel(){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.WHITE);

        add (new ScorePanel());
        add (new PieceHolder());
        add (new ButtonPanel());
}
}
