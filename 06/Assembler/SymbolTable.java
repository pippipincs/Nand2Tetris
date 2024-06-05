import java.util.HashMap;

public class SymbolTable {
    private HashMap<String, Integer> table;

    // Constructor
    public SymbolTable() {
        table = new HashMap<>();
        initializePredefinedSymbols();
    }

    // Adds <symbol, address> to the table
    public void addEntry(String symbol, int address) {
        table.put(symbol, address);
    }

    // Checks if symbol exists in the table
    public boolean contains(String symbol) {
        return table.containsKey(symbol);
    }

    // Returns the address associated with symbol
    public int getAddress(String symbol) {
        return table.get(symbol);
    }

    // Initializes predefined symbols
    private void initializePredefinedSymbols() {
        for (int i = 0; i <= 15; i++) {
            table.put("R" + i, i);
        }
        table.put("SCREEN", 16384);
        table.put("KBD", 24576);
        table.put("SP", 0);
        table.put("LCL", 1);
        table.put("ARG", 2);
        table.put("THIS", 3);
        table.put("THAT", 4);
    }
}

