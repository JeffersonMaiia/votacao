package com.sicred.votacao.domain.service;

import com.sicred.votacao.domain.entity.PautaStatusEnum;
import com.sicred.votacao.domain.entity.Voto;
import com.sicred.votacao.domain.exception.RegraNegocioException;
import com.sicred.votacao.domain.repository.VotoRepository;
import com.sicred.votacao.fixture.Fixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.stubbing.answers.ThrowsException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class VotoServiceTest {

    @InjectMocks
    private VotoService votoService;

    @Mock
    private VotoRepository votoRepository;
    @Mock
    private CpfService cpfService;
    @Mock
    private PautaService pautaService;

    @Test
    void deveValidarCpf() {
        var voto = Fixture.random(Voto.class);
        voto.getPauta().setStatus(PautaStatusEnum.ABERTA);

        when(cpfService.isAbleToVote(anyString())).thenReturn(false);

        Throwable throwable = catchThrowable(() -> votoService.votar(voto));

        assertThat(throwable)
                .isNotNull()
                .hasMessage("CPF Inválido")
                .isExactlyInstanceOf(RegraNegocioException.class);
    }

    @Test
    void deveValidarPautaAberta() {
        var voto = Fixture.random(Voto.class);
        voto.getPauta().setStatus(PautaStatusEnum.ENCERRADA);

        when(cpfService.isAbleToVote(anyString())).thenReturn(true);
        when(pautaService.isPautaAberta(any(UUID.class))).thenReturn(false);

        Throwable throwable = catchThrowable(() -> votoService.votar(voto));

        assertThat(throwable)
                .isNotNull()
                .hasMessage("Pauta não está aberta para votação!")
                .isExactlyInstanceOf(RegraNegocioException.class);
    }

    @Test
    void deveValidadeDuplicidadeDeVoto() {
        var voto = Fixture.random(Voto.class);
        voto.getPauta().setStatus(PautaStatusEnum.ABERTA);

        when(cpfService.isAbleToVote(anyString())).thenReturn(true);
        when(pautaService.isPautaAberta(any(UUID.class))).thenReturn(true);
        when(votoRepository.existsVotoByCpfAndPautaId(anyString(), any(UUID.class))).thenReturn(true);

        Throwable throwable = catchThrowable(() -> votoService.votar(voto));

        assertThat(throwable)
                .isNotNull()
                .hasMessage("CPF já votou nesta pauta!")
                .isExactlyInstanceOf(RegraNegocioException.class);
    }

    @Test
    void deveCadastrarVoto() {
        var voto = Fixture.random(Voto.class);
        voto.getPauta().setStatus(PautaStatusEnum.ABERTA);

        when(cpfService.isAbleToVote(anyString())).thenReturn(true);
        when(pautaService.isPautaAberta(any(UUID.class))).thenReturn(true);
        when(votoRepository.existsVotoByCpfAndPautaId(anyString(), any(UUID.class))).thenReturn(false);
        when(votoRepository.save(any(Voto.class))).thenReturn(voto);

        votoService.votar(voto);

        verify(votoRepository).save(voto);
    }
}
