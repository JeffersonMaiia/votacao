package com.sicred.votacao.domain.service;

import com.sicred.votacao.domain.entity.Voto;
import com.sicred.votacao.domain.exception.RegraNegocioException;
import com.sicred.votacao.domain.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class VotoService {

    private final VotoRepository votoRepository;
    private final PautaService pautaService;

    public UUID votar(Voto voto) {
        validarPautaAberta(voto);
        validarDuplicidadeDeVoto(voto);

        return votoRepository.save(voto).getId();
    }

    private void validarDuplicidadeDeVoto(Voto voto) {
        if(votoRepository.existsVotoByCpfAndPautaId(voto.getCpf(), voto.getPauta().getId())) {
            throw new RegraNegocioException("CPF já votou nesta pauta!");
        }
    }

    private void validarPautaAberta(Voto voto) {
        if (!pautaService.isPautaAberta(voto.getPauta().getId())) {
            throw new RegraNegocioException("Pauta não está aberta para votação!");
        }
    }
}
