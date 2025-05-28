package com.zoo.presentation.controller;

import com.zoo.application.service.ZooStatisticsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class ZooStatisticsController {

    private final ZooStatisticsService statisticsService;

    public ZooStatisticsController(ZooStatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public Map<String, Object> getStatistics() {
        return statisticsService.getZooStatistics();
    }
}
