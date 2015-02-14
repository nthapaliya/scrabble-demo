// Niraj Thapaliya, 2015

import javax.swing.*;

class Scrabble extends JFrame {
    Scrabble() {
        super("Scrabble 0.1a");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setContentPane(new board.Scrabble());
        pack();
        setVisible(true);
    }

    public static void main (String[] args){
        new Scrabble();
}

}
