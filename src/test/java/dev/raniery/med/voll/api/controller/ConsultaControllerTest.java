package dev.raniery.med.voll.api.controller;

import dev.raniery.med.voll.api.domain.Consulta.AgendaConsultas;
import dev.raniery.med.voll.api.domain.Consulta.DadosAgendamentoConsulta;
import dev.raniery.med.voll.api.domain.Consulta.DadosDetalhamentoConsulta;
import dev.raniery.med.voll.api.domain.Medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> jsonDadosAgendamentoConsulta;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> jsonDadosDetalhamentoConsulta;

    @MockitoBean
    private AgendaConsultas agendaConsultas;

    @Test
    @WithMockUser
    @DisplayName("Deve retornar status 400 quando informações de login estiverem incorretas")
    void agendarStatusCode400() throws Exception {
        var response = mockMvc.perform(post("/consultas"))
            .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    @WithMockUser
    @DisplayName("Deve retornar status 200 quando informações de login estiverem corretas")
    void agendarStatusCode200() throws Exception {
        LocalDateTime dataHora = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        Especialidade especialidade = Especialidade.PEDIATRIA;

        var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 5L, 1L, dataHora);
        when(agendaConsultas.agendarConsulta(any())).thenReturn(dadosDetalhamento);

        var response = mockMvc.perform(post("/consultas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonDadosAgendamentoConsulta.write(
                        new DadosAgendamentoConsulta(5L, 1L, dataHora, especialidade))
                    .getJson()))
            .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(200);

        var content = jsonDadosDetalhamentoConsulta.write(
                dadosDetalhamento)
            .getJson();

        assertThat(response.getContentAsString()).isEqualTo(content);
    }
}