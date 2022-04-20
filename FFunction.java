package DES;
/**
    A class that implements IFFunction Interface
    DES F Function takes the input of 32 bits
    Firstly it expands 32 bits to 48 using expansion table
    Then it xor's the expanded block with the round key
    The result from this xor goes into SBoxses of DES algorithm
    Where it is reduced to 32 bits
    These 32 bits finally go through a permutation
    @author Hanadi Jusufovic
*/
public class FFunction implements IFFunction {


    private String fFunctionValue; // String object that will hold the final calculation
                                    // Of F function
    private SBox sBox; // SBox Object that hold 8 SBoxes of DES Algorithm
    private IXor xor; // Xor Object
    private Key keyObject; // Key object that contains round keys

    private final int[][] exp = {{32, 1, 2, 3, 4, 5},
                                 {4, 5, 6, 7, 8, 9},
                                {8, 9, 10, 11, 12, 13},
                                {12, 13, 14, 15, 16, 17},
                                {16, 17, 18, 19, 20, 21},
                                {20, 21, 22, 23, 24, 25},
                                {24, 25, 26, 27, 28, 29},
                                {28, 29, 30, 31, 32, 1}}; // Expansion table

    private final int[][] perm = {{16, 7, 20, 21},
                                {29, 12, 28, 17},
                                {1, 15, 23, 26},
                                {5, 18, 31, 10},
                                {2, 8, 24, 14},
                                {32, 27, 3, 9},
                                {19, 13, 30, 6},
                                {22, 11, 4, 25}}; // Permutation table



    /** 
        FFunction Contructor Method
	*/
   public FFunction() {

        // Initialize the attributes
        this.sBox = new SBox();
        this.keyObject = Key.getInstance();
        this.xor = new Xor();
   } // End constructor 

   /** 
        Performs the F Function calculations
        And returns a String that represents 32 bit block
        @param block right half (32 bits) of each round is passed into fFunction
        @return String that represents the 32 bit block on which fFunction calculations are completed
	 */
   public String get(String block) {
    
    String expanded = expansion(block); // Expanded block

    String key = this.keyObject.getKey(); // Get the round key
                                    // Key class ensures that the right key is given


    StringBuilder builder = new StringBuilder(); // StringBuilder object for xor value

    for(int i=0; i<expanded.length(); i++) {
        char a = expanded.charAt(i);
        char b = key.charAt(i);

        builder.append(this.xor.xor(a, b));

    }

    String valueForSBox = builder.toString(); // String of 48 bits that is passed to SBox

    String valueFromSbox = this.sBox.get(valueForSBox); // String od 32 bits after SBox
    this.fFunctionValue = permuation(valueFromSbox); // Permutation

    return this.fFunctionValue; // Return 
   }

   /** 
        Expands the block to 48 bits using expansion table
        @param block block of 32 bits that is passed to fFunction
        @return String that represents expanded block (48 bits)
	 */
   private String expansion(String block) {

    StringBuilder builder = new StringBuilder();

    // Loop through the expansion table
    for(int i=0; i<exp.length; i++) {
        for(int j=0; j<exp[1].length; j++) {

            int bitIndex = exp[i][j];
            builder.append(block.charAt(bitIndex-1));
        }
    }
    
    return builder.toString();
   }

   
    /** 
        Permutes the block to 32 bits using permutation table
        @param block block of 32 bits that is obtaiend from sBoxes
        @return String that holds 32 bits of permuted bits in fFucntion
	 */
   private String permuation(String block) {

    StringBuilder builder = new StringBuilder();

    for(int i=0; i<perm.length; i++) {
        for(int j=0; j<perm[1].length; j++) {

            int bitIndex = perm[i][j];
            builder.append(block.charAt(bitIndex-1));
        }
    }

    return builder.toString();
   }

}
