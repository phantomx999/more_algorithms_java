// Name: Andrew Steinbrueck
// ID: 3949010
// x500: stein936

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.util.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

//driver
public class topologicalGraphApp{

	public static void main(String[] args){
	
		topologicalGraph a = new topologicalGraph();
		a.read(args[0]);
		a.write("output.txt");
	
	}

}