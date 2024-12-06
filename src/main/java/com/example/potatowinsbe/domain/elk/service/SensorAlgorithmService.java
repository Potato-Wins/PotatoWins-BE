package com.example.potatowinsbe.domain.elk.service;

import com.example.potatowinsbe.domain.elk.entity.SensorData;
import com.example.potatowinsbe.domain.elk.repository.SensorDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SensorAlgorithmService {

    private static final Logger logger = LoggerFactory.getLogger(SensorAlgorithmService.class);

    private final SensorDataRepository sensorDataRepository;

    private static final double MIN_TEMP = 10.0;
    private static final double MAX_TEMP = 20.0;

    public SensorAlgorithmService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    /**
     * 장치별 최신 온도 데이터를 조회하고 JSON 형식으로 응답을 반환합니다.
     */
    public List<Map<String, Object>> monitorAndAdjustTemperature() {
        List<SensorData> latestData = findLatestSensorData();
        return latestData.stream()
                .map(data -> {
                    Map<String, Object> deviceInfo = new HashMap<>();
                    deviceInfo.put("deviceName", data.getDeviceName());
                    Double temperature = data.getTemp();
                    if (temperature == null) {
                        deviceInfo.put("status", "온도 데이터가 없습니다.");
                    } else {
                        deviceInfo.put("temp", temperature);
                        if (temperature < MIN_TEMP) {
                            double adjustment = MIN_TEMP - temperature;
                            deviceInfo.put("status", String.format("적정 온도 도달을 위해 %.2f°C 가열 필요.", adjustment));
                        } else if (temperature > MAX_TEMP) {
                            double adjustment = temperature - MAX_TEMP;
                            deviceInfo.put("status", String.format("적정 온도 도달을 위해 %.2f°C 냉각 필요.", adjustment));
                        } else {
                            deviceInfo.put("status", "이상이 없습니다.");
                        }
                    }
                    return deviceInfo;
                })
                .collect(Collectors.toList());
    }

    private List<SensorData> findLatestSensorData() {
        logger.info("데이터베이스에서 모든 데이터를 조회 중...");

        List<SensorData> allData = StreamSupport.stream(
                sensorDataRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).spliterator(),
                false
        ).collect(Collectors.toList());

        logger.info("총 {}개의 데이터가 조회되었습니다.", allData.size());

        return allData.stream()
                .collect(Collectors.groupingBy(SensorData::getDeviceName))
                .values()
                .stream()
                .map(deviceData -> deviceData.stream()
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("데이터가 존재하지 않습니다.")))
                .collect(Collectors.toList());
    }
}
