package Djikstra;

//Rahul Kapur
//this class does a bulk of the work by actually computing djikstra's path
//it updates the priority queue using the updateQueue method with the help of overriding some methods in the node class


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;



public class djikstrasAlgorithm {
	
	private static PriorityQueue<Node> locations = new PriorityQueue<Node>();//all locations
	private static PriorityQueue<Node> locations2 = new PriorityQueue<Node>();//all locations
	private static ArrayList<Node> shortestPath = new ArrayList<Node>();//series of nodes in the shortest path
	private static double path = 0;//shortest path distance
	

	
	

	
		 djikstrasAlgorithm(String source, String destination, String xy, String pairs) throws FileNotFoundException {
		//constructor takes in source destination and command line arguments	
			Node origin = new Node(source.toLowerCase());//create a node from the user inputted source
			Node finalStop = new Node(destination.toLowerCase());//create a node from the user inputted destination
			ArrayList<Neighbors> neighbors1 = null ;
			
			String fileName = xy;
	    	File file = new File(fileName);
	    	Scanner cityxy = new Scanner(file);//create a scanner from the command line arguments
	    	
			
	    	while(cityxy.hasNextLine()) {
	    		String line = cityxy.nextLine();
	    		String hello = "";
	        	String[] words = new String[3];// store city name and x and y coordinate
	        	int k =0;
	        	int g =0;
	        	while (k < line.length()) {//use to splice each line and place into words array 
	        		if (line.charAt(k)!= ' ') {//(accounting for multiple spaces between words)
	        			hello =hello + line.charAt(k);
	        			k++;
	        		}
	        		
	        		else if (line.charAt(k) == ' ' && !hello.equals("")){
	        			words[g] = hello;
	        			hello = "";
	        			k++;
	        			g++;	
	        		}
	        		
	        		else {
	        			k++;
	        		}
	        		
	        	}
	        	words[g] = hello;
	    		String word = words[0].toLowerCase();//location name
	    		
	    			Node letter = new Node(word); // location node
	    			letter.add(Integer.parseInt(words[1]), Integer.parseInt(words[2])); // store x and y value for location
	        		if (!locations.contains(letter)) {//populate city with its neigbors not including itself
	        			String fileName1 = pairs;//use citypairs
	        	    	File file1 = new File(fileName1);
	        	    	Scanner citypairs = new Scanner(file1);//create scanner
	        	    	neighbors1 = new ArrayList<Neighbors>(); 
	        	    	while(citypairs.hasNextLine()) {    		
	        	    		String line1 = citypairs.nextLine();
	        	    		String[] words1 = line1.split(" ");
	        	    		if (words1[0].toLowerCase().equals(word) || words1[1].toLowerCase().equals(word)) {
	        	    			String neighbor = "";
	        	    			if (words1[0].toLowerCase().equals(word)) {
	        	    				neighbor = words1[1].toLowerCase();//store neighbor
	        	    			}
	        	    			else {
	        	    				neighbor = words1[0].toLowerCase();//store neigbor
	        	    			}
	        	    			
	        	    			double distance = Double.parseDouble(words1[2]);
	        	    			Neighbors neighbors = new Neighbors(neighbor, distance);
	        	    			neighbors1.add(neighbors);//store neighbor in arraylist
	        	    			
	        	    		}
	        	    	}
	        	    	
	        	    	letter.neighbors = neighbors1;// add neighbor arraylist to field of the location
	        	    	locations.add(letter);	//add locations
	        	    	locations2.add(letter);//add to second locations which will be passed into the points class
	        		}
	    		
	    		
	    	}
	    	

	    	ArrayList<Node> visitedSet = new ArrayList<Node>();//visitedset arraylist of nodes
	    	Node originalLocation = origin.getNode(locations.remove(origin)); //remove the origin from list of all nodes 
	    	originalLocation.value=0;//set its value to 0
	    	visitedSet.add(originalLocation);
	    	updateQueue(originalLocation);//update values for each node relative to the origin
	    	Node deleted = locations.peek();
	    	
	    	
	    	while (!deleted.equals(finalStop)) {//loop through and store all visited points
	    		updateQueue(deleted);
	    		visitedSet.add(locations.poll());
	    		deleted = locations.peek();
	    		
	    	}
	    	
	    	Node previousNode = deleted;
	    	path = previousNode.value;
	    	while (previousNode!= null) {
	    		shortestPath.add(previousNode);//add visited nodes to shortestpath list
	    		previousNode = previousNode.prevNode;
	    	}
	    	
		
	    	Points2 points = new Points2(shortestPath, locations2, path);
		
		}
	
		
	
		
		

	private static void updateQueue(Node node) {//update other nodes after a node is added to the visited set
		for(int i =0; i<node.neighbors.size(); i++) {
			Node neighborNode = new Node(node.neighbors.get(i).getNeighbor());//get neigbor nodes
			neighborNode.value = node.neighbors.get(i).getDistance();
			if (locations.contains(neighborNode)) {
				Node newNode = neighborNode.getNode(locations.remove(neighborNode));
				if (newNode.value >= node.neighbors.get(i).getDistance() + node.value) {//update value based on if statement
					newNode.value = node.neighbors.get(i).getDistance() + node.value;
					newNode.prevNode = node;//store the previous node
					locations.add(newNode); //add back to priority queue after value is updated
				}
				
				else {
					locations.add(newNode);
				}
			}
		}
		
	}
 
 }
	

