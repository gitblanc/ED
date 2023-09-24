/**
 * 
 */

/**
 * @author Eduardo Blanco Bielsa - UO285176
 *
 */
public class Algoritmos {

	private static final long SLEEP_TIME = 250;

	// Algoritmos de medición de tiempos

	/**
	 * Algoritmo de orden n (lineal), siendo la n pasada la carga de trabajo
	 * 
	 * @param n
	 */
	public void linear(int n) {
		for (int i = 0; i < n; i++) {
			doNothing();
		}
	}

	/**
	 * Algoritmo de orden n^2 (cuadrática)
	 * 
	 * @param n
	 */
	public void quadratic(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				doNothing();
			}
		}
	}

	/**
	 * Algoritmo de orden n^3 (cúbica)
	 * 
	 * @param n
	 */
	public void cubic(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					doNothing();
				}
			}
		}
	}

	/**
	 * Algoritmo de orden log n (logarítmica)
	 * 
	 * @param n
	 */
	public void logarithmic(int n) {
		while (n > 0) {
			doNothing();
			n = n / 2;
		}
	}

	/**
	 * Método en el que dormimos el hilo de ejecución en el método 250 ms
	 * 
	 * @return 0 si todo está bien o -1 si algo falló
	 */
	private static int doNothing() {
		try {
			Thread.sleep(SLEEP_TIME);
			return 0;
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}

	// ------------------------------------------------------------

	// Resto de algoritmos (iterativos y recursivos)

	/**
	 * Método 0 para calcular la potencia de dos de forma recursiva
	 */
	public static int recursivePow(int exponente) {
		if (exponente < 0)
			throw new IllegalArgumentException("Parámetro no aceptado");

		if (exponente == 0)
			return 1;

		doNothing();

		return recursivePow(exponente - 1) * 2;
	}

	/**
	 * Método 1 para calcular la potencia de dos de forma recursiva
	 */
	public static int recursivePow1(int exponente) {
		if (exponente < 0)
			throw new IllegalArgumentException("Parámetro no aceptado");

		if (exponente == 0)
			return 1;

		doNothing();

		return (recursivePow1(exponente - 1) + recursivePow1(exponente - 1));
	}

	/**
	 * Método 2 para calcular la potencia de dos de forma recursiva
	 */
	public static int recursivePow2(int exponente) {
		if (exponente < 0)
			throw new IllegalArgumentException("Parámetro no aceptado");

		if (exponente == 0)
			return 1;

		doNothing();

		if (exponente % 2 == 0)
			return (recursivePow2(exponente / 2) * recursivePow2(exponente / 2));
		else
			return (recursivePow2(exponente / 2) * recursivePow2(exponente / 2) * 2);
	}

	/**
	 * Método 3 para calcular la potencia de dos de forma recursiva
	 */
	public static long recursivePow3(int exponente) {
		if (exponente < 0)
			throw new IllegalArgumentException("Parámetro no aceptado");

		if (exponente == 0)
			return 1;

		doNothing();

		long result = recursivePow3(exponente / 2);
		if (exponente % 2 == 0)
			return (result * result);
		else
			return (result * result * 2);
	}

	/**
	 * Método para calcular la potencia de una base y un exponente dado de forma
	 * recursiva
	 * 
	 * @param base
	 * @param exponente
	 * @return
	 */
	public static int recursivePow(int base, int exponente) {
		if (exponente < 0 || base < 0)
			throw new IllegalArgumentException("Parámetro no aceptado");

		if (exponente == 0)
			return 1;

		return recursivePow(base, exponente - 1) * base;
	}

	/**
	 * Método para calcular la potencia de una base y un exponente dados de forma
	 * iterativa
	 */
	public static int iterativePow(int base, int exponente) {
		if (exponente < 0 || base < 0)
			throw new IllegalArgumentException("Parámetro no aceptado");

		int res = 1;
		for (int i = 1; i <= exponente; i++) {
			res = res * base;
		}

		return res;
	}

	/**
	 * Método para calcular el fatorial de un número de forma recursiva
	 */
	public static int recursiveFactorial(int number) {
		if (number < 0)
			throw new IllegalArgumentException("Parámetro no aceptado");

		if (number == 0)
			return 1;
		return recursiveFactorial(number - 1) * number;
	}

	/**
	 * Método para calcular el fatorial de un número de forma iterativa
	 */
	public static int iterativeFactorial(int number) {
		if (number < 0)
			throw new IllegalArgumentException("Parámetro no aceptado");

		int res = 1;
		while (number != 0) {
			res *= number;
			number--;
		}

		return res;
	}

	/**
	 * Método para calcular fibonacci de un término de forma recursiva
	 */
	// CUIDADO: Se pasa el termino, no el numero (termino 2, no número 2)
	public static int recursiveFibonacci(int termino) {
		if (termino < 1)
			throw new IllegalArgumentException("Parámetro no aceptado");

		if (termino == 1)
			return 0;

		if (termino == 2)
			return 1;

		return recursiveFibonacci(termino - 1) + recursiveFibonacci(termino - 2);
	}

	/**
	 * Método para calcular fibonacci de un término de forma iterativa
	 */
	public static int iterativeFibonacci(int termino) {
		if (termino < 1)
			throw new IllegalArgumentException("Parámetro no aceptado");
		
		int primero = 0;
		int segundo = 1;

		if (termino == 1)
			return primero;

		for (int i = 3; i <= termino; i++) {
			int siguiente = primero + segundo;
			primero = segundo;
			segundo = siguiente;
		}

		return segundo;
	}

}
