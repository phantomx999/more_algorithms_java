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

public class topologicalGraph{

	public int numberOfVertices;	// total number of vertices (courses)
	public Vertex[] answer;			// the path order of classes to take 
	public Vertex[] ver;			// reads in every class vertex and its prerequisites
	public int totalCount = 0;		// index used in answer[], keeps track of total number of vertices 
	public int verticesLeft;		// vertices remaining after being eliminated from queue and stored in answer[]

	public topologicalGraph(){		// constructor
		numberOfVertices = 0;
	}
	
	public void topologicalSort(){		// topological sort starting with no incoming edge vertices
		
		Vertex[] temp = new Vertex[numberOfVertices];					// temp storage of vertices
		for(int i = 0; i < numberOfVertices && verticesLeft > 0; i++){	// initialize
			temp[i] = new Vertex(numberOfVertices);
		}
		
		Queue<Vertex> que = new LinkedList<Vertex>();	// que used to first bring in non incoming edge vertices
		for(int i = 0; i < numberOfVertices; i++){
			if(ver[i].inEdges == 0 && ver[i].visited == false){					// if no incoming edges
            	que.add(ver[i]);									// add it to queue
            	ver[i].visited = true;								// mark current vertex as processed
            }
		}
		
		while(verticesLeft > 0 && !que.isEmpty()){					// still number of remaining unprocessed vertices
			answer[totalCount] = que.remove();		// store beginning vertex of queue in answer[]
			verticesLeft--;							// decrement remaining vertices
			ListIterator<Vertex> nextVer = answer[totalCount].adjacentVertices.listIterator();	// go through adjacent vertices of current vertex
			totalCount++;							// increment totalCount of vertices processed

			int i = 0;
			while (nextVer.hasNext()) {				// more destination class vertices from current, prerequisite vertex
				temp[i] = nextVer.next();			// store destination vertex

				for(int x = 0; x < ver.length; x++){										
					if(ver[x].classNumber == temp[i].classNumber && ver[x].inEdges > 0){	
																// check if stored vertex value classNumber is the same as
						ver[x].inEdges--;						// any other vertex class number.  If so, decrememnt the incoming edges
																// of this destination vertex since 1 of the prerequisite vertices was processed
					}											// so as long as there still remains incoming edges to this dest vertex
													
					if(ver[x].inEdges == 0 && ver[x].visited == false){		// if no more incoming edges to vertex and it has not 
						que.add(ver[x]);									// been processed, add to queue and mark as processed
						ver[x].visited = true;
					}
				}
				i++;
			}
		}
	}
	
	public boolean read(String fileName){				// read in file
		Scanner s1 =  null ;  
		try  {
			s1 =  new  Scanner( new  File(fileName));
			
			 } catch  (Exception e) {  
			return false ; }
		while(s1.hasNextLine()){						// count number of lines = number of vertices in graph
			this.numberOfVertices++;
			s1.nextLine();
		}
		
		Scanner s =  null ;  
		try  {
			s =  new  Scanner( new  File(fileName));
			
			 } catch  (Exception e) {  
			return false ; }
		
		this.ver = new Vertex[numberOfVertices];			//intialize
		for(int v = 0; v < numberOfVertices; v++){
			ver[v] = new Vertex(numberOfVertices);
		}	
		int i = 0;
		while(s.hasNextLine()){								// go through file and parse int class numbers into vertex array
			String str = s.nextLine();
			String[] stringsFirst = str.split("[:|\\s\u00A0]+");
			int[] strToInt = new int[stringsFirst.length];
			for(int j = 0; j < stringsFirst.length; j++){
				String stringFirstIth = stringsFirst[j].trim().replaceAll("\u00A0", ""); 
				strToInt[j] = Integer.parseInt(stringFirstIth);
				if(j == 0){
					ver[i].classNumber = strToInt[j];	// for destination classes (classes with prereqs), first int read in on line
				}
				else{									// for prerequisite classes, any remaining classes read in on line
				 	ver[i].inEdges++;					// if a prerequisite exists, increment number of incoming edges for destination class
				 	ver[j].addAdjacentVertex(ver[i]);		// make this prerequisite class point as a vertex to destination class (adjacent vertex)
				}
			}
			i++;
		}
		
		this.verticesLeft = numberOfVertices;
		this.answer = new Vertex[numberOfVertices];
		
		return true;
			
	}
	
	public boolean write(String fileName){		// write to file
		PrintWriter p = null;
		try{
			p = new PrintWriter( new File(fileName)); }  catch (Exception e) {
		return false; }
	
		this.topologicalSort();		// call topological sort	

		for(int i = 0; i < this.answer.length; i++){			// print answer[] vertex class number order
			if(answer[i] != null && i == answer.length - 1){
				p.print(this.answer[i].classNumber);
			}
			else if(answer[i] != null){
				p.print(this.answer[i].classNumber + " ");
			}
		}
		p.close();
		return true;
	}
	
	
}