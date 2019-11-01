//priority queue elements 
public class Customer {
	
	private String name;	//customer name
	private int priority;	//customer priority
	
	
	//constructor
	public Customer(String initName,int initPriority){
		name = initName;
		priority = initPriority;
	}
	
	//getter and setters...
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getPriority(){
		return priority;
	}
	
	public void setPriority(int priority){
		this.priority = priority;
	}
	
	//output toString()
	public  String toString(){
		return (getName()+" "+getPriority()+"\n");
	}
	
}