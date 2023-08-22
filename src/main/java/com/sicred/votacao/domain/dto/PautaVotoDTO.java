package com.sicred.votacao.domain.dto;

import com.sicred.votacao.domain.entity.PautaStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class PautaVotoDTO {

    private UUID id;
    private PautaStatusEnum status;
    private Long votosSim;
    private Long votosNao;
}
