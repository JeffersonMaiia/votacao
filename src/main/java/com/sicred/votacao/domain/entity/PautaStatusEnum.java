package com.sicred.votacao.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PautaStatusEnum {

    ABERTA("ABERTA"),
    ENCERRADA("ENCERRADA");

    private String status;
}
