package com.sicred.votacao.domain.client.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCPFEnumResponseDTO {

    ABLE_TO_VOTE("ABLE_TO_VOTE"),
    UNABLE_TO_VOTE("UNABLE_TO_VOTE");

    private final String status;
}
