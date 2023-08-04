package br.com.itau.casebackendjr.domain.personal;

import br.com.itau.casebackendjr.domain.dto.RegisterUpdateDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Register {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String country;

    public void updateData(RegisterUpdateDTO dto) {
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
