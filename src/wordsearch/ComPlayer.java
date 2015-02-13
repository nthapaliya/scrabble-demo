package wordsearch;

class ComPlayer extends Player {

    int bestScore;
    String bestWord;

ComPlayer() {
    super("Computer");

        // while (LetterBag.getSize() > 0 && comPieces.size() < 7) {
        //         String piece = LetterBag.drawRandom();
        //         piece = piece.toLowerCase();
        //         comPieces.add(piece);
        //         // System.out.print(piece);
        // }
        // System.out.println();
}

public void compTurn(){
        // board = buttonPanel.board;
        // transBoard = buttonPanel.transBoard;

        bestScore = 0;
        bestWord = "";

        // tryWords(board);
        // tryWords(transBoard);
        //
        // if (bestScore > 0)
        //         play (bestWord);
        // else {
        //         while (comPieces.size() > 0) {
        //                 LetterBag.addToBag(comPieces.remove(0));
        //         }
        //         while (LetterBag.getSize()>0 && comPieces.size() < 7) {
        //                 String pieces = LetterBag.drawRandom().toLowerCase();
        //                 comPieces.add(pieces);
        //         }
        //         System.out.println();
        //         JOptionPane.showMessageDialog
        //                 (Board.board,
        //                 "Computer decided to lose turn");
        // }
}

void play (String s) {
        // Tile[][]  theBoard = new Tile[15][15];
        // Scanner scn = new Scanner(s);
        // String word = scn.next();
        // int x = scn.nextInt();
        // int y = scn.nextInt();
        // int score = scn.nextInt();
        // boolean trans = new Boolean(scn.next());
        // word = word.toUpperCase();
        //
        // System.out.println (word);
        //
        // if (trans) theBoard = transBoard;
        // else theBoard = board;
        //
        // for (int i = y - word.length() + 1; i < y + 1; i++) {
        //         theBoard[x][i].placeTile(""+ word.charAt(i - y + word.length() - 1));
        // }
        // buttonPanel.makePermanent();
        // ButtonPanel.lastBest = word;
        // ButtonPanel.additional = score;
        // ButtonPanel.compScore = ButtonPanel.compScore + score;
        // ScorePanel.scorePanel.repaint();
        //
        // for (int j=0; j < word.length(); j++) {
        //         comPieces.remove (""+s.charAt(j));
        // }
        // fillPieces();

}


void tryWords () {   // this method finds "anchors"
        // for (int i = 0; i < 15; i++) {
        //         int y = 0;
        //         for (int j = 0; j < 15; j++) {
        //                 if (buttonPanel.isAdjacent(i, j, theBoard)) {
        //                         y = j;
        //                         break;
        //                 }
        //         }
        //         if (buttonPanel.isAdjacent(i, y, theBoard)) {
        //                 LeftPart("", i, y, y, theBoard);
        //                 //break;
        //         }
        // }
}

void putWords (String s, int x, int y) { /////////////////////////
        // int start = y - s.length() + 1;            ////////////////////////////
        //
        // for (int i = start; i < y; i++) {
        //         String c = "" + s.toUpperCase().charAt(i-start);
        //         theBoard[x][i].placeTile(c);
        // }
        // boolean isTranspose = (theBoard == transBoard);
        //
        // if(buttonPanel.checkValid()) {
        //         int score = buttonPanel.getPoints();
        //         if (score > bestScore) {
        //                 bestScore = score;
        //                 bestWord = s + " " + x + " " + y + " " + score + " " + isTranspose;
        //         }
        // }
        //
        //
        // Board.clearBoard();
}

// void LegalMove(String s, int x, int y, Tile[][] theBoard) {
//         putWords (s, x, y, theBoard);
// }

// void LeftPart (String partialWord, int x, int y, int limit, Tile[][] theBoard) {
//         ExtendRight (partialWord, x, y + 1, theBoard);
//         ArrayList<String> children = search.getChildNodes(partialWord);
//
//         if (limit > 0) {
//                 for (String edgeE: children) {
//                         if (comPieces.contains(edgeE)) {
//                                 comPieces.remove(edgeE);
//                                 LeftPart (partialWord + edgeE, x, y, limit - 1, theBoard);
//                                 comPieces.add(edgeE);
//                         }
//                 }
//         }
// }

// void ExtendRight (String partialWord, int x, int y, Tile [][] theBoard) {
//         if (y < 15) {
//                 if (!theBoard[x][y].tileOccupied()) { // case: empty tile
//                         ArrayList<String> children = search.getChildNodes (partialWord);
//                         for (String edgeE: children) {
//                                 if (comPieces.contains(edgeE)
//                                     && crossCheck(x, y, theBoard, edgeE)) {
//                                         comPieces.remove(edgeE);
//
//                                         if (search.contains(partialWord+edgeE)) { //////////
//                                                 if (y == 14)
//                                                         LegalMove (partialWord + edgeE, x, y, theBoard);
//                                                 else if (!theBoard[x][y+1].tileOccupied()) {
//                                                         LegalMove (partialWord + edgeE, x, y, theBoard);
//                                                 }
//                                         }
//
//                                         ExtendRight (partialWord + edgeE, x, y + 1, theBoard);
//                                         comPieces.add(edgeE);
//                                 }
//                         }
//
//                 } else { // case: occupied tile
//                         String l = theBoard[x][y].tileLetter.toLowerCase();
//                         if (search.hasNextNode (partialWord, l)) {
//                                 ExtendRight(partialWord + l, x, y + 1, theBoard);
//                         }
//                 }
//         }
// }

// boolean crossCheck(int x, int y, Tile[][] theBoard, String edgeE) {
//         String s = "";
//
//         for (int i = 0; i < 15; i++) {
//                 if (theBoard[i][y].tileOccupied())
//                         s = s + theBoard[i][y].tileLetter;
//                 else if (i == x) s = s + "*";
//                 else s = s + " ";
//         }
//         s = s.trim();
//         try {
//                 if (s.equals("*")) return true;
//                 else if (s.indexOf(" ") == -1 && s.indexOf("*")!=-1) {
//                         s = s.replace('*', edgeE.charAt(0));
//                         return search.contains(s);
//                 } else { // case where it has spaces in between
//                         s.replace('*', edgeE.charAt(0));
//                         return false;
//                 }
//         } catch (ArrayIndexOutOfBoundsException e) {
//                 System.out.println (s + " " + x +" " + y);
//         }
//         return false;
// }

// public void fillPieces(){
//         while(comPieces.size() < 7 && LetterBag.getSize() > 0) {
//                 comPieces.add( LetterBag.drawRandom().toLowerCase());
//         }
// }
}
