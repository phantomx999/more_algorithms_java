// Name: Andrew Steinbrueck
// ID: 3949010
// X500: stein936

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

public class DijkstraApp{

	public static void main(String[] args){
	
		Dijkstra a = new Dijkstra();
		a.read(args[0]);
		a.write("output.txt");
	
	}

}
