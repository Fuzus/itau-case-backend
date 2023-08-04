package br.com.itau.casebackendjr.infra.adapter.entities;

import br.com.itau.casebackendjr.domain.personal.Register;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cadastros")
public class RegisterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String country;

    public RegisterEntity(Register register){
        this.firstName = register.getFirstName();
        this.lastName = register.getLastName();
        this.age = register.getAge();
        this.country = register.getCountry();
    }

    public void update(Register register){
        this.firstName = register.getFirstName();
        this.lastName = register.getLastName();
        this.age = register.getAge();
        this.country = register.getCountry();
    }

    public Register toPersonalData() {
        return new Register(this.id, this.firstName, this.lastName, this.age, this.country);
    }
}
