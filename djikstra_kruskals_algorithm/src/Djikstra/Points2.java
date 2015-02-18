package Djikstra;

//Rahul Kapur
//Points class is used to graph the points and draw the path

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.PriorityQueue;

import javax.swing.JFrame;


public class Points2 extends JFrame {
	
	PriorityQueue<Node> locations = new PriorityQueue<Node>();//pass in all locations to the constructor from the djikstra class
	ArrayList<Node> shortestPath = new ArrayList<Node>();// sequence of shortest path nodes in a array list 
	Double path;//path distance (i.e. shortest path value)

	
	Points2(ArrayList<Node> shortestPath,PriorityQueue<Node> locations, Double path ) {
		this.shortestPath =shortestPath;
		this.locations=locations;
		this.path=path;
		setTitle("Djikstra's Shortest Path with value of " + String.valueOf(this.path));
		setSize(800, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	
	public void paint (Graphics g)  {
		
	while(!locations.isEmpty()) {
		Node a = locations.poll();//for each node in location
		g.fillRect(a.getX()/4, a.getY()/4, 5, 5);//plot a filled rectangle at coordinate
		g.drawString(a.location, a.getX()/4, a.getY()/4);//write name of location
	
	}
	
	for (int i =0; i < shortestPath.size()-1; i++) {// for each node in the shortest path
		Node b = shortestPath.get(i);//store a node
		Node c = shortestPath.get(i+1);// store next
		g.drawLine(b.getX()/4, b.getY()/4, c.getX()/4, c.getY()/4);//draw a line between their coordinates
	}
	
	}

	
	
	
}
