/**
 * 
 */
package main;

import java.text.DecimalFormat;

import exceptions.ElementNotPresentException;
import exceptions.FullStructureException;

/**
 * Clase Grafo Genérica
 * 
 * @author Eduardo Blanco Bielsa - UO285176
 *
 * @param <T>
 */
public class Grafos<T> {
	private double[][] weights;// Pesos de las arista
	private boolean[][] edges;// Matriz de aristas
	private T[] nodes;// Nodos: Lista de nodos
	private int size; // Tamaño del grafo

	// Para floyd
	private double[][] A; // matriz de costes
	private int[][] P; // matriz de rutas
	private String cadena;// cadena para el path

	/**
	 * Constructor que declara las matrices de aristas, pesos y el vector de nodos
	 * 
	 * @param dimension
	 */
	@SuppressWarnings("unchecked")
	public Grafos(int dimension) {
		this.size = 0;
		this.nodes = (T[]) new Object[dimension];
		this.weights = new double[dimension][dimension];
		this.edges = new boolean[dimension][dimension];
	}

	/**
	 * Devuelve el size
	 * 
	 * @return size
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Método que devuelve la posición de un nodo en el grafo
	 * 
	 * @param node
	 * @return int
	 */
	public int getNode(T node) {
		if (node == null || this.size == 0)
			return -1;

		int i = 0;
		for (T n : nodes) {
			if (n != null && n.equals(node))
				return i;
			i++;
		}
		return -1;
	}

	/**
	 * Método que devuelve si existe un nodo en el grafo
	 * 
	 * @param node
	 * @return True/False
	 */
	public boolean existsNode(T node) {
		return getNode(node) >= 0;
	}

	/**
	 * Método que añade un nodo al grafo
	 * 
	 * @param node
	 * @return True/False
	 */
	public boolean addNode(T node) {
		if (node == null)
			throw new NullPointerException("El nodo es nulo");

		if (existsNode(node))
			return false;

		if (getSize() == this.nodes.length)
			throw new FullStructureException("El nodo no cabe");

		this.nodes[getSize()] = node;// Inserta el nodo

		for (int i = 0; i < getSize(); i++) {
			this.edges[i][getSize()] = false;
			this.edges[getSize()][i] = false;
			this.weights[i][getSize()] = 0.0;
			this.weights[getSize()][i] = 0.0;
		}

		size++;

		return true;
	}

	/**
	 * Método que elimina un nodo
	 * 
	 * @param node
	 * @return True/false
	 */
	public boolean removeNode(T node) {
		if (node == null)
			throw new NullPointerException("El nodo es nulo");

		if (!existsNode(node))
			return false;

		int posDel = getNode(node);

		for (int i = 0; i < getSize(); i++) {
			this.edges[i][posDel] = this.edges[getSize() - 1][posDel];
			this.edges[posDel][i] = this.edges[posDel][getSize() - 1];
			this.weights[i][posDel] = this.weights[getSize() - 1][posDel];
			this.weights[posDel][i] = this.weights[posDel][getSize() - 1];
		}

		// Codo
		this.edges[posDel][posDel] = this.edges[getSize() - 1][getSize() - 1];
		this.weights[posDel][posDel] = this.weights[getSize() - 1][getSize() - 1];

		// Eliminas el nodo
		this.nodes[posDel] = this.nodes[getSize() - 1];

		size--;
		return true;

	}

	/**
	 * Método que añade una arista
	 * 
	 * @param source
	 * @param target
	 * @param weight
	 * @return true/false
	 */
	public boolean addEdge(T source, T target, double weight) {
		if (weight <= 0)
			throw new IllegalArgumentException("Peso inválido");

		if (!existsNode(source) || !existsNode(target))
			throw new ElementNotPresentException("Nodo no presente");

		if (existsEdge(source, target))
			return false;

		int sourcePos = getNode(source);
		int targetPos = getNode(target);

		this.edges[sourcePos][targetPos] = true;
		this.weights[sourcePos][targetPos] = weight;
		return true;
	}

	/**
	 * Método que comprueba si existe una arista entre dos nodos
	 * 
	 * @param source
	 * @param target
	 * @return true/false
	 */
	public boolean existsEdge(T source, T target) {
		if (!existsNode(source) || !existsNode(target))
			return false;

		int sourcePos = getNode(source);
		int targetPos = getNode(target);

		return this.edges[sourcePos][targetPos];
	}

	/**
	 * Método que devuelve una arista entre dos nodos si existe
	 * 
	 * @param source
	 * @param target
	 * @return el peso de la arista
	 */
	public double getEdge(T source, T target) {
		if (!existsNode(source) || !existsNode(target))
			throw new ElementNotPresentException("Nodo no presente");

		int sourcePos = getNode(source);
		int targetPos = getNode(target);

		if (!existsEdge(source, target))
			return -1;
		return this.weights[sourcePos][targetPos];
	}

