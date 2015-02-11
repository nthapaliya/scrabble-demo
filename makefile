all: scrabble

scrabble: wordsearch board
	javac -cp dst/ src/Scrabble.java -d dst/

board:
	javac -cp dst/ src/board/*.java -d dst/

wordsearch:
	javac -cp dst/ src/wordsearch/*.java -d dst/

clean:
	rm dst/board/*.class
	rm dst/wordsearch/*.class
	rm dst/*.class
