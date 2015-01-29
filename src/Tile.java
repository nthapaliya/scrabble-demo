import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.Scanner;

class Tile extends JPanel {

final int TILE_SIZE = 45;

TileType tileType;
String text1, text2;     // "DOUBLE" "WORD" etc
Color color,col;         // don't know
Tile currentTile, north, south, east, west;
public static Tile boardClicked;
public boolean permanent;

public int tileRow, tileCol;

boolean hover;

public String tileLetter;
public int tileValue;
boolean thisClicked;


Tile (int row, int col){
        permanent = false;
        tileRow = row; tileCol = col;

        tileType = TileType.getTileType(row, col);
        currentTile = this;

        setBackground(Color.WHITE);

        setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));

        addMouseListener( new HoverListener());
}

public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint
                (RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON );

        setDefault();

        g.setColor (Color.GRAY);
        g.fillRoundRect(0,0,TILE_SIZE-1, TILE_SIZE-1,10,10);


        if (hover&& tileLetter==null) g.setColor(color.darker());
        else g.setColor(color);

        g.fillRoundRect(0,0, TILE_SIZE-3, TILE_SIZE-3, 10,10);



        if (tileLetter==null) { // empty tile

                if (tileType!=TileType.CENTER_TILE &&
                    tileType!=TileType.PLAIN) {
                        getTileText();

                        g.setColor(Color.BLACK);
                        g.setFont(new Font("Courier Sans", Font.PLAIN, 11 ));
                        g.drawString (text1, (int)(TILE_SIZE-text1.length()*6)/2, 20);
                        g.drawString (text2,(int)(TILE_SIZE-text2.length()*6)/2, 35);


                }
        } else if (tileLetter!=null) {

                if (permanent) col = Color.ORANGE;
                else if (thisClicked) col = Color.GREEN;
                else col = Color.YELLOW.brighter();

                g.setColor(Color.BLACK);
                g.fillRect(2,2, TILE_SIZE-1, TILE_SIZE-1);
                g.drawRect(0,0, TILE_SIZE-3, TILE_SIZE-3);
                g.setColor(col);
                g.fillRect(1,1,TILE_SIZE-4,TILE_SIZE-4); //fill in square


                g.setColor(Color.BLACK);       // fill in text
                g.setFont (new Font("Courier Sans", Font.PLAIN, 36));
                g.drawString(tileLetter,7,35);

                g.setFont(new Font("Courier Sans", Font.BOLD, 12));
                g.drawString(tileValue+"", 31,44);
        }
}

void getTileText(){
        Scanner s = new Scanner(tileType.toString());
        text1 = s.next();
        if (s.hasNext()) text2 = s.next();
}

public void setDefault(){

        if (tileType == TileType.DOUBLE_WORD) {
                color = Color.PINK;

        }
        else if (tileType == TileType.TRIPLE_WORD) {
                color = Color.RED;
        }
        else if (tileType == TileType.DOUBLE_LETTER) {
                color = Color.BLUE.brighter();

        }
        else if (tileType == TileType.TRIPLE_LETTER) {
                color = Color.CYAN;

        }
        else if (tileType == TileType.CENTER_TILE) {
                color = Color.YELLOW;
        }
        else {
                color = new Color(255,204,102);
        }
}

public void setValues(PlayerPiece p){
        tileLetter = new String(p.toString());
        tileValue = p.value;
}
public void clearTile(){
        tileLetter = null;
        tileValue = -1;
        thisClicked = false;
        permanent = false;
        repaint();
}
public void placeTile(String s) {

        tileLetter = s;
        tileValue = Piece.getPiece(s).getValue();
        thisClicked = false;
        //permanent = false;
        repaint();
}


boolean tileOccupied(){
        return tileLetter != null;
}
boolean pieceSelected(){       // playerpiece from sidepanel clicked
        return (PlayerPiece.selected != null && !pieceEmpty());
}
boolean tileClicked(){     //board is clicked
        return boardClicked != null;
}
boolean pieceEmpty(){     // used in piece selected
        if (PlayerPiece.selected!=null) return PlayerPiece.selected.isEmpty;
        else return false;
}

void resetSelected(){
        PlayerPiece.selected.color = Color.YELLOW;
        PlayerPiece.selected.repaint();
}

class HoverListener extends MouseAdapter {
public void mouseEntered(MouseEvent e){
        hover = true;
        repaint();
}
public void mouseExited (MouseEvent e){
        hover = false;
        repaint();
}


public void mousePressed (MouseEvent e) {   //truth table approach here
        if (!permanent) {
          boolean TO, PS, TC;
          TO = tileOccupied();
          PS = pieceSelected();
          TC = tileClicked();

                if (tileOccupied() && pieceSelected() && tileClicked()) {
                        System.out.println("Case 1");
                } else if (!tileOccupied() && pieceSelected() && !tileClicked()) {
                        setValues (PlayerPiece.selected);
                        repaint();

                        PlayerPiece.selected.remove();
                        PlayerPiece.selected.repaint();
                        PlayerPiece.selected = null;

                        //System.out.println("Case 2");
                } else if (!tileOccupied() && !pieceSelected() && tileClicked()) {
                        tileLetter = new String(boardClicked.tileLetter);
                        tileValue = boardClicked.tileValue;
                        repaint();

                        boardClicked.clearTile();
                        boardClicked.repaint();
                        boardClicked = null;

                        //System.out.println("Case 3");
                } else if (!tileOccupied() && !pieceSelected() && !tileClicked()) {
                        //System.out.println("Case 4");
                } else if (tileOccupied() && pieceSelected() && tileClicked()) {
                        System.out.println("Case 5");
                } else if (tileOccupied() && pieceSelected() && !tileClicked()) {
                        PlayerPiece.selected.color = Color.YELLOW;
                        PlayerPiece.selected.repaint();
                        PlayerPiece.selected = null;


                        //System.out.println("Case 6");
                } else if (tileOccupied() && !pieceSelected() && tileClicked()) {
                        boardClicked.thisClicked = false;
                        boardClicked.repaint();

                        boardClicked = null;
                        //System.out.println("Case 7");
                } else if (tileOccupied() && !pieceSelected() && !tileClicked()) {
                        thisClicked = true;
                        boardClicked = currentTile;
                        repaint();

                        //System.out.println("Case 8");
                }
        }
}
}
}