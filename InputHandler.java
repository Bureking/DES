package DES;
import java.io.FileInputStream; 
import java.io.*;


/**
    A class that implements IInputHandler Interface
    Inputhandler class reads the input file
    And converts it into binary representation
    @author Hanadi Jusufovic
*/
public class InputHandler implements IInputHandler  {

    private File file; // Reference to the File object 
    private FileInputStream stream; // Reference to FileInputStream object
    private String binary; // Private String attribute binary

     /** 
        InputHandler Contructor Method
        @param inputFile name of the input file
        @throws FileNotFoundException if file not found throws an exception
	*/
    
    public InputHandler(String inputFile) throws FileNotFoundException {

        try {
            
            //Initialize the variables in try/catch statement
            this.file = new File(inputFile);
            this.stream = new FileInputStream(this.file);
        }
        // Catch FileNotFoundException
        catch(FileNotFoundException e) {

            e.printStackTrace();
        }
        // Convert the file to binary representation
        this.binary = buildBinary(read());
        
        // Check if the input file has any characters
        // If not exit
        if(this.binary.length() == 0) {
            System.out.println("Input file is empty!");
            System.exit(1);
        }
    } // End constructor

    /** 
        Returns the input file as String of characters
        that are contained in the input file
        @return Data stored in the input file
	 */
    private String read() {

        StringBuilder stringBuilder = new StringBuilder(); // Create StringBuilder object
        int i=0; // Iterator for parsing the file

        try { // Try/Catch block for reading the file

            // While the file contains characters
            while((i=this.stream.read()) != -1) {

                stringBuilder.append((char)i); // Append the character at position i to StringBuilder object
        }

        this.stream.close(); // Close the stream

    } catch(Exception e) {

        e.printStackTrace();
    }

    return stringBuilder.toString(); // Return String object
}

    /** 
        Converts file of character
        To binary representation
        @param file input file
        @return Data converted to binary representation
	 */
    private String buildBinary(String file) {

        StringBuilder builder = new StringBuilder();

        char[] chars = file.toCharArray();
        for(char aChar : chars) {
            
            builder.append(String.format("%8s", Integer.toBinaryString(aChar)).replaceAll(" ", "0"));
        }

        return builder.toString();      
    }
    /** 
        Access method to get private variable binary
        @return Data stored in variable binary
	 */
    public String getBinary() {

        return this.binary;
    }
}
