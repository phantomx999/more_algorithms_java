// Name: Andrew Steinbrueck
// ID: 3949010
// x500: stein936

import java.util.Scanner; 
import java.io.PrintWriter; 
import java.io.File;
import java.util.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class DepthFirstSearch{

	public int numberOfVertices;
	public int[] answer;
	public Vertex[] ver;
	public int count = 0;
	public int verticesLeft;

	public DepthFirstSearch(){
		numberOfVertices = 0;
	}
	
	public void DFS(){
		System.out.println("get to here 2");	
		
		while(verticesLeft > 0){
			for(int i = 0; i < ver.length; i++){
					if(ver[i].visited == false){
						DFSVisit(ver[i]);
					}
			}
		}
			
		System.out.println("get to here 4");

	}
	
	public void DFSVisit(Vertex u){	

	if(u != null && u.visited == false){
   		if(count < answer.length && u.inDegree == 0){
   				u.visited = true;
   				answer[count] = u.classNumber;
				count++;
				verticesLeft--;
				System.out.println("answer[count]: " + answer[count - 1]);
			
			Vertex[] temp = new Vertex[numberOfVertices];
			for(int i = 0; i < numberOfVertices; i++){
				temp[i] = new Vertex(numberOfVertices);
			}

			int i = 0;
			
			ListIterator<Vertex> nextVer = u.adjacentVertices.listIterator();

			while (nextVer.hasNext()) {
				temp[i] = nextVer.next();
				System.out.println("vertex " + temp[i].classNumber + " adjacent--> " + temp[i].adjacentVertices);

				for(int x = 0; x < ver.length; x++){
					if(ver[x].classNumber == temp[i].classNumber && ver[i].inDegree > 0){
						ver[x].toString();
						ver[x].inDegree--;
						ver[x].toString();
					}
				}
    			if(i < temp.length){
   					temp[i].previous = u.classNumber; 
   				}
   				if(temp[i].visited == false && u.inDegree == 0){
   					this.DFSVisit(temp[i]);
   				}
   				i++;
   			}
   		}	
   	}
   		
	}
	
	
	public boolean read(String fileName){
		Scanner s1 =  null ;  
		try  {
			s1 =  new  Scanner( new  File(fileName));
			
			 } catch  (Exception e) {  
			return false ; }
			
		while(s1.hasNextLine()){
			this.numberOfVertices++;
			s1.nextLine();
		}
		
		this.ver = new Vertex[numberOfVertices];
		this.answer = new int[numberOfVertices];
		verticesLeft = numberOfVertices;
		for(int v = 0; v < numberOfVertices; v++){
			ver[v] = new Vertex(numberOfVertices);
		}
	
		Scanner s =  null ;  
		try  {
			s =  new  Scanner( new  File(fileName));
			
			 } catch  (Exception e) {  
			return false ; }
		
		int j = 0;
		while(s.hasNextLine()){
			String str = s.nextLine();
			String[] stringsFirst = str.split("[:|\\s\u00A0]+");	
			int[] strToInt = new int[stringsFirst.length];
			System.out.println("stringsFirst " + stringsFirst.length);
			//String stringIth = stringsFirst[i].trim().replaceAll("\u00A0", ""); 
			for(int i = 0; i < stringsFirst.length; i++){
				String stringIth = stringsFirst[i].trim().replaceAll("\u00A0", ""); 
				strToInt[i] = Integer.parseInt(stringIth);
				if(i == 0){
					ver[j].classNumber = strToInt[i];
				}
				else{
				 	ver[j].preReqs[i] = strToInt[i];
				 	ver[j].hasPreReq = true;
				 	ver[j].inDegree++;
				 	ver[j].afterPreReq[i] = strToInt[0];
				 	ver[i].addAdjacentVertex(ver[j]);
				}
			}
			System.out.println("Here are the vertices read in " + ver[j].toString());
			j++;
		}
		return true;		
	}
	
	public boolean write(String fileName){		// write to file
		PrintWriter p = null;
		try{
			p = new PrintWriter( new File(fileName)); }  catch (Exception e) {
		return false; }
	
		System.out.println("get to here 1");
		this.DFS();			
		System.out.println("get to here 5");

		for(int i = 0; i < this.answer.length; i++){	
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