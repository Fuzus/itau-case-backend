package br.com.itau.casebackendjr.domain.dto;

public record RegisterUpdateDTO(
        String firstName,
        String lastName,
        Integer age,
        String country
) {
}
