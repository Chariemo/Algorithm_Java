package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class AdjMatrixGraph {

	public static final int MAX_VERTEXNUM = 20;
	public static final int MAX_VALUE = 65535;
	public static final int WHITE = -1;
	public static final int GRAY = 0;
	public static final int BLACK = 1;
	private static final Vertex NIL = Vertex.NIL;
	private ArrayList<Vertex> vertexs = new ArrayList<>();
	private ArrayList<Edge> edges = new ArrayList<>();
	private int vertexNum;
	private int edgeNum;
	private boolean graphType;
	private Edge adj[][];
	private HashSet<Edge> mst = new HashSet<>();
	private HashMap<Integer, HashSet<Vertex>> msts = new HashMap<>();
	
	public AdjMatrixGraph(boolean graphType, int vertexNum, int edgeNum) {
		
		this.graphType = graphType;
		this.vertexNum = vertexNum;
		this.edgeNum = edgeNum;
		
		adj = new Edge[vertexNum][vertexNum];
		Edge edge;
		for (int i = 0; i < vertexNum; i++) {
			for (int j = 0; j < vertexNum; j++) {
				edge = new Edge(null, null);
				edge.setWeight(MAX_VALUE);
				adj[i][j] = edge;
			}
		}
	}
	
	public void createGraph() {
		
		System.out.print("Please input " + vertexNum + " vertexs: ");
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < vertexNum; i++) {
			
			vertexs.add(new Vertex(input.next(), i));
		}
		
		System.out.println("Please input the edges and weight:(as: A B 20)");
		String A, B;
		int a, b, weight, i;
		Edge edge;
		Vertex sVertex, eVertex;
		i = 1;
		while (i <= edgeNum) {
			
			sVertex = null;
			eVertex = null;
			System.out.print("The No." + i + " edges and weight: ");
			A = input.next();
			B = input.next();
			weight = input.nextInt();
			input.nextLine();
			
			for (a = 0; a < vertexNum; a++) {
				if (A.equals(vertexs.get(a).getLabel())) {
					sVertex = vertexs.get(a);
					break;
				}
			}
			for (b = 0; b < vertexNum; b++) {
				if (B.equals(vertexs.get(b).getLabel())) {
					eVertex = vertexs.get(b);
					break;
				}
			}
			
			if (sVertex != null && eVertex != null && weight <= MAX_VALUE) {
				
				edge = adj[a][b];
				edge.setsVertex(sVertex);
				edge.seteVertex(eVertex);
				edge.setWeight(weight);
				edges.add(edge);
				if (!graphType) {
					edge = adj[b][a];
					edge.setsVertex(eVertex);
					edge.seteVertex(sVertex);
					edge.setWeight(weight);
					edges.add(edge);
				}
				i++;
			} else {
				System.out.println("Wrong");
				continue;
			} 
		}
	}
	
	public void printGraph() {
		
		for (int i = 0 ; i < vertexNum; i++) {
			
			System.out.print("\t" + vertexs.get(i).getLabel());
		}
		System.out.println();
		
		for (int i = 0; i < vertexNum; i++) {
			
			System.out.print(vertexs.get(i).getLabel());
			for (int j = 0; j < vertexNum; j++) {
				
				if (adj[i][j].getWeight() == MAX_VALUE) {
					System.out.print("\tZ");
				}
				else {
					System.out.print("\t" + adj[i][j].getWeight());
				}
			}
			System.out.println();
		}
	}
	
	public void BFS(Vertex s) {
		
		Vertex vertex = null, toVertex = null;
		for (int i = 0; i < vertexNum; i++) {
			
			vertex = vertexs.get(i);
			if (vertex != NIL) {
				vertex.setColor(WHITE);
				vertex.setDistFromS(MAX_VALUE);
				vertex.setPreNode(NIL);
			}
		}
		
		s.setColor(GRAY);
		s.setDistFromS(0);
		s.setPreNode(NIL);
		Queue<Vertex> queue = new LinkedList<>();
		queue.add(s);
		while (!queue.isEmpty()) {
			
			vertex = queue.poll();
			int index = vertex.getIndex();
			for (int i = 0; i < vertexNum; i++) {
				
				if (adj[index][i].getWeight() != MAX_VALUE) {
					toVertex = vertexs.get(i);
					if (toVertex.getColor() == WHITE) {
						toVertex.setColor(GRAY);
						toVertex.setDistFromS(toVertex.getDistFromS()+1);
						toVertex.setPreNode(vertex);
						
						queue.add(toVertex);
					}
				}
			}
			vertex.setColor(BLACK);
		}
	}
	
	public Vertex getVertexByLabel(String label) {
		
		for (int i = 0; i < vertexNum; i++) {
			if (label.equals(vertexs.get(i).getLabel())) {
				return vertexs.get(i);
			}
		}
		return null;
	}
	
	public void printPath(Vertex sVertex, Vertex tVertex) {
		
		if (sVertex == tVertex) {
			System.out.print("->" + sVertex.getLabel());
		}
		else if (tVertex.getPreNode() == NIL) {
			System.out.println("There is no path from " + sVertex.getLabel() + " to " + tVertex.getLabel());
		}
		else {
			printPath(sVertex, tVertex.getPreNode());
			System.out.print("->" + tVertex.getLabel());
		}
		
	}
	
	public void DFS() {
		
		Vertex vertex = null;
		for (int i = 0; i < vertexNum; i++) {
			vertex = vertexs.get(i);
			vertex.setColor(WHITE);
			vertex.setPreNode(NIL);
		}
		
		for (int i = 0; i < vertexNum; i++) {
			vertex = vertexs.get(i);
			if (vertex.getColor() == WHITE) {
				DFSVisit(vertex);
			}
		}
	}
	
	public void DFSVisit(Vertex vertex) {
		
		Vertex tVertex;
		vertex.setColor(GRAY);
		int index = vertex.getIndex();
		
		for (int i = 0; i < vertexNum; i++) {
			if (adj[index][i].getWeight() != MAX_VALUE) {
				tVertex = vertexs.get(i);
				if (tVertex.getColor() == WHITE) {
					tVertex.setPreNode(vertex);
					DFSVisit(tVertex);
				}
			}
		}
		vertex.setColor(BLACK);
		System.out.print("->" + vertex.getLabel());
	}
	
	public HashSet<Edge> MstKruskal() {
		
		mst.clear();
		HashSet<Vertex> set = null;
		Edge edge;
		Vertex sVertex, eVertex;
		int sIndex, eIndex;
		for (int i = 0; i < vertexNum; i++) {
			
			set = new HashSet<>();
			set.add(vertexs.get(i));
			msts.put(i, set);
		}
		Collections.sort(edges);
		Iterator<Edge> iterator = edges.iterator();
		while (iterator.hasNext()) {
			
			edge = iterator.next();
			sVertex = edge.getsVertex();
			eVertex = edge.geteVertex();
			if ((sIndex = findSet(sVertex)) != (eIndex = findSet(eVertex))) {
				mst.add(edge);
				msts.get(sIndex).addAll(msts.get(eIndex));
				msts.remove(eIndex);
			}
		}
		
		return mst;
	}
	
	public int findSet(Vertex vertex) {
		
		int result = -1;
		Iterator<Entry<Integer, HashSet<Vertex>>> iterator = msts.entrySet().iterator();
		Entry<Integer, HashSet<Vertex>> entry;
		while (iterator.hasNext()) {
			entry = iterator.next();
			if (entry.getValue().contains(vertex)) {
				result = entry.getKey();
				break;
			}
		}
		return result;
	}
	
	public HashSet<Edge> MstPrim(Vertex sVertex) {
		
		mst.clear();
		Vertex vertex, tVertex;
		Edge edge;
		int index;
		PriorityQueue<Vertex> minPriQueue = new PriorityQueue<>();
		for (int i = 0; i < vertexNum; i++) {
			
			vertex = vertexs.get(i);
			if (vertex.equals(sVertex)) {
				vertex.setKey(0);
			}
			else {
				vertex.setKey(MAX_VALUE);
			}
			vertex.setPreNode(NIL);
			minPriQueue.offer(vertex);
		}
		while (!minPriQueue.isEmpty()) {
			
			vertex = minPriQueue.poll();
			index = vertex.getIndex();
			for (int i = 0; i < vertexNum; i++) {
				
				edge = adj[index][i];
				tVertex = edge.geteVertex();
				if (tVertex != null && minPriQueue.contains(tVertex) && edge.getWeight() < tVertex.getKey()) {
					
					tVertex.setKey(edge.getWeight());
					tVertex.setPreNode(vertex);
				}
			}
			
			if (!vertex.equals(sVertex)) {
				mst.add(adj[vertex.getIndex()][vertex.getPreNode().getIndex()]);
			}
			minPriQueue = rebuildePriQue(minPriQueue);
		}
		
		return mst;
	}
	
	public PriorityQueue<Vertex> rebuildePriQue(PriorityQueue<Vertex> minPriQueue) {
		
		PriorityQueue<Vertex> temp = new PriorityQueue<>();
		Iterator<Vertex> iterator = minPriQueue.iterator();
		while (iterator.hasNext()) {
			temp.add(iterator.next());
		}
		return temp;
	}
	
	public static void main(String[] args) {
		   
		Iterator<Edge> iterator;
		AdjMatrixGraph graph = new AdjMatrixGraph(false, 9, 14);
		graph.createGraph();
		graph.printGraph();
//		graph.BFS(graph.getVertexByLabel("s"));
//		graph.printPath(graph.getVertexByLabel("s"), graph.getVertexByLabel("u"));
//		graph.DFS();
		iterator = graph.MstPrim(new Vertex("a")).iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}	
	}
}
