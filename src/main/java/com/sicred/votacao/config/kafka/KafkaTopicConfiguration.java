package com.sicred.votacao.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@ConditionalOnProperty(name = "kafka.enabled")
@EnableKafka
@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public NewTopic topicWith3Replicas() {
        return TopicBuilder.name("pauta-encerrada")
                .replicas(3)
                .build();
    }
}
