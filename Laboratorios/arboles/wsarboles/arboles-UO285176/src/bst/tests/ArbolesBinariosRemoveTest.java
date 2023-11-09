package bst.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import bst.BSTree;

// Test de prueba si el m�todo removeNode devuelve
// la raiz

public class ArbolesBinariosRemoveTest {

	@Test
	public void test1() {
		BSTree<Integer> b = new BSTree<Integer>();

		// Borra en el �rbol vac�o
		assertEquals(false, b.removeNode(4));

		// Borra una clave null
		assertThrows(NullPointerException.class, () -> {
			b.removeNode(null);
		});

		assertEquals(true, b.addNode(10));
		assertEquals(true, b.addNode(5));
		assertEquals(true, b.addNode(15));
		assertEquals(true, b.addNode(2));
		assertEquals(true, b.addNode(6));
		assertEquals(true, b.addNode(14));
		assertEquals(true, b.addNode(11));
		assertEquals(true, b.addNode(16));

		// Recorridos
		assertEquals("2\t5\t6\t10\t11\t14\t15\t16", b.inOrder());

		// Borra un elemento que no existe
		assertEquals(false, b.removeNode(300));

		// Borra el 14 que tiene un s�lo hijo

		assertEquals(true, b.removeNode(14));
		assertEquals("2\t5\t6\t10\t11\t15\t16", b.inOrder());

		// Borra el 10 que tiene un dos hijo
		assertEquals(true, b.removeNode(10));
		assertEquals("2\t5\t6\t11\t15\t16", b.inOrder());

		// Borra el 2 que no tiene tiene hijos
		assertEquals(true, b.removeNode(2));
		assertEquals("5\t6\t11\t15\t16", b.inOrder());

	}

	@Test
	public void test2() {
		BSTree<Integer> b = new BSTree<Integer>();

		// Intenta borrar de un �rbol vac�o
		assertEquals(false, b.removeNode(50));
		assertNull(b.searchNode(50));
		assertEquals(true, b.addNode(10));
		assertEquals(true, b.addNode(100));
		assertEquals(true, b.addNode(60));
		assertEquals(true, b.addNode(30));
		assertEquals(true, b.addNode(2));
		assertEquals(true, b.addNode(-43));
		assertEquals(true, b.addNode(70));
		assertEquals(true, b.addNode(90));
		assertEquals(true, b.addNode(23));
		assertEquals(true, b.addNode(43));
		assertEquals(true, b.addNode(65));
		assertEquals(true, b.addNode(13));
		assertEquals(true, b.addNode(230));
		assertEquals(true, b.addNode(49));
		assertEquals(true, b.addNode(7));
		assertEquals(true, b.addNode(40));
		assertEquals(true, b.addNode(50));
		assertEquals(true, b.addNode(20));
		assertEquals(true, b.addNode(15));
		assertEquals(true, b.addNode(3));

		// A�ade un elemento que ya existe
		assertEquals(false, b.addNode(3));

		// Borra un nodo que no existe
		assertEquals(false, b.removeNode(500));

		// Busca un nodo que no existe
		assertNull(b.searchNode(500));

		// Recorridos
		assertEquals("-43\t2\t3\t7\t10\t13\t15\t20\t23\t30\t40\t43\t49\t50\t60\t65\t70\t90\t100\t230", b.inOrder());
		assertEquals("10\t2\t-43\t7\t3\t100\t60\t30\t23\t13\t20\t15\t43\t40\t49\t50\t70\t65\t90\t230", b.preOrder());
		assertEquals("-43\t3\t7\t2\t15\t20\t13\t23\t40\t50\t49\t43\t30\t65\t90\t70\t60\t230\t100\t10", b.postOrder());

		// Borra un nodo con un hijo: 7 tiene como hijo el 3
		assertEquals(true, b.removeNode(7));
		assertEquals("-43\t2\t3\t10\t13\t15\t20\t23\t30\t40\t43\t49\t50\t60\t65\t70\t90\t100\t230", b.inOrder());

		// Borra un nodo con un hijo: 20 tiene como hijo al 15
		assertEquals(true, b.removeNode(20));
		assertEquals("-43\t2\t3\t10\t13\t15\t23\t30\t40\t43\t49\t50\t60\t65\t70\t90\t100\t230", b.inOrder());

		// Borra un nodo con dos hijos: 2 tiene como hijos al -43 y 2
		// Busca el mayor del subarbol izquierdo (de los menores)
		assertEquals(true, b.removeNode(2));
		assertEquals("-43\t3\t10\t13\t15\t23\t30\t40\t43\t49\t50\t60\t65\t70\t90\t100\t230", b.inOrder());

		// Borra un nodo con dos sub�rboles: 100
		// Busca el mayor del subarbol izquierdo (de los menores)
		assertEquals(true, b.removeNode(100));
		assertEquals("-43\t3\t10\t13\t15\t23\t30\t40\t43\t49\t50\t60\t65\t70\t90\t230", b.inOrder());

		// Borra un nodo con dos sub�rboles: 60
		// Busca el mayor del subarbol izquierdo (de los menores)
		assertEquals(true, b.removeNode(60));
		assertEquals("-43\t3\t10\t13\t15\t23\t30\t40\t43\t49\t50\t65\t70\t90\t230", b.inOrder());

		// Borra una hoja: 40
		assertEquals(true, b.removeNode(40));
		assertEquals("-43\t3\t10\t13\t15\t23\t30\t43\t49\t50\t65\t70\t90\t230", b.inOrder());

		// Borra la raiz: 10
		assertEquals(true, b.removeNode(10));
		assertEquals("-43\t3\t13\t15\t23\t30\t43\t49\t50\t65\t70\t90\t230", b.inOrder());

		// Borra un nodo con dos sub�rboles: 50
		// Busca el mayor del subarbol izquierdo (de los menores)
		assertEquals(true, b.removeNode(50));

		assertEquals("-43\t3\t13\t15\t23\t30\t43\t49\t65\t70\t90\t230", b.inOrder());
	}

}
