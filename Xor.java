package DES;
/**
    A class that implements IXor Interface
    Performs the calculation on two characters
    That are in binary and returns a String that
    Represents the XOR calculation for these two binary
    bits
    @author Hanadi Jusufovic
*/

public class Xor implements IXor {

    private String xorValue;

    /** 
        Xor Contructor Method
	*/
    public Xor() {

    } // End constructor

    /**
        Method xor calculates the xor for two bits 
        @param a character
        @param b character
        @return String that represents xor calculatuon for these two bits
     */
    public String xor(char a, char b) {

        if(a=='0' && b=='1') {

            this.xorValue = "1";
        }
        else if(a=='1' && b=='0') {

            this.xorValue = "1";
        }
        else {

            this.xorValue = "0";
        }
        
        return this.xorValue;
    }
}
