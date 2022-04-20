package DES;
import java.util.Vector;

/**
    Describes the method of Manager class
    The class has method getEncryptedBlocks()
 */
public interface IRoundManager {

    /** 
        This method takes each block from the vector of blocks
        and then it splits it into left and right half, which are then passed to
        round one, and afterwards go through other 15 rounds
        @return Vector of Strings that hold encrypted blocks
	 */
    public Vector<String> getEncrpyedBlocks();  
}
