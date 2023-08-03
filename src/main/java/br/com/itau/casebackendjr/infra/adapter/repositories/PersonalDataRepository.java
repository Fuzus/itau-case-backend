package br.com.itau.casebackendjr.infra.adapter.repositories;

import br.com.itau.casebackendjr.domain.personal.PersonalData;
import br.com.itau.casebackendjr.domain.ports.repositories.PersonalRepositoryPort;
import br.com.itau.casebackendjr.infra.adapter.entities.PersonalDataEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
@Component
public class PersonalDataRepository implements PersonalRepositoryPort {

    private SpringPersonalDataRepository springRepository;

    public PersonalDataRepository(SpringPersonalDataRepository springRepository){
        this.springRepository = springRepository;
    }

    @Override
    public List<PersonalData> findAll() {
        List<PersonalDataEntity> list = this.springRepository.findAll();
        return list.stream().map(PersonalDataEntity::toPersonalData).toList();
    }

    @Override
    public PersonalData findById(Long id) {
        var entity = this.springRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cadastro nao encontrado"));
        return entity.toPersonalData();
    }

    @Override
    public PersonalData save(PersonalData personalData) {
        PersonalDataEntity entity;
        if (Objects.isNull(personalData.getId())){
            entity = new PersonalDataEntity(personalData);
        } else {
            entity = this.springRepository.findById(personalData.getId()).get();
            entity.update(personalData);
        }

        return this.springRepository.save(entity).toPersonalData();
    }

    @Override
    public void deleteById(Long id) {
        this.springRepository.deleteById(id);
    }
}
