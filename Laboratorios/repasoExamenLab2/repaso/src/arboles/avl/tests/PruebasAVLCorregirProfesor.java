package arboles.avl.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import arboles.avl.AVLTree;

public class PruebasAVLCorregirProfesor {

	@Test
	public void testAddRemove() {
		AVLTree<Integer> b = new AVLTree<Integer>();
		// A�ade elementos
		assertEquals(true, b.addNode(10));
		assertEquals("10:BF=0", b.inOrder());
		assertEquals(true, b.addNode(100));
		assertEquals("10:BF=1\t100:BF=0", b.inOrder());
		// RDD
		assertEquals(true, b.addNode(60));
		assertEquals("10:BF=0\t60:BF=0\t100:BF=0", b.inOrder());
		assertEquals(true, b.addNode(30));
		assertEquals("10:BF=1\t30:BF=0\t60:BF=-1\t100:BF=0", b.inOrder());
		assertEquals(true, b.addNode(2));
		assertEquals("2:BF=0\t10:BF=0\t30:BF=0\t60:BF=-1\t100:BF=0", b.inOrder());
		// RSI
		assertEquals(true, b.addNode(1));
		assertEquals("1:BF=0\t2:BF=-1\t10:BF=0\t30:BF=0\t60:BF=0\t100:BF=0", b.inOrder());
		assertEquals(true, b.addNode(70));
		assertEquals("1:BF=0\t2:BF=-1\t10:BF=1\t30:BF=0\t60:BF=1\t70:BF=0\t100:BF=-1", b.inOrder());
		// RDI
		assertEquals(true, b.addNode(90));
		assertEquals("1:BF=0\t2:BF=-1\t10:BF=1\t30:BF=0\t60:BF=1\t70:BF=0\t90:BF=0\t100:BF=0", b.inOrder());
		assertEquals(true, b.addNode(23));
		assertEquals("1:BF=0\t2:BF=-1\t10:BF=1\t23:BF=0\t30:BF=-1\t60:BF=0\t70:BF=0\t90:BF=0\t100:BF=0", b.inOrder());
		assertEquals(true, b.addNode(43));
		assertEquals("1:BF=0\t2:BF=-1\t10:BF=1\t23:BF=0\t30:BF=0\t43:BF=0\t60:BF=0\t70:BF=0\t90:BF=0\t100:BF=0",
				b.inOrder());
		// RSD
		assertEquals(true, b.addNode(65));
		assertEquals(
				"1:BF=0\t2:BF=-1\t10:BF=0\t23:BF=0\t30:BF=0\t43:BF=0\t60:BF=0\t65:BF=0\t70:BF=-1\t90:BF=-1\t100:BF=0",
				b.inOrder());
		assertEquals(true, b.addNode(13));
		assertEquals(
				"1:BF=0\t2:BF=-1\t10:BF=1\t13:BF=0\t23:BF=-1\t30:BF=-1\t43:BF=0\t60:BF=-1\t65:BF=0\t70:BF=-1\t90:BF=-1\t100:BF=0",
				b.inOrder());
		assertEquals(true, b.addNode(230));
		assertEquals(
				"1:BF=0\t2:BF=-1\t10:BF=1\t13:BF=0\t23:BF=-1\t30:BF=-1\t43:BF=0\t60:BF=-1\t65:BF=0\t70:BF=-1\t90:BF=0\t100:BF=1\t230:BF=0",
				b.inOrder());
		// RDD
		assertEquals(true, b.addNode(110));
		assertEquals(
				"1:BF=0\t2:BF=-1\t10:BF=1\t13:BF=0\t23:BF=-1\t30:BF=-1\t43:BF=0\t60:BF=-1\t65:BF=0\t70:BF=-1\t90:BF=0\t100:BF=0\t110:BF=0\t230:BF=0",
				b.inOrder());
		assertEquals(true, b.addNode(49));
		assertEquals(
				"1:BF=0\t2:BF=-1\t10:BF=1\t13:BF=0\t23:BF=-1\t30:BF=0\t43:BF=1\t49:BF=0\t60:BF=-1\t65:BF=0\t70:BF=-1\t90:BF=0\t100:BF=0\t110:BF=0\t230:BF=0",
				b.inOrder());
		assertEquals(true, b.addNode(7));
		assertEquals(
				"1:BF=0\t2:BF=0\t7:BF=0\t10:BF=1\t13:BF=0\t23:BF=-1\t30:BF=0\t43:BF=1\t49:BF=0\t60:BF=-1\t65:BF=0\t70:BF=-1\t90:BF=0\t100:BF=0\t110:BF=0\t230:BF=0",
				b.inOrder());
		assertEquals(true, b.addNode(40));
		assertEquals(
				"1:BF=0\t2:BF=0\t7:BF=0\t10:BF=1\t13:BF=0\t23:BF=-1\t30:BF=0\t40:BF=0\t43:BF=0\t49:BF=0\t60:BF=-1\t65:BF=0\t70:BF=-1\t90:BF=0\t100:BF=0\t110:BF=0\t230:BF=0",
				b.inOrder());
		// RSD
		assertEquals(true, b.addNode(50));
		assertEquals(
				"1:BF=0\t2:BF=0\t7:BF=0\t10:BF=0\t13:BF=0\t23:BF=-1\t30:BF=0\t40:BF=0\t43:BF=1\t49:BF=1\t50:BF=0\t60:BF=-1\t65:BF=0\t70:BF=-1\t90:BF=0\t100:BF=0\t110:BF=0\t230:BF=0",
				b.inOrder());
		// RDI
		assertEquals(true, b.addNode(20));
		assertEquals(
				"1:BF=0\t2:BF=0\t7:BF=0\t10:BF=0\t13:BF=0\t20:BF=0\t23:BF=0\t30:BF=0\t40:BF=0\t43:BF=1\t49:BF=1\t50:BF=0\t60:BF=-1\t65:BF=0\t70:BF=-1\t90:BF=0\t100:BF=0\t110:BF=0\t230:BF=0",
				b.inOrder());
		// RSI
		assertEquals(true, b.addNode(15));
		assertEquals(
				"1:BF=0\t2:BF=0\t7:BF=0\t10:BF=1\t13:BF=1\t15:BF=0\t20:BF=-1\t23:BF=0\t30:BF=0\t40:BF=0\t43:BF=1\t49:BF=1\t50:BF=0\t60:BF=0\t65:BF=0\t70:BF=-1\t90:BF=0\t100:BF=0\t110:BF=0\t230:BF=0",
				b.inOrder());
		assertEquals(true, b.addNode(3));
		assertEquals(
				"1:BF=0\t2:BF=1\t3:BF=0\t7:BF=-1\t10:BF=0\t13:BF=1\t15:BF=0\t20:BF=-1\t23:BF=0\t30:BF=0\t40:BF=0\t43:BF=1\t49:BF=1\t50:BF=0\t60:BF=0\t65:BF=0\t70:BF=-1\t90:BF=0\t100:BF=0\t110:BF=0\t230:BF=0",
				b.inOrder());

		// A�ade un elemento que ya existe
		assertEquals(false, b.addNode(3));
		assertEquals(
				"1:BF=0\t2:BF=1\t3:BF=0\t7:BF=-1\t10:BF=0\t13:BF=1\t15:BF=0\t20:BF=-1\t23:BF=0\t30:BF=0\t40:BF=0\t43:BF=1\t49:BF=1\t50:BF=0\t60:BF=0\t65:BF=0\t70:BF=-1\t90:BF=0\t100:BF=0\t110:BF=0\t230:BF=0",
				b.inOrder());
		// Borra un elemento que no existe
		assertEquals(false, b.removeNode(500));

		// Borro una hoja
		assertEquals(true, b.removeNode(3));
		assertEquals(
				"1:BF=0\t2:BF=0\t7:BF=0\t10:BF=1\t13:BF=1\t15:BF=0\t20:BF=-1\t23:BF=0\t30:BF=0\t40:BF=0\t43:BF=1\t49:BF=1\t50:BF=0\t60:BF=0\t65:BF=0\t70:BF=-1\t90:BF=0\t100:BF=0\t110:BF=0\t230:BF=0",
				b.inOrder());

		// Borra un elemento con un hijo
		assertEquals(true, b.removeNode(110));
		assertEquals(
				"1:BF=0\t2:BF=0\t7:BF=0\t10:BF=1\t13:BF=1\t15:BF=0\t20:BF=-1\t23:BF=0\t30:BF=0\t40:BF=0\t43:BF=1\t49:BF=1\t50:BF=0\t60:BF=0\t65:BF=0\t70:BF=-1\t90:BF=0\t100:BF=1\t230:BF=0",
				b.inOrder());

		// Borra un elemento con dos hijos
		assertEquals(true, b.removeNode(90));
		assertEquals(
				"1:BF=0\t2:BF=0\t7:BF=0\t10:BF=1\t13:BF=1\t15:BF=0\t20:BF=-1\t23:BF=0\t30:BF=0\t40:BF=0\t43:BF=1\t49:BF=1\t50:BF=0\t60:BF=0\t65:BF=0\t70:BF=1\t100:BF=1\t230:BF=0",
				b.inOrder());

		// Borra la raiz (30)
		assertEquals(true, b.removeNode(30));
		assertEquals(
				"1:BF=0\t2:BF=0\t7:BF=0\t10:BF=0\t13:BF=0\t15:BF=0\t20:BF=0\t23:BF=1\t40:BF=0\t43:BF=1\t49:BF=1\t50:BF=0\t60:BF=0\t65:BF=0\t70:BF=1\t100:BF=1\t230:BF=0",
				b.inOrder());

		// Borro 1\t7\t13
		assertEquals(true, b.removeNode(1));
		assertEquals(true, b.removeNode(7));
		assertEquals(true, b.removeNode(13));
		assertEquals(
				"2:BF=0\t10:BF=1\t15:BF=1\t20:BF=0\t23:BF=1\t40:BF=0\t43:BF=1\t49:BF=1\t50:BF=0\t60:BF=0\t65:BF=0\t70:BF=1\t100:BF=1\t230:BF=0",
				b.inOrder());

		// Borro 20. RSD 2,0
		assertEquals(true, b.removeNode(20));
		assertEquals(
				"2:BF=0\t10:BF=0\t15:BF=0\t23:BF=1\t40:BF=0\t43:BF=1\t49:BF=1\t50:BF=0\t60:BF=-1\t65:BF=0\t70:BF=1\t100:BF=1\t230:BF=0",
				b.inOrder());

		// Borro el 230. RDI -2, 1
		assertEquals(true, b.removeNode(230));
		assertEquals(
				"2:BF=0\t10:BF=0\t15:BF=0\t23:BF=-1\t40:BF=0\t43:BF=0\t49:BF=1\t50:BF=0\t60:BF=0\t65:BF=0\t70:BF=0\t100:BF=0",
				b.inOrder());

		// Borro 23
		assertEquals(true, b.removeNode(23));
		assertEquals(
				"2:BF=0\t10:BF=-1\t15:BF=-1\t40:BF=0\t43:BF=0\t49:BF=1\t50:BF=0\t60:BF=0\t65:BF=0\t70:BF=0\t100:BF=0",
				b.inOrder());

		// Borro 43 (raiz) y RSI sobre el nodo 15
		assertEquals(true, b.removeNode(43));
		assertEquals("2:BF=0\t10:BF=0\t15:BF=0\t40:BF=1\t49:BF=1\t50:BF=0\t60:BF=0\t65:BF=0\t70:BF=0\t100:BF=0",
				b.inOrder());

	}

