public class HuffmanApp{

	public static void main(String[] args){


	MinHeap mh = new MinHeap();
	mh.read(args[0]);

		
	mh.write("output.txt");
}

}
