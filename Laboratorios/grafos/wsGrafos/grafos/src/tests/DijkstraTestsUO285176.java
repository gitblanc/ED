package tests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import main.Grafos;

class DijkstraTestsUO285176 {

	// Hay que fijarse en poner para la matriz P siempre uno menos (Si a papel es 3,
	// aquí es 2...)
	@Test
	void testDijkstra1() {
		Grafos<Integer> graph = new Grafos<Integer>(7);
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.addNode(6);

		graph.addEdge(1, 3, 4);
		graph.addEdge(1, 5, 8);
		graph.addEdge(1, 2, 3);
		graph.addEdge(2, 5, 5);
		graph.addEdge(3, 5, 3);
		graph.addEdge(5, 4, 7);
		graph.addEdge(5, 6, 3);
		graph.addEdge(6, 4, 2);

		Assert.assertArrayEquals(new double[] { 0.0, 3.0, 4.0, 12.0, 7.0, 10.0 }, graph.dijkstra(1).getdDijkstra(), 0);
		Assert.assertArrayEquals(new int[] { -1, -1, -1, 5, 2, 4 }, graph.dijkstra(1).getpDijkstra());
	}

	@Test
	void testDijkstra2() {
		Grafos<Integer> graph = new Grafos<Integer>(5);
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.addEdge(1, 2, 10);
		graph.addEdge(1, 4, 30);
		graph.addEdge(1, 5, 100);
		graph.addEdge(2, 3, 50);
		graph.addEdge(3, 5, 10);
		graph.addEdge(4, 3, 20);
		graph.addEdge(4, 5, 60);
		Assert.assertArrayEquals(new double[] { 0.0, 10.0, 50.0, 30.0, 60.0 }, graph.dijkstra(1).getdDijkstra(), 0);
		Assert.assertArrayEquals(new int[] { -1, -1, 3, -1, 2 }, graph.dijkstra(1).getpDijkstra());
	}
}
