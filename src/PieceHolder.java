import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

class PieceHolder extends JPanel {
    Piece piece;
    public static LetterBag letterBag;
    public static PlayerPiece[] pieceArray;
    public static String[] pieces;
    JButton goButton, resetButton;

    PieceHolder(){

	letterBag = new LetterBag();
	pieceArray = new PlayerPiece[7];
	pieces = new String[7];

	setLayout(new GridLayout(7,1,10,5));
	//setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	setBackground(Color.WHITE);
	int count=0;
	for (int i = 0; i< 7 ; i++){
	    pieces[i] = letterBag.drawRandom();
	    pieceArray[i] = new PlayerPiece(LetterBag.getPiece(pieces[i]));
	    //JPanel whiteSpace1 = new JPanel();
	    //JPanel whiteSpace2 = new JPanel();

	    //whiteSpace1.setBackground(Color.WHITE);
	    //whiteSpace2.setBackground(Color.WHITE);

	    //add (whiteSpace1);
	    add (pieceArray[i]);
	    //add (whiteSpace2);
	}
    }

}
