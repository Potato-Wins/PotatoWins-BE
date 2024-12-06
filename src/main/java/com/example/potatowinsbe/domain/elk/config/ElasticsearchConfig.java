//package com.example.potatowinsbe.domain.elk.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;
//import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter;
//import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;
//import java.time.format.DateTimeFormatter;
//import java.time.LocalDateTime;
//
//@Configuration
//public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {
//
//    @Override
//    public ElasticsearchRestTemplate elasticsearchRestTemplate() {
//        return new ElasticsearchRestTemplate(elasticsearchClient());
//    }
//
//    @Override
//    public ElasticsearchCustomConversions elasticsearchCustomConversions() {
//        return new ElasticsearchCustomConversions(
//                Arrays.asList(
//                        new LocalDateTimeToStringConverter(),
//                        new StringToLocalDateTimeConverter()
//                )
//        );
//    }
//
//    private static class LocalDateTimeToStringConverter implements org.springframework.core.convert.converter.Converter<LocalDateTime, String> {
//        @Override
//        public String convert(LocalDateTime source) {
//            return source.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        }
//    }
//
//    private static class StringToLocalDateTimeConverter implements org.springframework.core.convert.converter.Converter<String, LocalDateTime> {
//        @Override
//        public LocalDateTime convert(String source) {
//            return LocalDateTime.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        }
//    }
//}
