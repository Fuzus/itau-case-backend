package br.com.itau.casebackendjr.infra.adapter.repositories;

import br.com.itau.casebackendjr.infra.adapter.entities.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringRegisterRepository extends JpaRepository<RegisterEntity, Long> {
}
