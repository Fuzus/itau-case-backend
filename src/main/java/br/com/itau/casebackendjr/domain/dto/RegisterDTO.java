package br.com.itau.casebackendjr.domain.dto;

import br.com.itau.casebackendjr.domain.personal.Register;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        Long id,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotNull
        Integer age,
        @NotBlank
        String country
) {
    public RegisterDTO(Register data){
        this(data.getId(), data.getFirstName(), data.getLastName(), data.getAge(), data.getCountry());
    }
}
