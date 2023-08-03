package br.com.itau.casebackendjr.infra.adapter.entities;

import br.com.itau.casebackendjr.domain.personal.PersonalData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cadastros")
public class PersonalDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String country;

    public PersonalDataEntity(PersonalData personalData){
        this.firstName = personalData.getFirstName();
        this.lastName = personalData.getLastName();
        this.age = personalData.getAge();
        this.country = personalData.getCountry();
    }

    public void update(PersonalData personalData){
        this.firstName = personalData.getFirstName();
        this.lastName = personalData.getLastName();
        this.age = personalData.getAge();
        this.country = personalData.getCountry();
    }

    public PersonalData toPersonalData() {
        return new PersonalData(this.id, this.firstName, this.lastName, this.age, this.country);
    }
}
