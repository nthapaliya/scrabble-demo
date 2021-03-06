package wordsearch;

public enum Tiles {

    PLAIN("Plain Tile", 1, 1),
    CENTER_TILE("Center Tile", 1, 1),
    DOUBLE_LETTER("Double Letter", 2, 1),
    TRIPLE_LETTER("Triple Letter", 3, 1),
    DOUBLE_WORD("Double Word", 1, 2),
    TRIPLE_WORD("Triple Word", 1, 3),
    BLANK("blank tile", 0, 0);

    private int letterMultiplier, wordMultiplier;
    private String type;

    private Tiles(String n, int i, int j) {
        type = n;
        letterMultiplier = i;
        wordMultiplier = j;
    }

    public static Tiles GetType(int row, int col) {
        if (row < 0 || col < 0) return BLANK;

        if (row > 7) {
            row = 14 - row;
        }
        if (col > 7) {
            col = 14 - col;
        }
        if (row < col) {
            int i = row;
            row = col;
            col = i;
        }

        if (row == col) {
            switch (row) {
                case 0:
                    return TRIPLE_WORD;
                case 1:
                case 2:
                case 3:
                case 4:
                    return DOUBLE_WORD;
                case 5:
                    return TRIPLE_LETTER;
                case 6:
                    return DOUBLE_LETTER;
                case 7:
                    return CENTER_TILE;
                default:
                    return null;
            }
        }

        if (row == 7 && col == 0) return TRIPLE_WORD;

        if (row == 3 && col == 0 || row == 6 && col == 2 || row == 7 && col == 3) {
            return DOUBLE_LETTER;
        }

        if (row == 5 && col == 1) return TRIPLE_LETTER;
        return PLAIN;
    }

    public static int LetterMultiplier(int i, int j) {
        return GetType(i, j).LetterMultiplier();
    }

    public static int WordMultiplier(int i, int j) {
        return GetType(i, j).WordMultiplier();
    }

    public String toString() {
        return type;
    }

    int LetterMultiplier() {
        return letterMultiplier;
    }

    int WordMultiplier() {
        return wordMultiplier;
    }
}
