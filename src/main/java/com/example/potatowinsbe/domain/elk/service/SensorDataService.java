package com.example.potatowinsbe.domain.elk.service;

import com.example.potatowinsbe.domain.elk.entity.SensorData;
import com.example.potatowinsbe.domain.elk.repository.SensorDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorDataService {

    private final SensorDataRepository sensorDataRepository;

    public SensorDataService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    public List<SensorData> getAllSensorData() {
        return (List<SensorData>) sensorDataRepository.findAll();
    }

    public List<SensorData> findByApplicationName(String applicationName) {
        return sensorDataRepository.findByApplicationName(applicationName);
    }
}
