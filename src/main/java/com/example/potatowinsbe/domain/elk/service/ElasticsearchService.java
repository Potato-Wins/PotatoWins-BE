//package com.example.potatowinsbe.domain.elk.service;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.http.ResponseEntity;
//
//@Service
//public class ElasticsearchService {
//
//    private final RestTemplate restTemplate;
//
//    public ElasticsearchService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public String getSensorData() {
//        String url = "http://34.64.200.202:5601";
//        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//        return response.getBody(); // Elasticsearch 결과(JSON)를 반환
//    }
//}
