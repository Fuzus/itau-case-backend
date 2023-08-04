package br.com.itau.casebackendjr.domain.ports.repositories;

import br.com.itau.casebackendjr.domain.model.Register;

import java.util.List;

public interface RegisterRepositoryPort {

    List<Register> findAll();
    Register findById(Long id);
    Register save(Register register);
    void deleteById(Long id);

}
