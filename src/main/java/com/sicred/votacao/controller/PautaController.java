package com.sicred.votacao.controller;

import com.sicred.votacao.controller.request.PautaRequestDTO;
import com.sicred.votacao.controller.response.PautaResponseDTO;
import com.sicred.votacao.domain.service.PautaService;
import com.sicred.votacao.mapper.PautaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/pauta")
public class PautaController {

    private final PautaService pautaService;
    private final PautaMapper pautaMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public UUID novaPauta(@RequestBody PautaRequestDTO pautaRequestDTO) {
        var pauta = pautaMapper.toEntity(pautaRequestDTO);

        return pautaService.save(pauta);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PautaResponseDTO> findAll() {
        var pautas = pautaService.findAll();

        return pautaMapper.toResponse(pautas);
    }
}
