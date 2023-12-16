package arboles.bst;

/**
 * @author Prodesores ED (Español)
 * @version 2023-24 distribuible
 */
public class BSTree<T extends Comparable<T>> {

	private BSTNode<T> raiz;

	/**
	 * getter del nodo raiz del arbol
	 */
	public BSTNode<T> getRoot() {
		return this.raiz;
	}

	/**
	 * setter del nodo raiz del arbol
	 */
	protected void setRoot(BSTNode<T> nodo) {
		this.raiz = nodo;
	}

	/**
	 * Se le pasa el objeto comparable que hay que insertar devuelve true si lo
	 * inserta Si ya existe, no lo inserta y devuelve false (implementado mas
	 * tarde). Si recibe un nodo nulo, no lo inserta y lanza una
	 * NullPointerException
	 */
	public boolean addNode(T nodo) {
		if (nodo == null)
			throw new NullPointerException("Elemento nulo");
		if (getRoot() == null) {
			setRoot(new BSTNode<T>(nodo));
			return true;
		}
		if (searchNode(nodo) != null)
			return false;

		return addNodeRecursivo(getRoot(), nodo);
	}

	private boolean addNodeRecursivo(BSTNode<T> raiz, T nodo) {
		if (raiz == null) {
			setRoot(new BSTNode<T>(nodo));
			return false;
		} else if (raiz.getInfo().compareTo(nodo) > 0) {// busca por la izquierda
			if (raiz.getLeft() == null) {
				raiz.setLeft(new BSTNode<T>(nodo));
				return true;
			} else {
				return addNodeRecursivo(raiz.getLeft(), nodo);
			}
		} else if (raiz.getInfo().compareTo(nodo) < 0) {// busca por la derecha
			if (raiz.getRight() == null) {
				raiz.setRight(new BSTNode<T>(nodo));
				return true;
			} else {
				return addNodeRecursivo(raiz.getRight(), nodo);
			}
		} else {
			return false;
		}
	}

	/**
	 * Se le pasa un objeto comparable que se busca Devuelve el objeto encontrado
	 * que cumple que es "equals" con el buscado, null si no lo encuentra por
	 * cualquier motivo
	 */
	public BSTNode<T> searchNode(T nodo) {
		if (nodo == null || getRoot() == null)
			return null;
		return searchNodeRecursivo(getRoot(), nodo);
	}

	private BSTNode<T> searchNodeRecursivo(BSTNode<T> root, T nodo) {
		if (root == null)
			return null;
		if (root.getInfo().compareTo(nodo) == 0)
			return root;
		else if (root.getInfo().compareTo(nodo) > 0) {// busca por la izquierda
			return searchNodeRecursivo(root.getLeft(), nodo);
		} else {// busca por la derecha
			return searchNodeRecursivo(root.getRight(), nodo);
		}
	}

	/**
	 * Genera un String con el recorrido en pre-orden (izquierda-derecha) (toString
	 * de los nodos separados por tabuladores)
	 */
	public String preOrder() {
		String cad = preOrderRecursivo(getRoot());
		return cad.substring(0, cad.length() - 1);
	}

	private String preOrderRecursivo(BSTNode<T> root) {
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
		String cad = postOrderRecursivo(getRoot());
		return cad.substring(0, cad.length() - 1);
	}

	private String postOrderRecursivo(BSTNode<T> root) {
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
		String cad = inOrderRecursivo(getRoot());
		return cad.substring(0, cad.length() - 1);
	}

	private String inOrderRecursivo(BSTNode<T> root) {
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
	public boolean removeNode(T nodo) {
		if (nodo == null)
			throw new NullPointerException("El elemento es nulo");
		if (getRoot() == null)
			return false;
		if (searchNode(nodo) == null)
			return false;
		setRoot(removeNodeRecursivo(getRoot(), nodo));
		return true;
	}

	private BSTNode<T> removeNodeRecursivo(BSTNode<T> root, T nodo) {
		if (root.getInfo().compareTo(nodo) > 0) {// izquierda
			BSTNode<T> newNode = removeNodeRecursivo(root.getLeft(), nodo);
			root.setLeft(newNode);
			return root;
		} else if (root.getInfo().compareTo(nodo) < 0) {// derecha
			BSTNode<T> newNode = removeNodeRecursivo(root.getRight(), nodo);
			root.setRight(newNode);
			return root;
		} else {// lo encuentra
			// Si la raiz no tiene hijos
			if (root.getLeft() == null && root.getRight() == null)
				return null;
			// Tiene un hijo por la izquierda
			else if (root.getLeft() != null && root.getRight() == null)
				return root.getLeft();
			// Tiene un hijo por la derecha
			else if (root.getLeft() == null && root.getRight() != null)
				return root.getRight();
			// Tiene dos hijos
			else {
				BSTNode<T> nodeMax = searchNodeMax(root.getLeft()); // saca el nodo máximo del subárbol izquierdo
				root.setLeft(removeNodeRecursivo(root.getLeft(), nodeMax.getInfo()));// elimina el nodo máximo obtenido
				root.setInfo(nodeMax.getInfo());
				return root;
			}
		}
	}

	private BSTNode<T> searchNodeMax(BSTNode<T> root) {
		if (root.getRight() != null)
			return searchNodeMax(root.getRight());
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
	public String tumbarArbol(BSTNode<T> p, int esp) {
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

}