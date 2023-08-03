package br.com.itau.casebackendjr.domain.dto;

public record PersonalDataUpdateDTO(
        String firstName,
        String lastName,
        Integer age,
        String country
) {
}
