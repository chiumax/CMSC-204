import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class Graph implements GraphInterface {
	HashMap<String, HashMap<String, Road>> adj_mat = new HashMap<String, HashMap<String, Road>>();
	private int[] dist;
	private String[] prev;
	private ArrayList<String> towns;

	@Override
	public Object getEdge(Object sourceVertex, Object destinationVertex) {
		try {
			return (Road) adj_mat.get(((Town) sourceVertex).getName()).get(((Town) destinationVertex).getName());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Object addEdge(Object sourceVertex, Object destinationVertex, int weight, String description) {
		Town src = (Town) sourceVertex;
		Town dest = (Town) destinationVertex;
		if (!adj_mat.containsKey(src.getName()) && !adj_mat.containsKey(dest.getName())) {
			throw new IllegalArgumentException();
		}
		if (src == null || dest == null) {
			throw new NullPointerException();
		}
		Road edge = new Road(src, dest, weight, description);
		adj_mat.get(src.getName()).put(dest.getName(), edge);
		adj_mat.get(dest.getName()).put(src.getName(), edge);
		return edge;
	}

	@Override
	public boolean addVertex(Object v) {
		if (v == null) {
			throw new NullPointerException();
		}
		Town town = (Town) v;
		if (adj_mat.containsKey(town.getName())) {
			return false;
		}
		adj_mat.put(town.getName(), new HashMap<String, Road>());
		adj_mat.get(town.getName()).put(town.getName(), null);
		return false;
	}

	@Override
	public boolean containsEdge(Object sourceVertex, Object destinationVertex) {
		try {
			if (adj_mat.get(((Town) sourceVertex).getName()).get(((Town) destinationVertex).getName()) != null) {
				return true;
			}

		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public boolean containsVertex(Object v) {
		if (adj_mat.containsKey(((Town) v).getName())) {
			return true;
		}
		return false;
	}

	@Override
	public Set edgeSet() {
		Set<Road> r = new HashSet<Road>();
		for (String key : adj_mat.keySet()) {
			r.addAll(adj_mat.get(key).values());
		}
		r.remove(null);
		return r;
	}

	@Override
	public Set edgesOf(Object vertex) {

		Town t = (Town) vertex;
		if (t == null) {
			throw new NullPointerException();
		}
		if (!adj_mat.containsKey(t.getName())) {
			throw new IllegalArgumentException();
		}

		Set<Road> r = new HashSet<Road>(adj_mat.get(t.getName()).values());
		r.remove(null);
		return r;
	}

	@Override
	public Object removeEdge(Object sourceVertex, Object destinationVertex, int weight, String description) {
		if (weight > -1 && description != null) {
			Town src = (Town) sourceVertex;
			Town dest = (Town) destinationVertex;
			Road r = adj_mat.get(src.getName()).get(dest.getName());
			adj_mat.get(src.getName()).put(dest.getName(), null);
			adj_mat.get(dest.getName()).put(src.getName(), null);
			return r;
		}
		return null;
	}

	@Override
	public boolean removeVertex(Object v) {
		// TODO Auto-generated method stub
		Town t = (Town) v;
		if (v == null || !adj_mat.containsKey(t.getName())) {
			return false;
		}
		adj_mat.remove(t.getName());
		return true;
	}

	@Override
	public Set vertexSet() {
		Set<Town> r = new HashSet<Town>();
		for (String k : adj_mat.keySet()) {
			r.add(new Town(k));
		}
		return r;
	}

	@Override
	public ArrayList shortestPath(Object sourceVertex, Object destinationVertex) {
		// TODO Auto-generated method stub
		dijkstraShortestPath(sourceVertex);
		Town dest = (Town) destinationVertex;
		// get the destination vertex and then just trace back until we get to the
		// source vertex.
		ArrayList<String> path = new ArrayList<String>();
		ArrayList<Integer> path_weight = new ArrayList<Integer>();
		List<Town> verts = new ArrayList<Town>(vertexSet());

		int ind = towns.indexOf(dest.getName());
		path.add(dest.getName());
		while (prev[ind] != null) {

			path.add(prev[ind]);
			path_weight.add(dist[ind]);
			ind = towns.indexOf(prev[ind]);

		}

		Collections.reverse(path);
		Collections.reverse(path_weight);
		ArrayList<String> finalPath = new ArrayList<String>();
		int runningCount = 0;
		for (int i = 0; i < path.size() - 1; i++) {
			finalPath.add(path.get(i) + " via "
					+ (((Road) this.getEdge(new Town(path.get(i)), new Town(path.get(i + 1)))).getName()) + " to "
					+ path.get(i + 1) + " " +(path_weight.get(i)- runningCount)+" mi");
			runningCount += path_weight.get(i)- runningCount;
		}

		return finalPath;
	}

	@Override
	public void dijkstraShortestPath(Object sourceVertex) {
		String src = ((Town) sourceVertex).getName();
		// TODO Auto-generated method stub
		List<Town> verts = new ArrayList<Town>(vertexSet());
		towns = new ArrayList<String>();
		ArrayList<String> unvisited = new ArrayList<String>();
		for (Town v : verts) {
			towns.add(v.getName());
			unvisited.add(v.getName());
		}
//		unvisited.remove(unvisited.indexOf(src));

		dist = new int[towns.size()];
		prev = new String[towns.size()];

		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[towns.indexOf(src)] = 0;

		while (!unvisited.isEmpty()) {

			// get neighboring verticies next to the source
			HashMap<String, Road> connected_nodes = adj_mat.get(src);
			for (String t : connected_nodes.keySet()) {
				if (unvisited.indexOf(t) != -1 && connected_nodes.get(t) != null) {
					int ind = towns.indexOf(t);
					int curr_ind = towns.indexOf(src);
					int weight = connected_nodes.get(t).getWeight();
					if (dist[curr_ind] + weight < dist[ind]) {

						dist[ind] = weight + dist[curr_ind];
						prev[ind] = src;
					}

				}

			}
			unvisited.remove(unvisited.indexOf(src));
			if(unvisited.isEmpty()) {
				break;
			}
			// get unvisited smallest distance, and set it as source

			
			int shortest = Integer.MAX_VALUE;
			int shortest_ind = -1;
			for (String t : unvisited) {
				int ind = towns.indexOf(t);
				if (dist[ind] < shortest) {
					shortest = dist[ind];
					shortest_ind = ind;
				}
			}
			
			if(shortest_ind== -1) {
	
				break;
			}
			src = towns.get(shortest_ind);

		}

	}

}
