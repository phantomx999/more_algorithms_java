public class CharNode {
	public char letter;		//letter to be encoded into binary through huffman data compression
	public int freq;		//frequency of the letter occurring in the file
	public String code;		//the binary code that represents the specific letter
	public CharNode left;	//child nodes left and right
	public CharNode right;
	
	//constructor
	public CharNode(char letter, int freq){
		this.letter = letter;
		this.freq = freq;
		this.code = "";
		left = right = null;
	}
	
	//constructor 2
	public CharNode(int freq){
		this.letter = '\0';
		this.freq = freq;
		this.code = "";
		left = right = null;
	}
	
}