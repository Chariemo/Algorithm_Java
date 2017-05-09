package graph;

public class Edge implements Comparable<Edge>{

	private static final int DEFAULT_WEIGHT = 1;
	private Vertex sVertex;
	private Vertex eVertex;
	private int weight;
	
	public Edge() {
	}
	
	public Edge(Vertex sVertex, Vertex eVertex) {
		
		this(sVertex, eVertex, DEFAULT_WEIGHT);
	}
	
	public Edge(Vertex sVertex, Vertex eVertex, int weight) {
		
		this.sVertex = sVertex;
		this.eVertex = eVertex;
		this.weight = weight;
	}

	public Vertex getsVertex() {
		return sVertex;
	}

	public void setsVertex(Vertex sVertex) {
		this.sVertex = sVertex;
	}

	public Vertex geteVertex() {
		return eVertex;
	}

	public void seteVertex(Vertex eVertex) {
		this.eVertex = eVertex;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		
		return this.weight > o.weight ? 1 : (this.weight < o.weight ? -1 : 0);
	}
	
	public String toString() {
		
		return "<" + this.sVertex.getLabel() + "," + this.eVertex.getLabel() + "> " + this.weight; 
	}
}
