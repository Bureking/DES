package DES;
import java.util.Vector;
/**
    A class that contains the main method and
    executes the code
    @author Hanadi Jusufovic
*/
public class Manager {

    /** 
        Main Method
        @param args Arguments for main method
	*/ 
    public static void main(String[] args) {

            // Create RoundManager and pass the inputfile's name
            RoundManager roundManager = new RoundManager("input.txt");

            Vector<String> encryptedBlocks = roundManager.getEncrpyedBlocks();

            OutputHandler outputHandler = new OutputHandler();

            outputHandler.write(encryptedBlocks);
    }    
}
