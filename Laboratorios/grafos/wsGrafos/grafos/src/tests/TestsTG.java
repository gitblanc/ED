package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import main.Grafos;

class TestsTG {

	@Test
	void testCentroGrafo() {
		Grafos<Integer> graph = new Grafos<Integer>(5);
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);

		graph.addEdge(1, 2, 1);
		graph.addEdge(2, 3, 2);
		graph.addEdge(3, 4, 2);
		graph.addEdge(4, 3, 2);
		graph.addEdge(3, 5, 4);
		graph.addEdge(5, 4, 5);
		graph.addEdge(4, 2, 1);

		graph.floyd();

		assertEquals(3, graph.centroGrafo());// nodos de 3
	}

	@Test
	void testExcentricidad() {
		Grafos<String> graph = new Grafos<String>(4);
		String a = "A";
		String b = "B";
		String c = "C";
		String d = "D";
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);

		graph.addEdge(a, b, 2);
		graph.addEdge(a, c, 8);
		graph.addEdge(b, c, 3);
		graph.addEdge(c, d, 1);
		graph.addEdge(b, d, 6);
		graph.addEdge(d, b, 2);

		graph.floyd();

		assertEquals(3, graph.excentricidad(b));// nodos de 3
	}

	@Test
	void testGradoEntrada() {
		Grafos<String> graph = new Grafos<String>(4);
		String a = "A";
		String b = "B";
		String c = "C";
		String d = "D";
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);

		graph.addEdge(a, b, 2);
		graph.addEdge(a, c, 8);
		graph.addEdge(b, c, 3);
		graph.addEdge(b, d, 6);
		graph.addEdge(d, b, 2);

		assertEquals(0, graph.calcularGradoEntrada(a));
		assertEquals(2, graph.calcularGradoEntrada(b));
		assertEquals(1, graph.calcularGradoEntrada(d));
	}

	@Test
	void testGradoSalida() {
		Grafos<String> graph = new Grafos<String>(4);
		String a = "A";
		String b = "B";
		String c = "C";
		String d = "D";
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);

		graph.addEdge(a, b, 2);
		graph.addEdge(a, c, 8);
		graph.addEdge(b, c, 3);
		graph.addEdge(b, d, 6);
		graph.addEdge(d, b, 2);

		assertEquals(2, graph.calcularGradoSalida(a));
		assertEquals(2, graph.calcularGradoSalida(b));
		assertEquals(1, graph.calcularGradoSalida(d));
	}

	@Test
	void testGradoNodo() {
		Grafos<String> graph = new Grafos<String>(4);
		String a = "A";
		String b = "B";
		String c = "C";
		String d = "D";
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);

		graph.addEdge(a, b, 2);
		graph.addEdge(a, c, 8);
		graph.addEdge(b, c, 3);
		graph.addEdge(b, d, 6);
		graph.addEdge(d, b, 2);

		assertEquals(2, graph.calcularGradoNodo(a));
		assertEquals(4, graph.calcularGradoNodo(b));
		assertEquals(2, graph.calcularGradoNodo(d));
	}

	@Test
	void testNodoFuente() {
		Grafos<String> graph = new Grafos<String>(4);
		String a = "A";
		String b = "B";
		String c = "C";
		String d = "D";
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);

		graph.addEdge(a, b, 2);
		graph.addEdge(a, c, 8);
		graph.addEdge(b, c, 3);
		graph.addEdge(b, d, 6);
		graph.addEdge(d, b, 2);

		assertEquals(true, graph.esNodoFuente(a));
		assertEquals(false, graph.esNodoFuente(b));
		assertEquals(false, graph.esNodoFuente(c));
	}

	@Test
	void testNodoSumidero() {
		Grafos<String> graph = new Grafos<String>(4);
		String a = "A";
		String b = "B";
		String c = "C";
		String d = "D";
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);

		graph.addEdge(a, b, 2);
		graph.addEdge(a, c, 8);
		graph.addEdge(b, c, 3);
		graph.addEdge(b, d, 6);
		graph.addEdge(d, b, 2);

		assertEquals(false, graph.esNodoSumidero(a));
		assertEquals(false, graph.esNodoSumidero(b));
		assertEquals(true, graph.esNodoSumidero(c));
	}

	@Test
	void testNodoAislado() {
		Grafos<String> graph = new Grafos<String>(5);
		String a = "A";
		String b = "B";
		String c = "C";
		String d = "D";
		String e = "E";
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);
		graph.addNode(e);

		graph.addEdge(a, b, 2);
		graph.addEdge(a, c, 8);
		graph.addEdge(b, c, 3);
		graph.addEdge(b, d, 6);
		graph.addEdge(d, b, 2);

		assertEquals(false, graph.esNodoAislado(a));
		assertEquals(false, graph.esNodoAislado(b));
		assertEquals(false, graph.esNodoAislado(c));
		assertEquals(false, graph.esNodoAislado(d));
		assertEquals(true, graph.esNodoAislado(e));
	}

	@Test
	void testDensidadGrafo() {
		Grafos<String> graph = new Grafos<String>(4);
		String a = "A";
		String b = "B";
		String c = "C";
		String d = "D";
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);

		// d = 0
		assertEquals(0, graph.densidadGrafo());

		graph.addEdge(a, b, 2);
		graph.addEdge(a, c, 8);
		graph.addEdge(b, c, 3);
		graph.addEdge(b, d, 6);
		graph.addEdge(d, b, 2);

		assertEquals(0.83, graph.densidadGrafo());
	}

	@Test
	void testContarSumiderosYFuentesYAisladosEInaccesibles() {
		Grafos<String> graph = new Grafos<String>(5);
		String a = "A";
		String b = "B";
		String c = "C";
		String d = "D";
		String e = "E";
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);

		graph.addEdge(a, b, 2);
		graph.addEdge(a, c, 8);
		graph.addEdge(b, c, 3);
		graph.addEdge(b, d, 6);
		graph.addEdge(d, b, 2);

		assertEquals(1, graph.contarSumideros());
		assertEquals(1, graph.contarFuentes());
		assertEquals(0, graph.contarAislados());
		assertEquals(1, graph.contarInaccesibles());

		graph.addNode(e);
		assertEquals(1, graph.contarAislados());
		assertEquals(2, graph.contarInaccesibles());
	}

	@Test
	void testTieneCiclos() {
		Grafos<String> graph = new Grafos<String>(4);
		String a = "A";
		String b = "B";
		String c = "C";
		String d = "D";
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);

		graph.addEdge(a, b, 2);
		graph.addEdge(b, d, 8);
		graph.addEdge(d, c, 3);
		graph.addEdge(c, a, 6);

		assertEquals(true, graph.tieneCiclos());

		graph.removeEdge(c, a);

		assertEquals(false, graph.tieneCiclos());
	}

	@Test
	void testShortestPathLength() {
		Grafos<String> graph = new Grafos<String>(4);
		String a = "A";
		String b = "B";
		String c = "C";
		String d = "D";
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);

		graph.addEdge(a, b, 2);
		graph.addEdge(b, c, 8);
		graph.addEdge(b, d, 8);
		graph.addEdge(d, b, 8);
		graph.addEdge(c, d, 3);
		graph.addEdge(a, c, 6);

		assertEquals(2, graph.shortestPathLength(a, d));
		assertEquals(2, graph.shortestPathLength(d, c));
		assertEquals(1, graph.shortestPathLength(d, b));
		assertEquals(-1, graph.shortestPathLength(d, a));
	}

	@Test
	void testCaminoCosteMinimo() {
		Grafos<String> graph = new Grafos<String>(4);
		String a = "A";
		String b = "B";
		String c = "C";
		String d = "D";
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);

		graph.addEdge(a, b, 2);
		graph.addEdge(b, c, 3);
		graph.addEdge(b, d, 6);
		graph.addEdge(d, b, 2);
		graph.addEdge(c, d, 1);
		graph.addEdge(a, c, 8);

		assertEquals(6, graph.caminoCosteMinimo(a, d));
		assertEquals(5, graph.caminoCosteMinimo(a, c));
		assertEquals(3, graph.caminoCosteMinimo(c, b));
		assertEquals(5, graph.caminoCosteMinimo(d, c));

	}

	@Test
	void testPrim() {
		Grafos<String> graph = new Grafos<String>(6);
		String a = "A";
		String b = "B";
		String c = "C";
		String d = "D";
		String e = "E";
		String f = "F";
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);
		graph.addNode(e);
		graph.addNode(f);

		graph.addEdge(a, b, 3);
		graph.addEdge(a, c, 3);
		graph.addEdge(a, d, 1);
		graph.addEdge(b, a, 3);
		graph.addEdge(b, c, 1);
		graph.addEdge(b, d, 2);
		graph.addEdge(b, f, 2);
		graph.addEdge(c, a, 3);
		graph.addEdge(c, b, 1);
		graph.addEdge(c, d, 2);
		graph.addEdge(c, e, 2);
		graph.addEdge(c, f, 4);
		graph.addEdge(d, a, 1);
		graph.addEdge(d, b, 2);
		graph.addEdge(d, c, 2);
		graph.addEdge(d, e, 1);
		graph.addEdge(e, c, 2);
		graph.addEdge(e, d, 1);
		graph.addEdge(f, b, 2);
		graph.addEdge(f, c, 4);

		List<String> prim = graph.prim(a);

		assertEquals(a, prim.get(0));
		assertEquals(d, prim.get(1));
		assertEquals(e, prim.get(2));
		assertEquals(c, prim.get(3));
		assertEquals(b, prim.get(4));
		assertEquals(f, prim.get(5));
	}

	@Test
	void testPrim2() {
		Grafos<Integer> graph = new Grafos<Integer>(6);
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.addNode(6);

		graph.addEdge(1, 2, 1);
		graph.addEdge(1, 3, 1);
		graph.addEdge(1, 4, 6);
		graph.addEdge(2, 1, 1);
		graph.addEdge(2, 5, 3);
		graph.addEdge(3, 1, 1);
		graph.addEdge(3, 4, 5);
		graph.addEdge(3, 5, 6);
		graph.addEdge(3, 6, 4);
		graph.addEdge(4, 1, 6);
		graph.addEdge(4, 3, 5);
		graph.addEdge(4, 6, 2);
		graph.addEdge(5, 2, 3);
		graph.addEdge(5, 3, 6);
		graph.addEdge(5, 6, 6);
		graph.addEdge(6, 3, 4);
		graph.addEdge(6, 4, 2);
		graph.addEdge(6, 5, 6);

		List<Integer> prim = graph.prim(4);

		assertEquals(4, prim.get(0));
		assertEquals(6, prim.get(1));
		assertEquals(3, prim.get(2));
		assertEquals(1, prim.get(3));
		assertEquals(2, prim.get(4));
		assertEquals(5, prim.get(5));
	}
}
