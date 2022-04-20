package DES;
import java.util.Vector;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

/**
    A class that implements IOutputHandler Interface
    OutputHandler class creates output file
    And converts each encrypted block to characters
    And writes them to the output file
    @author Hanadi Jusufovic
*/
public class OutputHandler implements IOutputHandler {

    private File file;
    private FileWriter writer;

     /** 
        OutputHandler Contructor Method
	*/
    public OutputHandler() {

        try {
            
            this.file = new File("output.txt");
            this.writer = new FileWriter("output.txt");
        } catch(IOException e) {

            e.printStackTrace();
        }
    } // End constructor 


    /** 
        Converts 64 bit encrypted blocks to characters
        And writes them to the output file
        @param encryptedBlocks Vector of Strings
	 */
    public void write(Vector<String> encryptedBlocks) {

        try {

            for(String block : encryptedBlocks) {

                StringBuilder builder = new StringBuilder();
                int charCode;
                for(int i=0; i<block.length(); i+=8) {
                    charCode = Integer.parseInt(block.substring(i, i+8), 2);
                    String character = Character.toString((char) charCode);

                    builder.append(character);
                }
                this.writer.write(builder.toString());
            }
            this.writer.close();

        } catch(IOException e) {

            e.printStackTrace();
        }
    }   
}
