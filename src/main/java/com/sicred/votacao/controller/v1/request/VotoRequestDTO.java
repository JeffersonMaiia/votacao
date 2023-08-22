package com.sicred.votacao.controller.v1.request;

import com.sicred.votacao.domain.entity.VotoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VotoRequestDTO {

    @NotBlank(message = "CPF é obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;
    @NotNull(message = "Voto é obrigatório")
    private VotoEnum voto;
    @NotNull(message = "Pauta é obrigatório")
    private UUID pautaId;
}
