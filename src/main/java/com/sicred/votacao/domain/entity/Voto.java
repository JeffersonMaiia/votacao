package com.sicred.votacao.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "voto", uniqueConstraints = {@UniqueConstraint(columnNames = {"cpf", "pauta_id"})})
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String cpf;

    @Enumerated(EnumType.STRING)
    private VotoEnum voto;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }
}
