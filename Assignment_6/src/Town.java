
public class Town {
	private String name;
	public Town (String name) {
		this.name = name;
	}
	public Town (Town templateTown) {
		this.name = templateTown.getName();
	}
	
	public int compareTo(Town o) {
		
		return this.name.compareTo(o.getName());
		
	}
	
	public boolean equals(Object obj) {
		Town t = (Town)obj;
		return this.name.equals(t.getName());
		
	}
	
	public String getName() {
		return this.name;
	}
	public int hashCode() {
		return this.name.hashCode();
	}
	public String toString() {
		return this.name;
	}
}
