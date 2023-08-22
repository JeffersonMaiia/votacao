package com.sicred.votacao.controller.v1.api;

import com.sicred.votacao.controller.v1.request.PautaRequestDTO;
import com.sicred.votacao.domain.dto.PautaVotoDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.UUID;

@OpenAPIDefinition(info = @Info(title = "Pauta API", version = "1.0", description = "API para gerenciamento de pautas"))
public interface PautaAPI {

    @Operation(summary = "Cadastra uma nova pauta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pauta criada"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    UUID novaPauta(PautaRequestDTO pautaRequestDTO);

    @Operation(summary = "Obter todas as pautas com votos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    List<PautaVotoDTO> findAllPautasComVotos();
}
