

/** 
* @author Prodesores ED (Español) 
* @version 2023-24 
*/ 
public class BSTNode <T extends Comparable<T>>{ 
private T info; 
private BSTNode<T> left; 
private BSTNode<T> right; 
 
 
/** 
* Se le pasa un objeto comparable 
*/ 
public BSTNode (T) {...} 
 
 
/** 
* Se le pasa la información que se quiere meter en el nodo 
*/ 
protected void setInfo(T) {...} 
 
 
/** 
* Devuelve la información que almacena el nodo 
*/ 
public T getInfo() {...} 
 
 
/** 
* Se le pasa el nodo que se quiere enlazar en el subárbol izquierdo 
*/ 
protected void setLeft(BSTNode<T>){...} 
 
 
/** 
* Se le pasa el nodo que se quiere enlazar en el subárbol derecho 
*/ 
protected void setRight(BSTNode<T>){...} 
 
 
/** 
* Devuelve el subárbol izquierdo 
*/ 
public BSTNode<T> getLeft () {...} 
 
 
/** 
* Devuelve el subárbol derecho 
*/ 
public BSTNode<T> getRight () {...} 
 
 
/**
	 * Metodo que devuelve una cadena con la información del BTSNode
	 * 
	 * @return  Un String que representa al objeto BTSNode
	 */
public String toString() { 
return info.toString(); 
}  
} 