package com.sicred.votacao.domain.dto;

import com.sicred.votacao.domain.entity.VotoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VotoDTO {

    private VotoEnum voto;
    private Long quantidade;
}
