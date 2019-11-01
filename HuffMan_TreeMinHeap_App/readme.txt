The Huffman's tree is implemented using ArrayList with each element as a CharNode Object. 
The CharNode Object has 5 member variables: letter(the character), frequency, 
code(huffman code for one specific character), and left and right child nodes

The way I'm getting the huffman code is:
1. Build the Huffman tree from bottom up
   a. To build the tree, I first turn the character array into a min heap structure.
   b. Then I use extrtactMin to get the two characters with the least freq, then I get the sum of the freq.
   c. The sum is then made into the parent node for the two child nodes. Then I put the sum node back into the min heap.
   I continuously do this until there is only one node in the min Heap and that's the root for the hoffman tree.

2. Next, I traverse the Huffman tree, have an array to record left/0, right/1, and 
   output the array for each leaf node in the tree (since every character will be at the 
   leaf node).
   

To Run:

./run.sh input.txt