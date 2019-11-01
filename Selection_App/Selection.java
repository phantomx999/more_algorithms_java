import  java.util.Scanner; 
import  java.io.PrintWriter; 
import  java.io.File;
import java.util.Arrays;
import java.util.ArrayList;

//selection class
public class Selection {
	public ArrayList<Integer> numberArray;	//array with numbers
	public int ith;							//desired number 'ith' size to get
	public int ithValue;					//actual ith value result
	
	//constructor
	public Selection(){
		numberArray = new ArrayList<Integer>();
		ith = -1;
		ithValue = -99999;
	}
		
	//select the ith biggest value
	public boolean selectIth(int ith, int start, int end){
		if(ith > end+1){	//if ith value larger than the end of array
			return false;
		}
		int pivot = numberArray.get(end);	//get pivot at end of array
		//the numbers that smaller than the pivot
		int i = start -1;
		for(int j = start; j < end; j++){	
			if (numberArray.get(j) <= pivot){	//if current index element less than or equal to the pivot
				i++;
				int temp = numberArray.get(j);	//swap i and j index values in array
				numberArray.set(j, numberArray.get(i));
				numberArray.set(i,temp);
			}
		}
		//swap the pivot with i+1
		numberArray.set(end, numberArray.get(i+1));
		numberArray.set(i+1,pivot);

		System.out.println(numberArray);
		
		if(ith == i+2){
			this.ithValue = pivot;	//assign result to pivot
		}else if(ith < i+2){		//if ith value is smaller than pivot
			selectIth(ith, start, i);	//recursive call on left side of pivot
		}else{							//if ith value is larger than pivot
			selectIth(ith, i+2, end);	//recursive call on right side of pivot
		}
		return true;	
	}
		
	//read in input
	public boolean read(String fileName){
		Scanner s =  null ;  
		try  {
			s =  new  Scanner( new  File(fileName));
			
			 } catch  (Exception e) {  
			return false ; }
		
		
		String ithL = s.nextLine();
		String ithS = ithL.trim().replaceAll("\u00A0", ""); 
		this.ith = Integer.parseInt(ithS);
		
		String number = s.nextLine();
		String[] stringArray = number.split("[\\s\u00A0]+");	

				
		
		for(int i = 0; i < stringArray.length; i++){
			String numbers = stringArray[i].trim().replaceAll("\u00A0", "");
			this.numberArray.add(i, Integer.parseInt(numbers));
		}
		
		return true;
			
	}
	
	//write to file
	public boolean  write(String fileName){
		PrintWriter p =  null ;  
		try  {
			p =  new  PrintWriter( new  File(fileName)); }  catch  (Exception e) {
		return false ; }
		
		selectIth(ith, 0, numberArray.size()-1);

		p.print(ithValue+" ");

		p.close();
		return true;
		
	}		

}










