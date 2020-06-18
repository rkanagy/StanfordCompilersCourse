import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class Graph<T> {
	private Hashtable<String, Vertex<T>> vertices;
	
	public Graph() {
		this.vertices = new Hashtable<String, Vertex<T>>();
	}
	
	public Enumeration<Vertex<T>> getVertices() {
		return vertices.elements();
	}
	
	public void addVertex(Vertex<T> vertex) {
		this.vertices.put(vertex.getLabel(),  vertex);
	}
	
	public void addEdge(Vertex<T> from, Vertex<T> to) {
		from.addNeighbor(to);
	}
	
	public Vertex<T> findVertex(String label) {
		return vertices.get(label);
	}
	
	public void setVerticesNotVisited() {
		for(Enumeration<Vertex<T>> v = getVertices(); v.hasMoreElements(); ) {
			Vertex<T> vertex= (Vertex<T>)v.nextElement();
			vertex.setBeingVisited(false);
			vertex.setVisited(false);
		}
	}
	
	public boolean hasCycle(Vertex<T> sourceVertex) {
		if (sourceVertex == null) vertices.get(0); // use first vertex in graph
		sourceVertex.setBeingVisited(true);
		
		for (Vertex<T> neighbor : sourceVertex.getInheritsAdjacencyList() ) {
			if (neighbor.isBeingVisited()) {
				return true;
			} else if (!neighbor.isVisited() && hasCycle(neighbor)) {
				return true;
			}
		}
		
		sourceVertex.setBeingVisited(false);
		sourceVertex.setVisited(true);
		return false;
	}
	
	public T findVisitedVertexInHierarchy(Vertex<T> childVertex) {
		if (childVertex.isVisited()) return childVertex.getAdditionalData();
		
		childVertex.setVisited(true);
		// it is assumed that this graph has no cycles and
		// the inherits adjacency list only contains at most one vertex
		List<Vertex<T>> vertexInherits = childVertex.getInheritsAdjacencyList();
		if (vertexInherits.size() < 1) return null;
		
		Vertex<T> parentVertex = vertexInherits.get(0);
		return findVisitedVertexInHierarchy(parentVertex);
		
	}
}
