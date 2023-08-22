package com.sicred.votacao.controller.v1;

import com.sicred.votacao.config.mapper.PautaMapper;
import com.sicred.votacao.controller.v1.api.PautaAPI;
import com.sicred.votacao.controller.v1.request.PautaRequestDTO;
import com.sicred.votacao.domain.dto.PautaVotoDTO;
import com.sicred.votacao.domain.service.PautaService;
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
    public List<PautaVotoDTO> findAllPautasComVotos() {
        return pautaService.listarPautasComVotos();
    }
}
