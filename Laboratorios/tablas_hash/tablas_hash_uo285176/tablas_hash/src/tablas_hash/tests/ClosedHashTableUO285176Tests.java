package tablas_hash.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tablas_hash.ClosedHashTable;

class ClosedHashTableUO285176Tests {

	@Test
	public void testAdd1Lineal() {
		ClosedHashTable<Integer> a = new ClosedHashTable<>(5, 0);// LINEAL

		assertThrows(NullPointerException.class, () -> {
			a.add(null);
		}); // Añade null

		assertEquals(true, a.add(1)); // Añade correctamente
		assertEquals(true, a.add(2));
		assertEquals(true, a.add(3));
		assertEquals("{_E_};{1};{2};{3};{_E_};[Size: 5 Num.Elems.: 3]", a.toString());

		assertEquals(true, a.add(4)); // Añade correctamente
		assertEquals(true, a.add(9)); // Añade correctamente con conflicto en posicion
		assertEquals("{9};{1};{2};{3};{4};[Size: 5 Num.Elems.: 5]", a.toString());

		assertEquals(false, a.add(6)); // Añade con tabla llena
		assertEquals("{9};{1};{2};{3};{4};[Size: 5 Num.Elems.: 5]", a.toString());
	}

	@Test
	public void testAdd2Cuadratica() {
		ClosedHashTable<Integer> a = new ClosedHashTable<>(13, 1);// CUADRÁTICA

		assertThrows(NullPointerException.class, () -> {
			a.add(null);
		}); // Añade null

		assertEquals(true, a.add(5)); // Añade correctamente
		assertEquals(true, a.add(8));
		assertEquals(true, a.add(10));
		assertEquals(true, a.add(8));
		assertEquals(true, a.add(5));
		assertEquals(true, a.add(11));
		assertEquals(true, a.add(6));
		assertEquals(true, a.add(7));
		assertEquals(true, a.add(14));
		assertEquals("{_E_};{14};{_E_};{7};{_E_};{5};{5};{6};{8};{8};{10};{11};{_E_};[Size: 13 Num.Elems.: 9]",
				a.toString());
	}

	@Test
	public void testAdd3DobleHash() {
		ClosedHashTable<Integer> a = new ClosedHashTable<>(13, 2);// CUADRÁTICA

		assertThrows(NullPointerException.class, () -> {
			a.add(null);
		}); // Añade null

		assertEquals(true, a.add(5)); // Añade correctamente
		assertEquals(true, a.add(8));
		assertEquals(true, a.add(10));
		assertEquals(true, a.add(8));
		assertEquals(true, a.add(5));
		assertEquals(true, a.add(14));
		assertEquals(true, a.add(6));
		assertEquals(true, a.add(7));
		assertEquals(true, a.add(6));
		assertEquals("{_E_};{14};{_E_};{6};{5};{5};{6};{7};{8};{_E_};{10};{8};{_E_};[Size: 13 Num.Elems.: 9]",
				a.toString());
	}

}
