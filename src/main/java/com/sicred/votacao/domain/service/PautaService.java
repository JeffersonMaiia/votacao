package com.sicred.votacao.domain.service;

import com.sicred.votacao.domain.dto.PautaVotoDTO;
import com.sicred.votacao.domain.entity.Pauta;
import com.sicred.votacao.domain.exception.NaoEncontradoExeption;
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

    public boolean isPautaAberta(UUID id) {
        Pauta pauta = this.pautaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoExeption("Pauta não encontrada!"));

        return pauta.isPautaAberta();
    }

    public List<PautaVotoDTO> listarPautasComVotos() {
        return this.pautaRepository.findAllPautaVotos();
    }

    public List<PautaVotoDTO> listarPautasComVotosEncerradas() {
        return this.pautaRepository.findAllPautaVotosProntosParaEncerrar();
    }

    public void encerrarPauta(UUID id){
        Pauta pauta = this.pautaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoExeption("Pauta não encontrada!"));

        pauta.encerrar();
        this.pautaRepository.save(pauta);
    }
}
