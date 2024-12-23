package com.mycompany.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Graph<T> {
    private HashMap<T, Set<T>> graph = new HashMap<>();

    /**
     * 
     * @param vertex
     */
    public void addVertex(T vertex) {
        if (graph.containsKey(vertex)) {
            throw new GraphException("Vertex already exists: " + vertex);
        }

        graph.put(vertex, new HashSet<>());
    }

    /**
     * 
     * @param source
     * @param destination
     * @param isBiDirectional
     */
    public void addEdge(T source, T destination, boolean isBiDirectional) {
        if (!graph.containsKey(source)) {
            throw new GraphException("Source vertex does not exist: " + source);
        }

        if (!graph.containsKey(destination)) {
            throw new GraphException("Destination vertex does not exist: " + destination);
        }

        graph.get(source).add(destination);
        if (isBiDirectional) {
            graph.get(destination).add(source);
        }
    }

    /**
     * 
     * @param vertex
     * @return
     */
    public Set<T> getNeighbors(T vertex) {
        if (!graph.containsKey(vertex)) {
            throw new GraphException("Vertex does not exist: " + vertex);
        }

        return graph.get(vertex);
    }

    /**
     * 
     * @return
     */
    public Set<T> getVertices() {
        return graph.keySet();
    }

    /**
     * 
     * @param vertex
     * @return
     */
    public boolean hasVertex(T vertex) {
        return graph.containsKey(vertex);
    }

    /**
     * 
     * @param source
     * @param destination
     * @return
     */
    public boolean hasEdge(T source, T destination) {
        return graph.containsKey(source) && graph.get(source).contains(destination);
    }

    /**
     * 
     * @param vertex
     */
    public void removeVertex(T vertex) {
        if (!graph.containsKey(vertex)) {
            throw new GraphException("Vertex does not exist: " + vertex);
        }

        for (T inSet: graph.remove(vertex)) {
            graph.get(inSet).remove(vertex);
        }
    }
    
}
