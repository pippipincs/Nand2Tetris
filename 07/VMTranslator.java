import java.io.IOException;
public class VMTranslator {
    public static void main(String[] args) throws IOException{
       Parser parser=new Parser(args[0]);
       CodeWriter codewriter=new CodeWriter(parser.name+".asm");
       while(parser.advance()){
            String type=parser.commandType();
            if(parser.if_arith()){
                codewriter.writeArithmetic(type);
            }else{
                codewriter.writePushPop(type,parser.arg1(), parser.arg2(),parser);
            }
            

       }
       codewriter.close();
    }
}