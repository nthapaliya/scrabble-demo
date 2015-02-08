package board;

public enum Piece {
    E('e', 1, 12),
    A('a', 1, 9),
    I('i', 1, 9),
    O('o', 1, 8),
    N('n', 1, 6),
    R('r', 1, 6),
    T('t', 1, 6),
    L('l', 1, 4),
    S('s', 1, 4),
    U('u', 1, 4),
    D('d', 2, 4),
    G('g', 2, 3),
    B('b', 3, 2),
    C('c', 3, 2),
    M('m', 3, 2),
    P('p', 3, 2),
    F('f', 4, 2),
    H('h', 4, 2),
    V('v', 4, 2),
    W('w', 4, 2),
    Y('y', 4, 2),
    K('k', 5, 1),
    J('j', 8, 1),
    X('x', 8, 1),
    Q('q', 10, 1),
    Z('z', 10, 1);

    private int value, distribution;

    private Piece(char l, int v, int d) {
        char letter = l;
        this.value = v;
        this.distribution = d;
    }

    public static Piece getPiece(char c) {
        for (Piece p : Piece.values()) {
            if (p.value == c) return p;
        }
        return null;
    }

    public int Value() {
        return value;
    }

    public int Dist() {
        return distribution;
    }
}
