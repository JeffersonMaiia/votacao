package com.sicred.votacao.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sicred.votacao.controller.v1.request.VotoRequestDTO;
import com.sicred.votacao.domain.entity.VotoEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@SpringBootTest
class VotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    void deveRealizarVoto_Retornar200()throws Exception{
        var votoRequestDTO = VotoRequestDTO.builder()
                .pautaId(UUID.fromString("fef7c2a2-aa94-48c6-a38f-2c08eeeb0c4d"))
                .cpf("08674029035")
                .voto(VotoEnum.SIM)
                .build();

        ResultActions perform = this.mockMvc.perform(post("/v1/voto")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(votoRequestDTO)))
                .andDo(print());

        perform.andExpect(status().isCreated())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void deveRealizarValidacaoCamposObrigatorios_Retorna400() throws Exception {
        var votoRequestDTO = VotoRequestDTO.builder()
                .build();

        ResultActions perform = this.mockMvc.perform(post("/v1/voto")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(votoRequestDTO)))
                .andDo(print());

        perform.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.cpf").value("CPF é obrigatório"))
                .andExpect(jsonPath("$.voto").value("Voto é obrigatório"))
                .andExpect(jsonPath("$.pautaId").value("Pauta é obrigatório"));
    }

    @Test
    void deveRealizarValidacaoPautaNaoExistente_Retorna404() throws Exception {
        var votoRequestDTO = VotoRequestDTO.builder()
                .pautaId(UUID.fromString("fef7c2a2-aa94-48c6-a38f-2c08eeeb0c4c"))
                .cpf("08674029035")
                .voto(VotoEnum.SIM)
                .build();

        ResultActions perform = this.mockMvc.perform(post("/v1/voto")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(votoRequestDTO)))
                .andDo(print());

        perform.andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value("Pauta não encontrada!"));
    }

    @Test
    void deveRealizarValidacaoPautaFechada_Retorna400() throws Exception {
        var votoRequestDTO = VotoRequestDTO.builder()
                .pautaId(UUID.fromString("c736c60d-ebe2-418d-b75a-9ad64f4991c3"))
                .cpf("08674029035")
                .voto(VotoEnum.SIM)
                .build();

        ResultActions perform = this.mockMvc.perform(post("/v1/voto")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(votoRequestDTO)))
                .andDo(print());

        perform.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Pauta não está aberta para votação!"));
    }

    @Test
    void deveRealizarValidacaoVotoDuplicado_Retornar400() throws Exception {
        var votoRequestDTO = VotoRequestDTO.builder()
                .pautaId(UUID.fromString("fef7c2a2-aa94-48c6-a38f-2c08eeeb0c4d"))
                .cpf("81395580014")
                .voto(VotoEnum.SIM)
                .build();

        ResultActions perform = this.mockMvc.perform(post("/v1/voto")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(votoRequestDTO)))
                .andDo(print());

        perform.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("CPF já votou nesta pauta!"));
    }
}
