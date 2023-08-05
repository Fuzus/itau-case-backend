package br.com.itau.casebackendjr.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record RegisterUpdateDTO(
        @JsonAlias({"nome", "firstName"})
        String firstName,
        @JsonAlias({"sobrenome", "lastName"})
        String lastName,
        @JsonAlias({"idade", "age"})
        Integer age,
        @JsonAlias({"pais", "country"})
        String country
) {
}
