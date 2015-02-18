package Djikstra;


public class Neighbors {
	
	private String neighbor;//neigboring location
	private double distance;//distance to location from city
	
	Neighbors(String neighbor, double distance) {
		this.neighbor=neighbor;//neighbor
		this.distance= distance;//distance
		
	}
	
	public String getNeighbor() {//get neighbor name
		return neighbor;
	}
	
	public double getDistance() {//get distance to neighbor
		return distance;
	}

	public void setDistance(double distance) {//set distance to neighbor
		this.distance = distance;
	}
	
	

}
