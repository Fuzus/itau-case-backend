package br.com.itau.casebackendjr.domain.ports.repositories;

import br.com.itau.casebackendjr.domain.personal.PersonalData;

import java.util.List;

public interface PersonalRepositoryPort {

    List<PersonalData> findAll();
    PersonalData findById(Long id);
    PersonalData save(PersonalData personalData);
    void deleteById(Long id);

}
