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

public class Vertex{

        public int classNumber; 	  		// class number vertex
        public boolean visited = false; 	// know that current vertex has already been processed
        public int inEdges = 0;				// number of incoming edges into vertex
        public List<Vertex> adjacentVertices = new LinkedList<Vertex>(); // adjacency list, outgoing edges connect from source vertex to dest vertices

		public Vertex(){			// constructor
            this.classNumber = 0;	
            this.visited = false;  
        }

        public Vertex(int classNumber, int numberOfVertices){	//constructor
            this.classNumber = classNumber;
            this.visited = false;  
        }
        
        public Vertex(int numberOfVertices){			// constructor
            this.classNumber = 0;
            this.visited = false;   
        }

        public void addAdjacentVertex(Vertex v){		// add vertices that have outgoing edges from current vertix.
            this.adjacentVertices.add(v);				// All current vertices that are prerequisites connect to
									 					// next course that requires these prerequisite classes
														// Connected by use of a linked list.
      }

}