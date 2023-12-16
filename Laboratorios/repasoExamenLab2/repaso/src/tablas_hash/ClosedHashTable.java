package p4Hash;


/**
 * @author Profesores ED
 * @version 2023-24 distribuible
 *
 */
public class ClosedHashTable<T> extends AbstractHash<T> {
// IMPORTANTE
//	No cambiar el nombre ni visibilidad de los atributos protected
	
	protected HashNode<T> tabla[]; 

	protected int hashSize;	// tamaño de la tabla, debe ser un numero primo
	protected int numElems;	// numero de elementos en la tabla en cada momento.

	public static final int LINEAL = 0;	// Tipo de exploracion en caso de colision, por defecto sera LINEAL
	public static final int CUADRATICA = 1;
	public static final int DOBLEHASH = 2;

	protected int exploracion; //exploracion que se realizara en caso de colision (LINEAL por defecto)

	/**
	 * Constructor para fijar el tamano al numero primo >= que el parametro y el tipo de exploración al indicado
	 * el tipo de exploracion(LINEAL=0, CUADRATICA=1, ...), si invalido LINEAL
	 */
	@SuppressWarnings("unchecked")
	public ClosedHashTable(int tam, int expl) {
		hashSize=nextPrimeNumber(tam);// Establece un tamaño valido si tam no es primo
		
		tabla = (HashNode<T>[]) new HashNode[hashSize]; // Crea el array de HashNode's
		//			Completar lo que falta...

	}

	/**
	 * Constructor para fijar el tamaño al numero primo >= que el parametro
	 * Se le pasa el tamaño de la table Hash, si no es un numero primo lo ajusta al primo superior
	 * el factor de carga limite, por encima del cual hay que redispersar (directa)
	 * el factor de carga limite, por debajo del cual hay que redispersar (inversa)
	 * el tipo de exploracion(LINEAL=0, CUADRATICA=1, ...), si invalido LINEAL
	 */
	public ClosedHashTable(int tam, double fcUP, double fcDOWN, int expl) { // Para la segunda clase
		//			Completar lo que falta...

	}

	@Override
	public int getNumOfElems() {
		//			Completar lo que falta...
	}

	@Override
	public int getSize() {
		//			Completar lo que falta...
	}

	@Override
	public boolean add(T elem) {
	//			Completar lo que falta...
	}

	@Override
	public T find(T elem) {
	//			Completar lo que falta...
	}

	@Override
	public boolean remove(T elem) {
	//			Completar lo que falta...
	}

	@SuppressWarnings("unchecked")
	@Override
	protected boolean reDispersion () { // Para la segunda clase
	//			Completar lo que falta...
	}

	@SuppressWarnings("unchecked")
	@Override
	protected boolean inverseReDispersion() { // Para la segunda clase
	//			Completar lo que falta...
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString (){
		StringBuilder cadena=new StringBuilder();
		for (int i=0;i< getSize();i++){
			cadena.append(associativeArray[i]);
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
