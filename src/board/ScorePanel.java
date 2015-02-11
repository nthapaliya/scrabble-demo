package board;

import wordsearch.Game;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    Game game;

    ScorePanel(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(200, 150));
        setBackground(Color.WHITE);
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

        g.drawString("Pieces remaining: " + game.remainingLetters() + " ", 8, 20);
        g.drawString("Player Score:  " + game.Player1Score(), 8, 40);
        g.drawString("Comp Score:  " + game.Player2Score(), 8, 60);

        String lastWord = game.LastWord();
        int lastScore = game.LastScore();

        if (lastWord != null) {
            g.drawString("Latest word(s) played", 8, 80);
            g.setFont(new Font("Courier Sans", Font.BOLD, 12));
            g.drawString(lastWord, 20, 100);
            g.setFont(new Font("Courier Sans", Font.PLAIN, 12));
            g.drawString("for a score: " + lastScore, 8, 120);

        }
    }
}
