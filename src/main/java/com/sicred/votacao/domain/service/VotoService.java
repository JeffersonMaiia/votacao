package com.sicred.votacao.domain.service;

import com.sicred.votacao.domain.dto.VotoDTO;
import com.sicred.votacao.domain.entity.Voto;
import com.sicred.votacao.domain.exception.RegraNegocioException;
import com.sicred.votacao.domain.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class VotoService {

    private final VotoRepository votoRepository;
    private final CpfService cpfService;
    private final PautaService pautaService;

    public UUID votar(Voto voto) {
        validaCpfValido(voto);
        validarPautaAberta(voto);
        validarDuplicidadeDeVoto(voto);

        return votoRepository.save(voto).getId();
    }

    public List<VotoDTO> listarVotosPorPauta(UUID idPauta) {
        return votoRepository.findVotoDtoGroupVotoByPautaId(idPauta);
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

    private void validaCpfValido(Voto voto) {
        if (!cpfService.isAbleToVote(voto.getCpf())) {
            throw new RegraNegocioException("CPF Inválido");
        }
    }
}
