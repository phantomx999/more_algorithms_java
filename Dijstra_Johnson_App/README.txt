// written by phantomx999

README:

1. Run code using run.sh 
	 -JohnsonApp reads from file and writes to file
	 -JonsonApp calls johnsonAlgo() when writing to fil
	 -jonsonAlgo() processes algorithm, and if necessary, calls dijkstraAlgo()
	 -finished result is written to file
	 
2. I read in the file as a 2d adjacency matrix array, then I used Johnson Algorithm.  
   I made a super vertex with directional 0 edges pointing to all other vertices using this algorithm.
   I then calculated shortest path from super vertex to other vertices using Bellman Ford  
   If the matrix had any negative cycles, then I would just print out "negative cycle".
   Otherwise, I then re-weighted the original matrix so that it had no negative edges
   I ran Dijkstra to find shortest paths for all vertices in this matrix
   I then un-weighted this matrix to get all the correct values of all vertices shortest
   paths to all other vertices.  
   
3. I assumed that if matrix had negative cycles, bellman ford would catch this before
   running dijkstra.  I also assumed 2000000 edges were non existent edges, or that
   2 million was the infinity value for vertex values when initializing.  Lastly, I assumed
   that it was a square adjacency matrix (no added vertices later on),
   as the problem implied that this was the case. 
   
./run.sh p4test1.txt