package DES;
import java.util.Queue;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
   * A class that generates and handles keys for each round
   * Keys in DES algorithm are 64 bits initially, but get reducded to 48 
   * A random 64 bit key is generated called K0
   * Then, the key goes through permutation 1 where it is reduced to 56 bits
   * For each round this key is shifted left by either 1 or 2 bits depending on the round
   * This is handled by the iterationShifts mapping below
   * Afterwards each subkey K1...K16 goes to permutation 2 where it is reduced to 48 bits
   * Each block uses exact same 16 keys, therefore the keys must be arranged such that in each round 1
   * Exaxt same key is used, etc.
   * For this reason Singleton Design Pattern is used
   * To ensure that only one Key object exists that will provide correct keys  
   * @author Hanadi Jusufovic 
*/
public class Key  {

    private Queue<String> keys; // Queue that holds keys
    private String key64; // Variable that holds generated 64 bit key
    private String key56; // Variable that holds 56 bit key
    private final int[][] pc1 = {{57, 49, 41, 33, 25, 17, 9},
                                 {1, 58, 50, 42, 34, 26, 18},
                                {10, 2, 59, 51, 43, 35, 27},
                                {19, 11, 3, 60, 52, 44, 36},
                                {63, 55, 47, 39, 31, 23, 15},
                                {7, 62, 54, 46, 38, 30, 22},
                                {14, 6, 61, 53, 45, 37, 29},
                                {21, 13, 5, 28, 20, 12, 4}}; // Permutation one
    
    
    private final int[][] pc2 = {{14, 17, 11, 24, 1, 5},
                                {3, 28, 15, 6, 21, 10},
                               {23, 19, 12, 4, 26, 8},
                               {16, 7, 27, 20, 13, 2},
                               {41, 52, 31, 37, 47, 55},
                               {30, 40, 51, 45, 33, 48},
                               {44, 49, 39, 56, 34, 53},
                               {46, 42, 50, 36, 29, 32}}; // Permutation two

    // Mapping between round number and number of left shifts
    private Map<Integer, Integer> iterationShifts = new HashMap<Integer, Integer>();
    private static Key instance = null; // Key object instance


     /** 
        Key Private Contructor Method 
	*/
    private Key() {

        this.keys = new LinkedList<String>(); // Initialize Queue 
        this.key64 = buildBinary(generateKey64());

        this.key56 = permuteKey64();
        createMap();
        generateKeys48();
    }

     /** Method that handles the key instance creation
      * It creates an instance of Key object if the Key object does not exist
      * Otherwise it will return the object
      * @return Key object
	 */
    public static synchronized Key getInstance() {

        if(instance == null) {
            instance = new Key();
        }
        return instance;
    }

     /** Method that creates the mapping between round number and the number of left
      * shifts that are needed to generate subkeys
	 */
    private void createMap() {
        iterationShifts.put(1, 1);
        iterationShifts.put(2, 1);
        iterationShifts.put(3, 2);
        iterationShifts.put(4, 2);
        iterationShifts.put(5, 2);
        iterationShifts.put(6, 2);
        iterationShifts.put(7, 2);
        iterationShifts.put(8, 2);
        iterationShifts.put(9, 1);
        iterationShifts.put(10, 2);
        iterationShifts.put(11, 2);
        iterationShifts.put(12, 2);
        iterationShifts.put(13, 2);
        iterationShifts.put(14, 2);
        iterationShifts.put(15, 2);
        iterationShifts.put(16, 1);

    }

     /** Method that genereated a random 64 bit key
      * @return 64 bit hexa decimal String 
	 */
    private String generateKey64() {

        String validCharacters = "0123456789abcdef";
        StringBuilder builder = new StringBuilder();

        //
        for(int i=0; i<16; i++) {

            double randNumber = Math.random();

            int index = (int)(validCharacters.length() * randNumber);
            builder.append(validCharacters.charAt(index));
        }
        return builder.toString();
    }

     /** Method that converts a Hexademical 64 bit key into 
      * binary 64 bit key
      * @param hex hexademical key representation 
      * @return 64 bit binary key
      * 
	 */
    private String buildBinary(String hex) {
        hex = hex.replaceAll("0", "0000");
        hex = hex.replaceAll("1", "0001");
        hex = hex.replaceAll("2", "0010");
        hex = hex.replaceAll("3", "0011");
        hex = hex.replaceAll("4", "0100");
        hex = hex.replaceAll("5", "0101");
        hex = hex.replaceAll("6", "0110");
        hex = hex.replaceAll("7", "0111");
        hex = hex.replaceAll("8", "1000");
        hex = hex.replaceAll("9", "1001");
        hex = hex.replaceAll("a", "1010");
        hex = hex.replaceAll("b", "1011");
        hex = hex.replaceAll("c", "1100");
        hex = hex.replaceAll("d", "1101");
        hex = hex.replaceAll("e", "1110");
        hex = hex.replaceAll("f", "1111");
        return hex;
    }

     /** Method that permutes 64 bit key 
      * into 56 bit key using permutation table one
	 */
    private String permuteKey64() {

        StringBuilder builder = new StringBuilder();

        for(int i=0; i<pc1.length; i++) {
            for(int j=0; j<pc1[1].length; j++) {

                int bitNumber = pc1[i][j];
                builder.append(this.key64.charAt(bitNumber-1));

            }
        }
        return builder.toString();

    }
    

     /** Method that generates the subkeys
      * It splits the 56 bit key into two equal halfs
      * It generates 16 keys
	 */
    private void generateKeys48() {

        String C = this.key56.substring(0, this.key56.length() / 2);
        String D = this.key56.substring(this.key56.length() / 2, this.key56.length());
        
        int c=0;

        while(c < 16) {

            //Bit shifts
            int k = iterationShifts.get(c+1) % C.length();
            C = C.substring(k) + C.substring(0, k);
            D = D.substring(k) + D.substring(0, k);

            //Concetanate
            String CD = C + D;
            //pc2
            StringBuilder builder = new StringBuilder();
            
            for(int i=0; i<pc2.length; i++) {
                for(int j=0; j<pc2[1].length; j++) {
    
                    int bitNumber = pc2[i][j];
                    builder.append(CD.charAt(bitNumber-1));
    
                }
            }
            c++;
            //add to keys
            keys.add(builder.toString());
        }

    }
    
     /** Method that handles the key retrieving 
      * It removes the key, but then it adds the same key
      * to the end of the queue, so that after 16 rounds, Key 1 
      * will come back to position 1 in queue
      * @return String key 
	 */
    public String getKey() {

        String key = keys.remove();
        keys.add(key);
        return key;
    }
}
