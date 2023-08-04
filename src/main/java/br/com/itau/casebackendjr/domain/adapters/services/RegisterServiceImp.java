package br.com.itau.casebackendjr.domain.adapters.services;

import br.com.itau.casebackendjr.domain.dto.RegisterDTO;
import br.com.itau.casebackendjr.domain.dto.RegisterUpdateDTO;
import br.com.itau.casebackendjr.domain.model.Register;
import br.com.itau.casebackendjr.domain.ports.interfaces.RegisterServicePort;
import br.com.itau.casebackendjr.domain.ports.repositories.RegisterRepositoryPort;

import java.util.List;

public class RegisterServiceImp implements RegisterServicePort {

    private RegisterRepositoryPort repositoryPort;

    public RegisterServiceImp(RegisterRepositoryPort repositoryPort){
        this.repositoryPort = repositoryPort;
    }


    @Override
    public List<RegisterDTO> getAll() {
        return repositoryPort.findAll().stream().map(RegisterDTO::new).toList();
    }

    @Override
    public RegisterDTO getPerson(Long id) {
        return new RegisterDTO(repositoryPort.findById(id));
    }

    @Override
    public RegisterDTO create(RegisterDTO dto) {
        Register register = new Register(
                dto.id(),
                dto.firstName(),
                dto.lastName(),
                dto.age(),
                dto.country()
        );

        return new RegisterDTO(repositoryPort.save(register));
    }

    @Override
    public RegisterDTO update(Long id, RegisterUpdateDTO dto) {
        var personalData = repositoryPort.findById(id);
        personalData.updateData(dto);
        return new RegisterDTO(repositoryPort.save(personalData));
    }

    @Override
    public void delete(Long id) {
        repositoryPort.deleteById(id);
    }
}
