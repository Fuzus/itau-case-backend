package br.com.itau.casebackendjr.infra.adapter.repositories;

import br.com.itau.casebackendjr.domain.model.Register;
import br.com.itau.casebackendjr.domain.ports.repositories.RegisterRepositoryPort;
import br.com.itau.casebackendjr.infra.adapter.entities.RegisterEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
@Component
public class RegisterRepository implements RegisterRepositoryPort {

    private SpringRegisterRepository springRepository;

    public RegisterRepository(SpringRegisterRepository springRepository){
        this.springRepository = springRepository;
    }

    @Override
    public List<Register> findAll() {
        List<RegisterEntity> list = this.springRepository.findAll();
        return list.stream().map(RegisterEntity::toRegister).toList();
    }

    @Override
    public Register findById(Long id) {
        var entity = findEntityById(id);
        return entity.toRegister();
    }

    @Override
    public Register save(Register register) {
        RegisterEntity entity;
        if (Objects.isNull(register.getId())){
            entity = new RegisterEntity(register);
        } else {
            entity = findEntityById(register.getId());
            entity.update(register);
        }

        return this.springRepository.save(entity).toRegister();
    }

    @Override
    public void deleteById(Long id) {
        findEntityById(id);
        this.springRepository.deleteById(id);
    }

    private RegisterEntity findEntityById(Long id) {
        return this.springRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Cadastro de id %d nao encontrado", id)));
    }
}
