package arboles.bst.tests;


import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import arboles.bst.BSTree;

public class ArbolesBinariosAddTest {

	@SuppressWarnings("deprecation")
	@Test
	public void test1() {
		BSTree<Integer> b = new BSTree<Integer>();

		// Busca en un �arbol vacio y devuelve null
		assertNull(b.searchNode(10));
		// Busca una clave null y devuelve null
		assertNull(b.searchNode(null));

		assertThrows(NullPointerException.class, () -> {
			b.addNode(null);
		});

		assertEquals(true, b.addNode(10));
		assertEquals(true, b.addNode(5));
		assertEquals(true, b.addNode(15));
		assertEquals(true, b.addNode(2));
		assertEquals(true, b.addNode(6));
		assertEquals(true, b.addNode(14));
		assertEquals(true, b.addNode(11));
		assertEquals(true, b.addNode(16));

		// A�ade un elemento que ya existe
		assertEquals(false, b.addNode(15));

		// Busca un nodo que no existe
		assertNull(b.searchNode(500));

		// Recorridos
		assertEquals("2\t5\t6\t10\t11\t14\t15\t16", b.inOrder());
		assertEquals("10\t5\t2\t6\t15\t14\t11\t16", b.preOrder());
		assertEquals("2\t6\t5\t11\t14\t16\t15\t10", b.postOrder());

		System.out.println(b.tumbarArbol(b.getRoot(), 3));

		// Busca un elemento que existe (5)
		assertEquals(b.searchNode(5).getInfo(), new Integer(5));
	}

	@Test
	public void test2() {
		BSTree<Integer> b = new BSTree<Integer>();

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

		// Busca un nodo que no existe
		assertNull(b.searchNode(500));

		// Recorridos
		assertEquals("-43\t2\t3\t7\t10\t13\t15\t20\t23\t30\t40\t43\t49\t50\t60\t65\t70\t90\t100\t230", b.inOrder());
		assertEquals("10\t2\t-43\t7\t3\t100\t60\t30\t23\t13\t20\t15\t43\t40\t49\t50\t70\t65\t90\t230", b.preOrder());
		assertEquals("-43\t3\t7\t2\t15\t20\t13\t23\t40\t50\t49\t43\t30\t65\t90\t70\t60\t230\t100\t10", b.postOrder());
	}

}
