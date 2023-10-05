package main;

/**
 * @author Nestor
 * @version 2022-23
 */
public class DijkstraDataClass {

	int nodeIndex;
	double dDijkstra[];// Vector D de Dijkstra (vector de costes)
	int pDijkstra[]; // Vector P de Dijkstra (vector de rutas)

	public DijkstraDataClass(int nNodes, int index) {
		nodeIndex = index;
		dDijkstra = new double[nNodes]; // Vector D de Dijkstra
		pDijkstra = new int[nNodes]; // Vector P de Dijkstra
	}

	public DijkstraDataClass(int nNodes, int index, double[] d, int[] p) {
		this(nNodes, index);
		setdDijkstra(d);
		setpDijkstra(p);
	}
	
	public void initializeP() {
		for(int i = 0; i < pDijkstra.length; i++)
			pDijkstra[i] = -1;
	}
	
	public int getNodeIndex() {
		return nodeIndex;
	}

	public void setNodeIndex(int nodeIndex) {
		this.nodeIndex = nodeIndex;
	}

	public double[] getdDijkstra() {
		return dDijkstra;
	}

	public void setdDijkstra(double[] dDijkstra) {
		this.dDijkstra = dDijkstra;
	}

	public int[] getpDijkstra() {
		return pDijkstra;
	}

	public void setpDijkstra(int[] pDijkstra) {
		this.pDijkstra = pDijkstra;
	}

}
