package DES;
/**
    A class that implements IFinalPermutation interface
    Class FinalPermutation performs the final permutation after 16 rounds
    And creates the encryptedBlock
    @author Hanadi Jusufovic
*/
public class FinalPermutation implements IFinalPermutation {

    private final int[][] fp = {{40, 8, 48, 16, 56, 24, 64, 32},
                                 {39, 7, 47, 15, 55, 23, 63, 31},
                                {38, 6, 46, 14, 54, 22, 62, 30},
                                {37, 5, 45, 13, 53, 21, 61, 29},
                                {36, 4, 44, 12, 52, 20, 60, 28},
                                {35, 3, 43, 11, 51, 19, 59, 27},
                                {34, 2, 42, 10, 50, 18, 58, 26},
                                {33, 1, 41, 9, 49, 17, 57, 25}}; // fp table

    private String returnValue;
    
     /** 
    This method takes 64 bit block and performs final permutation
    based on the fp table of the DES algorithm
    @param block 64 bit block after 16 rounds
    @return String that represents encrypted block 
	 */
    public String getFinalPermutation(String block) {

        this.returnValue = "";

        for(int i=0; i<fp.length; i++) {
            for(int j=0; j<fp[1].length; j++) {

                int bitIndex = fp[i][j];

                this.returnValue = this.returnValue + block.charAt(bitIndex-1);
            }
        }
        return this.returnValue;

    }

     /** 
        FinalPermutation Contructor Method
	*/
    public FinalPermutation() {
        
    } // End constructor
}
