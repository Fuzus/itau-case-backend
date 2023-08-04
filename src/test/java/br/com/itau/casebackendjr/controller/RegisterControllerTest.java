package br.com.itau.casebackendjr.controller;

import br.com.itau.casebackendjr.domain.dto.RegisterDTO;
import br.com.itau.casebackendjr.domain.ports.interfaces.RegisterServicePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class RegisterControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<RegisterDTO> personalDataDTOJson;

    @MockBean
    private RegisterServicePort registerServicePort;

    @Test
    @DisplayName("Deveria retornar uma lista vazia quando não ha nenhum cadastro")
    void getAll_cenario1() throws Exception {
        var response = mvc.perform(get("/cadastro")).andReturn().getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals("[]", response.getContentAsString());
    }

    @Test
    @DisplayName("Deveria retornar erro 400 quando passar dados invalidos para a requisicao")
    void create_cenario1() throws Exception {
        var response = mvc.perform(post("/cadastro")).andReturn().getResponse();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    @DisplayName("Deveria retornar codigo 201 e um Personal Data quando requisicao com dados validos")
    void create_cenario2() throws Exception {
        var personalDataDto = new RegisterDTO(null, "teste", "teste1", 10, "Brasil");

        when(registerServicePort.create(any())).thenReturn(personalDataDto);

        var response = mvc
                .perform(
                        post("/cadastro")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        personalDataDTOJson.write(
                                                new RegisterDTO(null, "teste", "teste1", 10, "Brasil")
                                        ).getJson()
                                )
                )
                .andReturn().getResponse();

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        var expectedJson = personalDataDTOJson.write(personalDataDto).getJson();
        Assertions.assertEquals(expectedJson, response.getContentAsString());

    }
}