/**
 * 
 */

/**
 * @author Eduardo Blanco Bielsa - UO285176
 *
 */
public class Algoritmos {

	private static final long SLEEP_TIME = 250;

	// Algoritmos de medici�n de tiempos

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
	 * Algoritmo de orden n^2 (cuadr�tica)
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
	 * Algoritmo de orden n^3 (c�bica)
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
	 * Algoritmo de orden log n (logar�tmica)
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
	 * M�todo en el que dormimos el hilo de ejecuci�n en el m�todo 250 ms
	 * 
	 * @return 0 si todo est� bien o -1 si algo fall�
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
	 * M�todo 0 para calcular la potencia de dos de forma recursiva
	 */
	public static int recursivePow(int exponente) {
		if (exponente < 0)
			throw new IllegalArgumentException("Par�metro no aceptado");

		if (exponente == 0)
			return 1;

		doNothing();

		return recursivePow(exponente - 1) * 2;
	}

	/**
	 * M�todo 1 para calcular la potencia de dos de forma recursiva
	 */
	public static int recursivePow1(int exponente) {
		if (exponente < 0)
			throw new IllegalArgumentException("Par�metro no aceptado");

		if (exponente == 0)
			return 1;

		doNothing();

		return (recursivePow1(exponente - 1) + recursivePow1(exponente - 1));
	}

	/**
	 * M�todo 2 para calcular la potencia de dos de forma recursiva
	 */
	public static int recursivePow2(int exponente) {
		if (exponente < 0)
			throw new IllegalArgumentException("Par�metro no aceptado");

		if (exponente == 0)
			return 1;

		doNothing();

		if (exponente % 2 == 0)
			return (recursivePow2(exponente / 2) * recursivePow2(exponente / 2));
		else
			return (recursivePow2(exponente / 2) * recursivePow2(exponente / 2) * 2);
	}

	/**
	 * M�todo 3 para calcular la potencia de dos de forma recursiva
	 */
	public static long recursivePow3(int exponente) {
		if (exponente < 0)
			throw new IllegalArgumentException("Par�metro no aceptado");

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
	 * M�todo para calcular la potencia de una base y un exponente dado de forma
	 * recursiva
	 * 
	 * @param base
	 * @param exponente
	 * @return
	 */
	public static int recursivePow(int base, int exponente) {
		if (exponente < 0 || base < 0)
			throw new IllegalArgumentException("Par�metro no aceptado");

		if (exponente == 0)
			return 1;

		return recursivePow(base, exponente - 1) * base;
	}

	/**
	 * M�todo para calcular la potencia de una base y un exponente dados de forma
	 * iterativa
	 */
	public static int iterativePow(int base, int exponente) {
		if (exponente < 0 || base < 0)
			throw new IllegalArgumentException("Par�metro no aceptado");

		int res = 1;
		for (int i = 1; i <= exponente; i++) {
			res = res * base;
		}

		return res;
	}

	/**
	 * M�todo para calcular el fatorial de un n�mero de forma recursiva
	 */
	public static int recursiveFactorial(int number) {
		if (number < 0)
			throw new IllegalArgumentException("Par�metro no aceptado");

		if (number == 0)
			return 1;
		return recursiveFactorial(number - 1) * number;
	}

	/**
	 * M�todo para calcular el fatorial de un n�mero de forma iterativa
	 */
	public static int iterativeFactorial(int number) {
		if (number < 0)
			throw new IllegalArgumentException("Par�metro no aceptado");

		int res = 1;
		while (number != 0) {
			res *= number;
			number--;
		}

		return res;
	}

	/**
	 * M�todo para calcular fibonacci de un t�rmino de forma recursiva
	 */
	// CUIDADO: Se pasa el termino, no el numero (termino 2, no n�mero 2)
	public static int recursiveFibonacci(int termino) {
		if (termino < 1)
			throw new IllegalArgumentException("Par�metro no aceptado");

		if (termino == 1)
			return 0;

		if (termino == 2)
			return 1;

		return recursiveFibonacci(termino - 1) + recursiveFibonacci(termino - 2);
	}

	/**
	 * M�todo para calcular fibonacci de un t�rmino de forma iterativa
	 */
	public static int iterativeFibonacci(int termino) {
		if (termino < 1)
			throw new IllegalArgumentException("Par�metro no aceptado");
		
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
