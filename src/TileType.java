public enum TileType {


        PLAIN ("Plain Tile", 1,1),
        CENTER_TILE ("Center Tile", 1, 1),
        DOUBLE_LETTER ("Double Letter", 2, 1),
        TRIPLE_LETTER("Triple Letter", 3, 1),
        DOUBLE_WORD("Double Word", 1, 2),
        TRIPLE_WORD("Triple Word", 1, 3);


        private int letterMultiplier, wordMultiplier;
        private String type;

        private TileType (String n, int i, int j){
                type = n;
                letterMultiplier = i;
                wordMultiplier = j;
        }
        public String toString(){
                return type;
        }
        public int getLetterMultiplier(){
                return letterMultiplier;
        }
        public int getWordMultiplier() {
                return wordMultiplier;
        }
        public static TileType getTileType (int row, int col){
                if (row>7) {
                        row = 14 - row;
                }
                if (col>7) {
                        col = 14-col;
                }
                if (row<col) {
                        int i = row;
                        row = col;
                        col = i;
                }
                if (row == col) {
                        if (row == 7) return CENTER_TILE;
                        if (row == 0) return TRIPLE_WORD;
                        if (row>=1 && row <=4) return DOUBLE_WORD;
                        if (row == 5) return TRIPLE_LETTER;
                        else return DOUBLE_LETTER;
                }
                if (row==7 && col == 0) return TRIPLE_WORD;
                if (row ==3 && col ==0 ||
                    row ==6 && col ==2 ||
                    row ==7 && col ==3) return DOUBLE_LETTER;
                if (row ==5 && col ==1) return TRIPLE_LETTER;


                return PLAIN;
        }

}