	/**
	 * Método que elimina una arista
	 * 
	 * @param source
	 * @param target
	 * @return true/false
	 */
	public boolean removeEdge(T source, T target) {
		if (!existsNode(source) || !existsNode(target))
			throw new ElementNotPresentException("Nodo no presente");

		if (!existsEdge(source, target))
			return false;

		int sourcePos = getNode(target);
		int targetPos = getNode(target);

		this.edges[sourcePos][targetPos] = false;
		this.weights[sourcePos][targetPos] = 0.0;

		return true;

	}

	/**
	 * Método que devuelve la matriz de pesos
	 * 
	 * @return weights
	 */
	public double[][] getWeights() {
		return this.weights;
	}

	/**
	 * Método que devuelve la matriz de aristas
	 * 
	 * @return edges
	 */
	public boolean[][] getEdges() {
		return this.edges;
	}

	/**
	 * Método que resuelve el algoritmo de Dijkstra
	 * 
	 * @param i
	 * @return dijkstra
	 */
	public DijkstraDataClass dijkstra(T source) {
		if (source == null || !existsNode(source))
			return null; // no aplica

		boolean[] s = new boolean[size]; // nodods visitados
		DijkstraDataClass dijkstra = new DijkstraDataClass(size, 1);

		dijkstra.setpDijkstra(initializeP()); // inicializamos la matriz de caminos
		dijkstra.setdDijkstra(initializeD(source)); // inicializamos la matriz de costes

		double[] D = dijkstra.getdDijkstra();
		int[] P = dijkstra.getpDijkstra();

		// obtenemos el pivote para la siguiente iteración de dijkstra
		int w = getPivote(dijkstra.getdDijkstra(), s);

		while (w != -1) { // mientras que aún queden nodos por visitar
			s[w] = true;
			for (int m = 0; m < size; m++) {
				// Si no está visitado y existe una arista
				if (!s[m] && existsEdge(nodes[w], nodes[m])) {
					if ((D[w] + weights[w][m]) < D[m]) {
						D[m] = D[w] + weights[w][m];
						P[m] = w;
					}
				}
			}
			w = getPivote(D, s);
		}

		return dijkstra;

	}

	/**
	 * Método que devuelve la posición del nodo pivote (el nodo con menor coste que
	 * no haya sido visitado)
	 * 
	 * @param d
	 * @param s
	 * @return pos o -1
	 */
	public int getPivote(double[] d, boolean[] s) {
		double costeMinimo = Double.POSITIVE_INFINITY;
		int pos = -1;
		for (int i = 0; i < d.length; i++) {
			if (d[i] < costeMinimo && !s[i]) {
				costeMinimo = d[i];
				pos = i;
			}
		}
		return pos;
	}

	/**
	 * Inicializa la matriz de caminos (todos a -1)
	 * 
	 * @param dijkstra
	 */
	public int[] initializeP() {
		int[] p = new int[getSize()];
		for (int i = 0; i < p.length; i++)
			p[i] = -1;

		return p;
	}

	/**
	 * Inicializa los costes de la matriz
	 * 
	 * @param dijkstra
	 * @param source
	 */
	public double[] initializeD(T source) {
		double d[] = new double[size];
		for (int i = 0; i < d.length; i++) {
			if (nodes[i].equals(source))
				d[i] = 0;
			else if (existsEdge(source, this.nodes[i]))
				d[i] = getEdge(source, this.nodes[i]);
			else
				d[i] = Double.POSITIVE_INFINITY;
		}

		return d;
	}

	/**
	 * Método que implementa el algoritmo de floyd
	 * 
	 * @return true/false
	 */
	public boolean floyd() {
		// Si no hay nodos
		if (getSize() == 0)
			return false;

		// Inicializamos la matriz de costes A y la de rutas P
		initializePFloyd();
		initializeAFloyd();

		for (int pivote = 0; pivote < getSize(); pivote++) {
			for (int source = 0; source < getSize(); source++) {
				for (int target = 0; target < getSize(); target++) {
					// Si el cose de usar un nodo intermedio es menor que ir directamente
					if (getFloydA()[source][pivote] + getFloydA()[pivote][target] < getFloydA()[source][target]) {
						this.A[source][target] = getFloydA()[source][pivote] + getFloydA()[pivote][target];
						this.P[source][target] = pivote;
					}
				}
			}
		}

		return true;
	}

