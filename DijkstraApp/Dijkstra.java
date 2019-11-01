// Name: Andrew Steinbrueck
// ID: 3949010
// X500: stein936

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

public class Dijkstra{

	public int[][] matrix;							// Graph containing all edges and vertices.  
	public int source;								// beginning vertex, start at 0 distance traveled
	public boolean[] visited;						// adjacent vertices, check if visited 
	public int[] route;								// vertex values in matrix
	public int distanceTraveled;					// total distance traveled in shortest path
	public int countRows;							// # of vertices in graph, also # of rows in matrix read in
	public int countColumns;						// columns in matrix, columns = rows = # of vertices
	public int destination;							// end vertex in route
	public int[] shortestPath;						// shortest path from source to destination
	public static final int infiValue = 2000000;	// infinity values set for non source vertices, also non existent edges
	
	public Dijkstra(){			// constructor
		this.source = 0;
		this.countRows = 0;
		this.countColumns = 0;
		this.distanceTraveled = 0;
		this.destination = 0;
	}
	
	public void dijkstraAlgo(int source, boolean[] visited, int[] route){	// Dijkstra shortest path algorithm
		initializeProblem();												// first initialize vertices to infinity
		findShortestPath(source, visited, route);							// then find shortest path with Dijkstra
	}
	
	//first set all vertices to false since they have not been visited yet,
	// and also initialize all vertexes distances to infinity 
	public void initializeProblem(){
		for(int i = 0; i < route.length; i++){
			visited[i] = false;
			route[i] = infiValue;
		}
		//now set source vertex distance to 0 and visited to true for source
		route[source] = 0;
	}
	
	//get shortest path from source to destination
	public void findShortestPath(int source, boolean[] visited, int[] route){
		int u = source;								// current vertex
		int[] temp = new int[countRows];			// stores current vertex being processed, used as PI path (backwards path)			
		int[] backwardsPath = new int[countRows];	// shortest path backwards (dest --> source)
		
		//go through all vertices in graph to find min distance from current vertex
		for(int vertex = 0; vertex < countRows && visited[destination] == false; vertex++){	
		
			u = computeMin();						// find min distance of current vertex to adjacent vertices			
			visited[u] = true;						// min distance vertex visited. 
			
			for(int v = 0; v < countColumns && visited[destination] == false; v++){
				if(visited[v] == false && matrix[u][v] + route[u] < route[v] && matrix[u][v] != 0){
				
					route[v] = matrix[u][v] + route[u]; 	// relax edges, once destination vertex processed then break loop
					temp[v] = u;							// store current vertex visited
					
				}					
			} 	
		}
		distanceTraveled = route[destination];		// smallest total distance traveled from source to dest
		int index = destination;					// temp index
		int element = destination;					// edge value 
		for(int i = 0; i < countRows; i++){ 		// currently, Dijkstra relaxed edges and set up shortest path...
			backwardsPath[i] = index;				// ...from source to dest, but we now have to ...
			element = temp[index];					// ...put this path data into array without any extra data (i.e. vertices not in shortest path from source --> dest)
			index = element;						// elements in temp are PI path to the previous adjacent vertex
		}											// so put these elements into backwardsPath, then reverse backwardsPath
													// to get shortestPath from source to dest
		int ind = 0;
		int count = 0;
		boolean flag = false;						// check if backwardsPath reaches source vertex
		while(!flag){								// to get length of shortestPath
			count++;
			if(backwardsPath[ind] == source){
				flag = true;						// reached source vertex, done finding length of shortestPath
			}
			ind++;
		}	
		shortestPath = new int[count];				// set length of shortestPath
		for(int i = 0, j = count - 1; i < count && j >= 0; i++, j--){
			shortestPath[i] = backwardsPath[j];		// reverse order of backwardsPath to get shortestPath from source to dest
		}
	}
	
	public int computeMin(){						// find min 
		int min = source;							// minimum vertex, set to infinity initially
		int value = infiValue;						// element tested for minimum value in array 
		for(int i = 0; i < countColumns; i++){
			if( visited[i] == false){				// vertex has not been visited yet and vertex less than infinity distance
					if( value > route[i]){		
						min  = i;					// set min to current vertex
						value = route[i];			// set min value to current vertex value
					}
			} 	
		}
		return min;		// return min vertex
	}
	
	public boolean read(String fileName){				// read in file
		Scanner s =  null;  
		try  {
			s =  new  Scanner( new  File(fileName));	
			
			 } catch  (Exception e) {  
			return false ; }
		this.source = Integer.parseInt(s.next());				// read in source
		this.destination = Integer.parseInt(s.next());			// read in destination
		s.nextLine();											
		s.useDelimiter(System.getProperty("line.separator"));
		while(s.hasNextLine()){									// read in lines = number of vertices
			countRows++;
			s.nextLine();		
		}
		countColumns = countRows;								// assume square matrix, cols = rows
		Scanner sc =  null ;  
		try  {
			sc =  new  Scanner( new  File(fileName));
			
			 } catch  (Exception e) {  
			return false ; }	
		sc.nextLine();										    // start read in matrix here
		this.matrix = new int[countRows][countColumns];			// set length of matrix to number of vertices
		for(int i = 0; i < countRows; i++){
			for(int j = 0; j < countColumns; j++){
				matrix[i][j] = Integer.parseInt(sc.next());		// put ints into matrix
			}
		}	
		this.route = new int[countRows];			//set to number of vertices in graph
		this.visited = new boolean[countRows];		//  			" "
		
		return true;						
	}


	public boolean write(String fileName){			// write to file
		PrintWriter p = null;
		try{
			p = new PrintWriter( new File(fileName)); }  catch (Exception e) {
		return false; }
		dijkstraAlgo(source, visited, route);			// use Dijkstra to find shortestPath and distanceTraveled
		p.print(distanceTraveled + ": ");				// print distance Traveled....
		for(int i = 0; i < shortestPath.length; i++){
			if(i == shortestPath.length - 1){
				p.print(shortestPath[i]);					// avoid printing white spaces after last element in shortestPath
			}
			else{
				p.print(shortestPath[i] + " ");				// print shortest Path
			}
		} 
		p.close();
		return true;
	}
}