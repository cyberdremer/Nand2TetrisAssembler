import java.util.HashMap;

public class SymbolTable {
    /*Takes in a String and a memory address and places the label into the hashtable*/
    HashMap<String, String> labelTable = new HashMap<>();
    SymbolTable(){
        //Initialize the symbol table
        labelTable.put("SP","0");
        labelTable.put("LCL","1");
        labelTable.put("ARG", "2");
        labelTable.put("THIS", "3");
        labelTable.put("THAT", "4");
        //Initialize the registers with their appropriate mem locations.
        for (int i = 0; i < 16; i++){
            String r = "R" + i;
            labelTable.put(r,Integer.toString(i));
        }
        labelTable.put("SCREEN","16384");
        labelTable.put("KBD","24756");



    }


    /**
     * Interface to access the private hashtable,
     * @param label The label we want to search for as a key.
     * @param address The address that we want to place as our value.
     */
    void putLabelTable(String label, String address){
        labelTable.put(label, address);
    }

    /**
     * Interface to get an address from the Symbol table.
     * @param label The label we want to search for.
     * @return The address of the label, if it exists.
     */
    public String getAddress(String label){
        return labelTable.get(label);

    }
}
