The PriorityQueue is implemented using ArrayList with each element as a Customer Object. 
The Customer Object has 2 member variables: name and priority.

The PriorityQueue class has 7 methods:
1. MaxHeapify:  Takes an index, makes a subtree into a max-Heap at that index.  
   It assumes that the subtree is already a max-Heap except the node at the index value
	
2. buildMaxHeap: run maxheapify bottom up for all parent nodes in tree in order to build maxheap.
   (leaf nodes are already max-Heaps)

3. insert: inserts customer into the queue
4. maximum: finds the highest priority customer
5. extractMax: finds and removes the highest priority customer
6. read: reads in an input txt file
7. write: writes output to an output txt file 

To Run:

./run.sh input.txt