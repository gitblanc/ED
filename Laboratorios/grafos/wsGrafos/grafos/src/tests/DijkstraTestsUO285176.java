package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import main.Grafos;

class DijkstraTestsUO285176 {

	@Test
	void testDijkstraEjercicioTeoria1() {
		Grafos<Integer> graph = new Grafos<Integer>(6);

		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.addNode(6);

		graph.addEdge(1, 3, 4);
		graph.addEdge(1, 2, 3);
		graph.addEdge(1, 5, 8);

		graph.addEdge(2, 5, 5);
		graph.addEdge(3, 5, 3);

		graph.addEdge(5, 4, 7);
		graph.addEdge(5, 6, 3);

		graph.addEdge(6, 4, 2);

		/*** COMPARO EL VECTOR DE D (COSTES) ***********/
		Assert.assertArrayEquals(new double[] { 0.0, 3.0, 4.0, 12.0, 7.0, 10.0 }, graph.dijkstra(1).getdDijkstra(), 0);
//		FALTA
		//Assert.assertArrayEquals(new int[]{-1, -1, -1, 6, 3, 5}, graph.dijkstra(1).getpDijkstra());
	}

}
