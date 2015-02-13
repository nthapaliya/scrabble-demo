package board;

import wordsearch.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonPanel extends JPanel implements ActionListener {
    Game game = MainWindow.game;
    Tile[][] tiles = MainWindow.tiles;

    JButton goButton, loseButton, reset;
    ButtonPanel() {

        goButton = new JButton("Go!");
        loseButton = new JButton("Lose Turn");
        reset = new JButton("Reset");

        goButton.addActionListener(this);
        loseButton.addActionListener(this);
        reset.addActionListener(this);

        add(goButton);
        add(loseButton);
        add(reset);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(goButton)) {

        } else if (e.getSource().equals(loseButton)) {
        } else { // resetButton;

        }

    }
}
