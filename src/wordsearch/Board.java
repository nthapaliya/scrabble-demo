package wordsearch;

/**
 * Created by nirajthapaliya on 2/9/15.
 */
class Board {
    char[][] state;
    int[][] crossSets;


    Board() {
        state = new char[15][15];
        crossSets = new int[15][15];
        // In an empty board, all letters are valid. Hence the full cross set.
        // The int allLetters is a bitSet corresponding to the alphabet.
        for (int i = 0; i < 15; i++) {
            for (int j=0; j < 15; j++) {
                crossSets[i][j] = BitSet.allLetters;
            }
        }
    }

    String ErrMsg(char[][] newState) {
        return null;
    }
    void computeCrossSets() {

    }
    char[][] transpose() {
        char[][] b = new char[15][15];
        for (int i = 0; i < 15; i++ ) {
            for (int j = 0; j < 15; j++) {
                b[i][j] = state[j][i];
            }
        }
        return b;
    }
    static class BitSet {
        final static int allLetters = 0x3ffffff;
        static int AddLetter(int bitSet, char c) {
            int i = c - 'a';
            int mask = 1<<i;
            return bitSet|mask;
        }
        static int RemoveLetter(int bitSet, char c) {
            int i = c - 'a';
            int mask = 1<<i;
            return bitSet&(~mask);
        }
    }
}
