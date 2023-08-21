package com.sicred.votacao.domain.client;

import com.sicred.votacao.domain.client.response.StatusCPFResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "cpfClient", url = "https://user-info.herokuapp.com/users")
public interface CpfClient {

    @GetMapping("/{cpf}")
    StatusCPFResponseDTO getStatusCPF(String cpf);
}
