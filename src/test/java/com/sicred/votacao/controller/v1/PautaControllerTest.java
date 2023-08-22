package com.sicred.votacao.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sicred.votacao.controller.v1.request.PautaRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class PautaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    void deveRealizarCadastro_Retorna200() throws Exception {
        var pautaRequestDTO = PautaRequestDTO.builder()
                .nome("Teste")
                .dataEncerramento(LocalDateTime.now().plusMinutes(10))
                .build();

        ResultActions perform = this.mockMvc.perform(post("/v1/pauta")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(pautaRequestDTO)))
                .andDo(print());

        perform.andExpect(status().isCreated())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void deveRealizarValidacaoCamposObrigatorios_Retorna400() throws Exception {
        var pautaRequestDTO = PautaRequestDTO.builder()
                .nome("")
                .dataEncerramento(LocalDateTime.now().plusMinutes(10))
                .build();

        ResultActions perform = this.mockMvc.perform(post("/v1/pauta")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(pautaRequestDTO)))
                .andDo(print());

        perform.andExpect(status().isBadRequest());
        perform.andExpect(jsonPath("$.nome").value("O nome da pauta é obrigatório"));
    }

    @Test
    void deveRetornarListaPautasComVotos_Retorna200() throws Exception {
        ResultActions perform = this.mockMvc.perform(get("/v1/pauta")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print());

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").exists());
    }
}
