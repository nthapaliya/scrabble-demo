package board;

import javax.swing.*;
import java.awt.*;
import wordsearch.Game;

class Rack extends JPanel {
    public static PlayerPiece[] pieceArray;
    public static String[] pieces;
    Game game;
    public Rack(Game game) {
        this.game = game;
        pieceArray = new PlayerPiece[7];
        pieces = new String[7];

        setLayout(new GridLayout(7, 1, 10, 5));
        setBackground(Color.WHITE);
        for (int i = 0; i < 7; i++) {
            char c = game.DrawRandom();
            pieces[i] = "" + c;
            pieceArray[i] = new PlayerPiece(c);
            add(pieceArray[i]);
        }
    }
}
