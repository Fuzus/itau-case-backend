package br.com.itau.casebackendjr.domain.dto;

import br.com.itau.casebackendjr.domain.model.Register;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        Long id,
        @NotBlank
        @JsonAlias({"nome", "firstName"})
        String firstName,
        @NotBlank
        @JsonAlias({"sobrenome", "lastName"})
        String lastName,
        @NotNull
        @JsonAlias({"idade", "age"})
        Integer age,
        @NotBlank
        @JsonAlias({"pais", "country"})
        String country
) {
    public RegisterDTO(Register data) {
        this(data.getId(), data.getFirstName(), data.getLastName(), data.getAge(), data.getCountry());
    }
}
