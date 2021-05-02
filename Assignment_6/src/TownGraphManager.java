import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class TownGraphManager implements TownGraphManagerInterface {
	private Graph g = new Graph();
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		if(g.addEdge(new Town(town1), new Town(town2), weight, roadName) != null) {			
			return true;
		}
		return false;
	}

	@Override
	public String getRoad(String town1, String town2) {
		return ((Road) g.getEdge(new Town(town1), new Town(town2))).getName();
	}

	@Override
	public boolean addTown(String v) {
		return g.addVertex(new Town(v));
	}

	@Override
	public Town getTown(String name) {
		return new Town(name);
	}

	@Override
	public boolean containsTown(String v) {
		return g.containsVertex(new Town(v));
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		return g.containsEdge(new Town(town1), new Town(town2));
	}

	@Override
	public ArrayList<String> allRoads() {
		ArrayList<Road> roads = new ArrayList<Road>(g.edgeSet());
		ArrayList<String> strRoads = new ArrayList<String>();
		for(Road r: roads) {
			strRoads.add(r.getName());
		}
		Collections.sort(strRoads);
		return strRoads;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		if(g.removeEdge(new Town(town1), new Town(town2), 0, road) != null) {
			
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteTown(String v) {
		// TODO Auto-generated method stub
		return g.removeVertex(new Town(v));
	}

	@Override
	public ArrayList<String> allTowns() {
		ArrayList<Town> towns = new ArrayList<Town>(g.vertexSet());
		ArrayList<String> strTowns= new ArrayList<String>();
		for(Town t: towns) {
			strTowns.add(t.getName());
		}
		Collections.sort(strTowns);
		return strTowns;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		return g.shortestPath(new Town(town1), new Town(town2));
	}

}
