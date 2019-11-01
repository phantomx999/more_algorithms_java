//written by phantomx999


README:

Run with finiteAutomatonApp, create object, read in file (pattern on 1st line, string on 2nd line).
Then call finiteAutomata algorithm -> first create the table based on number of states (pattern length + 1),
then fill in table based on current pattern character at each state and compared with column characters
being evaulated, assess whether prefix and suffix are equal and fill in table accordingly based on their length value.
Afterwards, run through the string indexes and use automata table to find a pattern in the string (reach highest state).  
If so, subtract by pattern length to get the beginning index where pattern exists in string.  
Same goes for reverse pattern, except make another FA table based on reverse pattern and then run it through 
the same original string in the same manner to find where reverse pattern exists in string indexes.  

I assumed only the 26 lowercase alphabet letters possibly existed in the string and pattern.  
I also assumed it would be fine to make a reverse FA table for the reverse pattern, given that
it would make the problem much easier rather than using the original FA table. 

To Run:

./run.sh problem1.txt 