package com.sicred.votacao.controller.v1.request;

import com.sicred.votacao.domain.entity.VotoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
public class VotoRequestDTO {

    @NotBlank(message = "CPF é obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;
    @NotNull(message = "Voto é obrigatório")
    private VotoEnum voto;
    @NotBlank(message = "Pauta é obrigatório")
    private String pautaId;
}
