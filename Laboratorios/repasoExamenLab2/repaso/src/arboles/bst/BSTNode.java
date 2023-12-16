package arboles.bst;

/**
 * @author Prodesores ED (Español)
 * @version 2023-24
 */
public class BSTNode<T extends Comparable<T>> {
	private T info;
	private BSTNode<T> left;
	private BSTNode<T> right;

	/**
	 * Se le pasa un objeto comparable
	 */
	public BSTNode(T elem) {
		setInfo(elem);
		this.left = null;
		this.right = null;
	}

	/**
	 * Se le pasa la informaciÃ³n que se quiere meter en el nodo
	 */
	protected void setInfo(T info) {
		this.info = info;
	}

	/**
	 * Devuelve la informaciÃ³n que almacena el nodo
	 */
	public T getInfo() {
		return this.info;
	}

	/**
	 * Se le pasa el nodo que se quiere enlazar en el subÃ¡rbol izquierdo
	 */
	protected void setLeft(BSTNode<T> elem) {
		this.left = elem;
	}

	/**
	 * Se le pasa el nodo que se quiere enlazar en el subÃ¡rbol derecho
	 */
	protected void setRight(BSTNode<T> elem) {
		this.right = elem;
	}

	/**
	 * Devuelve el subÃ¡rbol izquierdo
	 */
	public BSTNode<T> getLeft() {
		return this.left;
	}

	/**
	 * Devuelve el subÃ¡rbol derecho
	 */
	public BSTNode<T> getRight() {
		return this.right;
	}

	/**
	 * Metodo que devuelve una cadena con la informaciÃ³n del BTSNode
	 * 
	 * @return Un String que representa al objeto BTSNode
	 */
	public String toString() {
		return info.toString();
	}
}