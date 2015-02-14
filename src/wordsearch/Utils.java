package wordsearch;

import java.util.ArrayList;

/**
 * Created by nirajthapaliya on 2/8/15.
 */
public class Utils {

    public Utils() {
        ArrayList<String> wordList = ReadFile("../resources/enable1.txt");
    }

    private static ArrayList<String> ReadFile(String filename) {
        ArrayList<String> l = new ArrayList<String>();

        java.util.Scanner input;
        java.io.FileReader r;

        try {
            r = new java.io.FileReader(filename);
            input = new java.util.Scanner(r);
            while (input.hasNext()) {
                l.add(input.next());
            }
            input.close();
        } catch (java.io.FileNotFoundException e) {
            System.out.printf("File %s not found\n", filename);
            System.exit(1);
        }
        return l;
    }

}
