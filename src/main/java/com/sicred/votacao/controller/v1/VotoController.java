package com.sicred.votacao.controller.v1;

import com.sicred.votacao.controller.v1.request.VotoRequestDTO;
import com.sicred.votacao.domain.service.VotoService;
import com.sicred.votacao.config.mapper.VotoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/voto")
public class VotoController {

    private final VotoService votoService;
    private final VotoMapper votoMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID votar(@Valid @RequestBody VotoRequestDTO votoRequestDTO) {
        var voto = votoMapper.toEntity(votoRequestDTO);

        return votoService.votar(voto);
    }

}
