package com.sicred.votacao.domain.repository;

import com.sicred.votacao.domain.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VotoRepository extends JpaRepository<Voto, UUID> {

    boolean existsVotoByCpfAndPautaId(String cpf, UUID pauta_id);
}
