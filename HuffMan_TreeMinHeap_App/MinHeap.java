import  java.util.Scanner; 
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;


public class MinHeap{
	
	public ArrayList<CharNode> CharArray;	//array of nodes that have the characters, etc.
	public ArrayList<CharNode> FinalArray;	//the final array containing the char node with binary code representation for each character
	public int size;						//size of char array
	public String codeString;				//the binary code when traversing the tree
	
	//constructor
	public MinHeap(){
		CharArray = new ArrayList<CharNode>();
		FinalArray = new ArrayList<CharNode>();
		size = 0;
		codeString = "";
	}
	
		
	//index starts at 1, not 0
	//makes a min heap property for nodes
	public boolean MinHeapify(int index){
		if (index > size || 2*index > size){	//if index is larger than size or 2index > size
			return true; 
		}else{	//if parent node smaller than child node then swap
			if (2*index +1 > size){		//if parent node only has 1 child, and its smaller, then swap
				if (this.CharArray.get(index-1).freq > this.CharArray.get(2*index -1).freq){
					CharNode temp = this.CharArray.get(index-1);
					this.CharArray.set(index-1, this.CharArray.get(2*index -1));
					this.CharArray.set(2*index -1,temp);
						
				}
				return true;
			}else{	//parent node has two children, find the smallest node of the two of them
				int minChild = Math.min(CharArray.get(2*index-1).freq, CharArray.get(2*index).freq);	//see which child node of the 2 is smallest
				if (this.CharArray.get(index -1).freq > minChild){	//if parent node needs to be swapped with a child node
					if(minChild == CharArray.get(2*index-1).freq){	//if left child node needs to be swapped
						CharNode temp = this.CharArray.get(index-1);
						this.CharArray.set(index-1, this.CharArray.get(2*index -1));
						this.CharArray.set(2*index -1,temp);

						MinHeapify(2*index);
						return true;
					}else{	//if right child nodes needs to be swapped
						CharNode temp = this.CharArray.get(index-1);
						this.CharArray.set(index-1, this.CharArray.get(2*index));
						this.CharArray.set(2*index,temp);

						MinHeapify(2*index+1);
						return true;
					}
					
				}
			}
		}
		
		return false;	
	}
	
	//build tree of min heaps
	public boolean buildMinHeap(){
		for(int i = size/2; i >=1; i = i - 1){
			MinHeapify(i);	//keep calling min heapify
		}
		
		return true;
	}
	
	//did not use, but good to have since I had insert for the max heap priority queue problem
	public boolean insert(CharNode x){
		CharArray.add(x);
		size++;	
		
		buildMinHeap();
		return true;
	}
	
	//did not use
	public CharNode minimum(){
		CharNode CharNode = CharArray.get(0);
		return CharNode;
	}
	
	//removes the minimum node in the heap
	public CharNode ExtractMin(){
		CharNode CharNode = CharArray.get(0);
		
		this.CharArray.set(0,CharArray.get(size-1));
		CharArray.remove(CharArray.size() - 1);
		size--;
		
		buildMinHeap();
		return CharNode;
	} 
	
	//take 2 smallest nodes form char array and then put them together as child nodes of a sum parent nocde
	public boolean buildTrie(){
		//find the 2 character with the smallest freq
		CharNode leftCharNode = ExtractMin();
		CharNode rightCharNode = ExtractMin();
		//get the sum of the freq
		int sumFreq = leftCharNode.freq + rightCharNode.freq;
		CharNode newNode = new CharNode(sumFreq);
		newNode.left = leftCharNode;
		newNode.right = rightCharNode;
		CharArray.add(newNode);	
		
		size++;
		
		buildMinHeap();
		return true;
	}
	
	//build entire tree of huffman compression algorithm
	public boolean buildTree(){
		while(size != 1){
			buildTrie();	//keeps creating a sum node of 2 smallest child nodes
		}
		return true;
	}
	
	//traverse the tree and assign the binary code when going down branches.  left = 0, right = 1
	public void traverseHelper(CharNode node, String codeStringP){
		
		if (node.left == null && node.right == null){	//when there are no child nodes, takes care of white spaces and put node with code into final array
			String codeS = codeStringP.trim().replaceAll("\u00A0", "");
			node.code = codeS;
			FinalArray.add(node);
			codeStringP = "";
			return;
		}else{	//if at least 1 child node
			if (node.left != null){	//traverse toward left branch
        		traverseHelper(node.left, (codeStringP+"0"));
    		}

	    	if (node.right != null){	//traverse toward right branch
    	    	traverseHelper(node.right,(codeStringP+"1"));
    		}
		}
	}
	
	//assigns the binary code for each character when it traverses down the tree
	public boolean assignCode(){
		CharNode root = CharArray.get(0);
		traverseHelper(root, codeString);
		return true;
	}
	
	//read in input from file
	public boolean read(String fileName){
		Scanner s =  null ;  
		try  {
			s =  new  Scanner( new  File(fileName));
			
			 } catch  (Exception e) {  
			return false ; }
		
		int[] count = new int[26];
		
		while(s.hasNext()){
			String lines = s.next(); 
            char[] letters = lines.toCharArray();
            
            for(int i = 0; i< letters.length; i++){
            	if (letters[i] >= 'a' && letters[i] <= 'z'){
            		count[letters[i] - 'a']++;
            	}
          		
            }
		}
		
		//adds letter node to char array
		for(int i = 0; i < 26; i++){
			if (count[i] > 0){
				CharNode letterNode = new CharNode((char)(i+'a'), count[i]);
				CharArray.add(letterNode);
				size++;
			}
		}
		
		//build a min heap node tree
		buildMinHeap();
		
		return true;
			
	}
	
	//write to file
	public boolean  write(String fileName){
		PrintWriter p =  null ;  
		try  {
			p =  new  PrintWriter(new File(fileName)); }  catch  (Exception e) {
		return false ; }
		
		buildTree();	//builds the tree
		assignCode();	//and then assigns the code 
		
		//now prints out the code
		
		for (int i = 0; i < FinalArray.size();i++){
			p.print(FinalArray.get(i).letter+": "+FinalArray.get(i).code + "\n");
		}
		
		for (int i = 0; i < FinalArray.size();i++){
			System.out.println(FinalArray.get(i).letter+": "+FinalArray.get(i).code);
		}
		
		
		p.close();
		return true;
		
	}	
	
	

}










