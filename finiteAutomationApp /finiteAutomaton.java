// Andrew Steinbrueck
// ID: 3949010
// x500: stein936


import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class finiteAutomaton{

	public int colN = 26;	//number of characters,  english alphabet, 
	//automaton # of alphabet characters (columns) = alphabet = colN

	public int columns = colN;		// cols = # of alphabet characters total (26)
	public int numOfStates;			// automaton # of states (rows) = pattern length + 1
	public String pat;				// pattern string
	public String str;				// string being compared with pattern
	public int stringLength;		// # of characters in string
	public int patLength;			// # of characters in pattern
	public int[][] FA;				// finite automaton table
	public String revPat;			// pattern string reverse order
	public String revStr;			// string being compared with pattern reversed
	public int revStringLength;		// # of characters in reversed string 
	public int revPatLength;		// # of characters in reverse pattern
	public int[][] revFA;			// finite automaton table for reverse order
	public int[] answer;			// stores correct indexes where pattern is found
	public int countAnswer;			// keeps track of length of answer
	
	public finiteAutomaton(){		// constructor
		this.pat = "";
		this.str = "";
		this.revPat = "";
		this.numOfStates = 0;	
		this.stringLength = 0;	
		this.patLength = 0;		
		this.revPatLength = 0;
		this.countAnswer = 0;
	}

	public void compareString(int count){		// compare pattern with string using finite automaton
	
		this.createTable(FA, revFA);					// create finite automaton table
		int[] temp = new int[this.stringLength];	// stores answer[] elements ( indexes in string where pattern occurs)
		
		for(int i = 0; i < temp.length; i++){		// initialize to 0
			temp[i] = 0;
		}
		
		int index = 0;											// for forward pattern
		int revIndex = 0;										// reverse pattern
		for (int j = 0; j <= stringLength - patLength; j++){	// traverse through FA table and reverse FA table
			for(int i = j; i < patLength+j; i++){				
			index = FA[index][(int) str.charAt(i) - 97];			// go to state table based on current character in string and current index
			revIndex = revFA[revIndex][(int) str.charAt(i) - 97];	// current table location then processes which state in table to GOTO next
			if (index == FA.length - 1 || revIndex == FA.length - 1){	// if final state is reached, string match pattern (i - patlength + 1, which is i - numOfStates)
				index = 0;												// must subtract down by length of pattern to get first index of where pattern starts in string
				temp[count] = i - (patLength - 1);				// reset index, and now store answer index value
				count++;										// increment count to get next answer index value
			}
		}
		}
		
		
		this.answer = new int[count];
		
		for(int i = 0; i < count; i++){		// set answer[] to stored answers in temp, print answer in write()
			this.answer[i] = temp[i];
		}	
	}
	
	public void createTable(int[][] table, int[][] revTable){	// create finite automaton table
		char c = 'a';		// character that represents a column (a = 0 column, b = 1 column, c = 2, etc.)
		for(int state = 0; state < table.length - 1; state++){						//traver rows, process all the way until final state
			for(int i = 0; i < colN; i++){										// traverse columns of table
				c = (char) (i + 97);												// set column index as a character
				table[state][i] = this.fillInTable(numOfStates, state, c, pat);			// fill automaton table
				revTable[state][i] = this.fillInTable(numOfStates, state, c, revPat);	// fill reverse pattern automaton table
			}
			
		}
	}
	
	public int fillInTable(int NumOfState, int state, char c, String pat){		// fill out elements in automaton table
		
		if (state < NumOfState){			// state is less than highest state (patlength + 1) 
			if(c == pat.charAt(state)){		// if table column index character == current character in pattern
				return state + 1;			// then set next highest state value in this location of table
			}
		}	
		
		if( (NumOfState - state) == NumOfState){	// when in first state (state = 0), if character does not match string, then set 0
			if(c != pat.charAt(state)){				// to current table location
				return 0;
			}
		}
		
		// for the rest of states besides first, 0 state...
		// row acts as prefix, while j acts as suffix
		for(int row = state; row > 0; row--){		// compare prefix and suffix, starting from longest possible length...
			int temp = 0;
			int difference = state + 1 - row;		// ...and decrementing length down to 1, if no match, return 0
			if (c == pat.charAt(row - 1)){			// character must match pattern character at previous row to continue forward
				for(int j = 0; j < row - 1; j++){		// go up until last previous state of current row 
					if (pat.charAt(j) != pat.charAt(difference + j)){		//compare pattern char with pattern char + difference (state + 1 - row)
						j = row - 1;				// exit inner loop	
						temp = j;													
					}
				}
				if (temp == row - 1){		// if a match exists between prefix and suffix
					return row;						// then return row value into current table location
				} 
			}
		}	
		return 0;	// if no matching prefix and suffix, return 0 to table current location
	}				

	public boolean read(String fileName){		// read in file
		Scanner s =  null ;  
		try  {
			s =  new  Scanner( new  File(fileName));
			
			 } catch  (Exception e) {  
			return false ; }
		
		int count = 0;
		String temp = s.nextLine();				// read in pattern
		count = temp.length();
		this.patLength = count;					// set pattern lengths
		this.revPatLength = count;
		this.pat = temp;						// get pattern
    	for(int i = patLength-1; i>=0; i--) {
        	revPat = revPat + pat.charAt(i);	// get reverse pattern
    	}
		
		int count2 = 0;
		String temp2 = s.nextLine();				// read in string
		count2 = temp2.length();
		this.stringLength = count2;					// set string length
		this.revStringLength = count2;
		this.str = temp2;							// get string	
		this.numOfStates = patLength + 1;			// set number of states in finite automaton table (1 extra state than patLength)
		this.FA = new int[numOfStates][columns];	// set finite automaton table length
		this.revFA = new int[numOfStates][columns];	// set reverse finite automaton length
			
		return true;		
	}


	public boolean write(String fileName){		// write to file
		PrintWriter p = null;
		try{
			p = new PrintWriter( new File(fileName)); }  catch (Exception e) {
		return false; }
	
		this.compareString(this.countAnswer);			// use finite automaton to compare string with pattern 

		for(int i = 0; i < this.answer.length; i++){	// print index where pattern found in string
			if(i == answer.length - 1){
				p.print(this.answer[i]);
			}
			else{
				p.print(this.answer[i] + " ");
			}
		}
	
		p.close();
		return true;

	}

}