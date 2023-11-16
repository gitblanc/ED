package avl_modificado;

/**
 * @author UO285176 - Eduardo Blanco Bielsa
 * @version 2023-24 distribuible
 */
public class AVLTreeInverso<T extends Comparable<T>> {

	private AVLNodeInverso<T> raiz;

	/**
	 * getter del nodo raiz del arbol
	 */
	public AVLNodeInverso<T> getRoot() {
		return this.raiz;
	}

	/**
	 * setter del nodo raiz del arbol
	 */
	protected void setRoot(AVLNodeInverso<T> nodo) {
		this.raiz = nodo;
	}

	/**
	 * Se le pasa el objeto comparable que hay que insertar devuelve true si lo
	 * inserta Si ya existe, no lo inserta y devuelve false (implementado mas
	 * tarde). Si recibe un nodo nulo, no lo inserta y lanza una
	 * NullPointerException
	 */
	public boolean addNode(T clave) {
		if (clave == null)
			throw new NullPointerException("No puede ser nulo");

		if (getRoot() == null) {
			setRoot(new AVLNodeInverso<T>(clave));
			return true;
		}

		if (searchNode(clave) != null)
			return false;// ya existía

		AVLNodeInverso<T> node = addNodeRecursivo(getRoot(), clave);
		setRoot(node);
		return true;
	}

	private AVLNodeInverso<T> addNodeRecursivo(AVLNodeInverso<T> root, T clave) {
		if (root == null) {
			root = new AVLNodeInverso<T>(clave);
			return updateBFHeightBalanceo(root);
		}
		if (root.getInfo().compareTo(clave) > 0) { // raiz mayor que clave
			if (root.getLeft() == null) {
				root.setLeft(new AVLNodeInverso<T>(clave));
				return updateBFHeightBalanceo(root);
			} else {
				root.setLeft(addNodeRecursivo(root.getLeft(), clave));// busca por la izquierda
				return updateBFHeightBalanceo(root);
			}
		} else if (root.getInfo().compareTo(clave) < 0) {// raiz menor que clave
			if (root.getRight() == null) {
				root.setRight(new AVLNodeInverso<T>(clave));
				return updateBFHeightBalanceo(root);
			} else {
				root.setRight(addNodeRecursivo(root.getRight(), clave));// busca por la derecha
				return updateBFHeightBalanceo(root);
			}
		} else
			return null;
	}

	/**
	 * Se le pasa un objeto comparable que se busca Devuelve el objeto encontrado
	 * que cumple que es "equals" con el buscado, null si no lo encuentra por
	 * cualquier motivo
	 */
	public AVLNodeInverso<T> searchNode(T clave) {
		if (clave == null || getRoot() == null)
			return null;

		return searchNodeRecursivo(getRoot(), clave);
	}

	private AVLNodeInverso<T> searchNodeRecursivo(AVLNodeInverso<T> root, T clave) {
		if (root == null)
			return null;
		if (root.getInfo().compareTo(clave) == 0)
			return root;
		if (root.getInfo().compareTo(clave) > 0) { // si la raiz es mayor que la clave
			return searchNodeRecursivo(root.getLeft(), clave); // busca por la izquierda
		} else if (root.getInfo().compareTo(clave) < 0) {// si la raiz es menor que la clave
			return searchNodeRecursivo(root.getRight(), clave);// busca por la derecha
		} else
			return null;
	}

	/**
	 * Genera un String con el recorrido en pre-orden (izquierda-derecha) (toString
	 * de los nodos separados por tabuladores)
	 */
	public String preOrder() {
		String recorrido = preOrderRecursivo(getRoot());
		return recorrido.substring(0, recorrido.length() - 1);
	}

	private String preOrderRecursivo(AVLNodeInverso<T> root) {
		if (root == null)
			return "";

		String cadena = root.getInfo().toString();
		cadena += "\t";
		cadena += preOrderRecursivo(root.getLeft());
		cadena += preOrderRecursivo(root.getRight());
		return cadena;
	}

