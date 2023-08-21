package com.sicred.votacao.config.mapper;

import com.sicred.votacao.controller.v1.request.VotoRequestDTO;
import com.sicred.votacao.domain.entity.Voto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface VotoMapper {

    @Mapping(target = "pauta.id", source = "pautaId")
    Voto toEntity(VotoRequestDTO votoRequestDTO);

}
