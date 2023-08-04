package br.com.itau.casebackendjr.domain.ports.interfaces;

import br.com.itau.casebackendjr.domain.dto.RegisterDTO;
import br.com.itau.casebackendjr.domain.dto.RegisterUpdateDTO;

import java.util.List;

public interface RegisterServicePort {

    List<RegisterDTO> getAll();
    RegisterDTO getPerson(Long id);
    RegisterDTO create(RegisterDTO dto);
    RegisterDTO update(Long id, RegisterUpdateDTO dto);
    void delete(Long id);

}
