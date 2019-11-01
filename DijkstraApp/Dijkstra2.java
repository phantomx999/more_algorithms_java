import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

public class Dijkstra2{

	public int[][] matrix;		//Graph containing all edges and vertices.  
	public int source;			// beginning vertex, start at 0 distance traveled
	public boolean[] visited;		// adjacent vertices, check if visited 
	public int[] route;			// shortest path route traveled 
	public int distanceTraveled;	//total distance traveled in shortest path
	public int countRows;				//# of vertices in graph, also # of rows in matrix read in
	public int countColumns;			// columns in matrix, columns = rows = # of vertices
	public int u;						// current vertex being visited
	public int destination;				// end vertex in route
	public int[] shortestPath;
	public static final int infiValue = 2000000;	// infinity values set for non source vertices, also non existent edges
	
	public Dijkstra2(){
		this.source = 0;
		this.u = 0;
		this.countRows = 0;
		this.countColumns = 0;
		this.distanceTraveled = 0;
		this.destination = 0;
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
		//visited[source] = true;
	}
	
	//Djikstra's algorithm to find shortest path
	public void findShortestPath(){
		int vertex;
		int[] temp = new int[countRows];
		int[] backwardsPath = new int[countRows];
		//int temp = 0;			// temp vertex
		//int countNeighborV = 0;
		//int[] adjacent;
		//int[] previousVertex;
		
		//go through all vertices in graph to find min distance from current vertex
		for(vertex = 0; vertex < countRows && visited[destination] == false; vertex++){	
			u = computeMin();				//find min distance from current vertex to adjacent vertices			
			visited[u] = true;	//min distance vertex visited. 
			//countNeighborV = adjacentV(u);
			//adjacent = new int[countColumns];
			//previousVertex = new int[countColumns];
			//countNeighborV = 0;
			for(int v = 0; v < countColumns && visited[destination] == false; v++){
				if(visited[v] == false && matrix[u][v] + route[u] < route[v] && matrix[u][v] != 0){
					route[v] = matrix[u][v] + route[u]; 
					temp[v] = u;
				}
				/*int distance = route[u] + matrix[u][v];
				if(route[v] < distance){
					route[v] = distance;
					previousVertex[v] = u;
					distanceTraveled += route[v];
				}
				else{
					distanceTraveled += route[u];
				}	
				*/					
			} 
			
		}
		
		distanceTraveled = route[destination];

		int index = destination;
		int nextIndex = destination;
		int element = destination;
		for(int i = 0; i < countRows; i++){ 
			backwardsPath[i] = index;
			element = temp[index];
			index = element;
		}
		
		int ind = 0;
		int count = 0;
		boolean flag = false;
		while(!flag){
			count++;
			if(backwardsPath[ind] == source){
				flag = true;
			}
			ind++;
		}
		
		shortestPath = new int[count];
		for(int i = 0, j = count - 1; i < count && j >= 0; i++, j--){
			shortestPath[i] = backwardsPath[j];			
		}
		
		
		
		
		
	//	int i = 0;
	//	while(shortestPath[i] != 
	
			for(int j = 0; j < countColumns; j++){
				System.out.print(backwardsPath[j] + " "); 
			}
	
	}
	
	public int computeMin(){
		int min = source;	// minimum vertex, set to infinity initially
		int value = infiValue;	// element tested for minimum value in array 
		for(int i = 0; i < countColumns; i++){
			if( visited[i] == false){	//vertex has not been visited yet and vertex less than infinity distance
					if( value > route[i]){		
						min  = i;	//set min to current vertex
						value = route[i];	//set min value to current vertex value
					}
			} 	
		}
	
		//distanceTraveled += number;	
		return min;
	}
	
	/*
	public int adjacentV(int u){
		int count = 0;
		for(int i = 0; i < countColumns; i++){
			if(matrix[u][i] != 0 || matrix[u][i] != infiValue){
				count++;
			}
		}
		return count;
	}
	*/
	
	public boolean read(String fileName){
		Scanner s =  null ;  
		try  {
			s =  new  Scanner( new  File(fileName));
			
			 } catch  (Exception e) {  
			return false ; }
		
		this.source = Integer.parseInt(s.next());
		this.destination = Integer.parseInt(s.next());
		s.nextLine();
		
		s.useDelimiter(System.getProperty("line.separator"));
		System.out.println("Source " + source);
		System.out.println("Destination " + destination);			
		//while(s.hasNextInt()){
		//	countColumns++;
		//	s.nextInt();
		//}
		while(s.hasNextLine()){
			countRows++;
			s.nextLine();		
		}
		
		countColumns = countRows;	//assume square matrix, cols = rows
		/*
		Scanner col = s;
		while(s.hasNextLine()){
    		++countRows;
    		while(col.hasNextInt()){
    		    ++countColumns;
    		    s.nextInt();
    		}
    		s.nextLine();
		}
		*/
		System.out.println("Count Columns " + countColumns);
		System.out.println("Count Rows " + countRows);
		
		Scanner sc =  null ;  
		try  {
			sc =  new  Scanner( new  File(fileName));
			
			 } catch  (Exception e) {  
			return false ; }	
		sc.nextLine();
		this.matrix = new int[countRows][countColumns];
		//String[] temp = new String[countColumns];
		for(int i = 0; i < countRows; i++){
		//	temp[i] = s.nextLine();
			for(int j = 0; j < countColumns; j++){
				matrix[i][j] = Integer.parseInt(sc.next());
			}
		}	
		
		
		for(int i = 0; i < countRows; i++){
			for(int j = 0; j < countColumns; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		
		
		//set to number of vertices in graph
		this.route = new int[countRows];
		this.visited = new boolean[countRows];
		this.u = source;
		
		initializeProblem();
		
		findShortestPath();
		
		
		return true;		
	}


	public boolean write(String fileName){
		PrintWriter p = null;
		try{
			p = new PrintWriter( new File(fileName)); }  catch (Exception e) {
		return false; }
		p.print(distanceTraveled + ": ");
		for(int i = 0; i < countRows; i++){
			p.print(shortestPath[i] + " ");
		} 
		p.close();
		return true;
	}
}