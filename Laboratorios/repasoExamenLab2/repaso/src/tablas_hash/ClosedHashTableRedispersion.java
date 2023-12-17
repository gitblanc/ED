package tablas_hash;

/**
 * @author Profesores ED
 * @version 2023-24 distribuible
 *
 */
public class ClosedHashTableRedispersion<T> extends AbstractHash<T> {
// IMPORTANTE
//	No cambiar el nombre ni visibilidad de los atributos protected

	protected HashNode<T> tabla[];

	protected int hashSize; // tamaño de la tabla, debe ser un numero primo
	protected int numElems; // numero de elementos en la tabla en cada momento.

	public static final int LINEAL = 0; // Tipo de exploracion en caso de colision, por defecto sera LINEAL
	public static final int CUADRATICA = 1;
	public static final int DOBLEHASH = 2;

	protected int exploracion; // exploracion que se realizara en caso de colision (LINEAL por defecto)

	private static final double MAX_LF = 0.5;
	private static final double MIN_LF = 0.16;

	private double maxlf;
	private double minlf;

	/**
	 * Constructor para fijar el tamano al numero primo >= que el parametro y el
	 * tipo de exploración al indicado el tipo de exploracion(LINEAL=0,
	 * CUADRATICA=1, ...), si invalido LINEAL
	 */
	@SuppressWarnings("unchecked")
	public ClosedHashTableRedispersion(int tam, int expl) {
		hashSize = nextPrimeNumber(tam);// Establece un tamaño valido si tam no es primo

		tabla = (HashNode<T>[]) new HashNode[hashSize]; // Crea el array de HashNode's
		// Completar lo que falta...

		this.numElems = 0;
		if (expl == LINEAL || expl == CUADRATICA || expl == DOBLEHASH)
			this.exploracion = expl;
		else
			this.exploracion = LINEAL;
		for (int i = 0; i < hashSize; i++)
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
	public ClosedHashTableRedispersion(int tam, double fcUP, double fcDOWN, int expl) { // Para la segunda clase
		this(tam, expl);
		this.maxlf = fcUP;
		this.minlf = fcDOWN;
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
			throw new NullPointerException("Elemento es null");
		if (getSize() == getNumOfElems())
			return false;

		int intento = 0;
		int pos = fDispersionClosed(elem, intento);
		while (this.tabla[pos].getStatus() == HashNode.LLENO && intento < getSize()) {
			pos = fDispersionClosed(elem, intento += 1);
		}
		if (pos == getSize())
			return false;
		this.tabla[pos].setInfo(elem);
		this.numElems += 1;
		reDispersion();
		return true;
	}

	private int fDispersionClosed(T elem, int intento) {
		int clave = fHash(elem);
		if (this.exploracion == LINEAL) {
			return (clave + intento) % getSize();
		} else if (this.exploracion == CUADRATICA) {
			return (clave + intento * intento) % getSize();
		} else {
			int primoAntecesor = previousPrimeNumber(getSize() - 1);
			return (clave + (intento * calculaSalto(primoAntecesor, clave))) % getSize();
		}
	}

	private int calculaSalto(int primoAntecesor, int clave) {
		return (primoAntecesor - clave) % primoAntecesor;
	}

	@Override
	public T find(T elem) {
		if (elem == null || getNumOfElems() == 0)
			return null;

		int pos = fHash(elem);
		int intento = 0;

		while (((this.tabla[pos].getStatus() == HashNode.LLENO && this.tabla[pos].getInfo() != elem)
				|| this.tabla[pos].getStatus() == HashNode.BORRADO) && intento < getSize()) {
			pos = fDispersionClosed(elem, intento += 1);
		}
		if (intento >= getSize())
			return null;

		return this.tabla[pos].getInfo();
	}

	@Override
	public boolean remove(T elem) {
		if (elem == null)
			throw new NullPointerException("El elemento es nulo");
		if (getNumOfElems() == 0 || find(elem) == null)
			return false;

		int pos = fHash(elem);
		int intento = 0;

		while ((this.tabla[pos].getStatus() == HashNode.LLENO && this.tabla[pos].getInfo() != elem)
				|| this.tabla[pos].getStatus() == HashNode.BORRADO) {
			pos = fDispersionClosed(elem, intento += 1);
		}
		this.tabla[pos].remove();
		this.numElems -= 1;
		inverseReDispersion();
		return true;

	}

	@SuppressWarnings("unchecked")
	@Override
	protected boolean reDispersion() { // Para la segunda clase
		double fc = calculateFc();
		if (fc > MAX_LF) {
			// Crear una nueva tabla y hacer una copia
			HashNode<T> aux[] = this.tabla;
			int newSize = nextPrimeNumber(getSize() * 2);
			this.tabla = (HashNode<T>[]) new HashNode[newSize];
			this.numElems = 0;
			this.hashSize = newSize;

			// Inicializamos la nueva tabla
			for (int i = 0; i < getSize(); i++)
				this.tabla[i] = new HashNode<>();

			// Si hay un elemento con estado LLENO, lo añadimos a la nueva tabla
			for (int i = 0; i < aux.length; i++) {
				if (aux[i].getStatus() == HashNode.LLENO)
					add(aux[i].getInfo());
			}

			return true;
		}

		return false;
	}

	private double calculateFc() {
		return (double) getNumOfElems() / getSize();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected boolean inverseReDispersion() { // Para la segunda clase
		double fc = calculateFc();

		if (fc < MIN_LF) {
			HashNode<T> aux[] = this.tabla;
			int newSize = previousPrimeNumber(getSize() / 2);
			this.tabla = (HashNode<T>[]) new HashNode[newSize];
			this.numElems = 0;
			this.hashSize = newSize;

			for (int i = 0; i < getSize(); i++)
				this.tabla[i] = new HashNode<>();

			for (int i = 0; i < aux.length; i++) {
				if (aux[i].getStatus() == HashNode.LLENO)
					add(aux[i].getInfo());
			}

			return true;
		}
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

	public double getMaxlf() {
		return maxlf;
	}

	public void setMaxlf(double maxlf) {
		this.maxlf = maxlf;
	}

	public double getMinlf() {
		return minlf;
	}

	public void setMinlf(double minlf) {
		this.minlf = minlf;
	}
}