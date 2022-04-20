package DES;
/**
    A class that implements IRound Interface
    Performs one round
    And returns the String of 64 bits 
    @author Hanadi Jusufovic
*/
public class Round implements IRound {

    private IXor xor; // Xor Object
    private IFFunction fFunction; // FFunction Object

     /** 
        Round Contructor Method
	*/
    public Round() {
        
        // Initialize the attributes
        this.fFunction = new FFunction();
        this.xor = new Xor();
    }

     /** Method that handles the round and 
      * For each round Ln = R(n-1)
      * Rn = L(n-1) XOR FFunction(R(n-1), Kn)
      * FFunction manages the key
	 	@return String that represents 64 bit block after one round is performed
        @param left 32 bits left side
        @param right 32 bits right side
	 */
    public String get(String left, String right) {

        // Two strings for the return 
        String nextLeft;
        String nextRight;

        //Left(n) = Right(n-1)
        nextLeft = right;

        // Return from fFunction(R(n-1), Kn)
        String fRight = fFunction.get(right);

        // Xor fFunction return value and L(n-1)
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<left.length(); i++) {

            char a = left.charAt(i);
            char b = fRight.charAt(i);

            builder.append(xor.xor(a, b));
        }
        nextRight = builder.toString();

        return nextLeft + nextRight;
    }
}
