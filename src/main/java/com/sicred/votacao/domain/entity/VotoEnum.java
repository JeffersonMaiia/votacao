package com.sicred.votacao.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VotoEnum {

    SIM("Sim"),
    NAO("Não");

    private String descricao;
}
