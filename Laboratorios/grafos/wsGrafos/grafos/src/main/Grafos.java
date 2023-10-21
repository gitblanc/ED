/**
 * 
 */
package main;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Queue;

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

		int sourcePos = getNode(source);
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

	// EJERCICIOS EXTRA PARA EXAMEN

	public double excentricidad(T node) {
		if (node == null)
			throw new NullPointerException("el nodo no puede ser null");
		if (getNode(node) == -1)
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

	// Número de arcos que llegan a un nodo
	public int calcularGradoEntrada(T node) {
		int grado = 0;
		int posNode = getNode(node);
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (j == posNode) {
					if (this.edges[i][j])
						grado += 1;
				}
			}
		}

		return grado;
	}

	// Número de arcos que salen de un nodo
	public int calcularGradoSalida(T node) {
		int grado = 0;
		int posNode = getNode(node);
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (i == posNode) {
					if (this.edges[i][j])
						grado += 1;
				}
			}
		}

		return grado;
	}

	public int calcularGradoNodo(T node) {
		return calcularGradoEntrada(node) + calcularGradoSalida(node);
	}

	// Sin entradas
	public boolean esNodoFuente(T node) {
		int posNode = getNode(node);

		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (j == posNode) {
					if (this.edges[i][j])
						return false;
				}
			}
		}

		return true;
	}

	// Sin salidas
	public boolean esNodoSumidero(T node) {
		int posNode = getNode(node);

		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (i == posNode) {
					if (this.edges[i][j])
						return false;
				}
			}
		}

		return true;
	}

	// Sin entradas ni salidas
	public boolean esNodoAislado(T node) {
		int posNode = getNode(node);

		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (i == posNode) {
					if (this.edges[i][j])
						return false;
				}
				if (j == posNode) {
					if (this.edges[i][j])
						return false;
				}
			}
		}

		return true;
	}

	// La densidad responde a la fórmula 2m/n(n-1) siendo m el número de arcos y n
	// el número de nodos
	// si d = 0, todos los vértices son aislados
	// si d = 1, se trata de un grafo completo
	public double densidadGrafo() {
		int m = calcularNumeroAristas();
		int n = getSize();
		double densidad = (2.0 * m) / (n * (n - 1));
		// Redondear el resultado a 2 decimales
		return Math.round(densidad * 100.0) / 100.0;
	}

	private int calcularNumeroAristas() {
		int cont = 0;
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (this.edges[i][j])
					cont += 1;
			}
		}
		return cont;
	}

	// para saber si el grafo es fuertemente conexo
	public boolean esConexo() {
		floyd();

		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (this.A[i][j] == Double.POSITIVE_INFINITY)
					return false;
			}
		}

		return true;
	}

	public int contarSumideros() {
		int cont = 0;
		for (int i = 0; i < getSize(); i++) {
			if (esNodoSumidero(this.nodes[i]))
				cont += 1;
		}
		return cont;
	}

	public int contarFuentes() {
		int cont = 0;
		for (int i = 0; i < getSize(); i++) {
			if (esNodoFuente(this.nodes[i]))
				cont += 1;
		}
		return cont;
	}

	public int contarAislados() {
		int cont = 0;
		for (int i = 0; i < getSize(); i++) {
			if (esNodoAislado(this.nodes[i]))
				cont += 1;
		}
		return cont;
	}

	// suma de los nodos aislados mas los fuentes
	public int contarInaccesibles() {
		int cont = 0;
		for (int i = 0; i < getSize(); i++) {
			if (esNodoAislado(this.nodes[i]) || esNodoFuente(this.nodes[i]))
				cont += 1;
		}
		return cont;
	}

	// comprueba si el grafo tiene ciclos
	public boolean tieneCiclos() {
		boolean[] visitados = new boolean[getSize()];
		boolean[] enProceso = new boolean[getSize()];

		for (int i = 0; i < getSize(); i++) {
			if (!visitados[i] && tieneCicloUtil(i, visitados, enProceso)) {
				return true;
			}
		}

		return false;
	}

	private boolean tieneCicloUtil(int nodo, boolean[] visitados, boolean[] enProceso) {
		if (enProceso[nodo]) {
			return true; // Ciclo detectado
		}

		if (visitados[nodo]) {
			return false; // Ya se ha verificado este nodo, no hay ciclo
		}

		visitados[nodo] = true;
		enProceso[nodo] = true;

		for (int vecino = 0; vecino < getSize(); vecino++) {
			if (edges[nodo][vecino] && tieneCicloUtil(vecino, visitados, enProceso)) {
				return true;
			}
		}

		enProceso[nodo] = false;
		return false;
	}

	// calcula la longitud mínima entre dos nodos, es decir, el camino con menos
	// pasos entre uno y otro
	public int shortestPathLength(T source, T target) {
		if (!existsNode(source) || !existsNode(target)) {
			return -1; // Al menos uno de los nodos no existe en el grafo
		}

		boolean[] visitados = new boolean[getSize()];
		Queue<T> cola = new LinkedList<>();
		int[] distancias = new int[getSize()];

		int sourceIndex = getNode(source);
		int targetIndex = getNode(target);

		cola.offer(nodes[sourceIndex]);
		visitados[sourceIndex] = true;
		distancias[sourceIndex] = 0;

		while (!cola.isEmpty()) {
			T nodoActual = cola.poll();
			int nodoActualIndex = getNode(nodoActual);

			for (int i = 0; i < getSize(); i++) {
				if (edges[nodoActualIndex][i] && !visitados[i]) {
					cola.offer(nodes[i]);
					visitados[i] = true;
					distancias[i] = distancias[nodoActualIndex] + 1;
					if (i == targetIndex) {
						return distancias[i]; // Se ha encontrado el nodo destino, devuelve la distancia
					}
				}
			}
		}

		return -1; // No hay camino entre los nodos
	}

	public double caminoCosteMinimo(T source, T target) {
		if (!existsNode(source) || !existsNode(target)) {
			throw new ElementNotPresentException("Nodo no presente");
		}

		int sourceIndex = getNode(source);
		int targetIndex = getNode(target);

		// Si la matriz de rutas no ha sido calculada, llamar a floyd() para calcularla
		if (getFloydA() == null) {
			floyd();
		}

		// Si no hay camino entre los nodos, devolver -1
		if (getFloydA()[sourceIndex][targetIndex] == Double.POSITIVE_INFINITY) {
			return -1;
		}

		// Longitud del camino de coste mínimo entre los nodos source y target
		return getFloydA()[sourceIndex][targetIndex];
	}

	// obtenemos el árbol libre abarcador a partir de un nodo
	@SuppressWarnings("unchecked")
	public boolean prim(T source) {
		T[] U = (T[]) new Object[getSize()];
		U[0] = source;

		T[] T = (T[]) new Object[getSize()];

		return true;
	}
}
