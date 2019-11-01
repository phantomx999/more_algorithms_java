import  java.util.Scanner; 		//for read and write
import  java.io.PrintWriter; 
import  java.io.File;
import java.util.Arrays;	
import java.util.ArrayList;

//priority queue class
public class PriorityQueue {
	
	public ArrayList<Customer> customerArray;	//customer array
	public int size;							//size of queue
	
	
	//constructor
	public PriorityQueue(){
		customerArray = new ArrayList<Customer>();
		size = 0;
	}
	
	
	//makes parent node have higher priority element than child nodes	
	public boolean MaxHeapify(int index){
		if (index > size || 2*index > size){	//special case when index is larger than size of tree.  index starts at 1, not 0, for tree
			return true; 
		}else{	//if parent node smaller than any child node, then swap
			if (2*index +1 > size){	//swap procedure
				if (this.customerArray.get(index-1).getPriority() < this.customerArray.get(2*index -1).getPriority()){	//if there is only child, and it is bigger, then swap
					Customer temp = this.customerArray.get(index-1);
					this.customerArray.set(index-1, this.customerArray.get(2*index -1));
					this.customerArray.set(2*index -1,temp);
						
				}
				return true;
			}else{		//recursion to keep checking lower leafs in tree, has to keep checking if lower nodes meet criteria for a max heap
				int maxChild = Math.max(customerArray.get(2*index-1).getPriority(), customerArray.get(2*index).getPriority());	//look at which child node is larger of the two
				if (this.customerArray.get(index -1).getPriority() < maxChild){	//if child nodes need to be swapped with parent
					if(maxChild == customerArray.get(2*index-1).getPriority()){	//if left priority equals max child, swap with left
						Customer temp = this.customerArray.get(index-1);
						this.customerArray.set(index-1, this.customerArray.get(2*index -1));
						this.customerArray.set(2*index -1,temp);

						MaxHeapify(2*index);
						return true;
					}else{	//otherwise, swap with the right child
						Customer temp = this.customerArray.get(index-1);
						this.customerArray.set(index-1, this.customerArray.get(2*index));
						this.customerArray.set(2*index,temp);

						MaxHeapify(2*index+1);
						return true;
					}
					
				}
			}
		}
		
		return false;	
	}
	
	//build tree of max heaps
	public boolean buildMaxHeap(){
		for(int i = size/2; i >=1; i = i - 1){	
			MaxHeapify(i);	//make 1 node max heap parent by calling max heapify
		}
		
		return true;
	}
	
	//correctly inserts customer into the heap
	public boolean insert(Customer x){
		customerArray.add(x);
		size++;	
		
		buildMaxHeap();		//builds the tree of max heaps
		return true;
	}
	
	//gets the first customer
	public Customer maximum(){
		Customer customer = customerArray.get(0);
		return customer;
	}
	
	//removing first customer
	public Customer ExtractMax(){
		Customer customer = customerArray.get(0);
		
		this.customerArray.set(0,customerArray.get(size-1));
		size--;
		
		buildMaxHeap();
		return customer;
	} 
	
	//read in file
	public boolean read(String fileName){
		Scanner s =  null ;  
		try  {
			s =  new  Scanner( new  File(fileName));
			
			 } catch  (Exception e) {  	//finds exceptional input that isnt typcial
			return false ; }
		
		
		while(s.hasNext()){		//goes through file and reads in each customer and their priority number
			String Customer = s.nextLine();
			String[] customerInfo = Customer.split(":");
			String nextName = customerInfo[0];
			String nextPriorityS = customerInfo[1].trim().replaceAll("\u00A0", ""); 

			int nextPriority = Integer.parseInt(nextPriorityS);

			Customer nextCustomer = new Customer(nextName, nextPriority);
			customerArray.add(nextCustomer);
			size++;	
		}
		
		buildMaxHeap();		//after reading in data, creates MaxHeap from read in data
		return true;
			
	}
	
	//write to file
	public boolean  write(String fileName){
		PrintWriter p =  null ;  
		try  {
			p =  new  PrintWriter( new  File(fileName)); }  catch  (Exception e) {
		return false ; }
		
		int originalSize = size;
		for(int x = 0; x < originalSize; x++){		//removes highest priority customer and the prints out customer name and priority
			Customer maxCustomer = ExtractMax();
			System.out.println(maxCustomer.toString());
			p.print(maxCustomer.getName()+" ");
		}
		
		p.close();
		return true;
		
	}	
	
	

}










