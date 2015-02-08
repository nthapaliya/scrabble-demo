// Niraj Thapaliya, 2015

import board.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

class Scrabble extends JFrame {

Scrabble(){
        super("Scrabble 0.1a");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(new BoardLayout());
        pack();
        setVisible(true);
}

public static void main (String[] args){
        new Scrabble();
}
}
