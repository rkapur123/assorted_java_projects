package kruskalsalgorithm;
//Rahul Kapur
//this is used to output the gui


import java.awt.Dimension;
import java.awt.Graphics;
import java.util.EmptyStackException;
import java.util.PriorityQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Points extends JFrame {
	
	PriorityQueue<Edge> visitedEdges = new PriorityQueue<Edge>();//pass in the respective edges that form the MST
	PriorityQueue<Edge> allEdges = new PriorityQueue<Edge>();//pass in all edges
	int width = 900;
	int height = 500;

	
	Points( PriorityQueue<Edge> allEdges,  PriorityQueue<Edge> visitedEdges) {
		this.allEdges= allEdges;
		this.visitedEdges= visitedEdges;
		setTitle("Kurskal's Minimal Spanning Tree");//create title
		setSize(1100, 700);//create size
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void paint (Graphics g)  {
	
		
		while(!visitedEdges.isEmpty()) {//plot points 
			Edge a = visitedEdges.poll();//remove edge from visited list
			g.fillRect( (int)a.x1/3, height - (int) a.y1/3, 5, 5);//create filled rectangles at location 1
			g.drawString(a.location1,(int) a.x1/3, height-(int) a.y1/3);//write location name
			g.fillRect((int)a.x2/3, height-(int) a.y2/3, 5, 5);//create filled rectangles at location 2
			g.drawString(a.location2,(int) a.x2/3,height- (int) a.y2/3);//write location name
			g.drawLine((int)a.x1/3,height-(int) a.y1/3,(int) a.x2/3,height-(int) a.y2/3);//draw a line connecting them
		}
		
		
		
	}
	
	
	
		
		
	
}
