import java.util.ArrayList;
import java.util.List;

public class Vertex<T> {
	private String label;
	private T additionalData;
	private boolean beingVisited;
	private boolean visited;
	private List<Vertex<T>> inheritsAdjacencyList;
	private List<Vertex<T>> inheritedByAdjacencyList;

	public Vertex(String label, T additionalData) {
		this.label = label;
		this.additionalData = additionalData;
		this.inheritsAdjacencyList = new ArrayList<Vertex<T>>();
		this.inheritedByAdjacencyList = new ArrayList<Vertex<T>>();
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public T getAdditionalData() {
		return this.additionalData;
	}
	public boolean isBeingVisited() {
		return beingVisited;
	}

	public void setBeingVisited(boolean beingVisited) {
		this.beingVisited = beingVisited;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public List<Vertex<T>> getInheritsAdjacencyList() {
		return inheritsAdjacencyList;
	}
	
	public List<Vertex<T>> getInheritedByAdjacencyList() {
		return this.inheritedByAdjacencyList;
	}
	
	public Vertex<T> getAncestor() {
		if (inheritsAdjacencyList.size() < 1) return null;
		return inheritsAdjacencyList.get(0);
	}
	public List<Vertex<T>> getDescendants() {
		if (this.inheritedByAdjacencyList.size() < 1) return null;
		return this.inheritedByAdjacencyList;
	}
	
	public void addNeighbor(Vertex<T> adjacent) {
		this.inheritsAdjacencyList.add(adjacent);
		List<Vertex<T>> adjacentInheritedByAdjacencyList = adjacent.getInheritedByAdjacencyList();
		adjacentInheritedByAdjacencyList.add(this);
	}
}