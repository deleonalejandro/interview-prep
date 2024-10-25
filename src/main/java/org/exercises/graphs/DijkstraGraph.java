package org.exercises.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraGraph {

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(1, 2, 24);
        g.addEdge(1, 4, 20);
        g.addEdge(3, 1, 3);
        g.addEdge(4, 3, 12);

        g.shortestPath(1, 4);
    }

    static class Graph {

        private final int vertex;
        private final List<VertexWeight>[] adjecencyList;

        public Graph(int vertex) {
            this.vertex = vertex;
            this.adjecencyList = new ArrayList[vertex];
            for (int i = 0; i < vertex; i++) {
                this.adjecencyList[i] = new ArrayList<>();
            }
        }

        public void addEdge(int u, int v, int weight) {
            adjecencyList[u - 1].add(VertexWeight.of(v, weight));
            adjecencyList[v - 1].add(VertexWeight.of(u, weight));
        }

        public void shortestPath(int source) {
            Result result = executeDijkstra(source);
            int[] dist = result.dist();
            System.out.println("Shortest distance from source: " + source);
            for (int i = 0; i < this.vertex; i++) {
                System.out.println((i + 1) + "\t" + dist[i]);
            }
        }

        public void shortestPath(int source, int target) {
            Result result = executeDijkstra(source);
            int[] prev = result.prev();

            System.out.println("Shortest path from source: " + source + " to target: " + target);
            System.out.println("Distance: " + result.dist()[target - 1]);

            int parent = prev[target - 1];
            int normSource = source - 1;

            if (parent == -1) {
                System.out.println("Path: No path exists");
            } else {
                System.out.print(target);
                while (parent != normSource) {
                    System.out.print("->" + parent);
                    parent = prev[parent];
                }
                System.out.print("->" + source);
            }
        }

        private Result executeDijkstra(int source) {
            PriorityQueue<VertexWeight> pq = new PriorityQueue<>();
            int[] dist = new int[this.vertex];
            int[] prev = new int[this.vertex];
            Arrays.fill(dist, Integer.MAX_VALUE);
            Arrays.fill(prev, -1);

            dist[source - 1] = 0;
            pq.add(VertexWeight.of(source, 0));

            while (!pq.isEmpty()) {
                int u = pq.poll().vertex;

                for (VertexWeight neighbor : this.adjecencyList[u - 1]) {
                    int v = neighbor.vertex;
                    int weight = neighbor.weight;

                    // Relaxation
                    if (dist[v - 1] > dist[u - 1] + weight) {
                        dist[v - 1] = dist[u - 1] + weight;
                        prev[v - 1] = u - 1;
                        pq.add(neighbor);
                    }
                }
            }

            return new Result(dist, prev);
        }

        private record Result(int[] dist, int[] prev) {
        }

        @Override
        public String toString() {
            return Arrays.toString(adjecencyList);
        }

        static class VertexWeight implements Comparable<VertexWeight> {

            final int vertex;
            final int weight;

            private VertexWeight(int vertex, int weight) {
                this.vertex = vertex;
                this.weight = weight;
            }

            public static VertexWeight of(int vertex, int weight) {
                return new VertexWeight(vertex, weight);
            }

            @Override
            public int compareTo(VertexWeight that) {
                return Integer.compare(this.weight, that.weight);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof VertexWeight that)) return false;

                if (vertex != that.vertex) return false;
                return weight == that.weight;
            }

            @Override
            public int hashCode() {
                int result = vertex;
                result = 31 * result + weight;
                return result;
            }

            @Override
            public String toString() {
                return String.format("[%d, %d]", this.vertex, this.weight);
            }
        }
    }
}
