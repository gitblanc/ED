package main;

import java.text.DecimalFormat;

/**
 * @author Profesores ED 2023
 * @version 2023-24
 */
public class GraphTG2023<T> {
	/**
	 * Constante infinito
	 */
	protected static final double Inf = Double.POSITIVE_INFINITY;

	/**
	 * Vector de nodos
	 */
	protected T[] nodes; // Vector de nodos
	/**
	 * Matriz de aristas
	 */
	protected boolean[][] edges; // matriz de aristas
	/**
	 * Matriz de pesos
	 */
	protected double[][] weights; // matriz de pesos

	/**
	 * Numero de elementos en un momento dado
	 */
	protected int numNodes; // numero de elementos en un momento dado

	// Para floyd
	private double[][] A; // matriz de costes
	private int[][] P; // matriz de rutas

	/**
	 * 
	 * @param tam Numero maximo de nodos del grafo
	 */
	@SuppressWarnings("unchecked")
	public GraphTG2023(int tam) {
		nodes = (T[]) new Object[tam];
		numNodes = 0;
		edges = new boolean[tam][tam];
		weights = new double[tam][tam];
	}

	public int getNumMaxNodes() {
		return nodes.length;
	}

	protected int getNumNodes() {
		return numNodes;
	}

	protected T[] getNodes() {
		return nodes;
	}

	protected boolean[][] getEdges() {
		return edges;
	}

	protected double[][] getWeights() {
		return weights;
	}

	protected double[][] getWeight() { // para compatibilidad con pruebas ingles
		return getWeights();
	}

