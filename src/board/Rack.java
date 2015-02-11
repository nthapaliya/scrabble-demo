package board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import wordsearch.Game;
import wordsearch.Letters;

class Rack extends JPanel {
    int[] playerRack;
    Game game;
    static PlayerPiece selected;

    public Rack(Game game) {
        this.game = game;
        setLayout(new GridLayout(7, 1, 10, 5));
        setBackground(Color.WHITE);
        playerRack = new int[26];

        for (int i = 0; i < 7; i++) {
            char c = game.DrawRandom();
            playerRack[c-'a']++;
            add(new PlayerPiece(c));
        }
    }

    class PlayerPiece extends JPanel {
//        PlayerPiece currentPiece;
        char c;
        String text;
        int value;
        Color color;
        boolean empty;

        PlayerPiece(char c) {
            this.c = c;
            color = Color.YELLOW;

            setPreferredSize(new Dimension(60, 60));
            setBackground(Color.WHITE);
            AddPiece(c);
            addMouseListener(new HoverListener());
        }
        void RemovePiece(){
            empty = true;
            repaint();
        }
        void AddPiece(char c) {
            empty = false;
            refreshValues(c);
            repaint();
        }
        void refreshValues(char c) {
            this.c = c;
            text = ("" + c).toUpperCase();
            value = Letters.Value(c);
        }
        boolean isSelected() {
            return selected == this;
        }
        void selectThis() {
            selected = this;
            color = Color.GREEN.darker();
            repaint();
        }

        void deselect() {
            selected.color = Color.YELLOW;
            selected.repaint();
            selected = null;
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g; // smooth fonts
            g2d.setRenderingHint
                    (RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            if (!empty) {
                g.setColor(Color.BLACK);
                g.fillRect(1, 1, 60, 60);

                g.setColor(color);

                g.fillRect(0, 0, 58, 58);

                g.setColor(Color.BLACK);
                g.setFont(new Font("Courier Sans", Font.PLAIN, 36));
                g.drawString(text, 13, 40);

                g.setFont(new Font("Courier Sans", Font.BOLD, 12));
                g.drawString(value + "", 42, 50);
            } else {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 60, 60);
                g.setColor(Color.GRAY);
                g.fillRect(2, 2, 58, 58);
            }
        }

        private class HoverListener extends MouseAdapter {
            public void mouseEntered(MouseEvent e) {
                if (!isSelected()) {
                    color = Color.YELLOW.darker();
                    repaint();
                }
            }

            public void mouseExited(MouseEvent e) {
                if (!isSelected()) {
                    color = Color.YELLOW;
                    repaint();
                }
            }

            public void mousePressed(MouseEvent e) {
                if (empty) {
                    if (selected == null && Tile.boardClicked == null) {
                        System.out.println("Rack to Rack: nothing selected, click on empty");
                    } else if (selected != null && Tile.boardClicked == null) {
                        System.out.println("Rack to rack, rack piece selected, click on empty");

                        AddPiece(selected.c);
                        selected.RemovePiece();
                        selected = null;

                    } else if (selected == null && Tile.boardClicked != null) {
                        System.out.println("Board to Rack, board piece selected, click on empty");

                        c = Tile.boardClicked.c;
                        AddPiece(c);
                        Tile.boardClicked.clearTile();

                    } else if (selected != null && Tile.boardClicked != null) {
                        System.out.println("Impossible case. Tiles on rack AND board selected, click on empty");
                    }
                } else {
                     if (selected != null && Tile.boardClicked == null) {
                         System.out.println("Rack to Rack, selected to occupied rack tile");
                        //Deselect selected
                         deselect();
                     } else if (selected == null && Tile.boardClicked == null) {
                         System.out.println("Rack to Rack, nothing selected, click on occupied");
                         selectThis();

                     } else if (selected == null && Tile.boardClicked != null) {
                         System.out.println("Board to Rack, click on occupied");
                         // deselect tile on board
                         Tile.boardClicked.thisClicked = false;
                         Tile.boardClicked.repaint();
                         Tile.boardClicked = null;
                    } else if (selected != null && Tile.boardClicked != null) {
                        System.out.println("Impossible case. Tile on rack AND board selected, click on occupied");
                    }
                }
            }

        }
    }

}
