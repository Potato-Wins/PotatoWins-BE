package com.example.potatowinsbe.domain.elk.repository;

import com.example.potatowinsbe.domain.elk.entity.SensorData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SensorDataRepository extends ElasticsearchRepository<SensorData, String> {
    // 특정 조건으로 이상 데이터를 조회
    List<SensorData> findByTempLessThan(double minTemp);
    List<SensorData> findByTempGreaterThan(double maxTemp);
    List<SensorData> findByApplicationName(String applicationName);
}
