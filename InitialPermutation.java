package DES;
import java.util.Vector;


/**
    A class that implements IInitialPermutation Interface
    InitialPermutation class applies initial permutation
    of DES algorithm to each 64 bit block 
    And returns the Vector of Strings that contain Permuted Blocks
    Each String in this Vector has 64 bits
    This class inherits from Split because initial permutation is applied
    To each split block
    @author Hanadi Jusufovic
*/
public class InitialPermutation extends Split implements IInitialPermutation {

    private Vector<String> permutedBlocks; // Vector of Strings to hold permuted blocks
    private final int[][] ip = {{58, 50, 42, 34, 26, 18, 10, 2},
                                 {60, 52, 44, 36, 28, 20, 12, 4},
                                {62, 54, 46, 38, 30, 22, 14, 6},
                                {64, 56, 48, 40, 32, 24, 16, 8},
                                {57, 49, 41, 33, 25, 17, 9, 1},
                                {59, 51, 43, 35, 27, 19, 11, 3},
                                {61, 53, 45, 37, 29, 21, 13, 5},
                                {63, 55, 47, 39, 31, 23, 15, 7}}; // Initial Permutation table

     /** 
        InitialPermutation Contructor Method
        @param inputHandler Instance of InputHandler class
	*/
    public InitialPermutation(IInputHandler inputHandler) {

        super(inputHandler); // Call the super constructor that will do the splitting 
        this.permutedBlocks = new Vector<String>(); // Initialize the vector
        initalPermutation(); // Method that applies ip to each block
    }

    /** 
        Creates the 64 bit permuted blocks and adds them to the Vector 
	 */
    private void initalPermutation() {

        for(String block : super.getBlocks()) { // For each block from Split getBlocks()
            
            StringBuilder newBlock = new StringBuilder(); // StringBuilder object that holds the new permuted block

            // Loop the table
            for(int i1=0; i1<ip.length; i1++) {
                for(int j1=0; j1<ip.length; j1++) {
                    int bitNumber = ip[i1][j1]; // Get the bit number (index)
                    newBlock.append(block.charAt(bitNumber-1)); // New block appends the character at the specified
                                                                // position
                }
            }
            this.permutedBlocks.add(newBlock.toString()); // Add the block to the Vector of permuted blocks
        }
    }
 

     /** 
        Access method to get private variable permutedBlocks
        @return Vector that stores 64 bit permuted blocks
	 */
    public Vector<String> getPermutedBlocks() {

        return this.permutedBlocks;
    }   

     /** 
        Access method to get private variable blocks 
        @return Vector that stores 64 bit blocks from Split Class
	 */
    public Vector<String> getBlocks() {

        return super.getBlocks();
    }   
}
