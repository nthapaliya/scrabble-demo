package board;

import javax.swing.*;
import java.awt.*;
import wordsearch.Game;

class PieceHolder extends JPanel {
    public static PlayerPiece[] pieceArray;
    public static String[] pieces;
    Piece piece;
    Game game;
    public PieceHolder(Game game) {
        this.game = game;
        pieceArray = new PlayerPiece[7];
        pieces = new String[7];

        setLayout(new GridLayout(7, 1, 10, 5));
        setBackground(Color.WHITE);
        int count = 0;
        for (int i = 0; i < 7; i++) {
            char c = game.DrawRandom();
            pieces[i] = "" + c;
            System.out.printf("char c is %c\n",c);
            pieceArray[i] = new PlayerPiece(Piece.getPiece(c));
            add(pieceArray[i]);
        }
    }
}
