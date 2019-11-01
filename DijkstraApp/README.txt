// written by phantomx999

README:

1. Run DijkstraApp, reads in file, calls DijkstraAlgo(), then writes to file shortest path 
   from source to destination and total distance traveled.  
   
2. Dijkstra first initializes all vertex values to infinity except source vertex = 0.  Then
   find min distance to next visited vertex and relax vertex values.  Continue until destination
   vertex reached, then use PI route to back track and find shortest path from source to destination, 
   and total distance between these two vertices.  

3.  Assume 2 million is non existent edges, and also set all vertex values to 2 million
   except source vertex = 0.  Also assume square adjacency matrix read in based on problem.  

To Run:

./run.sh p3test1.txt
