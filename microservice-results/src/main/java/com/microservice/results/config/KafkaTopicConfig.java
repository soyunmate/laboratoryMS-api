package com.microservice.results.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic generateTopic() {

        Map<String,String> configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE); // delete (borra mensaje) // compact (mantiene el mas actual)
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000" ); // tiempo ente intervalos de clean-up (retencion de mensajes) // por defecto no se borrar nunca (-1)
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824"); // tamaño maximo del segmento // el numero equivale a 1 gb en bytes
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012"); // tamaño maximo de cada mensaje - defecto en 1mb

        return TopicBuilder.name("result_emited")
                .partitions(2)
                .replicas(1)
                .configs(configurations)
                .build();
    }
}
