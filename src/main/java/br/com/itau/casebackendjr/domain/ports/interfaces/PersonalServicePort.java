package br.com.itau.casebackendjr.domain.ports.interfaces;

import br.com.itau.casebackendjr.domain.dto.PersonalDataDTO;
import br.com.itau.casebackendjr.domain.dto.PersonalDataUpdateDTO;

import java.util.List;

public interface PersonalServicePort {

    List<PersonalDataDTO> getAll();
    PersonalDataDTO getPerson(Long id);
    PersonalDataDTO create(PersonalDataDTO dto);
    PersonalDataDTO update(Long id, PersonalDataUpdateDTO dto);
    void delete(Long id);

}
