import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 
 */

/**
 * @author Eduardo Blanco Bielsa - UO285176
 *
 */
public class TestBench {

	// className: Nombre clase
	// methodName:Nombre método
	// n: parámetro que pasamos al método (carga de trabajo)
	public static Object testAlgorithm(String className, String methodName, int n) {
		try {
			Object obj = Class.forName(className).getDeclaredConstructor().newInstance();
			Method method = obj.getClass().getMethod(methodName, int.class);
			return method.invoke(obj, n);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	// Cronómetro
	public static void test(String output, int times, int startN, int endN, String className, String methodName)
			throws IOException {
		FileWriter file = null;
		PrintWriter pw;
		try {
			file = new FileWriter(output);
			pw = new PrintWriter(file);
			for (int workLoad = startN; workLoad < endN; workLoad++) {
				long startTime = System.currentTimeMillis();

				for (int time = 0; time < times; time++)
					testAlgorithm(className, methodName, workLoad);

				long finalTime = System.currentTimeMillis();
				long timeResult = ((finalTime - startTime) / times);
				// System.out.println("Carga" + workLoad + ", " + " Tiempo: " + timeResult);
				pw.println(workLoad + ";" + timeResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("El fichero " + output + " ya se escribi�!");
			if (file != null)
				file.close();
		}
	}
}
