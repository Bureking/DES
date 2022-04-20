package DES;
import java.util.List;
import java.util.ArrayList;

/**
    A class that implements ISBox Interface
    Performs the calculations of SBoxes
    Uses Directory Design Pattern to hold the 8 SBoxes
    @author Hanadi Jusufovic
*/
public class SBox implements ISBox {

    private List<ISBox> boxes; // List of SBoxes

     /** 
        SBox Contructor Method
	*/
    public SBox() {

        this.boxes = new ArrayList<ISBox>(); // Initialize the List
        createBoxes(); // Method that initializes the boxes
                        // And adds them to the directory
    } // End constructor

     /** 
        Method that initializes the boxes
        And adds them to the list 
	 */
    private void createBoxes() {

        this.boxes.add(new SBoxOne());
        this.boxes.add(new SBoxTwo());
        this.boxes.add(new SBoxThree());
        this.boxes.add(new SBoxFour());
        this.boxes.add(new SBoxFive());
        this.boxes.add(new SBoxSix());
        this.boxes.add(new SBoxSeven());
        this.boxes.add(new SBoxEight());
    }

     /** 
        Method that handles the SBoxes and 
        Passes the right input to each SBox
        @param block 48 bit block from fFunction 
        @return String that represents 32 bit block after SBox calculations
	 */
    public String get(String block) {

        StringBuilder builder = new StringBuilder(); // StringBuilder object

        // For each box in boxes list
        for(int i=0; i<boxes.size(); i++) {

            ISBox current = boxes.get(i); // Get the current SBox

            // Each SBox takes 6 bits of input and returns 4
            // Total of 8 boxes means that the input is 48 bits
            // And output 32
            String range = block.substring(i*6, (i*6 + 6)); 

            String currentBoxOutput = current.get(range); // Get the calculation of current Sbox

            builder.append(currentBoxOutput); // Apppend the calculation to the builder
        }

        return builder.toString(); // Return the String
    }
}
