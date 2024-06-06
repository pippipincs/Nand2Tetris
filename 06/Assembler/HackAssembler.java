import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


public class HackAssembler {
    private static String PATHTOTEST="../test/";    
    private static String PATHTOOUTPUT="../output/";
    private static String padto8String(String str){
        while (str.length() < 16) {
            str = "0" + str;
        }
        return str;
    }

    

    

    public static void main(String[] args) {
        
        for(String arg : args){

            try{
            SymbolTable table=new SymbolTable();
            String fileinput=PATHTOTEST+arg;
            Parser fileparser=new Parser(new File(fileinput));
            System.out.println(Arrays.toString(arg.split("\\.")));
            System.out.println(arg.split("\\.")+arg);
            String fileoutput=PATHTOOUTPUT+arg.split("\\.")[0]+".hack";
            FileWriter writer = new FileWriter(fileoutput);
            int cnt=0;
            int start=16;
            while(fileparser.hasMoreLines()){
                
                fileparser.advance();
                if(fileparser.commandType()=="L_COMMAND"){
                    table.addEntry(fileparser.symbol().trim(), cnt);
                }
                cnt+=1;
            }
            Parser parser2=new Parser(new File(fileinput));
            while(parser2.hasMoreLines()){
                
                parser2.advance();
                switch(parser2.commandType()){
                    
                    case "A_COMMAND":
                    String str=parser2.symbol().trim();
                    String instruction;
                    if(Parser.isNumber(str)){
                        instruction=padto8String(Integer.toBinaryString(Integer.parseInt(str)));
                        
                    }else{
                        if(table.contains(str)){
                            instruction=padto8String(Integer.toBinaryString(table.getAddress(str)));

                        }else{
                            table.addEntry(str, start);
                            instruction=padto8String(Integer.toBinaryString(start));
                            start+=1;
                        }
                    }
                    writer.write(instruction);
                    writer.write(System.lineSeparator()); 

                    break;

                    case "L_COMMAND":
                    break;

                    case "C_COMMAND":
                    instruction="111"+Coder.getComp(parser2.comp())+Coder.getDest(parser2.dest())+Coder.getJump(parser2.jump());
                    writer.write(instruction);
                    writer.write(System.lineSeparator());
                }
                
            }
            writer.close();
        }catch(IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }


        }

    }
    
    
    
    
}
