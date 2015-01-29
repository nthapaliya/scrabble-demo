# scrabble-demo

Demonstration of a Scrabble game written in java

It compiles, and even works! But it's not the best sample of my code.

`$ make
`$ java dst/Scrabble.class

I also have a pre-compiled .jar in dst/

This is old code that I had migrated from computer to computer disk over the years. I also, at various times, used different text-editors "code alignment" tools, which explains why the code is not really consistently indented.

I realize that the code is very hard to read, as (past me) did not care about documenting. I never thought that I would show this to anyone. After spending about a day trying parse through the code and figure out what calls what, I figure there are no 'simple' fixes. The code has to go through a major rewrite. I shall explain what I would do better as follows:

- Documentation: Lots of it. It should be informative but concise.
- Composability: In a program like this, there should be clear separation of concerns. The searching algorithm should be separate from the ui-logic. As I have written it, it's all a mish mash.
- Side effects: As the code is now, too many functions have side effects. A lack of documentation makes it difficult to find out what each function changes, and in general, I find "purer" functions to be easier to write and understand.
- Global variables: There are too many global variables. Coupled with the previous point, it's hard to reason the flow of the program.
