package dev.raniery.med.voll.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String authToken;

    @BeforeEach
    void setUp() throws Exception {
        String loginJson = """
            {
                "login": "admin@med.com",
                "senha": "123456"
            }""";

        var response = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson))
            .andExpect(status().isOk())
            .andReturn();

        var responseJson = response.getResponse().getContentAsString();
        authToken = responseJson.substring(10, responseJson.length() - 2);
    }

    @Test
    public void cadastrarPaciente_DeveRetornarStatus201() throws Exception {
        String randomCPF = generateRandomCPF();
        String pacienteJson = String.format("""
            {
                "nome": "Paciente Test",
                "email": "paciente@example.com",
                "cpf": "%s",
                "telefone": "11999999999",
                "endereco": {
                    "logradouro": "rua 1",
                    "bairro": "bairro",
                    "cep": "12345678",
                    "cidade": "Sao Paulo",
                    "uf": "SP"
                }
            }""", randomCPF);

        mockMvc.perform(post("/pacientes")
                .header("Authorization", "Bearer " + authToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(pacienteJson))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.nome").value("Paciente Test"))
            .andExpect(jsonPath("$.cpf").value(randomCPF));
    }

    private String generateRandomCPF() {
        return IntStream.range(0, 11).mapToObj(_ -> String.valueOf(new Random().nextInt(10))).collect(Collectors.joining());
    }

    @Test
    public void listarPacientes_DeveRetornarStatus200() throws Exception {
        mockMvc.perform(get("/pacientes")
                .header("Authorization", "Bearer " + authToken))
            .andExpect(status().isOk());
    }

    @Test
    public void atualizarPaciente_DeveRetornarStatus200() throws Exception {
        String updateJson = """
            {
                "id": 1,
                "nome": "Paciente Updated"
            }""";

        mockMvc.perform(put("/pacientes")
                .header("Authorization", "Bearer " + authToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson))
            .andExpect(status().isOk());
    }

    @Test
    public void deletarPaciente_DeveRetornarStatus204() throws Exception {
        mockMvc.perform(delete("/pacientes/1")
                .header("Authorization", "Bearer " + authToken))
            .andExpect(status().isNoContent());
    }
}