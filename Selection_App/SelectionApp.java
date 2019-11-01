public class SelectionApp{

	public static void main(String[] args){


	Selection se = new Selection();
	se.read(args[0]);
	System.out.println(se.numberArray); 
	System.out.println(se.ith);
		
	se.write("output.txt");
	
	System.out.println(se.ithValue);

}

}