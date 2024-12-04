package com.example.potatowinsbe.domain.elk.service;

import com.example.potatowinsbe.domain.elk.entity.SensorData;
import com.example.potatowinsbe.domain.elk.repository.SensorDataRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DataSyncService {

    private final SensorDataRepository mysqlRepository;

    // Elasticsearch repository는 이후 추가될 예정이라 아래 부분은 필요할 수 있음
    public DataSyncService(SensorDataRepository mysqlRepository) {
        this.mysqlRepository = mysqlRepository;
    }

    public void syncDataToMySQL(List<SensorData> elasticsearchData) {
        for (SensorData data : elasticsearchData) {
            SensorData entity = new SensorData();

            // Set all properties from Elasticsearch data
            entity.setApplicationID(data.getApplicationID());
            entity.setApplicationName(data.getApplicationName());
            entity.setDevEUI(data.getDevEUI());
            entity.setDeviceName(data.getDeviceName());
            entity.setTemp(data.getTemp());
            entity.setPH(data.getPH());
            entity.setTurbidity(data.getTurbidity());
            entity.setDoValue(data.getDoValue());
            entity.setNh4(data.getNh4());
            entity.setSalt(data.getSalt());
            entity.setAlcohol(data.getAlcohol());
            entity.setCreatedAt(LocalDateTime.now());
            entity.setUpdatedAt(LocalDateTime.now());

            // Save to MySQL
            mysqlRepository.save(entity);
        }
    }
}
