import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AlgoritmosTests {
	// tests de recursivePow
	@Test
	void testRecursivePow1() {
		try {
			assertEquals(1, Algoritmos.recursivePow(0));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 1");
			throw e;
		}
	}

	@Test
	void testRecursivePow2() {
		try {
			assertEquals(8, Algoritmos.recursivePow(3));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 8");
			throw e;
		}
	}

	@Test
	void testRecursivePow3() throws IllegalArgumentException {
		try {
			Algoritmos.recursivePow(-3);

			// Si no se lanza la excepci�n, el test fallar�
			fail("Se esperaba una excepci�n IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Verificar que la excepci�n tenga el mensaje correcto
			assertEquals("Par�metro no aceptado", e.getMessage());
		}
	}

	// tests de recursivePow1
	@Test
	void testRecursivePow1_1() {
		try {
			assertEquals(1, Algoritmos.recursivePow1(0));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 1");
			throw e;
		}
	}

	@Test
	void testRecursivePow1_2() {
		try {
			assertEquals(8, Algoritmos.recursivePow1(3));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 8");
			throw e;
		}
	}

	@Test
	void testRecursivePow1_3() throws IllegalArgumentException {
		try {
			Algoritmos.recursivePow1(-3);

			// Si no se lanza la excepci�n, el test fallar�
			fail("Se esperaba una excepci�n IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Verificar que la excepci�n tenga el mensaje correcto
			assertEquals("Par�metro no aceptado", e.getMessage());
		}
	}

	// tests de recursivePow2
	@Test
	void testRecursivePow2_1() {
		try {
			assertEquals(1, Algoritmos.recursivePow2(0));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 1");
			throw e;
		}
	}

	@Test
	void testRecursivePow2_2() {
		try {
			assertEquals(8, Algoritmos.recursivePow2(3));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 8");
			throw e;
		}
	}

	@Test
	void testRecursivePow2_3() throws IllegalArgumentException {
		try {
			Algoritmos.recursivePow2(-3);

			// Si no se lanza la excepci�n, el test fallar�
			fail("Se esperaba una excepci�n IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Verificar que la excepci�n tenga el mensaje correcto
			assertEquals("Par�metro no aceptado", e.getMessage());
		}
	}

	// tests de recursivePow3
	@Test
	void testRecursivePow3_1() {
		try {
			assertEquals(1, Algoritmos.recursivePow3(0));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 1");
			throw e;
		}
	}

	@Test
	void testRecursivePow3_2() {
		try {
			assertEquals(8, Algoritmos.recursivePow3(3));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 8");
			throw e;
		}
	}

	@Test
	void testRecursivePow3_3() throws IllegalArgumentException {
		try {
			Algoritmos.recursivePow3(-3);

			// Si no se lanza la excepci�n, el test fallar�
			fail("Se esperaba una excepci�n IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Verificar que la excepci�n tenga el mensaje correcto
			assertEquals("Par�metro no aceptado", e.getMessage());
		}
	}

	// tests de recursivePow (idicando base y exponente)
	@Test
	void testRecursivePowBaseExp1() {
		try {
			assertEquals(25, Algoritmos.recursivePow(5, 2));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 25");
			throw e;
		}
	}

	@Test
	void testRecursivePowBaseExp2() {
		try {
			assertEquals(1, Algoritmos.recursivePow(5, 0));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 1");
			throw e;
		}
	}

	@Test
	void testRecursivePowBaseExp3() {
		try {
			Algoritmos.recursivePow(-3, 0);

			// Si no se lanza la excepci�n, el test fallar�
			fail("Se esperaba una excepci�n IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Verificar que la excepci�n tenga el mensaje correcto
			assertEquals("Par�metro no aceptado", e.getMessage());
		}
	}

	@Test
	void testRecursivePowBaseExp4() {
		try {
			Algoritmos.recursivePow(3, -1);

			// Si no se lanza la excepci�n, el test fallar�
			fail("Se esperaba una excepci�n IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Verificar que la excepci�n tenga el mensaje correcto
			assertEquals("Par�metro no aceptado", e.getMessage());
		}
	}

	// tests de iterativePow
	@Test
	void testIterativePow1() {
		try {
			assertEquals(1, Algoritmos.iterativePow(5, 0));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 1");
			throw e;
		}
	}

	@Test
	void testIterativePow2() {
		try {
			assertEquals(125, Algoritmos.iterativePow(5, 3));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 125");
			throw e;
		}
	}

	@Test
	void testIterativePow3() {
		try {
			Algoritmos.iterativePow(3, -1);

			// Si no se lanza la excepci�n, el test fallar�
			fail("Se esperaba una excepci�n IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Verificar que la excepci�n tenga el mensaje correcto
			assertEquals("Par�metro no aceptado", e.getMessage());
		}
	}

	@Test
	void testIterativePow4() {
		try {
			Algoritmos.iterativePow(-3, 1);

			// Si no se lanza la excepci�n, el test fallar�
			fail("Se esperaba una excepci�n IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Verificar que la excepci�n tenga el mensaje correcto
			assertEquals("Par�metro no aceptado", e.getMessage());
		}
	}

	// tests de recursiveFactorial
	@Test
	void testRecursiveFact1() {
		try {
			assertEquals(1, Algoritmos.recursiveFactorial(0));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 1");
			throw e;
		}
	}

	@Test
	void testRecursiveFact2() {
		try {
			assertEquals(120, Algoritmos.recursiveFactorial(5));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 120");
			throw e;
		}
	}

	@Test
	void testRecursiveFact3() {
		try {
			Algoritmos.recursiveFactorial(-3);

			// Si no se lanza la excepci�n, el test fallar�
			fail("Se esperaba una excepci�n IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Verificar que la excepci�n tenga el mensaje correcto
			assertEquals("Par�metro no aceptado", e.getMessage());
		}
	}

	// tests de iterativeFactorial
	@Test
	void testIterativeFact1() {
		try {
			assertEquals(1, Algoritmos.iterativeFactorial(0));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 1");
			throw e;
		}
	}

	@Test
	void testIterativeFact2() {
		try {
			assertEquals(120, Algoritmos.iterativeFactorial(5));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 120");
			throw e;
		}
	}

	@Test
	void testIterativeFact3() {
		try {
			Algoritmos.iterativeFactorial(-3);

			// Si no se lanza la excepci�n, el test fallar�
			fail("Se esperaba una excepci�n IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Verificar que la excepci�n tenga el mensaje correcto
			assertEquals("Par�metro no aceptado", e.getMessage());
		}
	}

	// tests de recursiveFibonacci
	@Test
	void testRecursiveFibo1() {
		try {
			assertEquals(0, Algoritmos.recursiveFibonacci(1));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 0");
			throw e;
		}
	}

	@Test
	void testRecursiveFibo2() {
		try {
			assertEquals(13, Algoritmos.recursiveFibonacci(8));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 13");
			throw e;
		}
	}

	@Test
	void testRecursiveFibo3() {
		try {
			assertEquals(1, Algoritmos.recursiveFibonacci(2));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 1");
			throw e;
		}
	}

	@Test
	void testRecursiveFibo4() {
		try {
			Algoritmos.recursiveFibonacci(-3);

			// Si no se lanza la excepci�n, el test fallar�
			fail("Se esperaba una excepci�n IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Verificar que la excepci�n tenga el mensaje correcto
			assertEquals("Par�metro no aceptado", e.getMessage());
		}
	}

	// tests de iterativeFibonacci
	@Test
	void testIterativeFibo1() {
		try {
			assertEquals(0, Algoritmos.iterativeFibonacci(1));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 0");
			throw e;
		}
	}

	@Test
	void testIterativeFibo2() {
		try {
			assertEquals(13, Algoritmos.iterativeFibonacci(8));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 13");
			throw e;
		}
	}

	@Test
	void testIterativeFibo3() {
		try {
			assertEquals(1, Algoritmos.iterativeFibonacci(2));
		} catch (AssertionError e) {
			System.out.println("El resultado deber�a ser 1");
			throw e;
		}
	}

	@Test
	void testIterativeFibo4() {
		try {
			Algoritmos.iterativeFibonacci(-3);

			// Si no se lanza la excepci�n, el test fallar�
			fail("Se esperaba una excepci�n IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Verificar que la excepci�n tenga el mensaje correcto
			assertEquals("Par�metro no aceptado", e.getMessage());
		}
	}

}
