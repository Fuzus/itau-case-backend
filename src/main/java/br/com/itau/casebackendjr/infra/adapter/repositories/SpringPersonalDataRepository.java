package br.com.itau.casebackendjr.infra.adapter.repositories;

import br.com.itau.casebackendjr.infra.adapter.entities.PersonalDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringPersonalDataRepository extends JpaRepository<PersonalDataEntity, Long> {
}
