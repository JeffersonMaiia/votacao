package com.sicred.votacao.domain.service;

import com.sicred.votacao.domain.entity.Pauta;
import com.sicred.votacao.domain.repository.PautaRepository;
import com.sicred.votacao.fixture.Fixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PautaServiceTest {

    @InjectMocks
    private PautaService pautaService;

    @Mock
    private PautaRepository pautaRepository;

    @Test
    void deveSalvarUmaPauta() {
        final var pauta = Fixture.random(Pauta.class);
        when(pautaRepository.save(any(Pauta.class))).thenReturn(pauta);

        this.pautaService.save(pauta);

        verify(pautaRepository).save(pauta);
    }
}
