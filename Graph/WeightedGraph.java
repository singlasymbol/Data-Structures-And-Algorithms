import java.util.*;

class WeightedGraph {

		static class Edge {
			int source;
			int destination;
			int weight;

			Edge(int source, int destination, int weight) {
				this.source = source;
				this.destination = destination;
				this.weight = weight;
			}
		}

		static class Graph {
			int vertices;
			LinkedList<Edge> [] adjacencyList;

			Graph(int vertices) {
				this.vertices = vertices;
				adjacencyList = new LinkedList[vertices];

				for(int i = 0 ;i < vertices; i++) {
					adjacencyList[i] = new LinkedList<Edge>();
				}
			}

			public void addEdge(int source, int destination, int weight) {
				Edge edge = new Edge(source, destination, weight);
				adjacencyList[source].add(edge);
			}

			public void printGraph() {
				for(int i = 0; i < vertices; i++) {
					LinkedList<Edge> temp = adjacencyList[i];

					for(int j = 0 ; j < temp.size(); j++) {
						Edge edge = temp.get(j);
						System.out.println("source "+ i + " destination " + edge.destination + " weight " + edge.weight);
					}
				}
			}

		}

		public static void main(String[] args) {
			Graph gh = new Graph(5);

			gh.addEdge( 0, 1, 4);
			gh.addEdge( 1, 2, 1);
			gh.addEdge( 1, 3, 2);
			gh.addEdge( 3, 4, 3);
			gh.addEdge( 2, 4, 4);

			gh.printGraph();

		}

}