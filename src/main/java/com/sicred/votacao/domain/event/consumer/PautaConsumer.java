package com.sicred.votacao.domain.event.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PautaConsumer {

    @KafkaListener(topics = "pauta-encerrada", groupId = "votacao")
    public void consume(String message) {
        log.info("Pauta recebida: {}", message);
    }
}
