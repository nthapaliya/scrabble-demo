package board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nirajthapaliya on 2/14/15.
 */
class ButtonPanel extends PanelTemplate implements ActionListener {
    final JButton goButton;
    final JButton loseButton;
    final JButton reset;
    BoardLogic bl;

    ButtonPanel(BoardLogic bl) {
        this.bl = bl;
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
            bl.fixUnfixed();
            bl.refillRack();

        } else if (e.getSource().equals(loseButton)) {
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

            switch (i) {
                case JOptionPane.OK_OPTION:
                    bl.putUnfixedBackOnRack();
                    bl.putTilesBackInBag();
                    bl.refillRack();
                    return;
                default:
                    return;
            }

        } else { // resetButton;
            System.out.println("reset button");
            bl.putUnfixedBackOnRack();
        }
        bl.repaint();
    }
}
