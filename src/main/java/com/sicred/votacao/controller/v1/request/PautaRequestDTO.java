package com.sicred.votacao.controller.v1.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PautaRequestDTO {

    @NotBlank(message = "O nome da pauta é obrigatório")
    private String nome;

    @Schema(example = "dd/MM/yyyy HH:mm:ss")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataEncerramento;
}
