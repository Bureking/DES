package DES;
import java.io.FileNotFoundException;
import java.util.Vector;


/**
    A class that implements IManager interface
    Class Manager contains Objects of classes InputHandler, InitialPermutation,
    Round, and FinalPermutation
    It uses these classes to call the methods of these classes
    And to manage 16 rounds for each block
    Finally, it does the FinalPermuation
    @author Hanadi Jusufovic
*/
public class RoundManager implements IRoundManager {

    private IInputHandler inputHandler;
    private IInitialPermutation ip;
    private Round round;
    private IFinalPermutation finalPermutation;

    private Vector<String> encryptedBlocks; // Vector that will hold encrypted blocks
    private Vector<String> blocks; // Vector that will hold blocks from InitialPermutation that are split 
    private String encryptedBlock; // String that represents one encryptedBlock

    
     /** 
        RoundManager Contructor Method
        @param inputFile name of the input file
	*/
    public RoundManager(String inputFile) {
        
        try { // Try/Catch block to open the file
            this.inputHandler = new InputHandler(inputFile); // Initialize the InputHandler
            this.ip = new InitialPermutation(inputHandler);  // Initialize the InitialPermutation
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } 

        this.blocks = ip.getPermutedBlocks(); // get initial permutued blocks
        this.round = new Round(); // Initialize Round object
        this.finalPermutation = new FinalPermutation(); // Initialize FinalPermutation object 
        this.encryptedBlocks = new Vector<String>(); // Initialize Vector to hold encrypted blocks
        
} // End constructor

    /** 
        This method takes each block from the vector of blocks
        and then it splits it into left and right half, which are then passed to
        round one, and afterwards go through other 15 rounds
        @return Vector of Strings that hold encrypted blocks
	 */
    public Vector<String> getEncrpyedBlocks() {

        //For each block in blocks
        for(String block : blocks) {

            String left = block.substring(0, block.length() / 2);
            String right = block.substring(block.length() / 2, block.length());
            String roundOne = round.get(left, right); // Perform round one

            // Other 15 rounds
            for(int i=1; i<16; i++) {

                left = roundOne.substring(0, roundOne.length() / 2);
                right = roundOne.substring(roundOne.length() / 2, roundOne.length());

                roundOne = round.get(left, right); 
            }

            // Left half after 16 rounds
            String leftSixteen = roundOne.substring(0, roundOne.length() / 2);

            // Right half after 16 rounds 
            String rightSixteen = roundOne.substring(roundOne.length() / 2, roundOne.length());

            String R16L16 = rightSixteen + leftSixteen; // Exchange halfs

            // Encrypted block gets the return from finalPermutation
            this.encryptedBlock = finalPermutation.getFinalPermutation(R16L16);

            this.encryptedBlocks.add(encryptedBlock); // Add encrypted block to Vector
        }

    return this.encryptedBlocks;
    }

}
