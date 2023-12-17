package arboles.monticulos;

/**
 * @author Profesores ED
 * @version 2023-24 distribuible
 */
public class EDBinaryHeapMin<T extends Comparable<T>> implements EDPriorityQueue<T> {
	protected T[] monticulo;
	protected int numElementos;

	@SuppressWarnings("unchecked")
	public EDBinaryHeapMin(int numMaxElementos) {
		monticulo = (T[]) new Comparable[numMaxElementos];
		this.numElementos = 0;
	}

	/**
	 * Se le pasa el elemento que se quiere insertar en la cola Lanza
	 * NullPointerException si el elemento a insertar es null devuelve true si
	 * consigue insertarlo, false en caso contrario
	 */
	@Override
	public boolean add(T info) {
		if (info == null)
			throw new NullPointerException("El elemento es nulo");

		if (this.monticulo.length == this.numElementos)// montículo lleno
			return false;
		else {
			this.numElementos += 1;
			this.monticulo[this.numElementos - 1] = info;// se añade al final
			filtradoAscendente(this.numElementos - 1);
			return true;
		}
	}

	/**
	 * devuelve y elimina el elemento con mayor prioridad (cima del monticulo), o
	 * null si no hay elementos
	 */
	@Override
	public T poll() {
		if (this.numElementos == 0)
			return null;
		T masPrioritario = this.monticulo[0];
		this.monticulo[0] = this.monticulo[this.numElementos - 1];
		this.monticulo[this.numElementos - 1] = null;
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
			throw new NullPointerException("El elemento es nulo");

		int position = searchElement(info);
		if (position == -1)
			return false;
		else {
			T ultimoElemento = this.monticulo[this.numElementos - 1];
			this.monticulo[position] = ultimoElemento;
			this.monticulo[numElementos - 1] = null;
			this.numElementos -= 1;
			if (info.compareTo(ultimoElemento) > 0)
				filtradoAscendente(position);
			else if (info.compareTo(ultimoElemento) < 0)
				filtradoDescendente(position);
			return true;
		}
	}

	/**
	 * Indica si no hay ningun elemento
	 */
	@Override
	public boolean isEmpty() {
		return this.numElementos == 0;
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
		if (elemento == null)
			throw new NullPointerException("El elemento es nulo");
		if (this.numElementos == 0 || pos < 0 || pos >= this.numElementos)
			return false;

		T original = this.monticulo[pos];
		this.monticulo[pos] = elemento;
		if (original.compareTo(elemento) > 0)
			filtradoAscendente(pos);
		else if (original.compareTo(elemento) < 0)
			filtradoDescendente(pos);
		return true;
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
	protected void filtradoDescendente(int index) {
		int minHijo = getMinHijo(index);

		while (minHijo != -1 && this.monticulo[index].compareTo(this.monticulo[minHijo]) > 0) {// si el padre es mayor
																								// que el hijo
			T hijo = this.monticulo[minHijo];
			this.monticulo[minHijo] = this.monticulo[index];
			this.monticulo[index] = hijo;
			index = minHijo;
			minHijo = getMinHijo(index);
		}
	}

	/**
	 * Metodo que calcula y devuelve la posicion del hijo menor del nodo pasado como
	 * parametro.
	 * 
	 * @param pos La posicion del nodo padre
	 * @return La posicion del menor de sus hijos o -1 si no existe ninguno
	 */
	private int getMinHijo(int pos) {
		int posHijoIzq = Math.abs((2 * pos) + 1);
		int posHijoDer = Math.abs((2 * pos) + 2);

		if (posHijoIzq < this.numElementos && posHijoDer < this.numElementos) {
			T hijoIzq = this.monticulo[posHijoIzq];
			T hijoDer = this.monticulo[posHijoDer];
			if (hijoIzq.compareTo(hijoDer) > 0)
				return posHijoDer;
			else
				return posHijoIzq;
		} else if (posHijoIzq < this.numElementos && posHijoDer >= this.numElementos) {
			return posHijoIzq;
		} else
			return -1;
	}

	public int searchElement(T elem) {
		if (elem == null || this.numElementos == 0)
			return -1;
		return searchElementRecursive(0, elem);
	}

	private int searchElementRecursive(int index, T elem) {
		if (index >= this.numElementos || index < 0)
			return -1;
		else if (this.monticulo[index].compareTo(elem) == 0)
			return index;
		else if (this.monticulo[index].compareTo(elem) < 0) {// si el elemento a buscar es menor
			int hijoIzq = searchElementRecursive((2 * index) + 1, elem);// busca por la rama izquierda
			if (hijoIzq != -1)
				return hijoIzq;
		}
		return searchElementRecursive((2 * index) + 2, elem);// busca por la rama derecha
	}

}
