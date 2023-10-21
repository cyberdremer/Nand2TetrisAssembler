import java.io.*;

public class Parser {
    private String command;
    private CommandType readCommand;
    private BufferedReader br;
    Parser(String filename) throws FileNotFoundException {
        try {
            br = new BufferedReader(new FileReader(filename));

        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
    public CommandType commandType(){
        if (command.charAt(0) == '@'){
            readCommand = CommandType.A_COMMAND;
            return readCommand;
        }
        else if (command.charAt(0) == '('){
            readCommand = CommandType.L_COMMAND;
            return readCommand;

        }
        else {
            readCommand = CommandType.C_COMMAND;
            return readCommand;
        }
    }
    public void advance() throws IOException {
        String line;
        while( true ) {
            line = br.readLine();
            if( line == null ) {
                try {
                    if( br != null ) {
                        br.close();
                        command = null;
                    }
                } catch( IOException ex ) {
                    ex.printStackTrace();
                } finally {
                    break;
                }
            }
            line = line.replaceAll( "\\s","" ).replaceAll( "//.*", "" );
            if( line.length() != 0 ) {
                command = line;
                break;
            }
        }
    }
    String symbol(){
        //If there is no @ symbol then assume some sort of label
        if(!command.contains("@")){
            return command.replaceAll("\\(", "").replaceAll("\\)", "");
        }
        else {
            return command.replaceAll("@", "");

        }

    }

    String jump(){
        //If no ';' found then assume a dud
        if (!command.contains(";")){
            return "";
        }
        else{
            //If there is a ';' then we will remove every character prior to and including the semi-colon
            return command.replaceAll(".*;", "");
        }


    }

    String comp(){
        //Strip all of everything to the left of the '=' and to right of ';'.
        return command.replaceAll(".*=", "").replaceAll(";.*", "");


    }
    String dest(){
        if (!command.contains("=")){
            return "";
        }
        else {
            return command.replaceAll("=.*" ,"");
        }


    }

    String getCommand(){
        return command;
    }
}
