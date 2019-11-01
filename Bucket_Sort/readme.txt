To implement bucketSort on a list of names, I followed the following steps:
1. First, I put names starting with the same first character in the same bucket.  Buckets were
   then mapped alphabetically into an array list. 
   For example: Alison, Allen will be in the 1st bucket.  Bill, Bob will be in 2nd bucket, Charlotte will be in 3rd bucket, etc. 
   If there are more than 10 elements in an individual bucket, repeat step 1, but then put names in sub buckets 
   based off of the second characters in these names.

2. Secondly, I used insertion sort to sort the names in the buckets
   Sorting is done character by character in a recursive manner.
   
3. Lastly, I concatenated all the names in one arraylist


To Run:

./run.sh input.txt