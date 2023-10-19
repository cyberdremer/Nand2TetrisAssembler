import java.util.HashMap;

public class Code {
    HashMap<String, String> compMnemonics = new HashMap<>();
    HashMap<String, String> destMnemonics = new HashMap<>();

    HashMap<String, String> jumpMnemonics = new HashMap<>();


    Code(){
        compMnemonics.put("0", "0101010");
        compMnemonics.put("1", "0111111");
        compMnemonics.put("-1","0111010");
        compMnemonics.put("D", "0001100");
        compMnemonics.put("A","0110000");
        compMnemonics.put("!D","0001101");
        compMnemonics.put("!A", "0110001");
        compMnemonics.put("-D","0001111");
        compMnemonics.put("-A","")
    }



}
