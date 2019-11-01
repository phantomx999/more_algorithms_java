import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

public class DijkstraApp2{

	public static void main(String[] args){
	
		Dijkstra2 a = new Dijkstra2();
		a.read("problem2.txt");
		a.write("output2.txt");
	
	}

}