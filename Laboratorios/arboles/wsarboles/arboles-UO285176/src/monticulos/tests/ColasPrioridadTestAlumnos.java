package monticulos.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import monticulos.EDBinaryHeapMin;

class ColasPrioridadTestAlumnos {

	/**
	 * Test para probar la funcionalidad del metodo add
	 */
	@Test
	public void testAdd() {
		EDBinaryHeapMin<Integer> b = new EDBinaryHeapMin<>(5);
		assertThrows(NullPointerException.class, () -> {
			b.add(null);
		});

		assertEquals(true, b.add(1));

		assertEquals(true, b.add(2));
		assertEquals(true, b.add(4));
		assertEquals(true, b.add(3));
		assertEquals(true, b.add(1));
		assertEquals(false, b.add(5));
		assertEquals(false, b.add(1));

		assertEquals("1	1	4	3	2", b.toString());

	}

	/**
	 * Test para probar el metodo remove
	 */
	@Test
	public void testRemove() {

		EDBinaryHeapMin<Integer> b = new EDBinaryHeapMin<>(100);
		assertEquals(false, b.remove(2));
		assertEquals(true, b.add(1));
		assertEquals("1", b.toString());

		assertThrows(NullPointerException.class, () -> {
			b.remove(null);
		});

		b.clear();

		assertEquals(true, b.add(65));
		assertEquals(true, b.add(45));
		assertEquals(true, b.add(85));
		assertEquals(true, b.add(25));
		assertEquals(true, b.add(60));
		assertEquals(true, b.add(70));
		assertEquals(true, b.add(68));
		assertEquals(true, b.add(56));
		assertEquals(true, b.add(80));
		assertEquals(true, b.add(7));
		assertEquals(true, b.add(9));
		assertEquals(true, b.add(95));
		assertEquals(true, b.add(100));
		assertEquals(true, b.add(104));
		assertEquals(true, b.add(46));
		assertEquals(true, b.add(47));
		assertEquals(true, b.add(55));
		assertEquals(true, b.add(27));
		assertEquals(true, b.add(35));
		assertEquals(true, b.add(30));
		assertEquals(true, b.add(35));
		assertEquals(true, b.add(37));
		assertEquals(true, b.add(38));
		assertEquals(true, b.add(41));
		assertEquals(true, b.add(43));
		assertEquals(true, b.add(44));

		assertEquals(
				"7\t9\t41\t27\t25\t43\t68\t55\t35\t30\t37\t46\t44\t104\t70\t65\t56\t80\t47\t60\t35\t45\t38\t95\t85\t100",
				b.toString());

		assertEquals(false, b.remove(99));
		assertEquals(false, b.remove(22));
		assertEquals(false, b.remove(2));

		// Caso 1:No hay filtrado
		assertEquals(true, b.remove(38));
		assertEquals(
				"7\t9\t41\t27\t25\t43\t68\t55\t35\t30\t37\t46\t44\t104\t70\t65\t56\t80\t47\t60\t35\t45\t100\t95\t85",
				b.toString());

		// Caso 2: Filtrado descendente
		assertEquals(true, b.remove(68));
		assertEquals("7\t9\t41\t27\t25\t43\t70\t55\t35\t30\t37\t46\t44\t104\t85\t65\t56\t80\t47\t60\t35\t45\t100\t95",
				b.toString());

		// Caso 2: Filtrado descendente-- raiz
		assertEquals(true, b.remove(7));
		assertEquals("9	25	41	27	30	43	70	55	35	35	37	46	44	104	85	65	56	80	47	60	95	45	100",
				b.toString());

		b.clear();
		// Caso 3: Filtrado ascendente
		assertEquals(true, b.add(1));
		assertEquals(true, b.add(2));
		assertEquals(true, b.add(7));
		assertEquals(true, b.add(4));
		assertEquals(true, b.add(3));
		assertEquals(true, b.add(9));
		assertEquals(true, b.add(8));
		assertEquals(true, b.add(5));

		assertEquals("1	2	7	4	3	9	8	5", b.toString());
		assertEquals(true, b.remove(8));
		assertEquals("1	2	5	4	3	9	7", b.toString());

	}

	/**
	 * Test para probar el metodo poll
	 */
	@Test
	public void testPoll() {
		EDBinaryHeapMin<Integer> b = new EDBinaryHeapMin<>(100);
		assertNull(b.poll());

		assertEquals(true, b.add(60));
		assertEquals(true, b.add(40));
		assertEquals(true, b.add(80));
		assertEquals(true, b.add(20));
		assertEquals(true, b.add(55));
		assertEquals(true, b.add(65));
		assertEquals(true, b.add(63));
		assertEquals(true, b.add(51));
		assertEquals(true, b.add(75));
		assertEquals(true, b.add(2));
		assertEquals(true, b.add(4));
		assertEquals(true, b.add(90));
		assertEquals(true, b.add(95));
		assertEquals(true, b.add(99));
		assertEquals(true, b.add(41));
		assertEquals("2\t4\t41\t51\t20\t80\t63\t60\t75\t55\t40\t90\t95\t99\t65", b.toString());

		assertEquals(0, b.poll().compareTo(2));
		assertEquals("4\t20\t41\t51\t40\t80\t63\t60\t75\t55\t65\t90\t95\t99", b.toString());
		assertEquals(0, b.poll().compareTo(4));
		assertEquals("20\t40\t41\t51\t55\t80\t63\t60\t75\t99\t65\t90\t95", b.toString());
		assertEquals(0, b.poll().compareTo(20));
		assertEquals("40\t51\t41\t60\t55\t80\t63\t95\t75\t99\t65\t90", b.toString());
		assertEquals(0, b.poll().compareTo(40));
		assertEquals("41\t51\t63\t60\t55\t80\t90\t95\t75\t99\t65", b.toString());
		assertEquals(0, b.poll().compareTo(41));
		assertEquals("51\t55\t63\t60\t65\t80\t90\t95\t75\t99", b.toString());
	}

