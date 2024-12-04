package com.example.potatowinsbe.domain.elk.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@Document(indexName = "sensor_data")
public class SensorData {

    @Id
    private Long id;

    private Integer applicationID;
    private String applicationName;
    private String devEUI;
    private String deviceName;
    private Double temp;
    private Double pH;
    private Double turbidity;
    private Double doValue; // `DO` 대신 `doValue`로 변경
    private Double nh4;
    private Double salt;
    private Double alcohol;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and Setters
}
