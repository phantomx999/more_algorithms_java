// Andrew Steinbrueck
// ID: 3949010
// x500: stein936

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

public class finiteAutomatonApp{

	public static void main(String[] args){
	
		finiteAutomaton a = new finiteAutomaton();
		a.read(args[0]);
		a.write("output.txt");
	
	}

}
