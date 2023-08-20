package com.sicred.votacao.mapper;

import com.sicred.votacao.controller.request.PautaRequestDTO;
import com.sicred.votacao.controller.response.PautaResponseDTO;
import com.sicred.votacao.domain.entity.Pauta;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PautaMapper {

    Pauta toEntity(PautaRequestDTO pautaRequestDTO);

    PautaResponseDTO toResponse(Pauta pauta);
    List<PautaResponseDTO> toResponse(List<Pauta> pauta);

}
