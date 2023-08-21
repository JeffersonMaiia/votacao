package com.sicred.votacao.controller.v1;

import com.sicred.votacao.controller.v1.api.PautaAPI;
import com.sicred.votacao.controller.v1.request.PautaRequestDTO;
import com.sicred.votacao.controller.v1.response.PautaResponseDTO;
import com.sicred.votacao.domain.dto.VotoDTO;
import com.sicred.votacao.domain.service.PautaService;
import com.sicred.votacao.domain.service.VotoService;
import com.sicred.votacao.config.mapper.PautaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/pauta")
public class PautaController implements PautaAPI {

    private final PautaService pautaService;
    private final PautaMapper pautaMapper;
    private final VotoService votoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public UUID novaPauta(@Valid @RequestBody PautaRequestDTO pautaRequestDTO) {
        var pauta = pautaMapper.toEntity(pautaRequestDTO);

        return pautaService.save(pauta);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<PautaResponseDTO> findAll() {
        var pautas = pautaService.findAll();

        return pautaMapper.toResponse(pautas);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<VotoDTO> findVotosByPautaId(@PathVariable UUID id) {
        return votoService.listarVotosPorPauta(id);
    }
}
