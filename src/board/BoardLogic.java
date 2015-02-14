package board;

import wordsearch.Game;

/**
 * Created by nirajthapaliya on 2/13/15.
 */
class BoardLogic {
    final Game game;
    Tile[] rack;
    Tile[][] tiles;
    Scrabble sp;

    BoardLogic(Scrabble sp) {
        this.sp = sp;
        game = new Game("Player 1", "Player 2");
        rack = new Tile[7];
    }

    void putUnfixedBackOnRack() {
        char[] unfixed = new char[7];
        int index = 0;
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (!tile.isEmpty() && !tile.isPermanent()) {
                    unfixed[index++] = tile.getLetter();
                    tile.remove();
                }
            }
        }

        int rackIndex = 0;
        Tile rackTile = rack[rackIndex];

        for (int i = 0; i < index; i++) {
            while (!rackTile.isEmpty()) {
                rackTile = rack[++rackIndex];
            }
            rackTile.put(unfixed[i]);
        }
    }

    void refillRack() {
        for (int i = 0; i < 7; i++) {
            Tile rackTile = rack[i];
            if (rackTile.isEmpty()) {
                char c = game.DrawRandom();
                rackTile.put(c);
            }
        }
    }

    void fixUnfixed() {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (!tile.isEmpty() && !tile.isPermanent()) {
                    tile.makePermanent();
                }
            }
        }
    }

    void putTilesBackInBag() {
        for (Tile t : rack) {
            if (!t.isEmpty()) {
                game.AddToBag(t.getLetter());
                t.remove();
            }
        }
    }

    void repaint() {
        sp.repaint();
    }
}

