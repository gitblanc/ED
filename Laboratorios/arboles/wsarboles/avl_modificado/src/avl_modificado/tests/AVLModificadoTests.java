package avl_modificado.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import avl_modificado.AVLTreeInverso;

class AVLModificadoTests {

	@Test
	void testBalanceFactor() {
		AVLTreeInverso<Integer> b = new AVLTreeInverso<Integer>();
		// Insertar una clave null
		assertThrows(NullPointerException.class, () -> {
			b.addNode(null);
		});

		// 5, 18 10 -- RDD(10)
		assertEquals(true, b.addNode(5));
		assertEquals(true, b.addNode(18));
		assertEquals(true, b.addNode(10));
		assertEquals("5:BF=0\t10:BF=0\t18:BF=0", b.inOrder());
		// 40, 50 -- RSD(18)
		assertEquals(true, b.addNode(40));
		assertEquals(true, b.addNode(50));
		assertEquals("5:BF=0\t10:BF=-1\t18:BF=0\t40:BF=0\t50:BF=0", b.inOrder());

		// 15 -- RDD(10)
		assertEquals(true, b.addNode(15));
		assertEquals("5:BF=0\t10:BF=0\t15:BF=0\t18:BF=0\t40:BF=-1\t50:BF=0", b.inOrder());

		// 16
		assertEquals(true, b.addNode(16));
		assertEquals("5:BF=0\t10:BF=-1\t15:BF=-1\t16:BF=0\t18:BF=1\t40:BF=-1\t50:BF=0", b.inOrder());

		// 12
		assertEquals(true, b.addNode(12));
		assertEquals("5:BF=0\t10:BF=-1\t12:BF=0\t15:BF=0\t16:BF=0\t18:BF=1\t40:BF=-1\t50:BF=0", b.inOrder());

		// 14 -- RDD(10)
		assertEquals(true, b.addNode(14));
		assertEquals("5:BF=0\t10:BF=1\t12:BF=0\t14:BF=0\t15:BF=0\t16:BF=0\t18:BF=1\t40:BF=-1\t50:BF=0", b.inOrder());

		// 17 -- RDI(18)
		assertEquals(true, b.addNode(17));
		assertEquals("5:BF=0\t10:BF=1\t12:BF=1\t14:BF=0\t15:BF=0\t16:BF=-1\t17:BF=0\t18:BF=0\t40:BF=-1\t50:BF=0",
				b.inOrder());

		// Inserta un elemento que ya existe
		assertEquals(false, b.addNode(15));
	}

	@Test
	public void testDevolverPadre() {
		AVLTreeInverso<Integer> b = new AVLTreeInverso<Integer>();
		// Insertar una clave null

		assertNull(b.devolverPadre(null));

		// 5, 18 10 -- RDD(10)
		assertEquals(true, b.addNode(5));
		assertEquals(true, b.addNode(18));
		assertEquals(true, b.addNode(10));

		assertEquals(null, b.devolverPadre(7987));
		assertEquals(null, b.devolverPadre(10));
		assertEquals(10, (int) (b.devolverPadre(5).getInfo()));
		assertEquals(10, (int) (b.devolverPadre(18).getInfo()));
	}

	@Test
	public void testContarAristas() {
		AVLTreeInverso<Integer> b = new AVLTreeInverso<Integer>();
		// Insertar una clave null

		assertEquals(0, b.contarAristas(null, null));

		// 5, 18 10 -- RDD(10)
		assertEquals(true, b.addNode(5));
		assertEquals(true, b.addNode(18));
		assertEquals(true, b.addNode(10));

		assertEquals(true, b.addNode(3));
		assertEquals(true, b.addNode(6));
		assertEquals(true, b.addNode(17));
		assertEquals(true, b.addNode(20));

		assertEquals(4, b.contarAristas(3, 20));
	}
}