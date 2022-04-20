package DES;
/**
    Describes the method of Xor
    Method xor() returns the xor calculation 
    on two bits passed as parameters
 */
public interface IXor {

    /**
        Method xor calculates the xor for two bits 
        @param a character
        @param b character
        @return String that represents xor calculatuon for these two bits
     */
    public String xor(char a, char b);
}
