package DES;
/**
    Describes the method of Round
    Method get(String left, String right) returns the blocks of 64 bits 
    of the block that goes through one round
    Two strings left and right, of 32 bits are passed
 */

public interface IRound {

    /** Method that handles the round and 
      * For each round Ln = R(n-1)
      * Rn = L(n-1) XOR FFunction(R(n-1), Kn)
      * FFunction manages the key
	 	@return String that represents 64 bit block after one round is performed
        @param left 32 bits left side
        @param right 32 bits right side
	 */
    public String get(String left, String right);
}
