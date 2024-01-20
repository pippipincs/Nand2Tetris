@sum
M=0
                                                             
@R1
D=M
@n
M=D

@i
M=1

(Loop)
@i
D=M
@n
D=D-M
@End
D;JGT
@sum 
D=M
@R0   
D=D+M
@sum
M=D
@i
M=M+1
@Loop
0;JMP

(End)
@sum
D=M
@R2
M=D
@End
0;JMP