	/**
	 * Genera un String con el recorrido en post-orden (izquierda-derecha) (toString
	 * de los nodos separados por tabuladores)
	 */
	public String postOrder() {
		String recorrido = postOrderRecursivo(getRoot());
		return recorrido.substring(0, recorrido.length() - 1);
	}

	private String postOrderRecursivo(AVLNodeInverso<T> root) {
		String cadena = "";
		if (root != null) {
			cadena += postOrderRecursivo(root.getLeft());
			cadena += postOrderRecursivo(root.getRight());
			cadena += root.getInfo().toString() + "\t";
		}
		return cadena;
	}

	/**
	 * Genera un String con el recorrido en in-orden (izquierda-derecha) (toString
	 * de los nodos separados por tabuladores)
	 */
	public String inOrder() {
		String recorrido = inOrderRecursivo(getRoot());
		return recorrido.substring(0, recorrido.length() - 1);
	}

	private String inOrderRecursivo(AVLNodeInverso<T> root) {
		String cadena = "";
		if (root != null) {
			cadena += inOrderRecursivo(root.getLeft());
			cadena += root.getInfo().toString() + ":BF=" + root.getBf() + "\t";
			cadena += inOrderRecursivo(root.getRight());
		}
		return cadena;
	}

	/**
	 * Se le pasa el objeto que se quiere borrar que coincida con equals Si recibe
	 * un nodo nulo, lanza una NullPointerException Devuelve true si lo ha borrado,
	 * false caso contrario
	 */
	public boolean removeNode(T clave) {
		if (clave == null)
			throw new NullPointerException("El nodo no puede ser nulo");

		AVLNodeInverso<T> node = searchNode(clave);

		if (node == null)
			return false;

		setRoot(removeNodeRecursivo(getRoot(), clave));

		return true;
	}

	private AVLNodeInverso<T> removeNodeRecursivo(AVLNodeInverso<T> root, T clave) {
		if (root.getInfo().compareTo(clave) > 0) {// raiz mayor que clave
			AVLNodeInverso<T> nodo = removeNodeRecursivo(root.getLeft(), clave);// busca el nodo por la izquierda a
																				// eliminar
			root.setLeft(nodo);
			return updateBFHeightBalanceo(root);
		} else if (root.getInfo().compareTo(clave) < 0) {// raiz menor que clave
			AVLNodeInverso<T> nodo = removeNodeRecursivo(root.getRight(), clave);// busca el nodo por la derecha a
																					// eliminar
			root.setRight(nodo);
			return updateBFHeightBalanceo(root);
		} else {// si encuentra el nodo a borrar -> 3 casos
			// Si la raiz no tiene hijos
			if (root.getLeft() == null && root.getRight() == null) {
				return null;
			}
			// Si tiene un hijo a la izquierda
			else if (root.getLeft() != null && root.getRight() == null) {
				return root.getLeft();
			}
			// Si tiene un hijo a la derecha
			else if (root.getLeft() == null && root.getRight() != null) {
				return root.getRight();
			}
			// Si tiene dos hijos
			else {
				AVLNodeInverso<T> nodeMax = searchMaxNode(root.getLeft());// saca el nodo máximo del subárbol izquierdo
				root.setLeft(removeNodeRecursivo(root.getLeft(), nodeMax.getInfo()));// elimina el nodo máximo
				root.setInfo(nodeMax.getInfo());// actualiza la información del nodo a eliminar
				return updateBFHeightBalanceo(root);
			}
		}
	}

	// Método que busca el nodo máximo del subárbol izquierdo invocado por el
	// removeNodeRecursivo
	private AVLNodeInverso<T> searchMaxNode(AVLNodeInverso<T> root) {
		if (root.getRight() != null)
			return searchMaxNode(root.getRight());
		else
			return root;

	}

	public String toString() {
		return tumbarArbol(raiz, 0);
	}

