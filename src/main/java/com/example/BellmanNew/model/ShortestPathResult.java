package com.example.BellmanNew.model;

public class ShortestPathResult {
    private String path;
    private int distance;

    public ShortestPathResult() {
        // Empty constructor
    }

    public ShortestPathResult(String path, int distance) {
        this.path = path;
        this.distance = distance;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
