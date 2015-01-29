public enum Piece {

								E ("E",1,12),
								A ("A",1,9),
								I ("I",1,9),
								O ("O",1,8),
								N ("N",1,6),
								R ("R",1,6),
								T ("T",1,6),
								L ("L",1,4),
								S ("S",1,4),
								U ("U",1,4),
								D ("D",2,4),
								G ("G",2,3),
								B ("B",3,2),
								C ("C",3,2),
								M ("M",3,2),
								P ("P",3,2),
								F ("F",4,2),
								H ("H",4,2),
								V ("V",4,2),
								W ("W",4,2),
								Y ("Y",4,2),
								K ("K",5,1),
								J ("J",8,1),
								X ("X",8,1),
								Q ("Q",10,1),
								Z ("Z",10,1);

								private int value, distribution;
								private String s;

								private Piece (String s, int value, int distribution){
																this.s = s;
																this.value = value;
																this.distribution = distribution;
								}
								public String toString(){
																return s;
								}
								public int getValue(){
																return value;
								}
								public int getDist(){
																return distribution;
								}
								public static Piece getPiece (String s) {
																for (Piece p: Piece.values()) {
																								if (p.toString().equals(s)) return p;
																}
																return null;
								}
}
