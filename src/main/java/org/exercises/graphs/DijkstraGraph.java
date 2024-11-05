package org.exercises.graphs;

import java.util.*;

public class DijkstraGraph {

    public static void main(String[] args) {
        Graph g = new Graph(9);

        // Adding edges to create the graph
        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 2, 8);
        g.addEdge(1, 7, 11);
        g.addEdge(2, 3, 7);
        g.addEdge(2, 8, 2);
        g.addEdge(2, 5, 4);
        g.addEdge(3, 4, 9);
        g.addEdge(3, 5, 14);
        g.addEdge(4, 5, 10);
        g.addEdge(5, 6, 2);
        g.addEdge(6, 7, 1);
        g.addEdge(6, 8, 6);
        g.addEdge(7, 8, 7);

        g.displayAdjList();
        g.shortestPath(0, 4);
    }

    static class Graph {

        private final int vertex;
        private final List<List<VertexWeight>> adjecencyList;

        public Graph(int vertex) {
            this.vertex = vertex;
            this.adjecencyList = new ArrayList<>(vertex);
            for (int i = 0; i < vertex; i++) {
                this.adjecencyList.add(new ArrayList<>());
            }
        }

        public void addEdge(int u, int v, int weight) {
            // Undirected graph (u and v connected by a weighted edge)
            adjecencyList.get(u).add(VertexWeight.of(v, weight));
            adjecencyList.get(v).add(VertexWeight.of(u, weight));
        }

        public void shortestPath(int source) {
            Result result = executeDijkstra(source);
            List<Integer> dist = result.dist();
            System.out.println("Shortest distance from source: " + source);
            for (int i = 0; i < this.vertex; i++) {
                System.out.println(i + "\t" + dist.get(i));
            }
        }

        public void shortestPath(int source, int target) {
            Result result = executeDijkstra(source);
            List<Integer> prev = result.prev();
            List<Integer> dist = result.dist();

            System.out.println("Shortest path from source: " + source + " to target: " + target);
            System.out.println("Distance: " + dist.get(target));

            int parent = prev.get(target);
            if (parent == -1) {
                System.out.println("Path: No path exists");
            } else {
                System.out.print(target);
                while (parent != source) {
                    System.out.print(parent);
                    parent = prev.get(parent);
                }
                System.out.print(source);
            }
        }

        private Result executeDijkstra(int source) {
            PriorityQueue<VertexWeight> pq = new PriorityQueue<>();
            List<Integer> dist = new ArrayList<>(Collections.nCopies(this.vertex, Integer.MAX_VALUE));
            List<Integer> prev = new ArrayList<>(Collections.nCopies(this.vertex, -1));

            dist.set(source, 0);
            pq.add(VertexWeight.of(source, 0));

            while (!pq.isEmpty()) {
                int u = pq.poll().vertex;

                for (VertexWeight neighbor : adjecencyList.get(u)) {
                    int v = neighbor.vertex;
                    int weight = neighbor.weight; // If we had negative weights, we will be running on cyclic path.

                    // Relaxation
                    if (dist.get(v) > dist.get(u) + weight) {
                        dist.set(v, dist.get(u) + weight);
                        prev.set(v, u);
                        pq.add(VertexWeight.of(v, dist.get(v)));
                    }
                }
            }

            return new Result(dist, prev);
        }

        private record Result(List<Integer> dist, List<Integer> prev) {
        }

        // Method to display the adjacency list
        public void displayAdjList() {
            for (int i = 0; i < this.adjecencyList.size(); i++) {
                System.out.print(i + ": "); // Print the vertex
                for (VertexWeight j : this.adjecencyList.get(i)) {
                    System.out.print(j + " "); // Print its adjacent
                }
                System.out.println();
            }
        }

        @Override
        public String toString() {
            return this.adjecencyList.toString();
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
