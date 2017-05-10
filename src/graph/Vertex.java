package graph;

public class Vertex {

	public static final Vertex NIL = new Vertex(null);
	private int index;
	private String label;
	private Vertex preNode;
	private int color; //-1: white		0: gray		1: black
	private int distFromS;
	private int key;
	
	public Vertex(String label) {
		
		this.label = label;
	}
	public Vertex(String label, int index) {
		
		this.label = label;
		this.index = index;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Vertex getPreNode() {
		return preNode;
	}

	public void setPreNode(Vertex preNode) {
		this.preNode = preNode;
	}

	public int getDistFromS() {
		return distFromS;
	}

	public void setDistFromS(int distFromS) {
		this.distFromS = distFromS;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getKey() {
		return key;
	}
	
	public void setKey(int key) {
		this.key = key;
	}
	
	public boolean equals(Object object) {
		
		boolean result = false;
		if (this == object) {
			
			result = true;
		}
		else if (object != null && object instanceof Vertex && (Vertex)object != NIL) {
			
			if (this.getLabel().equals(((Vertex)object).getLabel())) {
				result = true;
			}
		}
		
		return result;
	}

	public String toString() {
		
		return this.label;
	}
}
