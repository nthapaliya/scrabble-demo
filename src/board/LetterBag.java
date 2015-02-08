package board;

import java.util.ArrayList;
import java.util.Collections;

public class LetterBag {
    private static ArrayList<String> letters;
    private static int[] letterArray;

    LetterBag() {
        letters = new ArrayList<String>();
        letterArray = new int[26];

//      Now creates a new 'bag
        for (Piece p : Piece.values()) {
            for (int i = 0; i < p.Dist(); i++) {
                letters.add(p.toString());
                letterArray[p.Letter()-'a']++;
            }
        }
    }


    public static String drawRandom() {
        Collections.shuffle(letters);
        String st = letters.remove(0);
        letters.trimToSize();
        return st; // returns the top piece
    }

    public static int getSize() {
        return letters.size();
    }

    public static void addToBag(String s) {
        letters.add(s);
    }

    public static Piece getPiece(String str) {
        for (Piece p : Piece.values()) {
            if (str.equals(p.toString())) return p;
        }
        return null;
    }
}
