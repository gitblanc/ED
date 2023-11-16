package avl_modificado;

/**
 * @author UO285176 - Eduardo Blanco Bielsa
 * @version 2023-24
 */
public class AVLNodeInverso<T extends Comparable<T>> {
	private T info;// contenido del nodo
	private AVLNodeInverso<T> left;// hijo izquierdo
	private AVLNodeInverso<T> right;// hijo derecho
	private int bf;// factor de balance
	private int height;// altura

	/**
	 * Se le pasa un objeto comparable
	 */
	public AVLNodeInverso(T clave) {
		this.info = clave;
		this.left = null;
		this.right = null;
		this.bf = 0;
		this.height = 0;
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
	public void setLeft(AVLNodeInverso<T> left) {
		this.left = left;
	}

	/**
	 * Se le pasa el nodo que se quiere enlazar en el subárbol derecho
	 */
	public void setRight(AVLNodeInverso<T> right) {
		this.right = right;
	}

	/**
	 * Devuelve el subárbol izquierdo
	 */
	public AVLNodeInverso<T> getLeft() {
		return this.left;
	}

	/**
	 * Devuelve el subárbol derecho
	 */
	public AVLNodeInverso<T> getRight() {
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

	/**
	 * Metodo que devuelve una cadena con la información del BTSNode
	 * 
	 * @return Un String que representa al objeto BTSNode
	 */
	public String toString() {
		return info.toString();
	}

	/*
	 * Método que actualiza el factor de balance y la altura
	 * 
	 * Si no tiene hijos, h = bf = 0 Si tiene un hijo, y su hijo tiene h = 0,1,2...,
	 * h += 1 Si tiene dos hijos, max(hijo1, hijo2), h = h(hijomax) + 1 El BF se
	 * corresponde con h(hijoder) - h(hijoizq)
	 * 
	 * CAMBIAMOS EL ORDEN DEL FACTOR DE BALANCE: ahora es izquierda - derecha
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
			setBf(-1 - getRight().getHeight());
		}
		// Tiene un hijo por la izquierda
		else if (getRight() == null && getLeft() != null) {
			setHeight(Math.max(getLeft().getHeight(), -1) + 1);
			setBf(getLeft().getHeight() - (-1));
		}
		// Tiene dos hijos
		else {
			setHeight(Math.max(getRight().getHeight(), getLeft().getHeight()) + 1);
			setBf(getLeft().getHeight() - getRight().getHeight());
		}
	}
}