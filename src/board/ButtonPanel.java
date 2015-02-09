package board;

import wordsearch.Dawg;
import wordsearch.Utils;
import wordsearch.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

class ButtonPanel extends JPanel implements ActionListener {

    public static int additional, userScore, compScore;
    public static String lastBest;
    private Tile[][] board;
    private Tile[][] transBoard;
    private JButton goButton;
    Dawg dictionary;
    Game game;
// ComPlayer comPlayer;

    ButtonPanel(Game game) {
        this.game = game;
        goButton = new JButton("Go!");
        JButton loseButton = new JButton("Lose Turn");

        add(goButton);
        add(loseButton);

        goButton.addActionListener(this);
        loseButton.addActionListener(this);

        // comPlayer = new ComPlayer(this);
        ArrayList<String> list = new Utils().wordlist;
        dictionary = new Dawg(list);
        board = Board.board.tiles;
        transBoard = transpose(board);
    }

    Tile[][] transpose(Tile[][] board) {
        transBoard = new Tile[15][15];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                transBoard[i][j] = board[j][i];
            }
        }
        return transBoard;
    }


    void returnPiecesToBag() {
        for (int i = 0; i < 7; i++) {
            if (!PieceHolder.pieceArray[i].isEmpty) {
                game.AddToBag(PieceHolder.pieces[i].charAt(0));
            }
            PieceHolder.pieceArray[i].isEmpty = true;
        }
    }

    void drawNewPieces() {
        PlayerPiece.selected = null;
        int i = 0;
        while (game.remainingLetters() > 0 && i < 7) {
            if (PieceHolder.pieceArray[i].isEmpty) {
                PieceHolder.pieces[i] = ""+game.DrawRandom();

                PieceHolder.pieceArray[i].value =
                        Piece.getPiece(PieceHolder.pieces[i].charAt(0)).Value();
                PieceHolder.pieceArray[i].text = PieceHolder.pieces[i];
                PieceHolder.pieceArray[i].isEmpty = false;
                PieceHolder.pieceArray[i].color = Color.YELLOW;
                PieceHolder.pieceArray[i].repaint();
            }
            i++;
        }
        ScorePanel.scorePanel.repaint();
    }

    boolean onCenter() {
        return (Board.board.tiles[7][7].tileLetter != null);
    }

    ArrayList<String> getWords(Tile[][] theBoard) {
        ArrayList<String> list = new ArrayList<String>();
        int y = 0;

        for (int i = 0; i < 15; i++) {
            String s = "";
            boolean isNew = false;
            boolean isAdjacent = false;

            for (int j = 0; j < 15; j++) {
                if (theBoard[i][j].tileLetter != null) {
                    s = s + theBoard[i][j].tileLetter;
                    isNew = isNew || !theBoard[i][j].permanent;
                    isAdjacent = isAdjacent || isAdjacent(i, j, theBoard);
                } else if (isNew) {
                    s = s.trim();
                    list.add(s + " " + isAdjacent + " " + i + " " + (j - 1));
                    s = "";
                    isNew = false;
                } else if (j != 0 && theBoard[i][j - 1].tileLetter != null) {
                    s = "";
                } else s = s + " ";
                y = j - 1;
            }
            s = s.trim();

            if (!s.equals("") && isNew) {
                list.add(s + " " + isAdjacent + " " + i + " " + y);
                //System.out.println(s + "+" + isAdjacent);
            }
        }
        return list;
    }

    boolean isAdjacent(int x, int y, Tile[][] theBoard) {
        if (x == 7 && y == 7) return true;
        else if (theBoard[x][y].permanent) return true;
        else if (x > 0 && y > 0 && x < 14 && y < 14)
            return (theBoard[x][y + 1].permanent ||
                    theBoard[x + 1][y].permanent ||
                    theBoard[x - 1][y].permanent ||
                    theBoard[x][y - 1].permanent);
        else {
            int xmin = 0, ymin = 0, xmax = 0, ymax = 0;
            if (x == 0) {
                xmin = x;
                xmax = x + 1;
            } else if (x == 14) {
                xmin = x - 1;
                xmax = x;
            }
            if (y == 0) {
                ymin = y;
                ymax = y + 1;
            } else if (y == 14) {
                ymin = y - 1;
                ymax = y;
            }

            return (theBoard[x][ymax].permanent ||
                    theBoard[xmax][y].permanent ||
                    theBoard[xmin][y].permanent ||
                    theBoard[x][ymax].permanent);
        }
    }

    void makePermanent() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (board[i][j].tileLetter != null) {
                    board[i][j].permanent = true;
                    board[i][j].repaint();
                }
            }
        }
    }

    boolean checkSingleLine() {   // check if placed in single line
        int a = getWords(board).size();
        int b = getWords(transBoard).size();

        return !(a > 1 && b > 1);
    }

    boolean checkAdjacent() {
        ArrayList<String> a = getWords(board);
        ArrayList<String> b = getWords(transBoard);
        ArrayList<String> c = new ArrayList<String>();
        c.addAll(a);
        c.addAll(b);

        boolean adjacent = false;
        for (String s : c) {
            Scanner scanner = new Scanner(s);
            scanner.next();
            Boolean bool = Boolean.valueOf(scanner.next());
            adjacent = adjacent || bool;
        }
        return adjacent;
    }

    boolean checkSpelling() {
        ArrayList<String> a = getWords(board);
        ArrayList<String> b = getWords(transBoard);
        ArrayList<String> c = new ArrayList<String>();
        c.addAll(a);
        c.addAll(b);

        boolean spellingGood = true;

        for (String s : c) {
            Scanner scanner = new Scanner(s);
            String word = scanner.next();
            if (word.length() > 1)
                spellingGood = spellingGood && dictionary.Contains(word);
        }
        return spellingGood;
    }

    boolean checkValid() {
        return checkSingleLine() && checkAdjacent() && checkSpelling() && onCenter();
    }

    int getPoints() {
        lastBest = "";
        return getPoints(board) + getPoints(transBoard);
    }

    int getPoints(Tile[][] theBoard) {
        ArrayList<String> b = getWords(theBoard);
        int total = 0;
        for (String s : b) {
            Scanner scanner = new Scanner(s);
            String word = scanner.next();
            scanner.next();
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            int wordMulti = 1;
            int score = 0;

            if (word.length() > 1) {
                for (int i = y - word.length() + 1; i < y + 1; i++) {
                    wordMulti = wordMulti * theBoard[x][i].tileType.WordMultiplier();
                    score = score + theBoard[x][i].tileValue *
                            theBoard[x][i].tileType.LetterMultiplier();
                }
                score = score * wordMulti;
                //System.out.println(score);
                lastBest = lastBest + " " + word;
            }
            total = total + score;
        }
        return total;
    }

    // void computerTurn() {
    //     comPlayer.compTurn();
    // }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(goButton)) {

            if (checkValid()) {
                additional = getPoints();
                userScore = userScore + additional;
                ScorePanel.scorePanel.repaint();

                makePermanent();
                drawNewPieces();
//                computerTurn();
            } else if (!onCenter())
                JOptionPane.showMessageDialog
                        (Board.board,
                                "First word should lie across the center tile");
            else if (!checkSpelling())
                JOptionPane.showMessageDialog
                        (Board.board,
                                "Use valid words and make sure all your combinations are valid");
            else if (!checkSingleLine())
                JOptionPane.showMessageDialog
                        (Board.board,
                                "Main word should be a single unbroken line");
            else
                JOptionPane.showMessageDialog
                        (Board.board,
                                "Place letters adjacent to placed tiles");
        } else {
            String[] options = {"Yes definitely", "No, not really"};
            int result = JOptionPane.showOptionDialog
                    (Board.board, "Are you sure? Absolutely sure?",
                            "Skipping a step? Really?", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            boolean good = true;

            if (result == 0) {
                for (int i = 0; i < 7; i++) {
                    if (PieceHolder.pieceArray[i].isEmpty) {
                        JOptionPane.showMessageDialog
                                (Board.board,
                                        "Place all pieces back in piece holder please");
                        good = false;
                        break;
                    }

                }
                if (good) {
                    returnPiecesToBag();
                    if (game.remainingLetters() > 0) drawNewPieces();
//                    computerTurn();
                }
            }

        }

    }
}
