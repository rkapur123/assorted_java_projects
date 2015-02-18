package kruskalsalgorithm;
//This class is used to store sets and add edges to sets using an array list of strings
import java.util.ArrayList;

public class Set {
	
	public String a;
	public String b;
	public String c;
	public ArrayList<String> set = new ArrayList<String>();//string arraylist to add to set
	
	Set(String a, String b) {//pass in location names
		this.a = a;
		this.b =b;
		this.set.add(this.a);
		this.set.add(this.b);
		
	}
	
	public void addEdgeToSet(String c){//add locations to set
		this.c = c;
		this.set.add(this.c);//add sting to arraylist
		
		 
		
	}

}
