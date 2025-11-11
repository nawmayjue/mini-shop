package org.nmj;

    import java.util.*;

public class DijkstrasAlgorithm {

    // Node class to store vertex and its distance from source
    static class Node implements Comparable<Node> {
        int vertex;
        int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    /**
     * Implements Dijkstra's algorithm to find shortest paths from a source.
     *
     * @param V The number of vertices in the graph.
     * @param adj An adjacency list where adj[i] contains a list of Node objects
     *            representing neighbors of vertex i and the weight of the edge to them.
     * @param source The starting vertex for finding shortest paths.
     * @return An array where dist[i] stores the shortest distance from 'source' to vertex i.
     */
    public int[] dijkstra(int V, List<List<Node>> adj, int source) {
        int[] dist = new int[V]; // Stores shortest distance from source to each vertex
        Arrays.fill(dist, Integer.MAX_VALUE); // Initialize all distances to infinity
        dist[source] = 0; // Distance from source to itself is 0

        // Priority queue to store nodes to be processed, ordered by distance
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int u = currentNode.vertex;
            int currentDist = currentNode.distance;

            // If we found a shorter path to 'u' already, skip this outdated entry
            if (currentDist > dist[u]) {
                continue;
            }

            // Iterate over neighbors of 'u'
            for (Node neighbor : adj.get(u)) {
                int v = neighbor.vertex;
                int weight = neighbor.distance; // 'distance' in Node here represents edge weight

                // Relaxation step: if a shorter path to 'v' is found through 'u'
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v])); // Add or update 'v' in priority queue
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int V = 5; // Number of vertices
        List<List<Node>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges: (source, destination, weight)
        adj.get(0).add(new Node(1, 2));
        adj.get(0).add(new Node(2, 4));
        adj.get(1).add(new Node(2, 1));
        adj.get(1).add(new Node(3, 7));
        adj.get(2).add(new Node(4, 3));
        adj.get(3).add(new Node(4, 2));

        DijkstrasAlgorithm dijkstra = new DijkstrasAlgorithm();
        int sourceVertex = 0;
        int[] shortestDistances = dijkstra.dijkstra(V, adj, sourceVertex);

        System.out.println("Shortest distances from source " + sourceVertex + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("To vertex " + i + ": " + shortestDistances[i]);
        }
    }
}