	/**
	 * Test para probar la funcionalidad del metodo isEmpty
	 */
	@Test
	public void testIsEmpty() {
		EDBinaryHeapMin<Integer> b = new EDBinaryHeapMin<>(5);
		assertTrue(b.isEmpty());

		assertEquals(true, b.add(1));
		assertFalse(b.isEmpty());

		assertEquals(true, b.add(2));
		assertFalse(b.isEmpty());

		assertEquals(true, b.remove(2));
		assertFalse(b.isEmpty());

		assertNotNull(b.poll());
		assertTrue(b.isEmpty());

	}

	/**
	 * Test para probar la funcionalidad del metodo clear
	 */
	@Test
	public void testClear() {
		EDBinaryHeapMin<Integer> b = new EDBinaryHeapMin<>(5);
		assertTrue(b.isEmpty());
		assertEquals(true, b.add(1));
		assertFalse(b.isEmpty());
		b.clear();
		assertTrue(b.isEmpty());

		assertEquals(true, b.add(1));
		assertEquals(true, b.add(2));
		assertEquals(true, b.add(3));
		assertEquals(true, b.add(4));
		assertEquals(true, b.add(5));
		b.clear();
		assertTrue(b.isEmpty());

	}

	/**
	 * Test para probar el metodo cambiarPrioridad
	 */
	@Test
	public void testCambiarPrioridad() {
		EDBinaryHeapMin<Integer> b = new EDBinaryHeapMin<>(100);
		assertEquals(true, b.add(1));

		assertThrows(NullPointerException.class, () -> b.cambiarPrioridad(0, null));

		assertEquals(false, b.cambiarPrioridad(-1, 2));
		assertEquals(false, b.cambiarPrioridad(1, 2));

		assertEquals(true, b.cambiarPrioridad(0, 2));
		assertEquals(-1, b.searchElement(1));

		b.clear();
		assertTrue(b.isEmpty());

		assertEquals(true, b.add(60));
		assertEquals(true, b.add(40));
		assertEquals(true, b.add(80));
		assertEquals(true, b.add(20));
		assertEquals(true, b.add(55));
		assertEquals(true, b.add(65));
		assertEquals(true, b.add(63));
		assertEquals(true, b.add(51));
		assertEquals(true, b.add(75));
		assertEquals(true, b.add(2));
		assertEquals(true, b.add(4));
		assertEquals(true, b.add(90));
		assertEquals(true, b.add(95));
		assertEquals(true, b.add(99));
		assertEquals(true, b.add(41));
		assertEquals("2\t4\t41\t51\t20\t80\t63\t60\t75\t55\t40\t90\t95\t99\t65", b.toString());

		assertEquals(true, b.cambiarPrioridad(0, 1));
		assertEquals("1\t4\t41\t51\t20\t80\t63\t60\t75\t55\t40\t90\t95\t99\t65", b.toString());
		assertEquals(true, b.cambiarPrioridad(0, 3));
		assertEquals("3\t4\t41\t51\t20\t80\t63\t60\t75\t55\t40\t90\t95\t99\t65", b.toString());
		assertEquals(true, b.cambiarPrioridad(b.searchElement(65), 64));
		assertEquals("3\t4\t41\t51\t20\t80\t63\t60\t75\t55\t40\t90\t95\t99\t64", b.toString());
		assertEquals(true, b.cambiarPrioridad(b.searchElement(99), 100));
		assertEquals("3\t4\t41\t51\t20\t80\t63\t60\t75\t55\t40\t90\t95\t100\t64", b.toString());
		assertEquals(true, b.cambiarPrioridad(b.searchElement(75), 50));
		assertEquals("3\t4\t41\t50\t20\t80\t63\t60\t51\t55\t40\t90\t95\t100\t64", b.toString());
		assertEquals(true, b.cambiarPrioridad(b.searchElement(4), 70));
		assertEquals("3\t20\t41\t50\t40\t80\t63\t60\t51\t55\t70\t90\t95\t100\t64", b.toString());
		assertEquals(true, b.cambiarPrioridad(0, 45));
		assertEquals("20\t40\t41\t50\t45\t80\t63\t60\t51\t55\t70\t90\t95\t100\t64", b.toString());
		assertEquals(true, b.cambiarPrioridad(0, 90));
		assertEquals("40\t45\t41\t50\t55\t80\t63\t60\t51\t90\t70\t90\t95\t100\t64", b.toString());
		assertEquals(true, b.cambiarPrioridad(b.searchElement(95), 5));
		assertEquals("5\t45\t40\t50\t55\t41\t63\t60\t51\t90\t70\t90\t80\t100\t64", b.toString());
	}

}
