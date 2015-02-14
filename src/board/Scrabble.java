package board;

/**
 * Created by nirajthapaliya on 2/13/15.
 */
class Scrabble extends PanelTemplate {

    Scrabble() {
        BoardLogic bl = new BoardLogic(this);
        add(new Board(bl));
        add(new SidePanel(bl));
    }
}