	/**
	 * Método que inicializa la matriz P de floyd
	 */
	private void initializeAFloyd() {
		this.A = new double[getSize()][getSize()];

		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (i == j)
					this.A[i][j] = 0;
				else if (existsEdge(this.nodes[i], this.nodes[j]))
					this.A[i][j] = this.weights[i][j];
				else
					this.A[i][j] = Double.POSITIVE_INFINITY;
			}
		}
	}

	/**
	 * Método que inicializa la matriz A de floyd
	 */
	private void initializePFloyd() {
		this.P = new int[getSize()][getSize()];

		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				this.P[i][j] = -1;
			}
		}
	}

	/**
	 * Devuelve la matriz A de Floyd
	 * 
	 * @return A
	 */
	public double[][] getFloydA() {
		return this.A;
	}

	/**
	 * Devuelve el coste del camino de coste mínimo entre origen y destino según
	 * Floyd
	 * 
	 * @param nodoOrigen
	 * @param nodoDestino
	 * @return double
	 */
	public double minCostPath(T nodoOrigen, T nodoDestino) {
		// Si no existe alguno de los nodos, lanza excepción
		if (!existsNode(nodoOrigen) || !existsNode(nodoDestino))
			throw new ElementNotPresentException("Uno de los nodos no existe");

		// Si la matriz de costes no existe, la crea llamando a Floyd
		if (getFloydA() == null)
			floyd();

		return getFloydA()[getNode(nodoOrigen)][getNode(nodoDestino)];
	}

	/**
	 * Indica el camino entre los nodos que se le pasan como parámetros con el
	 * formato siguiente:
	 * Origen<tab>(coste0)<tabr>Inter1<tab>(coste1)<tabulador>InterN<
	 * tab>(costeN)<tab>Destino<tab> – Si no hay camino:
	 * Origen<tab>(Infinity)<tab>Destino<tab>
	 * 
	 * Si Origen y Destino coinciden: Origen<tab>
	 * 
	 * Si no existen los 2 nodos devuelve una cadena vacía
	 * 
	 * @param origen
	 * @param destino
	 * @return cadena
	 */
	public String path(T origen, T destino) {
		this.cadena = "";

		if (!existsNode(origen) && !existsNode(destino))
			return this.cadena;
		else if (getNode(origen) == getNode(destino))
			this.cadena += origen.toString() + "\t";
		else if (minCostPath(origen, destino) == Double.POSITIVE_INFINITY)
			this.cadena += origen.toString() + "\t(" + Double.POSITIVE_INFINITY + ")\t" + destino.toString();
		else {
			this.cadena += origen.toString() + recursivePath(origen, destino) + destino.toString();
		}

		return this.cadena;
	}

	/**
	 * Método que ayuda a formar el path de forma recursiva
	 * 
	 * @param origen
	 * @param destino
	 * @return cadena
	 */
	private String recursivePath(T origen, T destino) {
		String cadena = "";
		if (this.P == null)
			floyd();
		int k = this.P[getNode(origen)][getNode(destino)];
		if (k != -1) {// en nuestro contexto significa que o bien existe camino directo o bien no
						// existe camino
			cadena += recursivePath(origen, this.nodes[k]) + this.nodes[k] + recursivePath(this.nodes[k], destino);
		} else {
			cadena += "\t(" + minCostPath(origen, destino) + ")\t";
		}

		return cadena;
	}

	/**
	 * Lanza el recorrido en profundidad de un grafo desde un nodo determinado
	 * 
	 * Al recorrer cada nodo añade el toString del nodo y un tabulador
	 * 
	 * Si no existe el nodo devuelve una cadena vacía
	 * 
	 * @param source
	 * @return cadena
	 */
	public String recorridoProfundidad(T source) {
		this.cadena = "";
		if (!existsNode(source)) {
			return cadena;
		}
		boolean[] visited = new boolean[getSize()];
		recursivoProfundidad(source, visited);
		return this.cadena;
	}

	/**
	 * Método auxiliar para calcular el recorrido en profundidad desde un nodo dado
	 * 
	 * @param source
	 * @param visited
	 */
	private void recursivoProfundidad(T source, boolean[] visited) {
		visited[getNode(source)] = true;
		this.cadena += source.toString() + "\t";
		// for each node w accessible from v do
		for (int i = 0; i < this.size; i++) {
			if (!visited[i] && existsEdge(source, this.nodes[i]))
				recursivoProfundidad(nodes[i], visited);
		}
	}

	/**
	 * ToString proporcionado
	 */
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.##");
		String cadena = "";
		cadena += "NODOS\n";
		for (int i = 0; i < size; i++) {
			cadena += nodes[i].toString() + "\t";
		}
		cadena += "\n\nARISTAS\n";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (edges[i][j])
					cadena += "T\t";
				else
					cadena += "F\t";
			}
			cadena += "\n";
		}
		cadena += "\nPESOS\n";

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				cadena += (edges[i][j] ? df.format(weights[i][j]) : "-") + "\t";
			}
			cadena += "\n";
		}
		return cadena;
	}
}
