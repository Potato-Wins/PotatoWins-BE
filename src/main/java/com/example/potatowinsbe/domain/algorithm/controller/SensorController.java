package com.example.potatowinsbe.domain.algorithm.controller;

import com.example.potatowinsbe.domain.algorithm.service.SensorService;
import com.example.potatowinsbe.domain.elk.entity.SensorData;
import com.example.potatowinsbe.domain.elk.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @Autowired
    private SensorDataRepository sensorDataRepository;

    /**
     * 모든 센서 데이터를 반환
     */
    @GetMapping("/sensors")
    public List<SensorData> getAllSensorData() {
        return sensorDataRepository.findAll();
    }

    /**
     * 이상 온도를 가진 데이터를 반환
     */
    @GetMapping("/sensors/abnormal")
    public String monitorAndAdjustTemperature() {
        return sensorService.monitorAndAdjustTemperature();
    }

    /**
     * 특정 온도 기준의 데이터를 확인
     * (예: 온도가 10도 미만이거나 20도 초과하는 데이터)
     */
    @GetMapping("/sensors/temp-range")
    public String checkTemperatureRange() {
        return sensorService.monitorAndAdjustTemperature();
    }
}
