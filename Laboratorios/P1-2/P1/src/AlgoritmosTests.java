import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AlgoritmosTests {

	@Test
	void testRecursivePow1() {
		try {
			assertEquals(1, Algoritmos.recursivePow(0));
		} catch (AssertionError e) {
			System.out.println("El resultado debería ser 1");
			throw e;
		}
	}

	@Test
	void testRecursivePow2() {
		try {
			assertEquals(8, Algoritmos.recursivePow(3));
		} catch (AssertionError e) {
			System.out.println("El resultado debería ser 8");
			throw e;
		}
	}

	@Test
	void testRecursivePow3() throws IllegalArgumentException {
		try {
			Algoritmos.recursivePow(-3);

			// Si no se lanza la excepción, el test fallará
			fail("Se esperaba una excepción IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Verificar que la excepción tenga el mensaje correcto
			assertEquals("Parámetro no aceptado", e.getMessage());
		}
	}

	@Test
	void testRecursivePow1_1() {
		try {
			assertEquals(1, Algoritmos.recursivePow1(0));
		} catch (AssertionError e) {
			System.out.println("El resultado debería ser 1");
			throw e;
		}
	}

	@Test
	void testRecursivePow1_2() {
		try {
			assertEquals(8, Algoritmos.recursivePow1(3));
		} catch (AssertionError e) {
			System.out.println("El resultado debería ser 8");
			throw e;
		}
	}
	
	@Test
	void testRecursivePow1_3() throws IllegalArgumentException {
		try {
			Algoritmos.recursivePow1(-3);

			// Si no se lanza la excepción, el test fallará
			fail("Se esperaba una excepción IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Verificar que la excepción tenga el mensaje correcto
			assertEquals("Parámetro no aceptado", e.getMessage());
		}
	}

	@Test
	void testRecursivePow2_1() {
		try {
			assertEquals(1, Algoritmos.recursivePow2(0));
		} catch (AssertionError e) {
			System.out.println("El resultado debería ser 1");
			throw e;
		}
	}

	@Test
	void testRecursivePow2_2() {
		try {
			assertEquals(8, Algoritmos.recursivePow2(3));
		} catch (AssertionError e) {
			System.out.println("El resultado debería ser 8");
			throw e;
		}
	}
	
	@Test
	void testRecursivePow2_3() throws IllegalArgumentException {
		try {
			Algoritmos.recursivePow2(-3);

			// Si no se lanza la excepción, el test fallará
			fail("Se esperaba una excepción IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Verificar que la excepción tenga el mensaje correcto
			assertEquals("Parámetro no aceptado", e.getMessage());
		}
	}

	@Test
	void testRecursivePow3_1() {
		try {
			assertEquals(1, Algoritmos.recursivePow3(0));
		} catch (AssertionError e) {
			System.out.println("El resultado debería ser 1");
			throw e;
		}
	}

	@Test
	void testRecursivePow3_2() {
		try {
			assertEquals(8, Algoritmos.recursivePow3(3));
		} catch (AssertionError e) {
			System.out.println("El resultado debería ser 8");
			throw e;
		}
	}
	
	@Test
	void testRecursivePow3_3() throws IllegalArgumentException {
		try {
			Algoritmos.recursivePow3(-3);

			// Si no se lanza la excepción, el test fallará
			fail("Se esperaba una excepción IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Verificar que la excepción tenga el mensaje correcto
			assertEquals("Parámetro no aceptado", e.getMessage());
		}
	}

	@Test
	void testIterativePow1() {
		try {
			assertEquals(1, Algoritmos.iterativePow(5, 0));
		} catch (AssertionError e) {
			System.out.println("El resultado debería ser 1");
			throw e;
		}
	}

	@Test
	void testIterativePow2() {
		try {
			assertEquals(125, Algoritmos.iterativePow(5, 3));
		} catch (AssertionError e) {
			System.out.println("El resultado debería ser 125");
			throw e;
		}
	}

	@Test
	void testRecursiveFact1() {
		try {
			assertEquals(1, Algoritmos.recursiveFactorial(0));
		} catch (AssertionError e) {
			System.out.println("El resultado debería ser 1");
			throw e;
		}
	}

	@Test
	void testRecursiveFact2() {
		try {
			assertEquals(120, Algoritmos.recursiveFactorial(5));
		} catch (AssertionError e) {
			System.out.println("El resultado debería ser 120");
			throw e;
		}
	}

	@Test
	void testIterativeFact1() {
		try {
			assertEquals(1, Algoritmos.iterativeFactorial(0));
		} catch (AssertionError e) {
			System.out.println("El resultado debería ser 1");
			throw e;
		}
	}

	@Test
	void testIterativeFact2() {
		try {
			assertEquals(120, Algoritmos.iterativeFactorial(5));
		} catch (AssertionError e) {
			System.out.println("El resultado debería ser 120");
			throw e;
		}
	}

	@Test
	void testRecursiveFibo1() {
		try {
			assertEquals(0, Algoritmos.recursiveFibonacci(1));
		} catch (AssertionError e) {
			System.out.println("El resultado debería ser 0");
			throw e;
		}
	}

	@Test
	void testRecursiveFibo2() {
		try {
			assertEquals(13, Algoritmos.recursiveFibonacci(8));
		} catch (AssertionError e) {
			System.out.println("El resultado debería ser 13");
			throw e;
		}
	}

	@Test
	void testRecursiveFibo3() {
		try {
			assertEquals(1, Algoritmos.recursiveFibonacci(2));
		} catch (AssertionError e) {
			System.out.println("El resultado debería ser 1");
			throw e;
		}
	}

	@Test
	void testIterativeFibo1() {
		try {
			assertEquals(0, Algoritmos.iterativeFibonacci(1));
		} catch (AssertionError e) {
			System.out.println("El resultado debería ser 0");
			throw e;
		}
	}

	@Test
	void testIterativeFibo2() {
		try {
			assertEquals(13, Algoritmos.iterativeFibonacci(8));
		} catch (AssertionError e) {
			System.out.println("El resultado debería ser 13");
			throw e;
		}
	}

	@Test
	void testIterativeFibo3() {
		try {
			assertEquals(1, Algoritmos.iterativeFibonacci(2));
		} catch (AssertionError e) {
			System.out.println("El resultado debería ser 1");
			throw e;
		}
	}

}
