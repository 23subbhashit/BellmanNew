package com.example.BellmanNew.service;

import com.example.BellmanNew.model.GraphData;
import com.example.BellmanNew.model.ShortestPathResult;
import org.springframework.stereotype.Service;

@Service
public class BellmanFordService {

    public ShortestPathResult computeShortestPath(GraphData graphData) {
        int[] distance = new int[graphData.getVertexCount()];
        int[] parent = new int[graphData.getVertexCount()];

        // Step 1: Initialize distances and parent pointers
        for (int i = 0; i < graphData.getVertexCount(); i++) {
            distance[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        distance[graphData.getSource()] = 0;

        // Step 2: Relax edges repeatedly
        for (int i = 0; i < graphData.getVertexCount() - 1; i++) {
            for (int j = 0; j < graphData.getEdgeCount(); j++) {
                int u = graphData.getEdges()[j].getSource();
                int v = graphData.getEdges()[j].getDestination();
                int weight = graphData.getEdges()[j].getWeight();
                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    parent[v] = u;
                }
            }
        }

        // Step 3: Check for negative weight cycles
        for (int i = 0; i < graphData.getEdgeCount(); i++) {
            int u = graphData.getEdges()[i].getSource();
            int v = graphData.getEdges()[i].getDestination();
            int weight = graphData.getEdges()[i].getWeight();
            if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                return new ShortestPathResult("Graph contains negative weight cycle", -1);
            }
        }

        // Construct the shortest path
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < graphData.getVertexCount(); i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                path.append("Vertex ").append(i).append(": No path\n");
            } else {
                path.append("Vertex ").append(i).append(": ");
                reconstructPath(parent, i, path);
                path.append(", Distance: ").append(distance[i]).append("\n");
            }
        }

        return new ShortestPathResult(path.toString(), 0);
    }

    private void reconstructPath(int[] parent, int vertex, StringBuilder path) {
        if (parent[vertex] != -1) {
            reconstructPath(parent, parent[vertex], path);
            path.append("->");
        }
        path.append(vertex);
    }
}
