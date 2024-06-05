
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;
public class Parser {
    
    public String currentinstruction="";
    public Scanner scanner;
    



    
    Parser(File file) {
        
        try {
            this.scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean hasMoreLines() {
        return scanner.hasNextLine();
    }
    
    
    
    public void advance() {
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            if(!lineScanner.hasNext()){
                lineScanner.close();
                continue;
            }else if(lineScanner.next().equals("//")){
                lineScanner.close();
                continue;
            }else{
                currentinstruction = line.trim();
                lineScanner.close();
                break;
            }
        }
        if(!scanner.hasNextLine()){
            scanner.close();
        }
    }

    public String commandType() {
        if (currentinstruction.startsWith("@")) {
            return "A_COMMAND";
        } else if (currentinstruction.startsWith("(")) {
            return "L_COMMAND";
        } else {
            return "C_COMMAND";
        }
    }

    public String symbol() {
        if (commandType().equals("A_COMMAND")) {
            return currentinstruction.substring(1);
        } else {
            return currentinstruction.substring(1, currentinstruction.length() - 1);
        }
    }
    public static boolean isNumber(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        String regex = "[-+]?\\d*\\.?\\d+";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(str.trim()).matches();
    }

    public String dest() {
        if (currentinstruction.contains("=")) {
            return currentinstruction.split("=")[0].trim();
        } else {
            return "";
        }
    }
    public String comp() {
        if (currentinstruction.contains("=")) {
           if(currentinstruction.contains(";")){
               return currentinstruction.split("=")[1].split(";")[0].trim();
              }else{
               return currentinstruction.split("=")[1].trim();  
              }
        } else {
           if(currentinstruction.contains(";")){
               return currentinstruction.split(";")[0].trim();
                }else{
                return currentinstruction.trim();
                }
        }
    }
    public String jump() {
        if (currentinstruction.contains(";")) {
            return currentinstruction.split(";")[1].trim();
        } else {
            return "";
        }
    }
}
       