package DES;
import java.util.Vector;

/**
    Describes the method of OutoutHandler
    Method write() writes to the output file
 */
public interface IOutputHandler {

    /** 
        Converts 64 bit encrypted blocks to characters
        And writes them to the output file
        @param encryptedBlocks Vector of Strings
	 */
    public void write(Vector<String> encryptedBlocks);
}
