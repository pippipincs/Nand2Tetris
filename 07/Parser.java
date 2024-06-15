import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

    private String currentline;
    private BufferedReader br;
    public String name;
    public Parser(String filename) throws IOException {
        br = new BufferedReader(new FileReader(filename));
        name = filename.split("\\.")[0];

    }

    public boolean advance() throws IOException {
        
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