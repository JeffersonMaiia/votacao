package com.sicred.votacao.domain.event.produce;

import com.sicred.votacao.domain.dto.PautaVotoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PautaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(PautaVotoDTO pautaVotoDTO) {
        kafkaTemplate.send("pauta-encerrada", pautaVotoDTO.getId().toString() ,pautaVotoDTO);
    }
}
