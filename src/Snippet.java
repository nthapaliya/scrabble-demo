import java.util.ArrayList;
import java.util.Collections;

class Snippet {

public static void main(String[] args){
        Dawg dg = new Dawg();
        ArrayList<String> l = ReadFile();
        dg.AddFromAlphabetic(l);

        for (String word: l) {
          if (!dg.contains(word)) {
            System.out.println("Something went wrong");
          }
        }
        System.out.println("ok");
}

public static ArrayList<String> ReadFile(){
        ArrayList<String> l = new ArrayList<String>();

        java.util.Scanner inpt;
        java.io.FileReader r;

        try {
                r = new java.io.FileReader("../resources/enable1.txt");
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
