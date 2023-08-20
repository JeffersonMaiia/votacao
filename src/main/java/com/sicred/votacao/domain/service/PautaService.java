package com.sicred.votacao.domain.service;

import com.sicred.votacao.domain.entity.Pauta;
import com.sicred.votacao.domain.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PautaService {

    private final PautaRepository pautaRepository;

    public UUID save(Pauta pauta) {
        return this.pautaRepository.save(pauta).getId();
    }

    public List<Pauta> findAll() {
        return this.pautaRepository.findAll();
    }
}
