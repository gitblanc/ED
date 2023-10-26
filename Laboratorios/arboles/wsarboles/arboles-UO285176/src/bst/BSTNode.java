package bst;

/**
 * @author UO285176 - Eduardo Blanco Bielsa
 * @version 2023-24
 */
public class BSTNode<T extends Comparable<T>> {
	private T info;// contenido del nodo
	private BSTNode<T> left;// hijo izquierdo
	private BSTNode<T> right;// hijo derecho

	/**
	 * Se le pasa un objeto comparable
	 */
	public BSTNode(T clave) {
		this.info = clave;
		this.left = null;
		this.right = null;
	}

	/**
	 * Se le pasa la información que se quiere meter en el nodo
	 */
	protected void setInfo(T clave) {
		this.info = clave;
	}

	/**
	 * Devuelve la información que almacena el nodo
	 */
	public T getInfo() {
		return this.info;
	}

	/**
	 * Se le pasa el nodo que se quiere enlazar en el subárbol izquierdo
	 */
	protected void setLeft(BSTNode<T> left) {
		this.left = left;
	}

	/**
	 * Se le pasa el nodo que se quiere enlazar en el subárbol derecho
	 */
	protected void setRight(BSTNode<T> right) {
		this.right = right;
	}

	/**
	 * Devuelve el subárbol izquierdo
	 */
	public BSTNode<T> getLeft() {
		return this.left;
	}

	/**
	 * Devuelve el subárbol derecho
	 */
	public BSTNode<T> getRight() {
		return this.right;
	}

	/**
	 * Metodo que devuelve una cadena con la información del BTSNode
	 * 
	 * @return Un String que representa al objeto BTSNode
	 */
	public String toString() {
		return info.toString();
	}
}