package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	private Vertex preVerTree[][];
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
		Vertex vertex;
		for (int i = 0; i < vertexNum; i++) {
			vertex = new Vertex(input.next(), i);
			vertex.setPreNode(NIL);
			vertexs.add(vertex);
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
		
		System.out.println();
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
		
		for (int i = 0; i < vertexNum; i++) {//初始化msts 以每个节点的序号为树标识
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
			if ((sIndex = findSet(sVertex)) != (eIndex = findSet(eVertex))) { //是否是在同一棵树
				mst.add(edge);
				msts.get(sIndex).addAll(msts.get(eIndex)); //合并
				msts.remove(eIndex); //删除被合并的mst
			}
		}
		
		return mst;
	}
	
	private int findSet(Vertex vertex) {
		
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
		PriorityQueue<Vertex> minKeyPriQueue = new PriorityQueue<>(comSortByKey);
		
		for (int i = 0; i < vertexNum; i++) {
			vertex = vertexs.get(i);
			if (vertex.equals(sVertex)) {
				vertex.setKey(0);
			}
			else {
				vertex.setKey(MAX_VALUE);
			}
			vertex.setPreNode(NIL);
			minKeyPriQueue.offer(vertex);
		}
		
		while (!minKeyPriQueue.isEmpty()) {
			
			vertex = minKeyPriQueue.poll();
			index = vertex.getIndex();
			
			for (int i = 0; i < vertexNum; i++) {
				edge = adj[index][i];
				tVertex = edge.geteVertex();
				
				if (tVertex != null && minKeyPriQueue.contains(tVertex) && edge.getWeight() < tVertex.getKey()) {
					tVertex.setKey(edge.getWeight());
					tVertex.setPreNode(vertex);
				}
			}
			
			if (!vertex.equals(sVertex)) {
				mst.add(adj[vertex.getIndex()][vertex.getPreNode().getIndex()]);
			}
			minKeyPriQueue = rebuildePriQue(minKeyPriQueue);
		}
		
		return mst;
	}
	
	private PriorityQueue<Vertex> rebuildePriQue(PriorityQueue<Vertex> minKeyPriQueue) {
		
		PriorityQueue<Vertex> temp = new PriorityQueue<>(comSortByKey);
		Iterator<Vertex> iterator = minKeyPriQueue.iterator();
		while (iterator.hasNext()) {
			temp.add(iterator.next());
		}
		return temp;
		
	}
	
	private Comparator<Vertex> comSortByKey = new Comparator<Vertex>() {

		@Override
		public int compare(Vertex o1, Vertex o2) {
			return o1.getKey() - o2.getKey();
		}
		
	};
	
	public void dijkstra (Vertex sVertex) {
		
		Edge edge;
		Vertex vertex;
		PriorityQueue<Vertex> minDistQueue = new PriorityQueue<>(comSortByDist);
		initSingleSource(sVertex, minDistQueue);
		
		while (!minDistQueue.isEmpty()) {
			
			vertex = minDistQueue.poll();
			for (int i = 0; i < vertexNum; i++) {
				
				edge = adj[vertex.getIndex()][i];
				if (edge.getWeight()!= MAX_VALUE) {
					
					relax(vertex, edge.geteVertex(), edge);
				}
			}
		}
	}
	
	private void initSingleSource(Vertex sVertex, Queue<Vertex> minDistQueue) {
		
		Vertex vertex;
		for (int i = 0; i < vertexNum; i++) {
			vertex = vertexs.get(i);
			if (vertex.equals(sVertex)) {
				vertex.setDistFromS(0);
			}
			else {
				vertex.setDistFromS(MAX_VALUE);
			}
			vertex.setPreNode(NIL);
			minDistQueue.offer(vertex);
		}
	}
	
	private void relax(Vertex aVertex, Vertex bVertex, Edge edge) {
		
		int dist = aVertex.getDistFromS() + edge.getWeight();
		if (bVertex.getDistFromS() > dist) {
			bVertex.setDistFromS(dist);
			bVertex.setPreNode(aVertex);
		}
	}
	
	private Comparator<Vertex> comSortByDist = new Comparator<Vertex>() {

		@Override
		public int compare(Vertex o1, Vertex o2) {
			return o1.getDistFromS() - o2.getDistFromS();
		}
	};
	
	public int[][] floyd() {
		
		int dist;
		int [][] distMatix = new int[vertexNum][vertexNum];
		preVerTree = new Vertex[vertexNum][vertexNum];
		
		for (int i = 0; i < vertexNum; i++) {
			for (int j = 0; j < vertexNum; j++) {
				distMatix[i][j] = adj[i][j].getWeight();
				if (i != j && adj[i][j].getWeight() != MAX_VALUE) {
					preVerTree[i][j] = vertexs.get(i);
				}
				else {
					preVerTree[i][j] = NIL;
				}
			}
		}
		
		for (int k = 0; k < vertexNum; k ++) {
			for (int i = 0; i < vertexNum; i++) {
				for (int j = 0; j < vertexNum; j++) {
	
					dist = distMatix[i][k] + distMatix[k][j];
					if (i != j && distMatix[i][j] > dist) {
						distMatix[i][j] = dist;
						preVerTree[i][j] = preVerTree[k][j];
					}
				}
			}
		}
		
		return distMatix;
	}
	
	public void printShortestPreVer() {
		
		System.out.println();
		for (int i = 0 ; i < vertexNum; i++) {
			
			System.out.print("\t" + vertexs.get(i).getLabel());
		}
		System.out.println();
		
		for (int i = 0; i < vertexNum; i++) {
			
			System.out.print(vertexs.get(i).getLabel());
			for (int j = 0; j < vertexNum; j++) {
				
				if (preVerTree[i][j] == NIL) {
					System.out.print("\tZ");
				}
				else {
					System.out.print("\t" + preVerTree[i][j]);
				}
			}
			System.out.println();
		}
	}
	
	public void printShortestPath(Vertex sVertex, Vertex eVertex) {
		
		if (sVertex == eVertex) {
			System.out.print("->" + sVertex);
		}
		else if (preVerTree[sVertex.getIndex()][eVertex.getIndex()] == NIL){
			System.out.println("No path from " + sVertex + " to " + eVertex);
		}
		else {
			printShortestPath(sVertex, preVerTree[sVertex.getIndex()][eVertex.getIndex()]);
			System.out.print("->" + eVertex);
		}
	}
	
	public static void main(String[] args) {
		   
		Iterator<Edge> iterator;
		AdjMatrixGraph graph = new AdjMatrixGraph(true, 5, 9);
		graph.createGraph();
		graph.printGraph();
//		graph.BFS(graph.getVertexByLabel("s"));
//		graph.printPath(graph.getVertexByLabel("s"), graph.getVertexByLabel("u"));
//		graph.DFS();
//		iterator = graph.MstPrim(new Vertex("a")).iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}	
//		
//		graph.dijkstra(new Vertex("t"));
		graph.floyd();
		graph.printShortestPath(graph.getVertexByLabel("a"), graph.getVertexByLabel("e"));
	}
}
