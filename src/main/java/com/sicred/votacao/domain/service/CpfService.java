package com.sicred.votacao.domain.service;

import com.sicred.votacao.domain.client.CpfClient;
import com.sicred.votacao.domain.client.response.StatusCPFEnumResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CpfService {

    private final CpfClient cpfClient;

    public boolean isAbleToVote(String cpf) {
        var response = cpfClient.getStatusCPF(cpf);

        return response.isAbleToVote();
    }
}
