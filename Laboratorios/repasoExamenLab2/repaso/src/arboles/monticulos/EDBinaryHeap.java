package p3Arboles;

/**
 * @author Profesores ED
 * @version 2023-24 distribuible
 */
 public class EDBinaryHeap<T extends Comparable<T>> implements EDPriorityQueue<T>{
	protected T [] elementos;
	protected int numElementos;

	
	public EDBinaryHeap(int numMaxElementos) {
		elementos = (T[]) new Comparable[numMaxElementos];
	}

	@Override
	public boolean add(T info) {
	}

	@Override
	public T poll() {
	}

	@Override
	public boolean remove(T info) {
	}

	@Override
	public boolean isEmpty() {
	}

	@Override
	public void clear() {
	}

	/**  
	 * Devuelve una cadena con la informacion de los elementos que contiene el  
	 * monticulo separados por tabuladores
	*/
	@Override
	public String toString() {
	
	}

	@Override
	public boolean cambiarPrioridad(int pos, T elemento);
    

    /**
     * Realiza una filtrado ascendente de minimos en el monticulo
     * 
     * Se le pasa el indice del elemento a filtrar
     */
    protected void filtradoAscendente(int ) {	}

    /**
     * Realiza una filtrado descendente de minimos en el monticulo
     * 
     * Se le pasa el indice del elemento a filtrar
     */
    protected void filtradoDescendente(int ) {	}
	
	/**
	 * Metodo que calcula y devuelve la posicion del hijo menor del nodo pasado
	 * como parametro.
	 * 
	 * @param pos  La posicion del nodo padre
	 * @return  La posicion del menor de sus hijos o -1 si no existe ninguno
	 */
	private int getMinHijo(int pos) {}

}
