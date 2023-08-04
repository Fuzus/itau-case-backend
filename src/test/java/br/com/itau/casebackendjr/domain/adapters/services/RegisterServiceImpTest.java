package br.com.itau.casebackendjr.domain.adapters.services;

import br.com.itau.casebackendjr.domain.dto.RegisterDTO;
import br.com.itau.casebackendjr.domain.model.Register;
import br.com.itau.casebackendjr.domain.ports.repositories.RegisterRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class RegisterServiceImpTest {

    private RegisterServiceImp service;

    @MockBean
    private RegisterRepositoryPort repository;

    @BeforeEach
    void init() {
        service = new RegisterServiceImp(repository);
    }

    @Test
    @DisplayName("Deveria retornar lista vazia quando não houver cadastros")
    void getAll_cenario1() {
        var registers = service.getAll();
        Assertions.assertTrue(registers.isEmpty());
    }

    @Test
    @DisplayName("Deveria retornar uma lista com 2 cadastros")
    void getAll_cenario2() {

        when(repository.findAll()).thenReturn(Arrays.asList(
                        new Register(1L,"first name1", "last name1", 10, "Brazil"),
                        new Register(2L,"first name2", "last name2", 10, "Brazil")
                ));

        var registers = service.getAll();

        Assertions.assertFalse(registers.isEmpty());
        Assertions.assertEquals(2, registers.size());
        var expectedRegister = createRegisters(2);
        Assertions.assertEquals(expectedRegister, registers);
    }

    @Test
    @DisplayName("Deveria retornar um erro entity not found")
    void getById_cenario1(){
        when(repository.findById(any())).thenThrow(new EntityNotFoundException());

        var expectedResult = EntityNotFoundException.class;
        Assertions.assertThrows(expectedResult, () -> service.getPerson(50L));
    }

    @Test
    @DisplayName("Deveria retornar um registerDto")
    void getById_cenario2(){
        when(repository.findById(any())).thenReturn(new Register(1L, "first name1", "last name1", 10, "Brazil"));

        var registerDto = service.getPerson(1L);
        Assertions.assertNotNull(registerDto);

        var expectedResult = createRegister(1);
        Assertions.assertEquals(expectedResult, registerDto);
    }

    private List<RegisterDTO> createRegisters(int quantity) {
        List<RegisterDTO> registerList = new ArrayList<>();
        for (int i = 1; i <= quantity; i++) {
            registerList.add(createRegister(i));
        }
        return registerList;
    }

    private RegisterDTO createRegister(int ordinal) {
        return new RegisterDTO(Integer.toUnsignedLong(ordinal), "first name" + ordinal, "last name" + ordinal, 10, "Brazil");
    }

}