	/**
	 * Genera un String con el arbol "tumbado" (la raiz a la izquierda y las ramas
	 * hacia la derecha) Es un recorrido InOrden-Derecha-Izquierda, tabulando para
	 * mostrar los distintos niveles Utiliza el toString de la informacion
	 * almacenada
	 *
	 * @param p   La raiz del arbol a mostrar tumbado
	 * @param esp El espaciado en numero de tabulaciones para indicar la profundidad
	 * @return El String generado
	 */
	public String tumbarArbol(AVLNodeInverso<T> p, int esp) {
		StringBuilder cadena = new StringBuilder();

		if (p != null) {
			cadena.append(tumbarArbol(p.getRight(), esp + 1));
			for (int i = 0; i < esp; i++)
				cadena.append("\t");
			cadena.append(p + "\n");
			cadena.append(tumbarArbol(p.getLeft(), esp + 1));
		}
		return cadena.toString();
	}

	// Rotaciones y demás...

	/*
	 * Este método reorganiza el subárbol del nodo que se le pasa como parámetro si
	 * es necesario (cuando su |BF| es >1) y lo devuelve una vez se han actualizado
	 * su altura y factor de balance
	 */
	private AVLNodeInverso<T> updateBFHeightBalanceo(AVLNodeInverso<T> nodo) {
		nodo.updateBFHeight();

		// CAMBIAMOS EL FACTOR DE BALANCE Y LAS ROTACIONES
		// Rotación izquierda
		if (nodo.getBf() == 2) {
			if (nodo.getLeft().getBf() == -1)
				nodo = doubleLeftRotation(nodo);
			else
				nodo = singleLeftRotation(nodo);
		}
		// Rotación derecha
		else if (nodo.getBf() == -2) {
			if (nodo.getRight().getBf() == 1)
				nodo = doubleRightRotation(nodo);
			else
				nodo = singleRightRotation(nodo);
		}

		return nodo;
	}

	/*
	 * Método que se encarga de realizar una rotación simple a la izquierda sobre el
	 * subárbol del nodo pasado como parámetro y devuelve el nodo resultante
	 */
	private AVLNodeInverso<T> singleLeftRotation(AVLNodeInverso<T> nodo) {
		AVLNodeInverso<T> aux = nodo.getLeft();
		nodo.setLeft(aux.getRight());
		nodo.updateBFHeight();
		aux.setRight(nodo);
		aux.updateBFHeight();
		return aux;
	}

	/*
	 * Método que se encarga de realizar una rotación simple a la derecha sobre el
	 * subárbol del nodo pasado como parámetro y devuelve el nodo resultante
	 */
	private AVLNodeInverso<T> singleRightRotation(AVLNodeInverso<T> nodo) {
		AVLNodeInverso<T> aux = nodo.getRight();
		nodo.setRight(aux.getLeft());
		nodo.updateBFHeight();
		aux.setLeft(nodo);
		aux.updateBFHeight();
		return aux;
	}

	/*
	 * Método que se encarga de realizar una rotación doble a la izquierda sobre el
	 * subárbol del nodo pasado como parámetro y devuelve el nodo resultante
	 */
	private AVLNodeInverso<T> doubleLeftRotation(AVLNodeInverso<T> nodo) {
		nodo.setLeft(singleRightRotation(nodo.getLeft()));
		return singleLeftRotation(nodo);
	}

	/*
	 * Método que se encarga de realizar una rotación doble a la derecha sobre el
	 * subárbol del nodo pasado como parámetro y devuelve el nodo resultante
	 */
	private AVLNodeInverso<T> doubleRightRotation(AVLNodeInverso<T> nodo) {
		nodo.setRight(singleLeftRotation(nodo.getRight()));
		return singleRightRotation(nodo);
	}

	public AVLNodeInverso<T> devolverPadre(T clave) {
		AVLNodeInverso<T> nodo = searchNode(clave);

		if (nodo == null)
			return null;

		return devolverPadreRecursivo(getRoot(), nodo, null);
	}

