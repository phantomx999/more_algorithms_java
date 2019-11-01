The Selection Algorithm is implemented using ArrayList with numbers in an array. 

ProcedureA: To get the “ith” biggest number, first I chose the last value as the pivot.  
Then, I put the values smaller than the pivot in front of it, and the values bigger than the pivot
after it.

if the pivot ended up at the ith place, we found the ith biggest number,
if pivot's index > i, repeat ProcedureA from array[0] to the element right before the pivot
if pivot's index < i, repeat ProcedureA from element right after the pivot to the end of array

To Run:

./run.sh input.txt