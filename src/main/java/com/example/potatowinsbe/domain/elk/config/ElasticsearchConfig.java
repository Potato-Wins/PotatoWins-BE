//package com.example.potatowinsbe.domain.elk.config;
//
//import lombok.Value;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {
//
//    @Value("${spring.elasticsearch.rest.uris}")
//    private String elasticsearchUrl;
//
//    @Override
//    @Bean
//    public RestHighLevelClient elasticsearchClient() {
//        return RestClients.create(ClientConfiguration.builder()
//                .connectedTo(elasticsearchUrl)
//                .build()).rest();
//    }
//}
