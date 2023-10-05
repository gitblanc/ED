/**
 * 
 */
package main;

import java.text.DecimalFormat;

import soporte.ElementNotPresentException;
import soporte.FullStructureException;

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
		int[] p = new int[size];
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
