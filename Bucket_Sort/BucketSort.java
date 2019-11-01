import  java.util.Scanner; 
import  java.io.PrintWriter; 
import  java.io.File;
import java.util.Arrays;
import java.util.ArrayList;


public class BucketSort {
	public ArrayList<ArrayList<String>> listOfBuckets;		//array of all buckets
	public ArrayList<String> nameArray;						//all names in the array

	
	//constructor
	public BucketSort(){
		listOfBuckets = new ArrayList<ArrayList<String>>();
		nameArray = new ArrayList<String>();

	}
	
		
	//other method, index starts at 1, not 0
	//compare word1, word2 character by character
	//if word1 is smaller, return 1, if equal return 0, else return -1
	public static int compareWords(String word1, String word2, int position){
		if(word1.length() < position+1 && word2.length() < position+1){
			return 0;
		}else if(word1.length() < position +1 && word2.length() >= position+1){
			return 1;
		}else if(word2.length() < position +1 && word1.length() >= position+1){
			return -1;
		}else if(word1.length() >= position+1 && word2.length() >= position+1){
			if(word1.charAt(position) < word2.charAt(position)){
				System.out.println(word1.charAt(position) < word2.charAt(position));
				return 1;
			}else if(word1.charAt(position) > word2.charAt(position)){
				System.out.println(word1.charAt(position) < word2.charAt(position));
				return -1;
			}else{
				position++;
				return compareWords(word1, word2, position);
			}
		}else{		//error, return 99, special case, isnt suppose to get to this return statement 
			return 99;
		}
		
	}
	
	//uses insertion sort to sort the names in the buckets
	public boolean insertionSort(ArrayList<String> nameBucket){
		for(int j = 1; j < nameBucket.size(); j++){
			int i = j-1;
			String key = nameBucket.get(j);
			while(i >= 0 && compareWords(nameBucket.get(i), key, 0) == -1){
				nameBucket.set(i+1, nameBucket.get(i));
				i--;
			}
			
			nameBucket.set(i+1, key);
		}
		
		return true;
	}
	
	//break into buckets by characters
	public boolean getBuckets(ArrayList<String> buckets, int position){
		for(int i = 0; i < 26; i++){
			ArrayList<String> wordBucket = new ArrayList<String>();
			for(int j = 0; j <buckets.size() ; j++){
				if(buckets.get(j).length() > position && Character.toLowerCase(buckets.get(j).charAt(position))-'a' == i){
					wordBucket.add(buckets.get(j));
				}
			}
			
			if(wordBucket.size() > 10){
				position++;
				getBuckets(wordBucket, position);
			}else if(wordBucket.size() > 0){
				
				listOfBuckets.add(wordBucket);
			}
			
		}
		
		for(int k = 0; k <listOfBuckets.size() ; k++){
			System.out.println(listOfBuckets.get(k));	
		}
		
		
		return true;
		
	}
	
	//perform bucketsort for each bucket
	public String bucketSort(){
		//break buckets;
		getBuckets(nameArray, 0);
		
		ArrayList<String> resultBuckets = new ArrayList<String>();
		
		
		//sort each bucket
		for(int i = 0; i < listOfBuckets.size(); i++){
			insertionSort(listOfBuckets.get(i));
			System.out.println(listOfBuckets.get(i));
			String resultBucket = "";

			for (String s : listOfBuckets.get(i))
				{
    				resultBucket += s + " ";
				}
			System.out.println(resultBucket);
			resultBuckets.add(resultBucket);
		}
		//put all buckets together
		String result = "";

			for (String s : resultBuckets)
				{
    				result += s + " ";
				}
		
		System.out.println(result);
		return result;
	}
	
	
	//read in data from file
	public boolean read(String fileName){
		Scanner s =  null ;  
		try  {
			s =  new  Scanner( new  File(fileName));
			
			 } catch  (Exception e) {  
			return false ; }
		
		
		while(s.hasNext()){
			String lines = s.nextLine();
			String[] names = lines.split("[\\s\u00A0]+");

			for(int i = 0; i < names.length; i++){
				System.out.println(names[i]);
			}
			
			for(int i = 0; i < names.length; i++){
				this.nameArray.add(names[i]);
			}
		}
		return true;
			
	}
	
	//write to file
	public boolean  write(String fileName){
		PrintWriter p =  null ;  
		try  {
			p =  new  PrintWriter( new  File(fileName)); }  catch  (Exception e) {
		return false ; }
		
		String sortedNames = bucketSort();	//uses bucket sort to get sorted names
		
		p.print(sortedNames);	//prints out sorted names
		
		p.close();
		return true;
		
	}	
	
	

}










