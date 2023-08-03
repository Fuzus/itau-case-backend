package br.com.itau.casebackendjr.domain.adapters.services;

import br.com.itau.casebackendjr.domain.dto.PersonalDataDTO;
import br.com.itau.casebackendjr.domain.dto.PersonalDataUpdateDTO;
import br.com.itau.casebackendjr.domain.personal.PersonalData;
import br.com.itau.casebackendjr.domain.ports.interfaces.PersonalServicePort;
import br.com.itau.casebackendjr.domain.ports.repositories.PersonalRepositoryPort;

import java.util.List;

public class PersonalServiceImp implements PersonalServicePort {

    private PersonalRepositoryPort repositoryPort;

    public PersonalServiceImp(PersonalRepositoryPort repositoryPort){
        this.repositoryPort = repositoryPort;
    }


    @Override
    public List<PersonalDataDTO> getAll() {
        return repositoryPort.findAll().stream().map(PersonalDataDTO::new).toList();
    }

    @Override
    public PersonalDataDTO getPerson(Long id) {
        return new PersonalDataDTO(repositoryPort.findById(id));
    }

    @Override
    public PersonalDataDTO create(PersonalDataDTO dto) {
        PersonalData personalData = new PersonalData(
                dto.id(),
                dto.firstName(),
                dto.lastName(),
                dto.age(),
                dto.country()
        );

        return new PersonalDataDTO(repositoryPort.save(personalData));
    }

    @Override
    public PersonalDataDTO update(Long id, PersonalDataUpdateDTO dto) {
        var personalData = repositoryPort.findById(id);
        personalData.updateData(dto);
        return new PersonalDataDTO(repositoryPort.save(personalData));
    }

    @Override
    public void delete(Long id) {
        repositoryPort.deleteById(id);
    }
}
