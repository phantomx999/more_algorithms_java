// Name: Andrew Steinbrueck
// ID: 3949010
// x500: stein936

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

public class JohnsonApp{

	public static void main(String[] args){
	
		Johnson a = new Johnson();			// create johnson object
		a.read(args[0]);					// read in file
		a.write("output.txt");				// write to file
	}
}