// written by phantomx999

README:

1. topologicalGraphApp creates object, reads in file, and writes to file.  When writing, it calls topologicalSort().
   It then runs through the algorithm and processes the data to find a plausible path of courses from prerequisite to
   destination courses

2.  I used topologicalSort making sure to process vertices with no incoming edges first in the queue, becauses
    having no incoming edges meant that these particular courses had no prerequisites.  After doing so,
    I decremented the number of incoming edges to these classes that required the already processed prereq courses
    everytime a prereq course was processed.  Soon, these classes also had no incoming edges and then I could put them
    in the queue as well and process them into answer[].  

3.  I assumed that there were no cycles in graph, and that each class was only stated once on the left side
    of the ":" so that number of vertices was equal to number of lines in fil.  
    
To Run:

./run.sh p2test1.txt