	private AVLNodeInverso<T> devolverPadreRecursivo(AVLNodeInverso<T> root, AVLNodeInverso<T> nodo,
			AVLNodeInverso<T> padre) {
		if (root == null)
			return null;

		if (root.getInfo().compareTo(nodo.getInfo()) == 0)
			return padre;
		if (root.getInfo().compareTo(nodo.getInfo()) > 0) { // si la raiz es mayor que la clave
			return devolverPadreRecursivo(root.getLeft(), nodo, root); // busca por la izquierda
		} else if (root.getInfo().compareTo(nodo.getInfo()) < 0) {// si la raiz es menor que la clave
			return devolverPadreRecursivo(root.getRight(), nodo, root);// busca por la derecha
		} else
			return null;
	}

	public int contarAristas(T clave1, T clave2) {
		if (getRoot() == null || clave1 == null || clave2 == null)
			return 0;

		AVLNodeInverso<T> nodo1 = searchNode(clave1);
		AVLNodeInverso<T> nodo2 = searchNode(clave2);

		if (nodo1 == null || nodo2 == null)
			return 0;

		// Obtenemos el ancestro común más bajo de ambos nodos
		AVLNodeInverso<T> ancestroComun = encontrarAncestroComun(getRoot(), nodo1, nodo2);

		// Calculamos la distancia de cada nodo al ancestro común
		int distanciaNodo1 = distanciaAlAncestro(nodo1, ancestroComun, 0);
		int distanciaNodo2 = distanciaAlAncestro(nodo2, ancestroComun, 0);

		// Sumamos las distancias para obtener el número de aristas entre los nodos
		return distanciaNodo1 + distanciaNodo2;
	}

	// Método para encontrar el ancestro común más bajo de dos nodos en el árbol
	private AVLNodeInverso<T> encontrarAncestroComun(AVLNodeInverso<T> root, AVLNodeInverso<T> nodo1,
			AVLNodeInverso<T> nodo2) {
		if (root == null || nodo1 == null || nodo2 == null)
			return null;

		if ((nodo1.getInfo().compareTo(root.getInfo()) < 0 && nodo2.getInfo().compareTo(root.getInfo()) > 0)
				|| (nodo1.getInfo().compareTo(root.getInfo()) > 0 && nodo2.getInfo().compareTo(root.getInfo()) < 0)) {
			// Si los nodos están en lados opuestos del ancestro actual, encontramos el
			// ancestro común
			return root;
		} else if (nodo1.getInfo().compareTo(root.getInfo()) < 0 && nodo2.getInfo().compareTo(root.getInfo()) < 0) {
			// Si ambos nodos están en el subárbol izquierdo, buscamos en el subárbol
			// izquierdo
			return encontrarAncestroComun(root.getLeft(), nodo1, nodo2);
		} else {
			// Si ambos nodos están en el subárbol derecho, buscamos en el subárbol derecho
			return encontrarAncestroComun(root.getRight(), nodo1, nodo2);
		}
	}

	// Método para calcular la distancia de un nodo al ancestro común más bajo
	private int distanciaAlAncestro(AVLNodeInverso<T> nodo, AVLNodeInverso<T> ancestroComun, int distancia) {
		if (nodo == null)
			return 0;

		if (nodo.getInfo().compareTo(ancestroComun.getInfo()) == 0) {
			// Hemos llegado al ancestro común
			return distancia;
		} else if (nodo.getInfo().compareTo(ancestroComun.getInfo()) < 0) {
			// Si el nodo está en el subárbol izquierdo, incrementamos la distancia y
			// continuamos
			return distanciaAlAncestro(nodo, ancestroComun.getLeft(), distancia + 1);
		} else {
			// Si el nodo está en el subárbol derecho, incrementamos la distancia y
			// continuamos
			return distanciaAlAncestro(nodo, ancestroComun.getRight(), distancia + 1);
		}
	}

}