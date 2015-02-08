package board;

import javax.swing.*;
import java.awt.*;

class PieceHolder extends JPanel {
    private static LetterBag letterBag;
    public static PlayerPiece[] pieceArray;
    public static String[] pieces;
    Piece piece;
    JButton goButton, resetButton;

    public PieceHolder() {
        letterBag = new LetterBag();
        pieceArray = new PlayerPiece[7];
        pieces = new String[7];

        setLayout(new GridLayout(7, 1, 10, 5));
        setBackground(Color.WHITE);
        int count = 0;
        for (int i = 0; i < 7; i++) {
            pieces[i] = LetterBag.drawRandom();
            pieceArray[i] = new PlayerPiece(LetterBag.getPiece(pieces[i]));
            add(pieceArray[i]);
        }
    }

}
