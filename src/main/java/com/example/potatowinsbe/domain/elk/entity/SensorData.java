package com.example.potatowinsbe.domain.elk.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
@Document(indexName = "sensor_data")
public class SensorData {

    @Id
    private Integer id;

    private Integer applicationID;
    private String applicationName;
    private Long devEUI;
    private String deviceName;
    private Double Temp;
    private Double pH;
    private Double TURBIDITY;
    private Double DO;
    private Double NH4;
    private Double salt;
    private Double ALCOHOL;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and Setters
}
