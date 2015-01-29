import java.util.ArrayList;
import java.util.Collections;

class LetterBag {

private static ArrayList<String> letters;

LetterBag(){
								letters = new ArrayList<String>();
								create();
}
private static void create(){
								for (Piece p: Piece.values()) {
																for (int i = 0; i<p.getDist(); i++) letters.add(p.toString());
								}
}
public static String drawRandom(){
								Collections.shuffle(letters);
								String st = new String(letters.remove(0));
								letters.trimToSize();
								return st; // returns the top piece
}
public static int getSize(){
								return letters.size();
}
public static void addToBag(String s){
								letters.add(s);
}
public static Piece getPiece (String str) {
								for (Piece p: Piece.values()) {
																if (str.equals(p.toString())) return p;
								}
								return null;
}

}