	@Test
	public void test_Add() {
		AVLTree<Integer> b = new AVLTree<Integer>();
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
		assertEquals("5:BF=0\t10:BF=1\t18:BF=0\t40:BF=0\t50:BF=0", b.inOrder());

		// 15 -- RDD(10)
		assertEquals(true, b.addNode(15));
		assertEquals("5:BF=0\t10:BF=0\t15:BF=0\t18:BF=0\t40:BF=1\t50:BF=0", b.inOrder());

		// 16
		assertEquals(true, b.addNode(16));
		assertEquals("5:BF=0\t10:BF=1\t15:BF=1\t16:BF=0\t18:BF=-1\t40:BF=1\t50:BF=0", b.inOrder());

		// 12
		assertEquals(true, b.addNode(12));
		assertEquals("5:BF=0\t10:BF=1\t12:BF=0\t15:BF=0\t16:BF=0\t18:BF=-1\t40:BF=1\t50:BF=0", b.inOrder());

		// 14 -- RDD(10)
		assertEquals(true, b.addNode(14));
		assertEquals("5:BF=0\t10:BF=-1\t12:BF=0\t14:BF=0\t15:BF=0\t16:BF=0\t18:BF=-1\t40:BF=1\t50:BF=0", b.inOrder());

		// 17 -- RDI(18)
		assertEquals(true, b.addNode(17));
		assertEquals("5:BF=0\t10:BF=-1\t12:BF=-1\t14:BF=0\t15:BF=0\t16:BF=1\t17:BF=0\t18:BF=0\t40:BF=1\t50:BF=0",
				b.inOrder());

		// Inserta un elemento que ya existe
		assertEquals(false, b.addNode(15));

	}

