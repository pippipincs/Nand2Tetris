import java.io.IOException;
public class VMTranslator {
    public static void main(String[] args) throws IOException{
       Parser parser=new Parser(args[0]);
       CodeWriter codewriter=new CodeWriter(parser.name+".asm");
       while(parser.advance()){
            String type=parser.commandType();
            String arg1=parser.arg1();
            int arg2=parser.arg2();
            codewriter.WriteAssembly(type,arg1,arg2,parser);

       }
       codewriter.close();
    }
}