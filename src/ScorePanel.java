import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

class ScorePanel extends JPanel {
public static ScorePanel scorePanel;

ScorePanel() {
        setPreferredSize(new Dimension(200,150));
        scorePanel = this;
        setBackground(Color.WHITE);
}
public void paintComponent (Graphics g) {
        super.paintComponent (g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint
                (RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON );


        g.setColor(Color.BLACK);
        g.drawRoundRect (1,1, 195, 140, 10, 10);
        g.setFont(new Font("Courier Sans", Font.PLAIN, 12 ));

        g.drawString("Pieces remaining: "+ LetterBag.getSize()+ " ", 8, 20);
        g.drawString("Player Score:  "+ ButtonPanel.userScore, 8, 40);
        g.drawString("Comp Score:  "+ ButtonPanel.compScore, 8, 60);
        g.drawString("Latest word(s) played", 8, 80);
        if (ButtonPanel.lastBest!=null) {
                g.setFont(new Font("Courier Sans", Font.BOLD, 12 ));
                g.drawString(ButtonPanel.lastBest, 20,100);
        }
        g.setFont(new Font("Courier Sans", Font.PLAIN, 12 ));
        g.drawString("for a score: " + ButtonPanel.additional,8, 120);

}
}
