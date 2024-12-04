package com.example.potatowinsbe.domain.algorithm.service;

import com.example.potatowinsbe.domain.elk.entity.SensorData;
import com.example.potatowinsbe.domain.elk.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {

    private static final double MIN_TEMP = 10.0;
    private static final double MAX_TEMP = 20.0;
    private static final double OPTIMAL_TEMP = 15.0; // 적정 온도
    private static final double WATER_COOLING_FACTOR = 0.1; // 물의 냉각 효과 (임의의 값)

    @Autowired
    private SensorDataRepository sensorDataRepository; //아마 디비 연결 지워놔서 그런듯?

    // 1분마다 ELK 데이터를 조회하여 이상 온도를 감지
    @Scheduled(fixedRate = 60000) // 60초(1분) 간격으로 실행
    public void scheduledMonitorAndAdjustTemperature() {
        // 스케줄러에서 호출되는 메서드는 반환값을 사용하지 않으므로, 결과를 로그로 출력하거나 다른 방식으로 처리합니다.
        String result = monitorAndAdjustTemperature();
        System.out.println(result);
    }

    // 컨트롤러에서 호출하여 결과를 반환하는 메서드
    public String monitorAndAdjustTemperature() {
        // 이상 온도를 감지 (적정 범위 외 데이터 조회)
        List<SensorData> lowTempData = sensorDataRepository.findByTempLessThan(MIN_TEMP);
        List<SensorData> highTempData = sensorDataRepository.findByTempGreaterThan(MAX_TEMP);

        StringBuilder result = new StringBuilder("이상 온도 데이터 처리 결과:\n");

        // 처리 결과 추가
        for (SensorData data : lowTempData) {
            result.append(handleLowTemperature(data)).append("\n");
        }
        for (SensorData data : highTempData) {
            result.append(handleHighTemperature(data)).append("\n");
        }

        // 이상 온도 데이터가 없을 경우 메시지 추가
        if (lowTempData.isEmpty() && highTempData.isEmpty()) {
            result.append("이상 온도 데이터가 없습니다.\n");
        }

        return result.toString();
    }

    private String handleLowTemperature(SensorData data) {
        double adjustment = OPTIMAL_TEMP - data.getTemp();
        double waterAmount = calculateWaterAmount(adjustment);
        return "장치 " + data.getDeviceName() + ": 현재 온도 " + data.getTemp() + "도, " +
                "적정 온도(" + OPTIMAL_TEMP + "도) 도달을 위해 약 " + String.format("%.2f", waterAmount) + "L의 따뜻한 물이 필요합니다.";
    }

    private String handleHighTemperature(SensorData data) {
        double adjustment = data.getTemp() - OPTIMAL_TEMP;
        double waterAmount = calculateWaterAmount(adjustment);
        return "장치 " + data.getDeviceName() + ": 현재 온도 " + data.getTemp() + "도, " +
                "적정 온도(" + OPTIMAL_TEMP + "도) 도달을 위해 약 " + String.format("%.2f", waterAmount) + "L의 차가운 물이 필요합니다.";
    }

    private double calculateWaterAmount(double temperatureDifference) {
        // 냉각 또는 가열에 필요한 물의 양을 계산 (Q = mcΔT 활용)
        double specificHeatCapacity = 4186; // 물의 비열 (J/(kg·°C))
        double coolingEnergyRequired = 10000; // 필요한 총 냉각 열량 (J, 임의의 값)

        // 필요한 물의 양 (kg 단위, 1kg = 1L)
        return coolingEnergyRequired / (specificHeatCapacity * temperatureDifference);
    }
}
