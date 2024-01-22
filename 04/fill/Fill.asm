// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen
// by writing 'black' in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen by writing
// 'white' in every pixel;
// the screen should remain fully clear as long as no key is pressed.

//// Replace this comment with your code.
(Looponex)
@KBD
D=M
@Blaken
D;JNE
@SCREEN
D=A
@addr
M=D
(Loopone)
@KBD
D=A
@addr
D=D-M
@Endone
D;JEQ
@addr
A=M
M=0
@addr
M=M+1
@Loopone
0;JMP
(Endone)
@Looponex
0;JMP

(Blaken)
@SCREEN
D=A
@addr
M=D
(Looptwo)
@KBD
D=A
@addr
D=D-M
@Endtwo
D;JEQ
@addr
A=M
M=-1
@addr
M=M+1
@Looptwo
0;JMP
(Endtwo)
@Looponex
0;JMP