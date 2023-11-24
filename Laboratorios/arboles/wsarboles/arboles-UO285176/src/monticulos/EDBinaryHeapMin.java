package monticulos;

/**
 * @author Eduardo Blanco Bielsa
 * @version 2023-24 distribuible Es un montículo de mínimos
 */
public class EDBinaryHeapMin<T extends Comparable<T>> implements EDPriorityQueue<T> {
	protected T[] monticulo;// vector de elementos de un determinado tamaño
	protected int numElementos;// número de elementos del montículo

	/**
	 * Constructor por defecto
	 * 
	 * @param numMaxElementos
	 */
	@SuppressWarnings("unchecked")
	public EDBinaryHeapMin(int numMaxElementos) {
		monticulo = (T[]) new Comparable[numMaxElementos];
		numElementos = 0;
	}

	/**
	 * Se le pasa el elemento que se quiere insertar en la cola Lanza
	 * NullPointerException si el elemento a insertar es null devuelve true si
	 * consigue insertarlo, false en caso contrario
	 */
	@Override
	public boolean add(T info) {
		if (info == null)
			throw new NullPointerException("Lo que se añade no puede ser nulo");
		else if (this.monticulo.length == getNumElementos())// Si el montículo está lleno
			return false;// devuelve false
		else {
			this.numElementos += 1;
			this.monticulo[getNumElementos() - 1] = info;
			filtradoAscendente(getNumElementos() - 1);
			return true;
		}
	}

	/**
	 * devuelve y elimina el elemento con mayor prioridad (cima del monticulo), o
	 * null si no hay elementos
	 */
	@Override
	public T poll() {
		if (getNumElementos() == 0)
			return null;

		T masPrioritario = this.monticulo[0];
		this.monticulo[0] = this.monticulo[getNumElementos() - 1];
		this.monticulo[getNumElementos() - 1] = null;
		this.numElementos--;
		filtradoDescendente(0);
		return masPrioritario;
	}

	/**
	 * Borra un elemento de la cola Lanza NullPointerException si el elemento a
	 * eliminar es null Se le pasa el elemento que se quiere eliminar de la cola
	 * devuelve true si estaba y lo elimina, false en caso contrario
	 */
	@Override
	public boolean remove(T info) {
		if (info == null)
			throw new NullPointerException("El elemento pasado no puede ser nulo");
		else if (!existsElement(info))
			return false;
		else {
			int posicion = searchElement(info);
			T ultimoElemento = this.monticulo[getNumElementos() - 1];

			this.monticulo[posicion] = this.monticulo[numElementos - 1];
			this.monticulo[numElementos - 1] = null;
			this.numElementos--;
			if (info.compareTo(ultimoElemento) > 0) {
				filtradoAscendente(posicion);
			} else if (info.compareTo(ultimoElemento) < 0) {
				filtradoDescendente(posicion);
			}
			return true;
		}
	}

	private boolean existsElement(T info) {
		for (int i = 0; i < numElementos; i++) {
			if (this.monticulo[i].equals(info)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Indica si no hay ningun elemento
	 */
	@Override
	public boolean isEmpty() {
		return this.numElementos == 0 ? true : false;
	}

	/**
	 * Elimina todos los elementos de la cola
	 */
	@Override
	public void clear() {
		for (int i = 0; i < numElementos; i++) {
			this.monticulo[i] = null;
		}
		this.numElementos = 0;
	}

	/**
	 * Devuelve una cadena con la informacion de los elementos que contiene el
	 * monticulo separados por tabuladores
	 */
	@Override
	public String toString() {
		String cadena = "";
		if (!isEmpty()) {
			for (int i = 0; i < numElementos - 1; i++) {
				cadena += this.monticulo[i] + "\t";
			}
			cadena += this.monticulo[numElementos - 1];
		}
		return cadena;
	}

	/**
	 * Metodo que cambia la prioridad del elemento de la cola que está en pos
	 * 
	 * Si recibe como elemento un null lanza NullPointerExceptio True si cambia la
	 * prioridad False en caso contrario
	 */
	@Override
	public boolean cambiarPrioridad(int pos, T elemento) {
		return false;// TO DO
	}

	/**
	 * Realiza una filtrado ascendente de minimos en el monticulo
	 * 
	 * Se le pasa el indice del elemento a filtrar
	 */
	protected void filtradoAscendente(int index) {
		while (index != 0) {
			int posPadre = Math.abs((index - 1) / 2);
			int posHijo = index;
			T padre = this.monticulo[posPadre];
			T hijo = this.monticulo[posHijo];
			if (padre.compareTo(hijo) > 0) {
				this.monticulo[posPadre] = hijo;
				this.monticulo[posHijo] = padre;
				filtradoAscendente(posPadre);
			}
			break;
		}
	}

	/**
	 * Realiza una filtrado descendente de minimos en el monticulo
	 * 
	 * Se le pasa el indice del elemento a filtrar
	 */
	protected void filtradoDescendente(int posPadre) {
		int minHijo = getMinHijo(posPadre);

		while (minHijo != -1 && this.monticulo[posPadre].compareTo(this.monticulo[minHijo]) > 0) {
			T aux = this.monticulo[minHijo];
			this.monticulo[minHijo] = this.monticulo[posPadre];
			this.monticulo[posPadre] = aux;
			posPadre = minHijo;
			minHijo = getMinHijo(posPadre);
		}
	}

	/**
	 * Metodo que calcula y devuelve la posicion del hijo menor del nodo pasado como
	 * parametro.
	 * 
	 * @param pos La posicion del nodo padre
	 * @return La posicion del menor de sus hijos o -1 si no existe ninguno
	 */
	private int getMinHijo(int index) {
		int posHijoIzq = Math.abs(2 * index + 1);
		int posHijoDer = Math.abs(2 * index + 2);

		if (posHijoIzq < getNumElementos() && posHijoDer < getNumElementos()) {
			T hijoIzq = this.monticulo[posHijoIzq];
			T hijoDer = this.monticulo[posHijoDer];
			if (hijoIzq.compareTo(hijoDer) > 0)
				return posHijoDer;
			else
				return posHijoIzq;
		} else if (posHijoIzq < getNumElementos() && posHijoDer >= getNumElementos()) {
			return posHijoIzq;
		} else
			return -1;
	}

	/**
	 * Devuelve el número de elementos en el montículo
	 * 
	 * @return
	 */
	private int getNumElementos() {
		return this.numElementos;
	}

	/**
	 * Devuelve la posición del elemento a buscar o -1 si no lo encuentra
	 * 
	 * @param elem
	 * @return
	 */
	public int searchElement(T elem) {
		for (int i = 0; i < getNumElementos(); i++) {
			if (this.monticulo[i].compareTo(elem) == 0)
				return i;
		}
		return -1;

	}

}
