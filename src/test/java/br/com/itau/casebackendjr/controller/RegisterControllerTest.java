package br.com.itau.casebackendjr.controller;

import br.com.itau.casebackendjr.domain.dto.RegisterDTO;
import br.com.itau.casebackendjr.domain.ports.interfaces.RegisterServicePort;
import jakarta.persistence.EntityNotFoundException;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class RegisterControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<RegisterDTO> registerDTOJson;

    @Autowired
    private JacksonTester<List<RegisterDTO>> listRegisterJson;

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
    @DisplayName("Deveria retornar codigo 200 uma lista com os cadastros")
    void getAll_cenario2() throws Exception {
        var expectedRegisters = createRegisters(2);

        when(registerServicePort.getAll()).thenReturn(expectedRegisters);

        var response = mvc.perform(get("/cadastro")).andReturn().getResponse();

        var expectedJson = listRegisterJson.write(expectedRegisters).getJson();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, response.getContentAsString());
    }

    @Test
    @DisplayName("Deveria retornar 404 quando não houver cadastro com id buscado")
    void getById_cenario1() throws Exception {
        when(registerServicePort.getPerson(any())).thenThrow(new EntityNotFoundException());

        var response = mvc.perform(get("/cadastro/30")).andReturn().getResponse();

        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    @DisplayName("Deveria retornar 200 e um cadastro quando existir")
    void getById_cenario2() throws Exception {
        var register = createRegister(1);
        when(registerServicePort.getPerson(any())).thenReturn(register);

        var response = mvc.perform(get("/cadastro/1")).andReturn().getResponse();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        var expectedJson = registerDTOJson.write(register).getJson();
        Assertions.assertEquals(expectedJson, response.getContentAsString());
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
        var personalDataDto = createRegister(1);

        when(registerServicePort.create(any())).thenReturn(personalDataDto);

        var response = mvc
                .perform(
                        post("/cadastro")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        registerDTOJson.write(
                                                new RegisterDTO(null, "first name1", "last name1", 10, "Brasil")
                                        ).getJson()
                                )
                )
                .andReturn().getResponse();

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        var expectedJson = registerDTOJson.write(personalDataDto).getJson();
        Assertions.assertEquals(expectedJson, response.getContentAsString());

    }


    private List<RegisterDTO> createRegisters(int quantity){
        List<RegisterDTO> registerList = new ArrayList<>();
        for (int i = 1; i <= quantity; i++) {
           registerList.add(createRegister(i));
        }
        return registerList;
    }

    private RegisterDTO createRegister(int ordinal){
        return new RegisterDTO(Integer.toUnsignedLong(ordinal), "first name" + ordinal, "c" + ordinal, 10, "Brazil");
    }
}