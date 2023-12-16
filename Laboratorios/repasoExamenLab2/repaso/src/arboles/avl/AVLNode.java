package arboles.avl;

/**
 * @author Prodesores ED (Español)
 * @version 2023-24
 */
public class AVLNode<T extends Comparable<T>> {
	private T info;// contenido del nodo
	private AVLNode<T> left;// hijo izquierdo
	private AVLNode<T> right;// hijo derecho
	private int bf;// factor de balance
	private int height;// altura

	/**
	 * Se le pasa un objeto comparable
	 */
	public AVLNode(T elem) {
		this.info = elem;
		this.left = null;
		this.right = null;
		this.bf = 0;
		this.height = 0;
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
	public void setLeft(AVLNode<T> elem) {
		this.left = elem;
	}

	/**
	 * Se le pasa el nodo que se quiere enlazar en el subÃ¡rbol derecho
	 */
	public void setRight(AVLNode<T> elem) {
		this.right = elem;
	}

	/**
	 * Devuelve el subÃ¡rbol izquierdo
	 */
	public AVLNode<T> getLeft() {
		return this.left;
	}

	/**
	 * Devuelve el subÃ¡rbol derecho
	 */
	public AVLNode<T> getRight() {
		return this.right;
	}

	/*
	 * Devuelve la altura
	 */
	public int getHeight() {
		return height;
	}

	/*
	 * Se le asigna una nueva altura
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/*
	 * Devuelve el factor de balance
	 */
	public int getBf() {
		return bf;
	}

	/*
	 * Se le va a asignar un nuevo factor de balance
	 */
	public void setBf(int bf) {
		this.bf = bf;
	}

	/*
	 * Método que actualiza el factor de balance y la altura
	 * 
	 * Si no tiene hijos, h = bf = 0 Si tiene un hijo, y su hijo tiene h = 0,1,2...,
	 * h += 1 Si tiene dos hijos, max(hijo1, hijo2), h = h(hijomax) + 1 El BF se
	 * corresponde con h(hijoder) - h(hijoizq)
	 * 
	 */
	public void updateBFHeight() {
		// No tiene hijos
		if (getRight() == null && getLeft() == null) {
			setHeight(0);
			setBf(0);
		}
		// Tiene un hijo por la derecha
		else if (getRight() != null && getLeft() == null) {
			setHeight(Math.max(-1, getRight().getHeight()) + 1);
			setBf(getRight().getHeight() - (-1));
		}
		// Tiene un hijo por la izquierda
		else if (getRight() == null && getLeft() != null) {
			setHeight(Math.max(getLeft().getHeight(), -1) + 1);
			setBf(-1 - getLeft().getHeight());
		}
		// Tiene dos hijos
		else {
			setHeight(Math.max(getRight().getHeight(), getLeft().getHeight()) + 1);
			setBf(getRight().getHeight() - getLeft().getHeight());
		}
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