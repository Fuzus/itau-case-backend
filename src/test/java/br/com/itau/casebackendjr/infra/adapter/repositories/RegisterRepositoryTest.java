package br.com.itau.casebackendjr.infra.adapter.repositories;

import br.com.itau.casebackendjr.domain.model.Register;
import br.com.itau.casebackendjr.infra.adapter.entities.RegisterEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class RegisterRepositoryTest {

    private RegisterRepository repository;

    @Autowired
    private SpringRegisterRepository springRepository;

    @Autowired
    private EntityManager manager;

    @BeforeEach
    void init() {
        this.repository = new RegisterRepository(springRepository);
    }

    @Test
    @DisplayName("Deveria retonar uma lista vazia caso não haja nenhum cadastro")
    void getAll_cenario1() {
        var result = repository.findAll();
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Deveria retornar uma lista com 2 cadastros")
    void getAll_cenario2() {
        List<Register> expectedResults = Arrays.asList(createRegister(1L).toRegister(), createRegister(2L).toRegister());
        var result = repository.findAll();
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(expectedResults, result);
    }

    @Test
    @DisplayName("Deveria retornar entityNotFondException")
    void getById_cenario1(){
        var expectedResult = EntityNotFoundException.class;
        Assertions.assertThrows(expectedResult, () -> repository.findById(1L));
    }

    @Test
    @DisplayName("Deveria retornar um cadastro")
    void getById_cenario2(){
        var expectedResult = createRegister(1L).toRegister();
        var result = repository.findById(expectedResult.getId());
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Deveria criar um novo cadastro")
    void save_cenario1() {
        var result = repository.save(createRegister());
        Assertions.assertNotNull(result.getId());
        Assertions.assertInstanceOf(Register.class, result);
    }

    @Test
    @DisplayName("Deveria alterar dados de um cadastro existente")
    void save_cenario2(){
        String newFirstName = "Matheus";
        Integer newAge = 45;

        var result = createRegister(1L).toRegister();
        String oldFirstName = result.getFirstName();
        Integer oldAge = result.getAge();
        result.setFirstName(newFirstName);
        result.setAge(newAge);

        repository.save(result);

        Assertions.assertNotEquals(oldFirstName, result.getFirstName());
        Assertions.assertNotEquals(oldAge, result.getAge());

        Assertions.assertEquals(newFirstName, result.getFirstName());
        Assertions.assertEquals(newAge, result.getAge());
    }

    @Test
    @DisplayName("Deveria retornar EntityNotFoundException")
    void deleteById_cenario1() {
        var expectedResult = EntityNotFoundException.class;
        Assertions.assertThrows(expectedResult, () -> repository.deleteById(1L));
    }

    @Test
    @DisplayName("Deveria deletar o cadastro selecionado pelo id")
    void deleteById_cenario2() {
        var entity = createRegister(1L).toRegister();
        var id = entity.getId();
        repository.deleteById(id);
        Assertions.assertThrows(EntityNotFoundException.class, () -> repository.findById(id));
    }

    private RegisterEntity createRegister(Long ordinal){
        RegisterEntity entity = new RegisterEntity(null, "first name" + ordinal, "last name" + ordinal, 10, "Brazil");
        manager.persist(entity);
        return entity;
    }

    private Register createRegister(){
        return new Register(null, "first name", "last name", 10, "Brazil");
    }

}