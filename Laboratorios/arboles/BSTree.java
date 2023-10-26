

/**
* @author Prodesores ED (Español)
* @version 2023-24 distribuible
*/
public class BSTree <T extends Comparable<T>>{


private BSTNode<T> raiz;

/**
 * getter del nodo raiz del arbol
 */
protected BSTNode<T> getRoot() {...}

/**
 * setter del nodo raiz del arbol
 */
protected void setRoot(BSTNode<T> nodo) {...}


/**
 * Se le pasa el objeto comparable que hay que insertar
 * devuelve true si lo inserta
 * Si ya existe, no lo inserta y devuelve false (implementado mas tarde). 
 * Si recibe un nodo nulo, no lo inserta y lanza una NullPointerException
 */
public boolean addNode(T) {...}


/**
* Se le pasa un objeto comparable que se busca
* Devuelve el objeto encontrado que cumple que es "equals" con el
* buscado, null si no lo encuentra por cualquier motivo
*/
public BSTNode<T> searchNode(T) {...}


/**
* Genera un String con el recorrido en pre-orden (izquierda-derecha)
* (toString de los nodos separados por tabuladores)
*/
public String preOrder() {...}


/**
* Genera un String con el recorrido en post-orden (izquierda-derecha)
* (toString de los nodos separados por tabuladores)
*/
public String postOrder() {...}


/**
* Genera un String con el recorrido en in-orden (izquierda-derecha)
* (toString de los nodos separados por tabuladores)
*/
public String inOrder() {...}


/**
* Se le pasa el objeto que se quiere borrar que coincida con equals
* Si recibe un nodo nulo, lanza una NullPointerException
* Devuelve true si lo ha borrado, false caso contrario
*/
public boolean removeNode (T){...}



public String toString() {
	return tumbarArbol(raiz, 0);
}



/**
* Genera un String con el arbol "tumbado" (la raiz a la izquierda y las ramas hacia la derecha)
* Es un recorrido InOrden-Derecha-Izquierda, tabulando para mostrar los distintos niveles
* Utiliza el toString de la informacion almacenada
*
* @param p La raiz del arbol a mostrar tumbado
* @param esp El espaciado en numero de tabulaciones para indicar la profundidad
* @return El String generado
*/
protected String tumbarArbol(BSTNode<T> p, int esp) {
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