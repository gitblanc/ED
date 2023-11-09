package bst.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bst.BSTree;

class BSTTests {

	@Test
	void testAddNode() {
		BSTree<Integer> tree = new BSTree<>();
		assertEquals(true, tree.addNode(7));// no existe la raíz
		assertEquals(true, tree.addNode(8));
		assertEquals(true, tree.addNode(3));
		assertEquals(true, tree.addNode(5));
		assertEquals(false, tree.addNode(7));// ya existe

		try {
			tree.addNode(null);
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

		assertEquals(true, tree.addNode(10));
		assertEquals(true, tree.addNode(1));
		assertEquals(true, tree.addNode(4));
		assertEquals(true, tree.addNode(6));
		assertEquals(true, tree.addNode(9));
		assertEquals(true, tree.addNode(2));

		System.out.println("TEST 1: addNode()");
		System.out.println(tree.toString());
	}

	@Test
	void testSearchNode() {
		BSTree<Integer> tree = new BSTree<>();
		assertNull(tree.searchNode(7));// caso extremo: probar si raíz del árbol es null

		assertEquals(true, tree.addNode(7));
		assertEquals(true, tree.addNode(8));
		assertEquals(true, tree.addNode(3));
		assertEquals(true, tree.addNode(5));
		assertEquals(false, tree.addNode(7));// ya existe

		// Existen
		assertNotNull(tree.searchNode(7));
		assertNotNull(tree.searchNode(8));
		assertNotNull(tree.searchNode(3));
		assertNotNull(tree.searchNode(5));

		// No existen
		assertNull(tree.searchNode(0));
		assertNull(tree.searchNode(7324));
		assertNull(tree.searchNode(79));
		assertNull(tree.searchNode(4));
		assertNull(tree.searchNode(null));// caso extremo: probar null
	}

	@Test
	void testRemoveNode() {
		BSTree<Integer> tree = new BSTree<>();

		// caso extremo: clave null
		try {
			tree.removeNode(null);
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(false, tree.removeNode(7));// caso extremo: raiz del arbol null (no hay nodos)

		assertEquals(true, tree.addNode(7));// no existe la raíz
		assertEquals(true, tree.addNode(8));
		assertEquals(true, tree.addNode(3));
		assertEquals(true, tree.addNode(5));
		assertEquals(true, tree.addNode(10));
		assertEquals(true, tree.addNode(1));
		assertEquals(true, tree.addNode(4));
		assertEquals(true, tree.addNode(6));
		assertEquals(true, tree.addNode(9));
		assertEquals(true, tree.addNode(2));

		System.out.println("TEST 3: removeNode()");
		System.out.println("Sin eliminaciones");
		System.out.println(tree.toString());

		assertEquals(true, tree.removeNode(1));
		System.out.println("Eliminamos la clave 1");
		System.out.println(tree.toString());

		assertEquals(true, tree.removeNode(4));
		System.out.println("Eliminamos la clave 4");
		System.out.println(tree.toString());

		assertEquals(true, tree.removeNode(8));
		System.out.println("Eliminamos la clave 8");
		System.out.println(tree.toString());

		assertEquals(true, tree.removeNode(7));
		System.out.println("Eliminamos la clave 7");
		System.out.println(tree.toString());

		assertEquals(true, tree.removeNode(10));
		System.out.println("Eliminamos la clave 10");
		System.out.println(tree.toString());
	}

}
