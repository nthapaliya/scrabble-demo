// Niraj Thapaliya, 2015

import board.MainWindow;

import javax.swing.*;

class Scrabble extends JFrame {

Scrabble(){
        super("Scrabble 0.1a");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(new MainWindow());
        pack();
        setVisible(true);
}

public static void main (String[] args){
        new Scrabble();
}
}
