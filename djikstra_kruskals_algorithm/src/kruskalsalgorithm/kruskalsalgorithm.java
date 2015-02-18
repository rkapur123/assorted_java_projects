package kruskalsalgorithm;
//Rahul Kapur
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class kruskalsalgorithm {
	
	private static PriorityQueue<Edge> allEdges = new PriorityQueue<Edge>();
	private static PriorityQueue<Edge> allEdges2 = new PriorityQueue<Edge>();
	private static PriorityQueue<Edge> visitedEdges = new PriorityQueue<Edge>();
	private static PriorityQueue<Edge> visitedEdges2 = new PriorityQueue<Edge>();
	private static ArrayList<Set> set1 = new ArrayList<Set>();
	
	public static void main (String args[]) throws FileNotFoundException {
		
		
		int i=0;
		String fileName = args[0];//take in cities and coordinates
		File file = new File(fileName);
		Scanner input = new Scanner(file);
		while(input.hasNextLine()) {//loop through cities
			String line = input.nextLine();
			String hello = "";
        	String[] cityAndCoordinates = new String[3];//store cities, x,or y coordinates in each element of the list
        	int k =0;
        	int g =0;
        	while (k < line.length()) {//while loop accounts for spacing between words per line
        		if (line.charAt(k)!= ' ') {
        			hello =hello + line.charAt(k);
        			k++;
        		}
        		
        		else if (line.charAt(k) == ' ' && !hello.equals("")){
        			cityAndCoordinates[g] = hello;
        			hello = "";
        			k++;
        			g++;	
        		}
        		
        		else {
        			k++;
        		}
        		cityAndCoordinates[g] = hello;
        		
        	}
			String city = cityAndCoordinates[0];//city name
			double xCoordinate = Double.parseDouble(cityAndCoordinates[1]);// x coordinate
			double yCoordinate = Double.parseDouble(cityAndCoordinates[2]);// y coordinate
			
			String fileName2 = args[0];
			File file2 = new File(fileName2);
			Scanner input2 = new Scanner(file2);//create another scanner with the cityxy input file
			i++;
			for (int j =0; j<i; j++) {
				input2.nextLine();
			}
			
			while(input2.hasNextLine()) {
				String line2 = input2.nextLine();//follow similar strategy as above to get the details for each city
				String hello1 = "";
	        	String[] cityAndCoordinates2 = new String[3];
	        	int c =0;
	        	int x =0;
	        	while (c < line2.length()) {//account for spaces in the input file
	        		if (line2.charAt(c)!= ' ') {
	        			hello1 =hello1 + line2.charAt(c);
	        			c++;
	        		}
	        		
	        		else if (line2.charAt(c) == ' ' && !hello1.equals("")){
	        			cityAndCoordinates2[x] = hello1;
	        			hello1 = "";
	        			c++;
	        			x++;	
	        		}
	        		
	        		else {
	        			c++;
	        		}
	        		
	        	}
	        	cityAndCoordinates2[x] = hello1;
				String city2 = cityAndCoordinates2[0];
				double xCoordinate2 = Double.parseDouble(cityAndCoordinates2[1]);//x coordinate
				double yCoordinate2 = Double.parseDouble(cityAndCoordinates2[2]);//y coordinate
				double xDistance = Math.pow(xCoordinate - xCoordinate2, 2);// squared distance between two x coordinates
				double yDistance = Math.pow(yCoordinate - yCoordinate2, 2);// squared distance between two y coordinates
				double distance = Math.sqrt(xDistance + yDistance);
				
				Edge a = new Edge(city, city2, distance, xCoordinate,xCoordinate2, yCoordinate, yCoordinate2);// store as edge
				allEdges.add(a);// add to all edges list
				allEdges2.add(a);// create another list that will be pass4d to the points class
				
			}
			
			
		}
		
		
		
		
			Edge first = allEdges.peek();
			Set original = new Set(first.location1, first.location2);// create a set with the minimum edge from the priority queue
			set1.add(original);//add this to the arraylist of sets
			visitedEdges.add(allEdges.poll());//add the edge to the visited list

			 
			
			
			int counter =0;
			
			while (visitedEdges.size()< i-1) {// must reach n-1 edges with the below conditions to get an MST
				Edge a = allEdges.poll();
				counter++;
				int value = checkSet(a.location1);//determine whether they are in the same set or not
				int value2 = checkSet(a.location2);
				
				if (value==value2 && value!=-1) {// same set do nothing
					
				}
				
				else if (value!= -1 && value2==-1) {// one is one set the other isnt in any
					set1.get(value).addEdgeToSet(a.location2);
					visitedEdges.add(a);
					visitedEdges2.add(a);

				}
				
				else if (value2!= -1 && value==-1) {//one is in one set the other isnt in any
					set1.get(value2).addEdgeToSet(a.location1);
					visitedEdges.add(a);
					visitedEdges2.add(a);

				}
				
				else if (value == -1 && value2 ==-1) {// both are in no sets so create a new set and add to the arraylist
					Set next = new Set(a.location1, a.location2);
					set1.add(next);
					visitedEdges.add(a);
					visitedEdges2.add(a);

				}
				
				else if (value!=value2 && value2!= -1 && value!= -1) {//both in different sets
					visitedEdges.add(a);
					visitedEdges2.add(a);
					unionizeSet(value, value2);// unionize the two sets
					
				}
				
	
				
			}
			
			Points points = new Points (allEdges2, visitedEdges);
			System.out.println("List of edge below:");
			while (!visitedEdges2.isEmpty()) {
				Edge c = visitedEdges2.poll();
				System.out.println(c.location1 + "-" + c.location2);
			} 
			
			
			
			
			
		
	}

	private static void unionizeSet(int value, int value2) {//merge two sets without duplicates
		Set one = set1.get(value);
		Set two = set1.get(value2);
		
		for (int l =0; l < two.set.size(); l++) {
			one.set.add(two.set.get(l));	//add all elements from two to one
		}
		
		set1.set(value,one);
		set1.remove(value2);
		

		
		
		
	}

	private static int checkSet(String location) {//check if elements are in the arraylist of sets or not
		boolean check1 = false;
		int k = -3;
		for (int j =0; j<set1.size(); j++) {//loop through arraylist
			if (set1.get(j).set.contains(location)) {//check if it is contained in any lement of the arraylist
				check1=true;
				k = j;
			}
			
		}
		
		if (check1==true) {
			return k;//it is contained
		}
		
		else {
			return-1;// it is not contained
		}
		
	}
}
