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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MedicoControllerTest {

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

    private String generateRandomCRM() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    @Test
    public void cadastrarMedico_DeveRetornarStatus201() throws Exception {
        String randomCRM = generateRandomCRM();
        String medicoJson = String.format("""
            {
                "nome": "Dr Test",
                "email": "test@example.com",
                "crm": "%s",
                "especialidade": "ORTOPEDIA",
                "telefone": "11999999999",
                "endereco": {
                    "logradouro": "rua 1",
                    "bairro": "bairro",
                    "cep": "12345678",
                    "cidade": "Sao Paulo",
                    "uf": "SP"
                }
            }""", randomCRM);

        mockMvc.perform(post("/medicos")
                .header("Authorization", "Bearer " + authToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(medicoJson))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.nome").value("Dr Test"))
            .andExpect(jsonPath("$.crm").value(randomCRM))
            .andExpect(jsonPath("$.especialidade").value("ORTOPEDIA"));
    }

    @Test
    public void listarMedicos_DeveRetornarStatus200() throws Exception {
        mockMvc.perform(get("/medicos")
                .header("Authorization", "Bearer " + authToken))
            .andExpect(status().isOk());
    }

    @Test
    public void atualizarMedico_DeveRetornarStatus200() throws Exception {
        String updateJson = """
            {
                "id": 1,
                "nome": "Dr Updated"
            }""";

        mockMvc.perform(put("/medicos")
                .header("Authorization", "Bearer " + authToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson))
            .andExpect(status().isOk());
    }

    @Test
    public void deletarMedico_DeveRetornarStatus204() throws Exception {
        mockMvc.perform(delete("/medicos/1")
                .header("Authorization", "Bearer " + authToken))
            .andExpect(status().isNoContent());
    }
}