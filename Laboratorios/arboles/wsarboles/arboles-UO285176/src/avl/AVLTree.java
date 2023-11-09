package avl;

/**
 * @author UO285176 - Eduardo Blanco Bielsa
 * @version 2023-24 distribuible
 */
public class AVLTree<T extends Comparable<T>> {

	private AVLNode<T> raiz;

	/**
	 * getter del nodo raiz del arbol
	 */
	public AVLNode<T> getRoot() {
		return this.raiz;
	}

	/**
	 * setter del nodo raiz del arbol
	 */
	protected void setRoot(AVLNode<T> nodo) {
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
			setRoot(new AVLNode<T>(clave));
			return true;
		}

		if (searchNode(clave) != null)
			return false;// ya existía

		AVLNode<T> node = addNodeRecursivo(getRoot(), clave);
		setRoot(node);
		return true;
	}

	private AVLNode<T> addNodeRecursivo(AVLNode<T> root, T clave) {
		if (root == null) {
			root = new AVLNode<T>(clave);
			return updateBFHeightBalanceo(root);
		}
		if (root.getInfo().compareTo(clave) > 0) { // raiz mayor que clave
			if (root.getLeft() == null) {
				root.setLeft(new AVLNode<T>(clave));
				return updateBFHeightBalanceo(root);
			} else {
				root.setLeft(addNodeRecursivo(root.getLeft(), clave));// busca por la izquierda
				return updateBFHeightBalanceo(root);
			}
		} else if (root.getInfo().compareTo(clave) < 0) {// raiz menor que clave
			if (root.getRight() == null) {
				root.setRight(new AVLNode<T>(clave));
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
	public AVLNode<T> searchNode(T clave) {
		if (clave == null || getRoot() == null)
			return null;

		return searchNodeRecursivo(getRoot(), clave);
	}

	private AVLNode<T> searchNodeRecursivo(AVLNode<T> root, T clave) {
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

	private String preOrderRecursivo(AVLNode<T> root) {
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

	private String postOrderRecursivo(AVLNode<T> root) {
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

	private String inOrderRecursivo(AVLNode<T> root) {
		String cadena = "";
		if (root != null) {
			cadena += inOrderRecursivo(root.getLeft());
			cadena += root.getInfo().toString() + "\t";
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

		AVLNode<T> node = searchNode(clave);

		if (node == null)
			return false;

		setRoot(removeNodeRecursivo(getRoot(), clave));

		return true;
	}

	private AVLNode<T> removeNodeRecursivo(AVLNode<T> root, T clave) {
		if (root.getInfo().compareTo(clave) > 0) {// raiz mayor que clave
			AVLNode<T> nodo = removeNodeRecursivo(root.getLeft(), clave);// busca el nodo por la izquierda a eliminar
			root.setLeft(nodo);
			return root;
		} else if (root.getInfo().compareTo(clave) < 0) {// raiz menor que clave
			AVLNode<T> nodo = removeNodeRecursivo(root.getRight(), clave);// busca el nodo por la derecha a eliminar
			root.setRight(nodo);
			return root;
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
				AVLNode<T> nodeMax = searchMaxNode(root.getLeft());// saca el nodo máximo del subárbol izquierdo
				root.setLeft(removeNodeRecursivo(root.getLeft(), nodeMax.getInfo()));// elimina el nodo máximo
				root.setInfo(nodeMax.getInfo());// actualiza la información del nodo a eliminar
				return root;
			}
		}
	}

	// Método que busca el nodo máximo del subárbol izquierdo invocado por el
	// removeNodeRecursivo
	private AVLNode<T> searchMaxNode(AVLNode<T> root) {
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
	public String tumbarArbol(AVLNode<T> p, int esp) {
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
	private AVLNode<T> updateBFHeightBalanceo(AVLNode<T> nodo) {
		nodo.updateBFHeight();

		// Rotación izquierda
		if (nodo.getBf() == -2) {
			if (nodo.getLeft().getBf() == 1)
				nodo = doubleLeftRotation(nodo);
			else
				nodo = singleLeftRotation(nodo);
		}
		// Rotación derecha
		else if (nodo.getBf() == 2) {
			if (nodo.getRight().getBf() == -1)
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
	private AVLNode<T> singleLeftRotation(AVLNode<T> nodo) {
		AVLNode<T> aux = nodo.getLeft();
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
	private AVLNode<T> singleRightRotation(AVLNode<T> nodo) {
		AVLNode<T> aux = nodo.getRight();
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
	private AVLNode<T> doubleLeftRotation(AVLNode<T> nodo) {
		nodo = singleRightRotation(nodo.getLeft());
		return singleLeftRotation(nodo);
	}

	/*
	 * Método que se encarga de realizar una rotación doble a la derecha sobre el
	 * subárbol del nodo pasado como parámetro y devuelve el nodo resultante
	 */
	private AVLNode<T> doubleRightRotation(AVLNode<T> nodo) {
		nodo = singleLeftRotation(nodo.getRight());
		return singleRightRotation(nodo);
	}
}