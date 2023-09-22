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
		// System.out.println(TestBench.testAlgorithm("Algoritmos",
		// "iterativeFactorial", 6));
		TestBench.test("linear.csv", 5, 1, 5, "Algoritmos", "linear");
//		TestBench.test(5, 1, 5, "Algoritmos", "quadratic");
//		TestBench.test(5, 1, 5, "Algoritmos", "cubic");
//		TestBench.test(5, 1, 5, "Algoritmos", "logarithmic");
//		TestBench.test(5, 1, 5, "Algoritmos", "logarithmic");
//		TestBench.test(5, 1, 5, "Algoritmos", "recursivePow");
//		TestBench.test(5, 1, 5, "Algoritmos", "recursivePow1");
//		TestBench.test(5, 1, 5, "Algoritmos", "recursivePow2");
//		TestBench.test(5, 1, 5, "Algoritmos", "recursivePow3");
	}
}
