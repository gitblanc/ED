package arboles.monticulos.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import arboles.monticulos.EDBinaryHeapMin;

class BinaryHeapMinTestsUO285176 {

	@Test
	void testAdd() {
		EDBinaryHeapMin<Integer> monticulo = new EDBinaryHeapMin<>(9);
		assertTrue(monticulo.add(12));
		assertTrue(monticulo.add(14));
		assertTrue(monticulo.add(15));
		assertTrue(monticulo.add(20));
		assertTrue(monticulo.add(16));
		assertTrue(monticulo.add(17));
		assertTrue(monticulo.add(19));
		assertTrue(monticulo.add(24));
		assertTrue(monticulo.add(18));
	}

	@Test
	void testPoll() {
		EDBinaryHeapMin<Integer> monticulo = new EDBinaryHeapMin<>(9);
		assertTrue(monticulo.add(12));
		assertTrue(monticulo.add(14));
		assertTrue(monticulo.add(15));
		assertTrue(monticulo.add(20));
		assertTrue(monticulo.add(16));
		assertTrue(monticulo.add(17));
		assertTrue(monticulo.add(19));
		assertTrue(monticulo.add(24));
		assertTrue(monticulo.add(30));
		System.out.println(monticulo.toString());
		assertEquals(12, monticulo.poll());
		System.out.println(monticulo.toString());
	}

	@Test
	void testRemove() {
		EDBinaryHeapMin<Integer> monticulo = new EDBinaryHeapMin<>(9);
		assertTrue(monticulo.add(1));
		assertTrue(monticulo.add(2));
		assertTrue(monticulo.add(3));
		assertTrue(monticulo.add(4));
		assertTrue(monticulo.add(5));
		assertTrue(monticulo.add(7));
		assertTrue(monticulo.add(8));
		assertTrue(monticulo.add(9));
		System.out.println(monticulo.toString());
		assertEquals(true, monticulo.remove(5));
		System.out.println(monticulo.toString());
	}

}
