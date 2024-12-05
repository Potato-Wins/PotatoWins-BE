package com.example.potatowinsbe.domain.elk.controller;

import com.example.potatowinsbe.domain.elk.entity.SensorData;
import com.example.potatowinsbe.domain.elk.service.SensorDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class SensorDataController {

    private static final Logger logger = LoggerFactory.getLogger(SensorDataController.class);

    private final SensorDataService sensorDataService;

    public SensorDataController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

    @GetMapping("/sensor-data")
    public List<SensorData> getAllSensorData() {
        List<SensorData> allData = sensorDataService.getAllSensorData();
        logger.info("Returned {} records to the client", allData.size());
        return allData;
    }

    @GetMapping("/sensor-data/search")
    public List<SensorData> findByApplicationName(@RequestParam String applicationName) {
        List<SensorData> data = sensorDataService.findByApplicationName(applicationName);
        logger.info("Returned {} records for applicationName '{}'", data.size(), applicationName);
        return data;
    }
}

