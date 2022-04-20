package DES;
import java.util.Vector;

/**
    Describes the method of Split
    Method getBlocks() returns the Vector of Strings 
    that represent blocks of 64 bits 
    of the input file represented in binary 
 */
public interface ISplit {

    /** 
        Access method to get private variable blocks
        @return Vector that stores 64 bit blocks
	 */
    public Vector<String> getBlocks();  
}
