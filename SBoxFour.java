package DES;

/**
    A class that implements ISBox Interface
    Performs the calculations of SBox Four 
    Uses Directory Design Pattern 
    Each SBox performs similar calculation
    But the uses different table
    @author Hanadi Jusufovic
*/
public class SBoxFour  implements ISBox {


    private final int[][] box = {{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                                {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                                {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                                {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}};

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
    
    
    /** 
        SBoxFour Contructor Method
	*/
    public SBoxFour() {}
    
}
