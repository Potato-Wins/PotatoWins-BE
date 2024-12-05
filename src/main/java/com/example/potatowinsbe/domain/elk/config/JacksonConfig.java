//package com.example.potatowinsbe.domain.elk.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//@Configuration
//public class JacksonConfig { //Spring Boot에서 Jackson의 ObjectMapper를 설정해 기본 날짜 형식을 지정
//    @Bean
//    public ObjectMapper objectMapper() {
//        JavaTimeModule javaTimeModule = new JavaTimeModule();
//        javaTimeModule.addDeserializer(LocalDateTime.class,
//                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        javaTimeModule.addSerializer(LocalDateTime.class,
//                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(javaTimeModule);
//        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        return mapper;
//    }
//}
