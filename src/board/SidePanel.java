package board;

import javax.swing.*;
import java.awt.*;
import wordsearch.Game;

class SidePanel extends JPanel {

    SidePanel(Game game) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.WHITE);

        add(new ScorePanel(game));
        add(new PieceHolder(game));
        add(new ButtonPanel(game));
    }
}
