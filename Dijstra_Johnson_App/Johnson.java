// Name: Andrew Steinbrueck
// ID: 3949010
// x500: stein936

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.util.*;
import java.io.*; 

public class Johnson{

	public int numberOfVertices;	// total number of vertices, not counting super vertex
	public int totalColumns;		// equal to numberOfVertices
	public int[][] matrix;			// adjacency matrix read in and computed on
	public int[][] primeMatrix;		// prime adjacency matrix, includes super vertex
	public int[] vertexValue;		// the values of every vertex
	public int[] route;				// shortest path from source vertex to all other vertices, used in Dijkstra
	public boolean notNegCycle = true;	// check for negative cycle
	public boolean[] visited;			// check if vertex was processed, used for Dijkstra
	public int infinity = 2000000;		// infinity value, no edges too
	
	//constructor
	public Johnson(){
		this.numberOfVertices = 0;
		this.totalColumns = 0;
	}

	//johnson algorithm
	public boolean johnsonAlgo(){
		buildPrimeMatrix();		// build super vertex matrix'
		this.notNegCycle = BellmanFord();	// find shortest paths with Bellman-Ford, check for negative cycle
		route[0] = 0;						
		if(notNegCycle == true){		//if no negative cycle
			Dijkstra dijk = new Dijkstra(0, visited, route, matrix);	// instantiate to call Dijkstra Algorithm
			for(int u = 0; u < numberOfVertices; u++){					// re-weight adjacency matrix, no negative edges
				for(int v = 0; v < numberOfVertices; v++){
					matrix[u][v] = matrix[u][v] + vertexValue[u] - vertexValue[v];
				}
			}
					
			for(int i = 0; i < numberOfVertices; i++){		// use dijkstra on re-weighted adjacency matrix
				dijk.dijkstraAlgo(i, matrix);				// find shortest paths for all vertices
			}		
			
			for(int u = 0; u < numberOfVertices; u++){		// un-weight adjacency matrix to get correct values
				for(int v = 0; v < numberOfVertices; v++){
					matrix[u][v] = matrix[u][v] - vertexValue[u] + vertexValue[v];
				}
			}
			
			return true;		//no negative cycle
		}
		return false;			//negative cycle
	}

	//build super vertex matrix'
	public void buildPrimeMatrix(){
		this.primeMatrix = new int[numberOfVertices + 1][totalColumns + 1];		// matrix' 1 row and column > matrix
		for(int i = 0; i < numberOfVertices; i++){		// initialize matrix'	
			for(int j = 0; j < totalColumns; j++){	
					primeMatrix[i][j] = matrix[i][j];
			}
		}	
		for(int i = 0; i < numberOfVertices + 1; i++){			
			for(int j = totalColumns; j < totalColumns + 1; j++){
				if(i == numberOfVertices){				// initialize matrix' super vertex edges to all 0's
						primeMatrix[i][j] = 0;
				}
				else if(j == totalColumns){		
					primeMatrix[i][j] = infinity;		// initialize no edges from other vertices to super vertex
				}
			}
		} 
	}

	// find shortest paths for all source vertices to destination vertices for matrix'
	public boolean BellmanFord(){
		initializeVertices();					// initialize all to infinity except source = 0
		int source = numberOfVertices;			// start from super vertex
		for(int i = 0; i < numberOfVertices - 1; i++){
			for(int u = source; u > 0; u--){
				for(int v = 0; v < numberOfVertices; v++){		
					if (vertexValue[v] > vertexValue[u] + primeMatrix[u][v]){	// relax edges
						vertexValue[v] = vertexValue[u] + primeMatrix[u][v];
					}
				}
			}
		}
		
		for(int i = 0; i < numberOfVertices; i++){			// check for negative cycle
			for(int j = 0; j < numberOfVertices; j++){
				if(vertexValue[i] > vertexValue[j] + primeMatrix[j][i]){
					return false;		// negative cycle exists
				}
			}
		}
		return true;		// no negative cycle
	}
	
	// initialize all vertex values to infinity and visited to false
	public void initializeVertices(){
		vertexValue = new int[numberOfVertices+1];
		route = new int[numberOfVertices];
		visited = new boolean[numberOfVertices];
		for(int i = 0; i < numberOfVertices; i++){
			route[i] = infinity;
			vertexValue[i] = infinity;
			visited[i] = false;
		}
		vertexValue[numberOfVertices] = 0;		// super vertex = 0 as source
		route[0] = 0;
	}
	
	
	public boolean read(String fileName){		// read in file	
		Scanner s =  null;  
		try  {
			s =  new  Scanner( new  File(fileName));
			
			 } catch  (Exception e) {  
			return false ; }
		
		//s.useDelimiter(System.getProperty("line.separator"));
		//long[] temp = new long[30];
		//int ind = 0;
		while(s.hasNextLine()){			//count # of vertices by # of lines
			this.numberOfVertices++;
			s.nextLine();		
		}
		this.totalColumns = numberOfVertices;	//assume square matrix, cols = rows
		this.matrix = new int[numberOfVertices][totalColumns];
		this.route = new int[numberOfVertices];
		Scanner sc =  null;  
		try  {
			sc =  new  Scanner( new File(fileName));		
			 } catch  (Exception e) {  
			return false ; }
		for(int i = 0; i < numberOfVertices; i++){		//read in integers into matrix
			for(int j = 0; j < totalColumns; j++){
				 matrix[i][j] = sc.nextInt();
			}
		}	 
		return true;		
	}
	
	public boolean write(String fileName){			// write to file
		PrintWriter p = null;
		try{
			p = new PrintWriter( new File(fileName)); }  catch (Exception e) {
		return false; }
		
		boolean b = johnsonAlgo();			// if no negative cycle, print shortest path matrix
		if(b){
			for(int i = 0; i < numberOfVertices; i++){
				for(int j = 0; j < numberOfVertices; j++){
					if(i == numberOfVertices - 1 && j == numberOfVertices - 1){
						p.print(matrix[i][j]);
						break;							// to avoid any white space after very last element printed
					}
					else{
					p.print(matrix[i][j] + " " );		// otherwise, print regularly
					}
				}
				p.print("\n");
			}
		}
		else{								// if negative cycle exists, print default message "Negative Cycle"
			p.print("Negative cycle");
		}	
			
		p.close();
		return true;
	}



}