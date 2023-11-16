package avl.tests;

import org.junit.jupiter.api.Test;

import avl.AVLTree;

public class RotationTests {

	@Test
	void test() {
		AVLTree<Integer> tree = new AVLTree<>();
		tree.addNode(7);
		System.out.println(tree.toString());
		tree.addNode(5);
		System.out.println(tree.toString());
		tree.addNode(8);
		System.out.println(tree.toString());
		tree.addNode(4);
		System.out.println(tree.toString());
		tree.addNode(6);
		System.out.println(tree.toString());
		tree.addNode(9);
		System.out.println(tree.toString());
		tree.addNode(10);
		System.out.println(tree.toString());
	}

}
