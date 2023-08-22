package com.sicred.votacao.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "pauta")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_encerramento")
    private LocalDateTime dataEncerramento;

    @Enumerated(EnumType.STRING)
    private PautaStatusEnum status;

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Voto> votos;

    @PrePersist
    public void prePersist() {
        if(this.dataEncerramento == null){
            this.dataEncerramento = LocalDateTime.now().plusMinutes(1);
        }
        this.dataCriacao = LocalDateTime.now();
        this.status = PautaStatusEnum.ABERTA;
    }


    public boolean isPautaAberta() {
        return PautaStatusEnum.ABERTA.equals(this.status) &&
                LocalDateTime.now().isBefore(this.dataEncerramento);
    }

    public void encerrar() {
        this.status = PautaStatusEnum.ENCERRADA;
    }
}
