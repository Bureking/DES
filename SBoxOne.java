package DES;
/**
    A class that implements ISBox Interface
    Performs the calculations of SBox One 
    Uses Directory Design Pattern 
    Each SBox performs similar calculation
    But the uses different table
    @author Hanadi Jusufovic
*/
public class SBoxOne implements ISBox {

    private final int[][] box = {{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                            {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                            {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                            {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}}; //SBox One table

    
    /** 
        SBoxOne Contructor Method
	*/
    public SBoxOne() {} // End constructor
    
     /** 
        Method that handles the SBox calculation 
        String of 6 bits is passed
        4 bits are returned
        The calculation proceeds as follows:
        The first and last bit of the input represent in base 2
        a number in the decimal range 0 to 3 (00 to 11)
        Let that number be i
        Middle 4 bits represent in base 2 a number in decimal range
        0 to 15, let that number be j
        Look up the number in the table in i-th row
        and j-th column. It is a number in 0 to 15 range that is uniquely 
        represented by a 4 bit block, that block is output of SBox
        @param block 6 bit block that is managed by SBox class
        @return String that represents 4 bit block of the SBox calculations
	 */
    public String get(String block) {

        String i = block.substring(0, 1) + block.substring(block.length() - 1);
        String j = block.substring(1, block.length() - 1);

        int i1 = Integer.parseInt(i, 2);
        int j1 = Integer.parseInt(j, 2);

        String value = String.format("%" + 4 + "s", Integer.toBinaryString(box[i1][j1])).replaceAll(" ", "0");
      
        return value;   
    }                   
}
