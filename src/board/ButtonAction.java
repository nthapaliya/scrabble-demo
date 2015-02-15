package board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nirajthapaliya on 2/15/15.
 */
public class ButtonAction implements ActionListener {
    BoardLogic bl;
    String[] labels;
    ButtonAction(BoardLogic bl, String[] labels) {
        this.bl = bl;
        this.labels = labels;
    }
    void goButton() {
        bl.fixUnfixed();
        bl.refillRack();
    }
    void loseButton() {
        String[] options = new String[]{"Yes", "Cancel"};
        int i = JOptionPane.showOptionDialog(
                null,
                new String[]{
                        "Lose turn and re-draw tiles?",
                        "Your current rack will be put back and you will draw a new set of tiles"},
                "Are you sure?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[1]
        );

        if (i == JOptionPane.OK_OPTION) {
            bl.putUnfixedBackOnRack();
            bl.putTilesBackInBag();
            bl.refillRack();
        }
    }
    void resetButton() {
        bl.putUnfixedBackOnRack();
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(labels[0])) {
            goButton();
        } else if (e.getActionCommand().equals(labels[1])) {
            loseButton();
        } else {
            resetButton();
        }
        bl.repaint();
    }
}
