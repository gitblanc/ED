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
	 * Para medir algoritmos de orden n (lineal), siendo la n pasada la carga de
	 * trabajo
	 * 
	 * @param n
	 */
	public void linear(int n) {
		for (int i = 0; i < n; i++) {
			doNothing();
		}
	}

	public void quadratic(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				doNothing();
			}
		}
	}

	public void cubic(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					doNothing();
				}
			}
		}
	}

	// Dormimos el hilo de ejecución en el método 250 ms
	private int doNothing() {
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

	public static int recursivePow(int base, int exponente) {
		if (exponente < 0)
			throw new IllegalArgumentException("Parámetro no aceptado");

		if (exponente == 0)
			return 1;

		return recursivePow(base, exponente - 1) * base;
	}

	public static int iterativePow(int base, int exponente) {
		if (exponente < 0)
			throw new IllegalArgumentException("Parámetro no aceptado");

		int res = 1;
		for (int i = 1; i <= exponente; i++) {
			res = res * base;
		}

		return res;
	}

	public static int recursiveFactorial(int number) {
		if (number < 0)
			throw new IllegalArgumentException("Parámetro no aceptado");

		if (number == 0)
			return 1;
		return recursiveFactorial(number - 1) * number;
	}

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

	// CUIDADO: Pasas el termino, no el numero (termino 2, no número 2)
	public static int recursiveFibonacci(int termino) {
		if (termino < 1)
			throw new IllegalArgumentException("Parámetro no aceptado");

		if (termino == 1)
			return 0;

		if (termino == 2)
			return 1;

		return recursiveFibonacci(termino - 1) + recursiveFibonacci(termino - 2);
	}

	public static int iterativeFibonacci(int termino) {

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
