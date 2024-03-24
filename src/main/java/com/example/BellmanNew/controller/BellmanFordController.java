package com.example.BellmanNew.controller;

import com.example.BellmanNew.model.GraphData;
import com.example.BellmanNew.model.ShortestPathResult;
import com.example.BellmanNew.service.BellmanFordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BellmanFordController {

    @Autowired
    private BellmanFordService bellmanFordService;

    @PostMapping("/shortest-path")
    public ShortestPathResult computeShortestPath(@RequestBody GraphData graphData) {
        return bellmanFordService.computeShortestPath(graphData);
    }
}
