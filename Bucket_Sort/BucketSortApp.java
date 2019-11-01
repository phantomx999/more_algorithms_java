public class BucketSortApp{

	public static void main(String[] args){


	BucketSort bs = new BucketSort();
	bs.read(args[0]);
	System.out.println(bs.nameArray); 

		
	bs.write("output.txt");
}

}