//package com.example.potatowinsbe.domain.elk.controller;
//
//import com.example.potatowinsbe.domain.elk.entity.SensorData;
//import com.example.potatowinsbe.domain.elk.service.DataSyncService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/elk")
//public class DataSyncController {
//
//    private final DataSyncService dataSyncService;
//
//    public DataSyncController(DataSyncService dataSyncService) {
//        this.dataSyncService = dataSyncService;
//    }
//
//    // Elasticsearch 데이터를 받아 MySQL로 저장하는 API
//    @PostMapping("/sync-data")
//    public String syncData(@RequestBody List<SensorData> elasticsearchData) {
//        dataSyncService.syncDataToMySQL(elasticsearchData);
//        return "Data synchronized to MySQL!";
//    }
//}
