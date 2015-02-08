import java.util.ArrayList;
import wordsearch.Game;
class Snippet {

    public static void main(String[] args){
//        ArrayList<String> l = ReadFile(".../resources/enable1.txt");
//        Dawg dg = new Dawg(l);
//
//        for (String word: l) {
//          if (!dg.Contains(word)) System.out.println("Something went wrong");
//        }
//        System.out.println("ok");
    Game game = new Game("p1", "p2");

    }

    public static ArrayList<String> ReadFile(String filename){
        ArrayList<String> l = new ArrayList<String>();

        java.util.Scanner inpt;
        java.io.FileReader r;

        try {
                r = new java.io.FileReader(filename);
                inpt = new java.util.Scanner(r);
                while (inpt.hasNext()) {
                        l.add(inpt.next());
                }
                inpt.close();
        } catch (java.io.FileNotFoundException e) {
                System.out.println("File not found");
                System.exit(1);
        }

        return l;
}

}
