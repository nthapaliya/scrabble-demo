import java.util.ArrayList;

public class Search extends Trie {

private java.util.Scanner inpt;
private java.io.FileReader r;

public Search(){
  super();
  // this.AddFromAlphabetic(ReadFile());
  ArrayList<String> l = ReadFile();
  for (String s: l) {
    this.add(s);
  }
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