	@Test
	public void test_Remove() {
		AVLTree<Integer> b = new AVLTree<Integer>();
		// Borra una clave null
		assertThrows(NullPointerException.class, () -> {
			b.removeNode(null);
		});

		// Borra en un �rbol vac�o
		assertEquals(false, b.removeNode(12));

		// Insertar 5, 18 10, 40, 50, 15, 16, 12, 14, 17
		b.addNode(5);
		b.addNode(18);
		b.addNode(10);
		b.addNode(40);
		b.addNode(50);
		b.addNode(15);
		b.addNode(16);
		b.addNode(12);
		b.addNode(14);
		b.addNode(17);
		assertEquals("5:BF=0\t10:BF=-1\t12:BF=-1\t14:BF=0\t15:BF=0\t16:BF=1\t17:BF=0\t18:BF=0\t40:BF=1\t50:BF=0",
				b.inOrder());

		// Borra un una clave que es null
		assertThrows(NullPointerException.class, () -> {
			b.removeNode(null);
		});

		// Borra un elemento que no existe
		assertEquals(false, b.removeNode(90));

		// Borra una clave sin hijos --> 50
		assertEquals(true, b.removeNode(50));
		assertEquals("5:BF=0\t10:BF=-1\t12:BF=-1\t14:BF=0\t15:BF=0\t16:BF=1\t17:BF=0\t18:BF=-1\t40:BF=0", b.inOrder());

		// Borra un elemento que no existe
		assertEquals(false, b.removeNode(50));

		// Borra una clave con un hijo izquierdo --> 10
		assertEquals(true, b.removeNode(10));
		assertEquals("5:BF=0\t12:BF=0\t14:BF=0\t15:BF=1\t16:BF=1\t17:BF=0\t18:BF=-1\t40:BF=0", b.inOrder());

		// Borra la ra�z que tiene dos hijos --> 15
		assertEquals(true, b.removeNode(15));
		assertEquals("5:BF=0\t12:BF=-1\t14:BF=1\t16:BF=1\t17:BF=0\t18:BF=-1\t40:BF=0", b.inOrder());

		// Borra la ra�z que tiene dos hijos --> 14
		assertEquals(true, b.removeNode(14));
		assertEquals("5:BF=0\t12:BF=-1\t16:BF=0\t17:BF=0\t18:BF=0\t40:BF=0", b.inOrder());

		// Borra una clave que es hoja --> 17
		assertEquals(true, b.removeNode(17));
		assertEquals("5:BF=0\t12:BF=-1\t16:BF=0\t18:BF=1\t40:BF=0", b.inOrder());

		// Borra una clave que tiene un hijo derecho --> 18
		assertEquals(true, b.removeNode(18));
		assertEquals("5:BF=0\t12:BF=-1\t16:BF=-1\t40:BF=0", b.inOrder());

		// Borra una clave que es hoja --> 40
		assertEquals(true, b.removeNode(40));
		assertEquals("5:BF=0\t12:BF=0\t16:BF=0", b.inOrder());

		// Borra la ra�z que tiene dos hijos --> 12
		assertEquals(true, b.removeNode(12));
		assertEquals("5:BF=1\t16:BF=0", b.inOrder());

		// Borra un hijo que es hoja --> 16
		assertEquals(true, b.removeNode(16));
		assertEquals("5:BF=0", b.inOrder());

		// Borra la ra�z que no tiene hijos
		assertEquals(true, b.removeNode(5));

		// Borra el 5
		assertEquals(false, b.removeNode(5));
	}

}
