package com.example.potatowinsbe.domain.elk.service;

import com.example.potatowinsbe.domain.elk.entity.SensorData;
import com.example.potatowinsbe.domain.elk.repository.SensorDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorAlgorithmService {

    private static final Logger logger = LoggerFactory.getLogger(SensorAlgorithmService.class);

    private final SensorDataRepository sensorDataRepository;

    private static final double MIN_TEMP = 10.0;
    private static final double MAX_TEMP = 20.0;
    private static final double OPTIMAL_TEMP = 15.0;

    public SensorAlgorithmService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    /**
     * 매 1분마다 이상 온도를 감지하고 조정 방안을 로깅합니다.
     */
    @Scheduled(fixedRate = 60000)
    public void monitorAndLogAbnormalTemperature() {
        List<SensorData> lowTempData = sensorDataRepository.findByTempLessThan(MIN_TEMP);
        List<SensorData> highTempData = sensorDataRepository.findByTempGreaterThan(MAX_TEMP);

        logger.info("이상 온도 감지 시작");
        lowTempData.forEach(data -> logger.warn(handleLowTemperature(data)));
        highTempData.forEach(data -> logger.warn(handleHighTemperature(data)));
    }

    /**
     * 이상 온도 데이터를 반환하는 메서드
     */
    public String monitorAndAdjustTemperature() {
        List<SensorData> lowTempData = sensorDataRepository.findByTempLessThan(MIN_TEMP);
        List<SensorData> highTempData = sensorDataRepository.findByTempGreaterThan(MAX_TEMP);

        StringBuilder result = new StringBuilder("이상 온도 데이터 처리 결과:\n");

        lowTempData.forEach(data -> result.append(handleLowTemperature(data)).append("\n"));
        highTempData.forEach(data -> result.append(handleHighTemperature(data)).append("\n"));

        if (lowTempData.isEmpty() && highTempData.isEmpty()) {
            result.append("이상 온도 데이터가 없습니다.\n");
        }

        return result.toString();
    }

    private String handleLowTemperature(SensorData data) {
        double adjustment = OPTIMAL_TEMP - data.getTemp();
        return String.format("장치 %s의 현재 온도 %.2f°C: 적정 온도 도달을 위해 %.2f°C 가열 필요.", data.getDeviceName(), data.getTemp(), adjustment);
    }

    private String handleHighTemperature(SensorData data) {
        double adjustment = data.getTemp() - OPTIMAL_TEMP;
        return String.format("장치 %s의 현재 온도 %.2f°C: 적정 온도 도달을 위해 %.2f°C 냉각 필요.", data.getDeviceName(), data.getTemp(), adjustment);
    }
}
