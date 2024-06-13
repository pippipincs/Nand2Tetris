import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

    private String currentline;
    private BufferedReader br;
    public Parser(String filename) throws IOException {
        br = new BufferedReader(new FileReader(filename));
    }

    public boolean hasMoreCommands() throws IOException {
        
        while(true){
            currentline = br.readLine().trim();
            if(currentline==null){
                return false;
            }
            if(currentline.startsWith("//") || currentline.isBlank()){
                continue;
            }
            break;
            

        }
        return true;
        
    }

    public String commandType() {
        String[] words = currentline.split("\\s+");
        String word = words[0];        
        
        if(word.equals("push")){
            return "C_PUSH";
        }
        if(word.equals("pop")){
            return "C_POP";
        }
        
        if(word.equals("label")){
            return "C_LABEL";
        }
        if(word.equals("goto")){
            return "C_GOTO";
        }
        if(word.equals("if-goto")){
            return "C_IF";
        }
        if(word.equals("function")){
            return "C_FUNCTION";
        }
        if(word.equals("return")){
            return "C_RETURN";
        }
        if(word.equals("call")){
            return "C_CALL";
        }
        return word;
    }
    public String arg1() {
        String[] words = currentline.split("\\s+");
    
        return words[1];
    }
    public int arg2() {
        String[] words = currentline.split("\\s+");
        return Integer.parseInt(words[2]);
    }
}