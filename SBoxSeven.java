package DES;

/**
    A class that implements ISBox Interface
    Performs the calculations of SBox Seven 
    Uses Directory Design Pattern 
    Each SBox performs similar calculation
    But the uses different table
    @author Hanadi Jusufovic
*/
public class SBoxSeven  implements ISBox {


    private final int[][] box = {{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                                {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                                {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                                {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}};


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
        SBoxSeven Contructor Method
	*/
    public SBoxSeven() {}
          
}