	/**
	 * Obtiene el indice de un nodo en el vector de nodos
	 * 
	 * @param node que es el nodo que se busca
	 * @return la posicion del nodo en el vector, -1 si no se encuentra
	 */
	protected int getNodeEx(T node) {
		for (int i = 0; i < numNodes; i++) {
			if (nodes[i].equals(node)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Inserta un nuevo nodo que se le pasa como parametro. Siempre lo inserta, no
	 * se controlan casos en que no lo pueda hacer
	 * 
	 * @param node el nodo que se quiere insertar
	 * @return true siempre
	 */
	public boolean addNodeEx(T node) {
		nodes[numNodes] = node;
		for (int i = 0; i <= numNodes; i++) {
			edges[numNodes][i] = false;
			edges[i][numNodes] = false;
		}
		numNodes++;
		return true;
	}

	/**
	 * Inserta una arista entre dos nodos con el peso indicado Devuelve true siempre
	 * No comprueba nada.
	 * 
	 * @param source     nodo origen
	 * @param target     nodo destino
	 * @param edgeWeight peso de la arista
	 * @return true siempre
	 */
	public boolean addEdgeEx(T source, T target, double edgeWeight) {
		int posOrigen = getNodeEx(source);
		int posDestino = getNodeEx(target);
		edges[posOrigen][posDestino] = true;
		weights[posOrigen][posDestino] = edgeWeight;
		return true;
	}

	/**
	 * Borra la arista del grafo que conecta dos nodos. Siempre la borra sin
	 * comprobar nada
	 * 
	 * @param source Nodo origen de la arista
	 * @param target Nodo destino de la arista
	 * @return true siempre
	 */
	public boolean removeEdgeEx(T source, T target) {
		int posOrigen = getNodeEx(source);
		int posDestino = getNodeEx(target);
		edges[posOrigen][posDestino] = false;
		return true;
	}

	/**
	 * Devuelve el peso de la arista que conecta dos nodos. No comprueba nada...
	 * 
	 * @param source Nodo origen de la arista
	 * @param target Nodo destino de la arista
	 * @return El peso de la arista
	 */
	public double getEdgeEx(T source, T target) {
		int posOrigen = getNodeEx(source);
		int posDestino = getNodeEx(target);
		return weights[posOrigen][posDestino];
	}

	/**
	 * @return Devuelve un String con la informacion del grafo usando StringBuilder
	 */
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.##");
		StringBuilder cadena = new StringBuilder();

		cadena.append("NODES\n");
		for (int i = 0; i < numNodes; i++) {
			cadena.append(nodes[i].toString() + "\t");
		}
		cadena.append("\n\nEDGES\n");
		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < numNodes; j++) {
				if (edges[i][j])
					cadena.append("T\t");
				else
					cadena.append("F\t");
			}
			cadena.append("\n");
		}
		cadena.append("\nWEIGHTS\n");
		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < numNodes; j++) {

				cadena.append((edges[i][j] ? df.format(weights[i][j]) : "-") + "\t");
			}
			cadena.append("\n");
		}

		return cadena.toString();
	}

	// EJERCICIOS EXTRA

	public boolean containsCycles() {
		for (int i = 0; i < this.nodes.length; i++) {
			for (int j = 0; j < this.nodes.length; j++) {
				if (existsEdge(i, j) && existsEdge(j, i))
					return true;
			}
		}
		return false;
	}

	private boolean existsEdge(int i, int j) {
		return getEdgeEx(nodes[i], nodes[j]) == -1;
	}

	public int centroGrafo() {
		if (this.nodes.length == 0)
			return -1;

		floyd();

		int[] maximos = new int[getSize()];
		int maximo = 0;
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (this.A[i][j] > maximo)
					maximo = j;
			}
			maximos[i] = maximo;
		}

		int minimo = (int) (Double.POSITIVE_INFINITY);
		for (int i = 0; i < maximos.length; i++) {
			if (maximos[i] < minimo)
				minimo = maximos[i];
		}

		return minimo;
	}

	public boolean floyd() {
		// Si no hay nodos
		if (this.nodes.length == 0)
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

	public double[][] getFloydA() {
		return this.A;
	}

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

	private int getSize() {
		return this.nodes.length;
	}

	public boolean existsEdge(T source, T target) {
		if (!existsNode(source) || !existsNode(target))
			return false;

		int sourcePos = getNodeEx(source);
		int targetPos = getNodeEx(target);

		return this.edges[sourcePos][targetPos];
	}

	public boolean existsNode(T node) {
		return getNodeEx(node) >= 0;
	}

	public double excentricidad(T node) {
		if (node == null)
			throw new NullPointerException("el nodo no puede ser null");
		if (getNodeEx(node) == -1)
			return -1;

		floyd();

		double[] maximos = new double[getSize()];
		double maximo;
		for (int i = 0; i < getSize(); i++) {
			maximo = 0;
			for (int j = 0; j < getSize(); j++) {
				if (this.A[j][i] > maximo)
					maximo = this.A[j][i];
			}
			maximos[i] = maximo;
		}

		double minimo = Double.POSITIVE_INFINITY;
		for (int i = 0; i < maximos.length; i++) {
			if (maximos[i] < minimo)
				minimo = maximos[i];
		}

		return minimo;
	}

	public double shortestPathLength(T origen, T destino) {
		if (origen == null || destino == null)
			throw new NullPointerException("el nodo no puede ser null");
		if (getNodeEx(origen) == -1 || getNodeEx(destino) == -1)
			return -1;

		//floyd(); esto para hacer costes
		inicializarMatrizAconUnos();

		double longitud = 1;
		int posOrigen = getNodeEx(origen);
		int posDestino = getNodeEx(destino);

		if (this.A[posOrigen][posDestino] == 1)
			return longitud;
		else
			return pathRecursivo(posOrigen, posDestino, longitud);
	}

	private void inicializarMatrizAconUnos() {
		this.A = new double[getSize()][getSize()];
		
		for(int i = 0; i < getSize(); i++) {
			for(int j = 0; j < getSize(); j++) {
				if(existsEdge(i, j))
					this.A[i][j] = 1;
				else
					this.A[i][j] = Double.POSITIVE_INFINITY;
			}
		}
	}

	private double pathRecursivo(int posOrigen, int posDestino, double longitud) {
		if (this.A[posOrigen][posDestino] == 1)
			return longitud + 1;
		else
			return pathRecursivo(this.P[posOrigen][posDestino], posDestino, (longitud + 1));
	}
}
