// Niraj Thapaliya, 2015

import board.MainWindow;

import javax.swing.*;
class Scrabble extends JFrame {
    Scrabble() {
        super("Scrabble 0.1a");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        board.MainWindow mw = new board.MainWindow();
        setContentPane(mw);
        pack();
        setVisible(true);
    }

    public static void main (String[] args){
        new Scrabble();
}

}
