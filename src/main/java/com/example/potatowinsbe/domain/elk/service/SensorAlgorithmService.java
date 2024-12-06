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

    // 임계값 설정
    private static final double MIN_TEMP = 10.0;
    private static final double MAX_TEMP = 20.0;
    private static final double MIN_SALT = 0.0;  // 무지개송어 민물 환경의 최소 염도
    private static final double MAX_SALT = 5.0;  // 무지개송어 민물 환경의 최대 염도
    private static final double MIN_PH = 6.5;    // 무지개송어 환경의 최소 pH
    private static final double MAX_PH = 8.5;    // 무지개송어 환경의 최대 pH

    public SensorAlgorithmService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    /**
     * 장치별 최신 데이터를 조회하고 각 항목에 대해 JSON 형식으로 응답을 생성합니다.
     */
    public List<Map<String, Object>> monitorAndAdjustMetrics() {
        List<SensorData> latestData = findLatestSensorData();
        return latestData.stream()
                .map(data -> {
                    Map<String, Object> deviceInfo = new HashMap<>();
                    deviceInfo.put("deviceName", data.getDeviceName());

                    // 온도 처리
                    Double temperature = data.getTemp();
                    if (temperature == null) {
                        deviceInfo.put("temp", "온도 데이터가 없습니다.");
                    } else {
                        deviceInfo.put("temp", analyzeAndAdjustMetric("온도", temperature, MIN_TEMP, MAX_TEMP, 1000.0, 15.0));
                    }

                    // 염분 처리
                    Double salt = data.getSalt();
                    if (salt == null) {
                        deviceInfo.put("salt", "염분 데이터가 없습니다.");
                    } else {
                        deviceInfo.put("salt", analyzeAndAdjustMetric("염분", salt, MIN_SALT, MAX_SALT, 1000.0, 0.0));
                    }

                    // pH 처리
                    Double pH = data.getPH();
                    if (pH == null) {
                        deviceInfo.put("pH", "pH 데이터가 없습니다.");
                    } else {
                        deviceInfo.put("pH", analyzeMetric("pH", pH, MIN_PH, MAX_PH));
                    }

                    return deviceInfo;
                })
                .collect(Collectors.toList());
    }

    /**
     * 특정 지표에 대해 분석하고 결과 메시지를 반환합니다.
     * 필요한 물의 양을 포함하여 계산합니다.
     */
    private Map<String, Object> analyzeAndAdjustMetric(String metricName, double value, double min, double max, double currentWaterVolume, double addedWaterValue) {
        Map<String, Object> result = new HashMap<>();
        result.put("value", value);

        if (value < min) {
            double adjustment = min - value;
            double requiredWater = calculateRequiredWater(currentWaterVolume, value, addedWaterValue, min);
            result.put("message", String.format("%s: %.2f (적정 수준까지 %.2f 증가 필요)", metricName, value, adjustment));
            result.put("requiredWater", requiredWater);
        } else if (value > max) {
            double adjustment = value - max;
            double requiredWater = calculateRequiredWater(currentWaterVolume, value, addedWaterValue, max);
            result.put("message", String.format("%s: %.2f (적정 수준까지 %.2f 감소 필요)", metricName, value, adjustment));
            result.put("requiredWater", requiredWater);
        } else {
            result.put("message", String.format("%s: %.2f (적정 수준)", metricName, value));
        }

        return result;
    }

    /**
     * 필요한 물의 양 계산
     */
    private double calculateRequiredWater(double currentVolume, double currentValue, double addedValue, double targetValue) {
        return Math.abs((targetValue * currentVolume - currentValue * currentVolume) / (addedValue - targetValue));
    }
    /**
     * 특정 지표에 대해 분석하고 결과 메시지를 반환합니다.
     */
    private Map<String, Object> analyzeMetric(String metricName, double value, double min, double max) {
        Map<String, Object> result = new HashMap<>();
        if (value < min) {
            double adjustment = min - value;
            result.put("value", value);
            result.put("message", String.format("%s: %.2f (적정 수준까지 %.2f 증가 필요)", metricName, value, adjustment));
            result.put("action", "increase");
            result.put("adjustment", adjustment);
        } else if (value > max) {
            double adjustment = value - max;
            result.put("value", value);
            result.put("message", String.format("%s: %.2f (적정 수준까지 %.2f 감소 필요)", metricName, value, adjustment));
            result.put("action", "decrease");
            result.put("adjustment", adjustment);
        } else {
            result.put("value", value);
            result.put("message", String.format("%s: %.2f (적정 수준)", metricName, value));
            result.put("action", "none");
        }
        return result;
    }


    /**
     * 각 장치별 최신 데이터를 조회
     */
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
