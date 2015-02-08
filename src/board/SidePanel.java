package board;

import javax.swing.*;
import java.awt.*;

class SidePanel extends JPanel {

    SidePanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.WHITE);

        add(new ScorePanel());
        add(new PieceHolder());
        add(new ButtonPanel());
    }
}
