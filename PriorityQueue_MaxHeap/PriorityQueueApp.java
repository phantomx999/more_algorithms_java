//priority queue app
public class PriorityQueueApp{

	public static void main(String[] args){


	PriorityQueue pq = new PriorityQueue();
	pq.read(args[0]);		//reads in file
	System.out.println(pq.customerArray); 	//testing output
	System.out.println(pq.size);
		
	pq.write("output.txt");		//outputs data into new file
	System.out.println(pq.size);

}

}