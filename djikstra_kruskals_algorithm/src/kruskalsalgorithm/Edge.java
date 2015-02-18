//Rahul Kapur
//Edge class stores the edge with its two locations , weight , and each locations coordinates
package kruskalsalgorithm;

public class Edge implements Comparable<Edge>{ //override the compare to method
	
	public String location1;
	public String location2;
	public double weight;
	public double x1;
	public double x2;
	public double y1;
	public double y2;
	public Edge remove;

	
	Edge(String location1, String location2, double weight, double x1, double x2, double y1, double y2) {
		this.location1 =location1;//locations
		this.location2= location2;
		this.weight=weight;//edge weight
		this.x1=x1;//coordinates
		this.x2=x2;
		this.y1= y1;
		this.y2=y2;
	}




	@Override
	public int compareTo(Edge a) {
		if (a.weight==this.weight) {
			return 0;
		}
		
		else if (this.weight > a.weight) {
			return 1;//this will allow us to implement a priority queue by just comparing weights
		}
		else {
			return -1;
		}
	}

}
