package Djikstra;

//Rahul Kapur
//A node refers to each location and the fields it holds

import java.util.ArrayList;


public class Node implements Comparable<Node>{//implement Comparable interface to override methods 
												// in order to store a node (vertex) in a priority queue
	
	
	public String location;//name of state or city
	public static int infinity= 100000000;//arbitrarily large number to represent infinity
	public double value; // assign a value to the node as it is visited
	public Node prevNode;// previous node from which this node has been arrived at
	public ArrayList<Neighbors> neighbors = new ArrayList<Neighbors>();
	public Node remove;
	public int x;
	public int y;
	
	 Node(String location) {//contructor which takes in city
		this(infinity, null, null, location);
	}

	 
	 Node(double value, Node prevNode, ArrayList<Neighbors> neighbors, String location) {
		this.value= value;
		this.prevNode=prevNode;
		this.neighbors=neighbors;//arraylist of neighboring cities
		this.location = location;
	}
	 
	 
	 public void add(int x, int y) { //add coordintes of location
		 this.x = x;
		 this.y = y;
	 }
	 
	 
	 public int getX() {// get x coordinate
		 return this.x;
	 }
	 
	 public int getY() {// get y coordinate
		 return this.y;
	 }
	 
	 
	
	 
	

	@Override
	public boolean equals(Object obj) {//override equals method to create an instance of the item 
		Node a = (Node) obj;

		 if (this.location.equals(a.location)) {
			 this.remove = a;//removed item using remove method in djikstra class
			 return true;
		 }
		 
		 else {
			 return false;
		 }
	}


	public Node getNode(boolean forRemove) {
		 return this.remove;//return the node
	 }




	@Override
	public int compareTo(Node b) {//override compareto to sort nodes based on their values
		
		if (this.value==b.value) {
			return 0;
		}
		
		else if (this.value > b.value) {
			return 1;
		}
		
		else {
			return -1;
		}
		// TODO Auto-generated method stub
		
	}
	
}
