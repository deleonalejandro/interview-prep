package org.exercises.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxProbability {

    public static void main(String[] args) {
        int n = 3;
        int [][] edges = {{0, 1}, {1, 2}, {0, 2}};
        double[] succProb = {0.5, 0.5, 0.3};
        int start = 0;
        int end = 2;

        System.out.println(maxProbability(n, edges, succProb, start, end));
    }

    static class Pair {
        int v;
        double prob;

        public static Pair of(int v, double prob) {
            var p = new Pair();
            p.v = v;
            p.prob = prob;
            return p;
        }

        public double getProb() {
            return prob;
        }
    }

    public static void addEdge(List<List<Pair>> adj, int u, int v, double prob) {
        // Undirected graph
        adj.get(u).add(Pair.of(v, prob));
        adj.get(v).add(Pair.of(u, prob));
    }

    // Using adj list
    public static double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {

        List<Double> dist = new ArrayList<>(Collections.nCopies(n, 0.0));
        dist.set(start_node, 1.0);

        // Create adj list
        List<List<Pair>> adjList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            addEdge(adjList, edges[i][0], edges[i][1], succProb[i]);
        }

        // Bellman-Ford to update vertex on n-1 iterations
        for (int j = 0; j < n - 1; j++) {
            boolean updated = false;
            for (int u = 0; u < n; u++) {
                for (Pair p : adjList.get(u)) {
                    int v = p.v;
                    double prob = p.prob;

                    if (dist.get(v) < dist.get(u) * prob) {
                        dist.set(v, dist.get(u) * prob);
                        updated = true;
                    }
                }
            }

            if (!updated) {
                break;
            }
        }

        return dist.get(end_node);
    }
}
