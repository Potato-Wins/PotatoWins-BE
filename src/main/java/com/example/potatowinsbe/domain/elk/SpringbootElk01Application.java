package com.example.potatowinsbe.domain.elk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class SpringbootElk01Application implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootElk01Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Spring Boot ELK Application has started successfully!");

        // 여기에 시작 시 실행할 로직을 추가합니다.
        // 예: Elasticsearch 서버 연결 확인
        log.info("Connecting to Elasticsearch server...");
        // Connection or data initialization logic here
    }
}
