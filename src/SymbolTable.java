import java.util.HashMap;

public class SymbolTable {
    /*Takes in a String and a memory address and places the label into the hashtable*/
    HashMap<String, Integer> labelTable = new HashMap<>();
    SymbolTable(){
        labelTable.put("SP",0);
        labelTable.put("LCL",1);
        labelTable.put("ARG", 2);
        labelTable.put("THIS", 3);
        labelTable.put("THAT", 4);
        for (int i = 0; i < 16; i++){
            String r = "R" + i;
            labelTable.put(r,i);
        }
        labelTable.put("SCREEN",16384);
        labelTable.put("KBD",24756);



    }
}
