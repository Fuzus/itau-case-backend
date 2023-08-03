package br.com.itau.casebackendjr.domain.dto;

import br.com.itau.casebackendjr.domain.personal.PersonalData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonalDataDTO(
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
    public PersonalDataDTO(PersonalData data){
        this(data.getId(), data.getFirstName(), data.getLastName(), data.getAge(), data.getCountry());
    }
}
