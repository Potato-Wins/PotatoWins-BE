package com.example.potatowinsbe.domain.elk.service;

import com.example.potatowinsbe.domain.elk.entity.SensorData;
import com.example.potatowinsbe.domain.elk.repository.SensorDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SensorDataService {

    private static final Logger logger = LoggerFactory.getLogger(SensorDataService.class);

    private final SensorDataRepository sensorDataRepository;

    public SensorDataService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    /**
     * Retrieves all sensor data as a list.
     */
    public List<SensorData> getAllSensorData() {
        // Use Pageable.unpaged() for ElasticsearchRepository
        Page<SensorData> page = sensorDataRepository.findAll(Pageable.unpaged());
        List<SensorData> allData = page.getContent();

        // Logging the size of the fetched data
        logger.info("Fetched {} records from Elasticsearch", allData.size());
        return allData;
    }

    /**
     * Retrieves sensor data filtered by applicationName.
     */
    public List<SensorData> findByApplicationName(String applicationName) {
        List<SensorData> data = sensorDataRepository.findByApplicationName(applicationName);

        // Logging the size of the fetched data
        logger.info("Fetched {} records with applicationName '{}'", data.size(), applicationName);
        return data;
    }
}
