package Djikstra;

//Rahul Kapur
//Question class promts the user from starting and ending points

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Question extends JFrame{ //extend JFrame to represent gui
	JPanel jp = new JPanel();//place the text fields within
	JButton jb = new JButton("Compute");//calculate using djikstras algorith
	JTextField jt = new JTextField("Insert Source", 30);// source text field
	JTextField jt2 = new JTextField("Insert Destination", 30);//destination text field
	String source = "";
	String destination = "";
	String cityxy;//argument for main class
	String citypairs;// argument from main class
	boolean value = false;

	
	Question(final String cityxy, final String citypairs){// constructor of class
		this.cityxy= cityxy;//takes in parameter from main class
		this.citypairs= citypairs;
		setTitle("Ask User Input");//title
		setVisible(true);
		setSize(400,200);//frame size
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		jp.add(jt);//add first text field to panel
		

		
		jt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				source = jt.getText(); //store inputted text
			}
		});
		
		jp.add(jt2);//destination text field
		
		jt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				destination = jt2.getText();//store inputted destination
			}
		});
		
		
		jp.add(jb);//to compute using djikstras algorithm
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {//instantiate djikstra class to compute
					djikstrasAlgorithm dj = new djikstrasAlgorithm(jt.getText(), jt2.getText(), cityxy, citypairs);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		
		
		
		
		
		add(jp);

	}
	
	
	
}