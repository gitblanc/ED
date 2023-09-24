import java.io.IOException;

/**
 * 
 */

/**
 * @author Eduardo Blanco Bielsa - UO285176
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println(TestBench.testAlgorithm("Algoritmos", "iterativeFactorial", 6));
		// GRÁFICOS
		TestBench.test("linear.csv", 5, 1, 15, "Algoritmos", "linear");
		TestBench.test("quadratic.csv", 5, 1, 10, "Algoritmos", "quadratic");
		TestBench.test("cubic.csv", 5, 1, 10, "Algoritmos", "cubic");
		TestBench.test("logarithmic.csv", 5, 1, 50, "Algoritmos", "logarithmic");
		TestBench.test("recursivePow.csv", 5, 1, 15, "Algoritmos", "recursivePow");
		TestBench.test("recursivePow1.csv", 5, 1, 10, "Algoritmos", "recursivePow1");
		TestBench.test("recursivePow2.csv", 5, 1, 30, "Algoritmos", "recursivePow2");
		TestBench.test("recursivePow3.csv", 5, 1, 30, "Algoritmos", "recursivePow3");
	}
}
