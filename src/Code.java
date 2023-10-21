import java.util.HashMap;
import java.util.regex.Pattern;

public class Code {
    HashMap<String, String> compMnemonics = new HashMap<>();
    HashMap<String, String> destMnemonics = new HashMap<>();

    HashMap<String, String> jumpMnemonics = new HashMap<>();
    String comp,jump,dest, byteInstruction;


    Code(){
        //Comp for when A = 0.
        compMnemonics.put("0", "0101010");
        compMnemonics.put("1", "0111111");
        compMnemonics.put("-1","0111010");
        compMnemonics.put("D", "0001100");
        compMnemonics.put("A","0110000");
        compMnemonics.put("!D","0001101");
        compMnemonics.put("!A", "0110001");
        compMnemonics.put("-D","0001111");
        compMnemonics.put("-A","0110011");
        compMnemonics.put("D+1", "0011111");
        compMnemonics.put("A+1", "0110111");
        compMnemonics.put("D-1","0001110");
        compMnemonics.put("A-1", "0110010");
        compMnemonics.put("D+A","0000010");
        compMnemonics.put("D-A","0010011");
        compMnemonics.put("A-D","0000111");
        compMnemonics.put("D&A","0000000");
        compMnemonics.put("D|A","0010101");
        //Comp for when A = 1.

        compMnemonics.put("M", "1110000");
        compMnemonics.put("!M", "1110001");
        compMnemonics.put("-M","1110011");
        compMnemonics.put("M+1","1110111");
        compMnemonics.put("M-1","1110010");
        compMnemonics.put("D+M","1000010");
        compMnemonics.put("D-M","1010011");
        compMnemonics.put("M-D","1000111");
        compMnemonics.put("D&M","1000000");
        compMnemonics.put("D|M","1010101");


        destMnemonics.put("", "000");
        destMnemonics.put("M","001");
        destMnemonics.put("D", "010");
        destMnemonics.put("MD","011");
        destMnemonics.put("A","100");
        destMnemonics.put("AM","101");
        destMnemonics.put("AD","110");
        destMnemonics.put("AMD","111");

        jumpMnemonics.put("","000");
        jumpMnemonics.put("JGT","001");
        jumpMnemonics.put("JEQ","010");
        jumpMnemonics.put("JGE","011");
        jumpMnemonics.put("JLT","100");
        jumpMnemonics.put("JNE","101");
        jumpMnemonics.put("JLE","110");
        jumpMnemonics.put("JMP","111");











    }

    public String dest(String mnemonics){
        if(destMnemonics.containsKey(mnemonics)){
            dest = destMnemonics.get(mnemonics);
            return dest;

        }
        else {
            return null;
        }
    }


    public String comp(String mnemonics){
        if (compMnemonics.containsKey(mnemonics)){
            comp = compMnemonics.get(mnemonics);
            return comp;

        }
        else{
            return null;
        }
    }

    public String jmp(String mnemonics){
        if (jumpMnemonics.containsKey(mnemonics)){
            jump = jumpMnemonics.get(mnemonics);
            return jump;

        }
        else{
            return null;
        }
    }







}
