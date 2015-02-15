package board;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nirajthapaliya on 2/13/15.
 */
public class MainWindow extends PanelTemplate {
    public MainWindow() {
        BoardLogic bl = new BoardLogic(this);

        add(createBoard(bl));
        add(createSidePanel(bl));
    }

    PanelTemplate createBoard(BoardLogic bl) {
        PanelTemplate board = new PanelTemplate();
        board.setLayout(new GridLayout(15, 15, 1, 1));

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                bl.tiles[i][j] = new Tile(i, j);
                board.add(bl.tiles[i][j]);
            }
        }
        return board;
    }

    PanelTemplate createRack (BoardLogic bl) {
        PanelTemplate rackPanel = new PanelTemplate();
        rackPanel.setLayout(new GridLayout(7, 1, 0, 5));

        for (int i = 0; i < 7; i++) {
            char c = bl.game.DrawRandom();
            bl.rack[i] = new Tile(-1, -1);
            bl.rack[i].put(c);
            rackPanel.add(bl.rack[i]);
        }
        return rackPanel;
    }

    PanelTemplate createSidePanel(BoardLogic bl) {
        PanelTemplate sp = new PanelTemplate();

        sp.setLayout(new BoxLayout(sp, BoxLayout.PAGE_AXIS));

        sp.add(new ScorePanel(bl));
        sp.add(createRack(bl));
        sp.add(createButtonPanel(bl));

        return sp;
    }

    PanelTemplate createButtonPanel(BoardLogic bl) {
        String[] labels = new String[] {"Go!", "Lose Turn", "Reset"};

        PanelTemplate bp = new PanelTemplate();
        ButtonAction ba = new ButtonAction(bl, labels);
        bp.setLayout(new BoxLayout(bp, BoxLayout.PAGE_AXIS));


        for (String label: labels) {
            JButton button = new JButton(label);
            button.addActionListener(ba);
            bp.add(button);
        }
        return bp;
    }

    private class ScorePanel extends PanelTemplate {
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