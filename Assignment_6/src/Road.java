
public class Road {
	private Town source;
	private Town dest;
	private int weight;
	private String name;
	public Road(Town source, Town destination, int degrees, String name) {
		this.source = source;
		this.dest = destination;
		this.weight = degrees;
		this.name = name;
	}
	
	public Road (Town source, Town destination, String name){
		this.source = source;
		this.dest = destination;
		this.weight = 1;
		this.name = name;
	}
	
	public int compareTo(Road o) {
		return this.name.compareTo(o.getName());
	}
	
	public boolean contains(Town town) {
		if(source.equals(town) || dest.equals(town)) {
			return true;
		}
		
		return false;
	}
	public boolean equals(Object r) {
		Road c = (Road) r;
		if(c.getSource().equals(new Town(source)) && c.getDestination().equals(new Town(dest)) || c.getSource().equals(new Town(dest)) && c.getDestination().equals(new Town(source))) {
			return true;
		}
		return false;
	}
	public Town getDestination() {
		return this.dest;
	}
	public String getName() {
		return this.name;
	}
	public Town getSource() {
		return this.source;
	}
	public int getWeight() {
		return this.weight;
	}
	public String toString() {
		return this.name;
	}
}
