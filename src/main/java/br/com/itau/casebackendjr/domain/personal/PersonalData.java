package br.com.itau.casebackendjr.domain.personal;

import br.com.itau.casebackendjr.domain.dto.PersonalDataUpdateDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PersonalData {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String country;

    public void updateData(PersonalDataUpdateDTO dto) {
        if (dto.firstName() != null)
            setFirstName(dto.firstName());
        if (dto.lastName() != null)
            setLastName(dto.lastName());
        if (dto.age() != null)
            setAge(dto.age());
        if (dto.country() != null)
            setCountry(dto.country());
    }


}
