package tablas_hash;

/**
 * @author Profesores ED
 * @version 2023-24 distribuible
 *
 */
public class ClosedHashTable<T> extends AbstractHash<T> {
	// IMPORTANTE
	// No cambiar el nombre ni visibilidad de los atributos protected

	protected HashNode<T> tabla[];

	protected int hashSize; // tamaño de la tabla, debe ser un numero primo
	protected int numElems; // numero de elementos en la tabla en cada momento.

	public static final int LINEAL = 0; // Tipo de exploracion en caso de colision, por defecto sera LINEAL
	public static final int CUADRATICA = 1;
	public static final int DOBLEHASH = 2;

	protected int exploracion; // exploracion que se realizara en caso de colision (LINEAL por defecto)

	/**
	 * Constructor para fijar el tamano al numero primo >= que el parametro y el
	 * tipo de exploración al indicado el tipo de exploracion(LINEAL=0,
	 * CUADRATICA=1, ...), si invalido LINEAL
	 */
	@SuppressWarnings("unchecked")
	public ClosedHashTable(int tam, int expl) {
		// Establece un tamaño valido si tam no es primo
		this.hashSize = nextPrimeNumber(tam);
		// Crea el array de HashNode's
		this.tabla = (HashNode<T>[]) new HashNode[getSize()];
		// No hay elementos
		this.numElems = 0;
		// Si la exploración pasada no es una definida, se pone como lineal
		if (expl == LINEAL || expl == CUADRATICA || expl == DOBLEHASH)
			this.exploracion = expl;
		else
			this.exploracion = LINEAL;
		// Para la redispersión
		for (int i = 0; i < getSize(); i++)
			this.tabla[i] = new HashNode<>();
	}

	/**
	 * Constructor para fijar el tamaño al numero primo >= que el parametro Se le
	 * pasa el tamaño de la table Hash, si no es un numero primo lo ajusta al primo
	 * superior el factor de carga limite, por encima del cual hay que redispersar
	 * (directa) el factor de carga limite, por debajo del cual hay que redispersar
	 * (inversa) el tipo de exploracion(LINEAL=0, CUADRATICA=1, ...), si invalido
	 * LINEAL
	 */
	public ClosedHashTable(int tam, double fcUP, double fcDOWN, int expl) { // Para la segunda clase
		// Completar lo que falta...

	}

	@Override
	public int getNumOfElems() {
		return this.numElems;
	}

	@Override
	public int getSize() {
		return this.hashSize;
	}

	@Override
	public boolean add(T elem) {
		if (elem == null)
			throw new NullPointerException("El elemento es nulo");
		if (getSize() == getNumOfElems())// está llena
			return false;
		int intento = 0;
		int pos = fDispersionClosed(elem, intento);
		while (tabla[pos].getStatus() == HashNode.LLENO && intento < getSize()) {
			intento += 1;
			pos = fDispersionClosed(elem, intento);
		}
		if (pos == getSize())
			return false;
		this.tabla[pos].setInfo(elem);
		this.numElems++;
		return true;
	}

	// Devuelve una posición en la tabla hash
	protected int fDispersionClosed(T info, int intentos) {
		int clave = fHash(info);
		switch (this.exploracion) {
		case CUADRATICA:
			return (clave + intentos * intentos) % getSize();
		case DOBLEHASH:
			int primoAntecesor = previousPrimeNumber(getSize() - 1);
			return (clave + (intentos * calculaSalto(primoAntecesor, clave))) % getSize();
		case LINEAL:
		default:
			return (clave + intentos) % getSize();
		}
	}

	/*
	 * Método privado que actúa como función de cálculo de salto para la dispersión
	 * doble
	 */
	private int calculaSalto(int primoAntecesor, int clave) {
		return (primoAntecesor - clave) % primoAntecesor;
	}

	/*
	 * Método que buscar un elemento en la tabla hash Si no existe, devuelve null
	 */
	@Override
	public T find(T elem) {
		if (elem == null)
			return null;

		int pos = fHash(elem);
		int intentos = 0;
		// Si el elemento de la posición está ocupado y no es el que buscamos o está
		// borrado, sigue buscando
		while (((this.tabla[pos].getStatus() == HashNode.LLENO && !this.tabla[pos].getInfo().equals(elem))
				|| this.tabla[pos].getStatus() == HashNode.BORRADO) && intentos < getSize()) {
			intentos += 1;
			pos = fDispersionClosed(elem, intentos);
		}
		if (intentos >= getSize())
			return null;
		else
			return this.tabla[pos].getInfo();
	}

	/*
	 * Método que elimina un elemento pasado Si no existe devuelve false
	 */
	@Override
	public boolean remove(T elem) {
		if (elem == null)
			throw new NullPointerException("El elemento es nulo");
		if (find(elem) == null)
			return false;

		int pos = fHash(elem);
		int intentos = 0;

		while ((this.tabla[pos].getStatus() == HashNode.LLENO && !this.tabla[pos].getInfo().equals(elem))
				|| this.tabla[pos].getStatus() == HashNode.BORRADO) {
			pos = fDispersionClosed(elem, intentos);
			intentos++;
		}
		this.tabla[pos].remove();
		this.numElems -= 1;
		return true;

	}

	@Override
	protected boolean reDispersion() { // Para la segunda clase
		// VACIO
		return false;
	}

	@Override
	protected boolean inverseReDispersion() { // Para la segunda clase
		// VACIO
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		for (int i = 0; i < getSize(); i++) {
			// cadena.append(associativeArray[i]);
			cadena.append(this.tabla[i]);
			cadena.append(";");
		}
		cadena.append("[Size: ");
		cadena.append(getSize());
		cadena.append(" Num.Elems.: ");
		cadena.append(getNumOfElems());
		cadena.append("]");
		return cadena.toString();
	}
}
