package com.sicred.votacao.domain.repository;

import com.sicred.votacao.domain.dto.VotoDTO;
import com.sicred.votacao.domain.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VotoRepository extends JpaRepository<Voto, UUID> {

    boolean existsVotoByCpfAndPautaId(String cpf, UUID pauta_id);

    @Query("""
            select new com.sicred.votacao.domain.dto.VotoDTO(v.voto, count(v.voto))
            from Voto v where v.pauta.id = :pauta_id group by v.voto
            """)
    List<VotoDTO> findVotoDtoGroupVotoByPautaId(UUID pauta_id);
}
