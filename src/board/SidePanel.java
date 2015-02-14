package board;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nirajthapaliya on 2/14/15.
 */
class SidePanel extends PanelTemplate {

    SidePanel(BoardLogic bl) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        add(new ScorePanel(bl));
        add(new Rack(bl));
        add(new ButtonPanel(bl));
    }

    class Rack extends PanelTemplate {
        Rack(BoardLogic bl) {
            setLayout(new GridLayout(7, 1, 0, 5));
            for (int i = 0; i < 7; i++) {
                char c = bl.game.DrawRandom();
                bl.rack[i] = new Tile(-1, -1);
                bl.rack[i].put(c);
                add(bl.rack[i]);
            }
        }
    }

    class ScorePanel extends PanelTemplate {
        BoardLogic bl;

        ScorePanel(BoardLogic bl) {
            this.bl = bl;
            setPreferredSize(new Dimension(200, 150));
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint
                    (RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);


            g.setColor(Color.BLACK);
            g.drawRoundRect(1, 1, 195, 140, 10, 10);
            g.setFont(new Font("Courier Sans", Font.PLAIN, 12));

            g.drawString("Pieces remaining: " + bl.game.remainingLetters() + " ", 8, 20);
            g.drawString("Player Score:  " + bl.game.Player1Score(), 8, 40);
            g.drawString("Comp Score:  " + bl.game.Player2Score(), 8, 60);

            String lastWord = bl.game.LastWord();
            int lastScore = bl.game.LastScore();

            if (lastWord != null) {
                g.drawString("Latest word(s) played", 8, 80);
                g.setFont(new Font("Courier Sans", Font.BOLD, 12));
                g.drawString(lastWord, 20, 100);
                g.setFont(new Font("Courier Sans", Font.PLAIN, 12));
                g.drawString("for a score: " + lastScore, 8, 120);

            }
        }
    }
}
