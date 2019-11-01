// Name: Andrew Steinbrueck
// ID: 3949010
// x500: stein936

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

//same Dijkstra class/algorithm from Problem 3, but omit visited[destination] == false in
//the for loops test cases since we are trying to find the shortest path from every vertex
//to every other vertex, not shortest path from source vertex to a destination vertex only.
//also, omit several variables such as distanceTraveled, shortestPath and backwardsPath as they do not pertain to problem #4.
//lastly, make more slight adjustments to code from problem3 as Dijkstra is being used to find all shortest
//paths from all vertices and is dijkstraAlgo() is being called called by an instantiated Dijkstra object
//in problem4, so appropriate adjustments were made from problem3 program to work for problem4 program

public class Dijkstra{

	public int[][] matrix;		//Graph containing all edges and vertices.  
	public int source;			// beginning vertex, start at 0 distance traveled
	public boolean[] visited;		// adjacent vertices, check if visited 
	public int[] route;			// shortest path route traveled 
	public int distanceTraveled;	//total distance traveled in shortest path
	public int countRows;				//# of vertices in graph, also # of rows in matrix read in
	public int countColumns;			// columns in matrix, columns = rows = # of vertices
	public int destination;				// end vertex in route
	public int[] shortestPath;			// shortest path from source to destination vertex
	public static final int infiValue = 2000000;	// infinity values set for non source vertices, also non existent edges
	
	public Dijkstra(){		// constructor
		this.source = 0;
		this.countRows = 0;
		this.countColumns = 0;
		this.distanceTraveled = 0;
		this.destination = 0;
	}
	
	public Dijkstra(int source, boolean[] visited, int[] route, int[][] matrix){	// constructor
		this.source = source;
		this.countRows = matrix[0].length;
		this.countColumns = matrix[0].length;
		this.distanceTraveled = 0;
		this.destination = 0;
		this.visited = visited;
		this.route = route;
		this.matrix = matrix;
	}
	
	public void dijkstraAlgo(int source, int[][] matrix, int[] route, boolean[] visited){		// Dijkstra Algorithm
		initializeProblem(source, visited, route, matrix);		// first initialize if not done already	
		findShortestPath(source, visited, route, matrix);		// use Dijkstra to find shortest path
	}
	
	//first set all vertices to false since they have not been visited yet,
	// and also initialize all vertexes distances to infinity 
	public void initializeProblem(int source, boolean[] visited, int[] route, int[][] matrix){
		for(int i = 0; i < route.length; i++){
			visited[i] = false;
			route[i] = infiValue;
		}
		//now set source vertex distance to 0, since source vertex will be every vertex eventually, just start at 0
		route[source] = 0;
	}
	
	//Djikstra's algorithm to find shortest path
	public void findShortestPath(int source, boolean[] visited, int[] route, int[][] matrix){
		int u = source;			// current source vertex
	//	int[] temp = new int[countRows];
		//int[] backwardsPath = new int[countRows];	// from problem3, ignore
		
		// process all vertices in graph to find min distance from current vertex to all other vertices
		for(int vertex = 0; vertex < countRows; vertex++){	
			u = computeMin();				// find min distance from current vertex to adjacent vertices			
			visited[u] = true;				// min distance vertex visited. 
			for(int v = 0; v < countColumns; v++){
				if(matrix[u][v] + route[u] < route[v] && matrix[u][v] != 0){	// relax edges
					route[v] = matrix[u][v] + route[u]; 
					//temp[v] = u;
				}					
			} 	
		}
		
		for(int i = 0; i < countColumns; i++){	// set matrix to  shortest paths of current source vertex...
			matrix[source][i] = route[i];		// ...to all other adjacent vertices of that source
		}	
	}
	
	public int computeMin(){			// finds minimum 
		int min = source;							// minimum vertex, set to source initially
		int value = infiValue;						// element tested for minimum value in array 
		for(int i = 0; i < countColumns; i++){		
			if( visited[i] == false){			// vertex not been visited yet and vertex < infinity distance
					if( value > route[i]){		
						min  = i;				// set min to lowest vertex 
						value = route[i];		// set min value to smallest vertex value
					}
			} 	
		}
		return min;							// return the vertex with smallest value
	}
}