package DES;
/**
    Describes the method of FinalPermutation class
 */
public interface IFinalPermutation {

     /** 
    This method takes 64 bit block and performs final permutation
    based on the fp table of the DES algorithm
    @param block 64 bit block after 16 rounds
    @return String that represents encrypted block 
	 */
    public String getFinalPermutation(String block);
}
