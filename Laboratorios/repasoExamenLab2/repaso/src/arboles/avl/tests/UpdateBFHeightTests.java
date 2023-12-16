package arboles.avl.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import arboles.avl.AVLNode;

public class UpdateBFHeightTests {

	@Test
	void test() {
		AVLNode<Integer> cuatro = new AVLNode<Integer>(4);
		AVLNode<Integer> cinco = new AVLNode<Integer>(5);
		AVLNode<Integer> nueve = new AVLNode<Integer>(9);
		AVLNode<Integer> ocho = new AVLNode<Integer>(8);
		AVLNode<Integer> siete = new AVLNode<Integer>(7);
		AVLNode<Integer> seis = new AVLNode<Integer>(6);

		siete.setRight(ocho);
		siete.setLeft(seis);
		seis.setLeft(cuatro);
		seis.setRight(cinco);
		ocho.setRight(nueve);

		cuatro.updateBFHeight();
		cinco.updateBFHeight();
		nueve.updateBFHeight();
		ocho.updateBFHeight();
		siete.updateBFHeight();
		seis.updateBFHeight();

		assertEquals(0, cuatro.getHeight());
		assertEquals(0, cuatro.getBf());

		assertEquals(0, cinco.getHeight());
		assertEquals(0, cinco.getBf());

		assertEquals(1, seis.getHeight());
		assertEquals(0, seis.getBf());

		assertEquals(0, nueve.getHeight());
		assertEquals(0, nueve.getBf());

		assertEquals(1, ocho.getHeight());
		assertEquals(1, ocho.getBf());
		
		assertEquals(2, siete.getHeight());
		assertEquals(1, siete.getBf());
	}

}
