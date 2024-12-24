package com.mycompany.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TopologicalSort<T> {
    public List<T> khansAlgorithm(Graph<T> graph) {
        List<T> sortedList = new ArrayList<>();
        Queue<T> queue = new LinkedList<>();
        Map<T, Integer> degrees = new HashMap<>();
        
        // calculates the degrees of the vertices in the graph
        for (T vertex: graph.getVertices()) {
            if (!degrees.containsKey(vertex)) {
                degrees.put(vertex, 0);
            }
            for (T neighbor: graph.getNeighbors(vertex)) {
                if (degrees.containsKey(neighbor)) {
                    degrees.put(neighbor, degrees.get(neighbor) + 1);
                } else {
                    degrees.put(neighbor, 1);
                }
            }
        }

        // adds to queue the vertices with degree 0
        for (T vertex: degrees.keySet()) {
            if (degrees.get(vertex) == 0) {
                queue.add(vertex);
            }
        }

        while (!queue.isEmpty()) {
            T vertex = queue.poll();

            for (T neighbor: graph.getNeighbors(vertex)) {
                degrees.put(neighbor, degrees.get(neighbor) - 1);

                if (degrees.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }

            sortedList.add(vertex);
        }

        if (sortedList.size() != graph.getVertices().size()) {
            // cycle was found
            return null;
        }

        return sortedList;
    }

}
