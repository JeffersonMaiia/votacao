package com.sicred.votacao.domain.service;

import com.sicred.votacao.domain.client.CpfClient;
import com.sicred.votacao.domain.client.response.StatusCPFEnumResponseDTO;
import com.sicred.votacao.domain.client.response.StatusCPFResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CpfServiceTest {

    @InjectMocks
    private CpfService cpfService;

    @Mock
    private CpfClient cpfClient;

    @Test
    void deveRetornarSeCpfEhValido() {
        final var dto = StatusCPFResponseDTO.builder()
                .status(StatusCPFEnumResponseDTO.ABLE_TO_VOTE)
                .build();

        when(cpfClient.getStatusCPF(anyString()))
                .thenReturn(dto);

        boolean ableToVote = this.cpfService.isAbleToVote("12345678901");

        Assertions.assertTrue(ableToVote);
    }

    @Test
    void deveRetornarSeCpfEhNaoValido() {
        final var dto = StatusCPFResponseDTO.builder()
                .status(StatusCPFEnumResponseDTO.UNABLE_TO_VOTE)
                .build();

        when(cpfClient.getStatusCPF(anyString()))
                .thenReturn(dto);

        boolean ableToVote = this.cpfService.isAbleToVote("12345678901");

        Assertions.assertFalse(ableToVote);
    }

}
