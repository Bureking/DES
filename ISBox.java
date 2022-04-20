package DES;
/**
 * Describes the method of SBox
 * Method get returns the blocks of 32 bits 
 * of the block that goes through the SBoxes of DES Algorithm
 * String of 48 bits is passed
 */
public interface ISBox {

    /** 
        Method that handles the SBoxes and 
        Passes the right input to each SBox
        @param block 48 bit block from fFunction 
        @return String that represents 32 bit block after SBox calculations
	 */
    public String get(String block);   
}
