package com.sicred.votacao.controller.v1.response;

import com.sicred.votacao.domain.entity.PautaStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class PautaResponseDTO {

    private UUID id;
    private String nome;
    private LocalDateTime dataEncerramento;
    private PautaStatusEnum status;
}
