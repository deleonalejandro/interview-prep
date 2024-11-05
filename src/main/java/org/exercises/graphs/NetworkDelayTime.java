package org.exercises.graphs;

import java.util.*;

public class NetworkDelayTime {

    public static void main(String[] args) {
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int k = 2;
        int n = 4;

        System.out.println(networkDelayTime(times, n, k));
    }

    static class Pair {
        int v;
        int t;

        public static Pair of(int v, int t) {
            var p = new Pair();
            p.v = v;
            p.t = t;
            return p;
        }

        public int getT() {
            return t;
        }
    }

    static void addEdge(List<List<Pair>> adjList, int[] timeEdge) {
        // Directed graph
        adjList.get(timeEdge[0] - 1).add(Pair.of(timeEdge[1] - 1, timeEdge[2]));
    }

    public static int networkDelayTime(int[][] times, int n, int k) {
        List<List<Pair>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] row : times) {
            addEdge(adjList, row);
        }

        List<Integer> time = new ArrayList<>(Collections.nCopies(n, Integer.MAX_VALUE));
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparing(Pair::getT));
        // Time from source is 0
        time.set(k - 1, 0);
        pq.add(Pair.of(k - 1, 0));

        while(!pq.isEmpty()) {
            int u = pq.poll().v;

            for (Pair p : adjList.get(u)) {
                int v = p.v;
                int t = p.t;
                if (time.get(v) > time.get(u) + t) {
                    time.set(v, time.get(u) + t);
                    pq.add(Pair.of(v, time.get(v)));
                }
            }
        }

        Collections.sort(time);
        return time.get(n - 1) == Integer.MAX_VALUE ? -1 : time.get(n - 1);
    }
}
