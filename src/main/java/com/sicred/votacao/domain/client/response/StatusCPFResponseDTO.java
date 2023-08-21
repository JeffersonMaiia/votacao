package com.sicred.votacao.domain.client.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusCPFResponseDTO {

    private StatusCPFEnumResponseDTO status;

    public boolean isAbleToVote() {
        return StatusCPFEnumResponseDTO.ABLE_TO_VOTE.equals(status);
    }
}
