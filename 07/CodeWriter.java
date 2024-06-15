import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CodeWriter {
    BufferedWriter bw;
    private int counter=0;
    public CodeWriter(String filepath) throws IOException {
        bw = new BufferedWriter(new FileWriter(filepath, false));
}
    public void writeto(String line) throws IOException {
        bw.write(line);
        bw.newLine();
    }
    public void close() throws IOException {
        bw.close();
    }
    
    public void writeArithmetic(String command) throws IOException {
        counter++;
        String TRUE="TRUE"+counter;
        String CONTINUE="CONTINUE"+counter;
        switch(command){
            case "add":
            writeto("@SP");
            writeto("AM=M-1");
            writeto("D=M");
            writeto("A=A-1");
            writeto("M=D+M");
            break;
            case "sub":
            writeto("@SP");
            writeto("AM=M-1");
            writeto("D=M");
            writeto("A=A-1");
            writeto("M=M-D");
            break;
            case "neg":
            writeto("@SP");
            writeto("A=M-1");
            writeto("M=-M");
            break;
            case "eq":
            writeto("@SP");
            writeto("AM=M-1");
            writeto("D=M");
            writeto("A=A-1");
            writeto("D=M-D");
            writeto("@"+TRUE);
            writeto("D;JEQ");
            writeto("@SP");
            writeto("A=M-1");
            writeto("M=0");
            writeto("@"+CONTINUE);
            writeto("0;JMP");
            writeto("("+TRUE+")");
            writeto("@SP");
            writeto("A=M-1");
            writeto("M=1");
            writeto("("+CONTINUE+")");
            break;
            case "gt":
            writeto("@SP");
            writeto("AM=M-1");
            writeto("D=M");
            writeto("A=A-1");
            writeto("D=M-D");
            writeto("@"+TRUE);
            writeto("D;JGT");
            writeto("@SP");
            writeto("A=M-1");
            writeto("M=0");
            writeto("@"+CONTINUE);
            writeto("0;JMP");
            writeto("("+TRUE+")");
            writeto("@SP");
            writeto("A=M-1");
            writeto("M=1");
            writeto("("+CONTINUE+")");
            break;
            case "lt":
            writeto("@SP");
            writeto("AM=M-1");
            writeto("D=M");
            writeto("A=A-1");
            writeto("D=M-D");
            writeto("@"+TRUE);
            writeto("D;JLT");
            writeto("@SP");
            writeto("A=M-1");
            writeto("M=0");
            writeto("@"+CONTINUE);
            writeto("0;JMP");
            writeto("("+TRUE+")");
            writeto("@SP");
            writeto("A=M-1");
            writeto("M=1");
            writeto("("+CONTINUE+")");
            break;
            case "and":
            writeto("@SP");
            writeto("AM=M-1");
            writeto("D=M");
            writeto("A=A-1");
            writeto("M=D&M");
            break;
            case "or":
            writeto("@SP");
            writeto("AM=M-1");
            writeto("D=M");
            writeto("A=A-1");
            writeto("M=D|M");
            break;
            case "not":
            writeto("@sp");
            writeto("A=M-1");
            writeto("M=!M");
            break;

        }
        
    }

    public void writePushPop(String command, String segment, int index, Parser parser) throws IOException {
            counter++;
            String addr="addr"+counter;
            
            switch (segment){
            
            case "constant":
                writeto("@"+index);
                writeto("D=A");
                writeto("@SP");
                writeto("A=M");
                writeto("M=D");
                writeto("@SP");
                writeto("M=M+1");
                break;
                
            case "local":
                LATTcode(command, "LCL", index, addr);
                break;
            case "argument":
                LATTcode(command, "ARG", index, addr);

                break;
            case "this":
                LATTcode(command, "THIS", index, addr);
                break;
            case "that":
                LATTcode(command, "THAT", index, addr);
                break;
        
        
            

            case "temp":
                if(command=="push"){
                    writeto("@R5");
                    writeto("D=A");
                    writeto("@"+index);
                    writeto("A=D+A");
                    writeto("D=M");
                    writeto("@SP");
                    writeto("A=M");
                    writeto("M=D");
                    writeto("@SP");
                    writeto("M=M+1");
                }else{
                    writeto("R5");
                    writeto("D=A");
                    writeto("@"+index);
                    writeto("D=D+A");
                    writeto("@"+addr);
                    writeto("M=D");
                    writeto("@SP");
                    writeto("M=M-1");
                    writeto("A=M");
                    writeto("D=M");
                    writeto("@"+addr);
                    writeto("A=M");
                    writeto("M=D");

                }
                
                break;
        


            case "static":                                                                                                                                                                                    
                String N=parser.name+"."+index;
                if(command=="push"){
                    writeto("@"+N );
                    writeto("D=M");
                    writeto("@SP");
                    writeto("A=M");
                    writeto("M=D");
                    writeto("@SP");
                    writeto("M=M+1");
                }else{
                    writeto("@SP");
                    writeto("M=M-1");
                    writeto("A=M");
                    writeto("D=M");
                    writeto("@"+N);
                    writeto("M=D");
                }
                
                break;
            case "pointer":
                if(command=="push"){
                    pointer_push(index);
                }else{
                    pointer_pop(index);
                }                                                                                                                                          
        }

    }
    private void LATTsegment_push(String field,int index, String addr) throws IOException{
        
            writeto("@"+field);
            writeto("D=M");
            writeto("@"+index);
            writeto("A=A+D");
            writeto("D=M");
            writeto("@SP");
            writeto("A=M");
            writeto("M=D");
            writeto("@SP");
            writeto("M=M+1");
            

    }

    

            

    private void LATTsegment_pop(String field, int index, String addr) throws IOException{
        writeto("@"+field);
        writeto("D=M");
        writeto("@"+index);
        writeto("D=A+D");
        writeto("@"+addr);
        writeto("M=D");
        writeto("@SP");
        writeto("M=M-1");
        writeto("A=M");
        writeto("D=M");
        writeto("@"+addr);
        writeto("A=M");
        writeto("M=D");
        return;
    }
    private void LATTcode(String command, String field, int index, String addr) throws IOException{
        if(command=="push"){
        LATTsegment_push(field,index,addr);
    }else{
        LATTsegment_pop(field, index, addr);
    }
        return;
    }
    private void pointer_push(int index) throws IOException{
        String field;
        if(index==0){
            field="THIS";
        }else{
            field="THAT";
        }
        writeto("@"+field);
        writeto("D=M");
        writeto("@SP");
        writeto("A=M");
        writeto("M=D");
        writeto("@SP");
        writeto("M=M+1");
    }
    private void pointer_pop(int index) throws IOException{
        String field;
        if(index==0){
            field="THIS";
        }else{
            field="THAT";
        }
        writeto("@SP");
        writeto("M=M-1");
        writeto("A=M");
        writeto("D=M");
        writeto("@"+field);
        writeto("M=D");
        
    }
}
    
