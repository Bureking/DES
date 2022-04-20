package DES;
import java.util.Vector;

/**
    A class that implements ISplit Interface
    Split class splits the binary input into 
    64 bit blocks
    And returns the Vector of Strings
    Each String in this Vector has 64 bits
    @author Hanadi Jusufovic
*/
public class Split implements ISplit {

    private Vector<String> blocks; // Vector of Strings to hold the blocks
    private IInputHandler inputHandler; // Instance of IInputHandler to get the binary representation


    /** 
        Split Default Contructor Method 
	*/
    public Split() {}; // Default constructor used for inheritance

    /** 
        Split Common Use Contructor Method
        @param inputHandler an instance of InputHandler object
	*/
    public Split(IInputHandler inputHandler) {

        this.inputHandler = inputHandler; // Assign the inputHandler to instance of InputHandler
        this.blocks = new Vector<String>(); // Initialzie the Vector
        this.split(); // Method that splits the file into blocks
    } // End constructor

    /** 
        Creates the 64 bit blocks and adds them to the Vector 
	 */
    private void split() {

        int length = inputHandler.getBinary().length(); // Length of the binary input file
        //If the length is less than 64 that means that the file does not contain
        //One whole block, we will pad the rest with spaces
        if(length < 64) {

            // Block will be binary + padding
            String block = this.inputHandler.getBinary();
            int timesToPad = (64 - length) / 8; 

            for(int i=0; i<timesToPad; i++) {
                block += "00100000";
            }
            this.blocks.add(block);
        }
        else { // File is bigger than 64 bits

            // Get number of whole blocks
            int numberOfWholeBlocks = length / 64;

            for(int i=0; i<numberOfWholeBlocks; i++) {
                int start = i*64;
                int end = start + 64;
                String block = this.inputHandler.getBinary();

                String currentBlock = block.substring(start, end);
                this.blocks.add(currentBlock);
            }
            
            // Check if there exists more bits, for example
            // If 64 < length < 128 
            if(length % 64 != 0) {

                String block = this.inputHandler.getBinary();
                String currentBlock = block.substring(numberOfWholeBlocks*64, block.length());

                int timesToPad = (64 - currentBlock.length()) / 8;

                for(int i=0; i<timesToPad; i++) {
                    
                    currentBlock += "00100000";
                }

                this.blocks.add(currentBlock);
            }  
    }
}

     /** 
        Access method to get private variable blocks
        @return Vector that stores 64 bit blocks
	 */
    public Vector<String> getBlocks() {

        return this.blocks;
    }  
}
