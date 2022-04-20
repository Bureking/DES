package DES;
import java.util.Vector;

/**
    Describes the method of InitialPermutation
    Method getPermutedBlocks() returns the blocks of 64 bits 
    of the 64 bit blocks of input file after initial permutation 
    of DES algorithm
    Extends ISplit because initial permutation
    will be applied to each split block 
 */
public interface IInitialPermutation extends ISplit {

    /** 
        Access method to get private variable blocks 
        @return Vector that stores 64 bit blocks from Split Class
	 */
    public Vector<String> getPermutedBlocks(); 
}
