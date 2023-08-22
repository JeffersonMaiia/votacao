package com.sicred.votacao.config.feign;

import com.sicred.votacao.domain.exception.RegraNegocioException;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class FeignConfig {

    @Bean
    ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            HttpStatus status = HttpStatus.valueOf(response.status());
            switch (status) {
                case NOT_FOUND:
                    return new RegraNegocioException("CPF inválido");
                default:
                    return new Exception("Erro ao consultar CPF");
            }
        };
    }
}
