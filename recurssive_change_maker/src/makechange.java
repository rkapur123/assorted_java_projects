package final;

import java.util.ArrayList;


public class makechange {
	
	static int count = 0;
	static int count2=0;
	static int count3=0;
	static boolean one;
	
	static String line ="";
	static ArrayList<Integer> output = new ArrayList<Integer>();
	
	 
	

	
	
	public static String f(int n) {
		
		if (n%5 != 0) {
			return "The denominations of this do not exist";
		}
		
		else if (n%5 ==0) {
			System.out.println(line);
			return line;
		}
		
		else if (n >=25) {
			line = line + " 25";
			return f(n-25);
		}
		
		else if (n>=10 && n<25) {
			line = line + " 10";
			return f(n-10);
		}
		
		else  {
			return f(n-5);
		}
	}
	
	public static void main(String[] args){
		
		


		try {
			int inputValue = Integer.parseInt(args[0]);
			f(inputValue);
		} catch (NumberFormatException e) {
			System.out.println("Please input a number");
		}
		
		
		
	
	
		
		
	} 

}

