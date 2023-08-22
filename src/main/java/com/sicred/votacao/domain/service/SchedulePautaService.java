package com.sicred.votacao.domain.service;

import com.sicred.votacao.domain.event.produce.PautaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Slf4j
@EnableAsync
@Component
public class SchedulePautaService {

    private final PautaService pautaService;
    private final PautaProducer pautaProducer;

    @Async
    @Scheduled(fixedDelay = 10000)
    public void scheduleFixedDelayTask() {
        log.info("Start envio de pautas para encerramento: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        pautaService.listarPautasComVotosEncerradas()
                .forEach(pautaVotoDTO -> {
                    pautaService.encerrarPauta(pautaVotoDTO.getId());
                    pautaProducer.send(pautaVotoDTO);
                    log.info("Pauta enviada para encerramento: {}", pautaVotoDTO.getId());
                });
    }
}
