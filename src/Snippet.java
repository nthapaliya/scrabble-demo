import wordsearch.Game;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
class Snippet extends JPanel {

    public static void main(String[] args){
        new Snippet();
    }

    public Snippet () {
        String message = "This is my message";
        char[] charArray = message.toCharArray();

        Font font = new Font("Courier Sans", Font.PLAIN, 12);
        FontMetrics fm = getFontMetrics(font);

        System.out.printf("%s and %d\n", message, fm.charsWidth(charArray, 0, charArray.length));

    }
    public void paintComponent(Graphics g) {

    }

}
