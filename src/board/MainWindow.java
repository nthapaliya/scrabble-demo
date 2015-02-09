package board;

import javax.swing.*;
import java.awt.*;
import wordsearch.Game;

public class MainWindow extends JPanel {
    public Game game;

    public MainWindow() {
        game = new Game("Player 1", "Player 2");
        setBackground(Color.WHITE);
        add(new Board());
        add(new SidePanel(game));
    }
}
