import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the ABSOLUTE file location of the .asm file:");
        String fileLocation = myObj.nextLine();
        fileLocation = fileLocation.replace("\"", "");



        int pc = 0;
        //Initialize all of the modules needed for assembling
        Parser firstPassParser = new Parser(fileLocation);
        Parser secondPassParser = new Parser(fileLocation);
        Code codeModule = new Code();
        SymbolTable st = new SymbolTable();

        //Start the first pass of the assembling
        firstPassParser.advance();
        while (firstPassParser.getCommand() != null){
            if (firstPassParser.commandType() == CommandType.L_COMMAND){
                st.putLabelTable(firstPassParser.symbol(), Integer.toString(pc));
                pc--;
            }
            firstPassParser.advance();
            pc++;

        }

        //Start the second pass of the assembling.
        FileWriter write = null;
        int registerAllocation = 16;
        try {
            String filename = fileLocation;
            File outputFile = new File(filename.replaceAll("\\.asm", ".hack") );
            outputFile.createNewFile();
            write = new FileWriter(outputFile);

            secondPassParser.advance();
            while (secondPassParser.getCommand()!= null){
                if (secondPassParser.commandType().equals(CommandType.C_COMMAND)){
                    //Strings to hold values of the comp, dest, and jump.
                    String cCommand, comp, dest, jump;
                    comp = secondPassParser.comp();
                    dest = secondPassParser.dest();
                    jump = secondPassParser.jump();
                    //Get the binary code for the comp, dest and jump respectively.
                    String c = codeModule.comp(comp);
                    String d = codeModule.dest(dest);
                    String j = codeModule.jmp(jump);
                    //Concatenate the results and place them in the file.
                    cCommand = "111" + c + d + j + "\n";
                    write.write(cCommand);



                }
                //If we get an A Command, then we need to identify if only numbers follow it, if the symbol is in the table, or if the symbol is not in the table.
                else if (secondPassParser.commandType().equals(CommandType.A_COMMAND)) {
                    if ( !(Pattern.compile("[a-zA-z]").matcher(secondPassParser.symbol()).find() )){
                        //Write the binary value of the value following @
                        write.write(convertAddress(secondPassParser.symbol()) + "\n");
                    } else if (st.getAddress(secondPassParser.symbol()) == null) {
                        //If the symbol is not in the table, then place the symbol in the Symbol table, along with it's memaddress.
                        st.putLabelTable(secondPassParser.symbol(), Integer.toString(registerAllocation));
                        registerAllocation++;
                        //Place the symbol
                        write.write(convertAddress(st.getAddress(secondPassParser.symbol())) + "\n");

                    }
                    else {
                        //If the symbol already exists, then just get the address of the symbol and convert the address to binary.
                        write.write(convertAddress(st.getAddress(secondPassParser.symbol())) + "\n");
                    }


                }
                else{

                }
                secondPassParser.advance();


            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            //Close the buffer
            write.flush();
            write.close();

        }









    }


    public static String convertAddress(String addr) throws Exception{
        String bin;
        String zeroes = "";
        if (addr != null){
            bin = Integer.toBinaryString(Integer.parseInt(addr));
            for (int i = 0; i < (16 - bin.length()); i++){
                zeroes += "0";
            }
            return zeroes + bin;
        }
        else {
            throw new Exception("Null param");
        }

    }
}