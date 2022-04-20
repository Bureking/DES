package DES;
/**
    Describes the method of FFunction class
    Method get() returns the blocks of 32 bits 
    that represent the block that goes through the F function
    Of the DES algorithm
 */
public interface IFFunction {

    /** 
        Performs the F Function calculations
        And returns a String that represents 32 bit block
        @param block right half (32 bits) of each round is passed into fFunction
        @return String that represents the 32 bit block on which fFunction calculations are completed
	 */
    public String get(String block);   
}
