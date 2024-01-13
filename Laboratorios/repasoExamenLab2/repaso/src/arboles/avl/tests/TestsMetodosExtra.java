package arboles.avl.tests;

import org.junit.jupiter.api.Test;

import arboles.bst.BSTree;

class TestsMetodosExtra {

	@Test
	void testCalcularAlturaElemento() {
		BSTree<Character> tree = new BSTree<>();
		tree.addNode('A');
		tree.addNode('B');
		tree.addNode('C');
		tree.addNode('D');
		tree.addNode('E');
		tree.addNode('F');
		tree.addNode('G');
		tree.addNode('H');
		tree.addNode('I');
		tree.addNode('J');
		tree.addNode('K');
		tree.addNode('L');
		System.out.println(tree.toString());
	}

}
