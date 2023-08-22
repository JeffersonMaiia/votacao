package com.sicred.votacao.domain.repository;

import com.sicred.votacao.domain.dto.PautaVotoDTO;
import com.sicred.votacao.domain.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, UUID> {

    @Query("""
            select new com.sicred.votacao.domain.dto.PautaVotoDTO(
            p.id,
            p.status,
            sum(case when v.voto = 'SIM' then 1 else 0 end),
            sum(case when v.voto = 'NAO' then 1 else 0 end))
            from Pauta p
            left join Voto v on v.pauta.id = p.id
               group by p.id, p.status
            """)
    List<PautaVotoDTO> findAllPautaVotos();

    @Query("""
            select new com.sicred.votacao.domain.dto.PautaVotoDTO(
            p.id,
            p.status,
            sum(case when v.voto = 'SIM' then 1 else 0 end),
            sum(case when v.voto = 'NAO' then 1 else 0 end))
            from Pauta p
            left join Voto v on v.pauta.id = p.id
            where p.status = 'ABERTA'
            and p.dataEncerramento < current_timestamp
               group by p.id, p.status
            """)
    List<PautaVotoDTO> findAllPautaVotosProntosParaEncerrar();
}
