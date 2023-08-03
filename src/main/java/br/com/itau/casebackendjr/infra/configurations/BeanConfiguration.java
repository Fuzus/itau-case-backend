package br.com.itau.casebackendjr.infra.configurations;

import br.com.itau.casebackendjr.domain.adapters.services.PersonalServiceImp;
import br.com.itau.casebackendjr.domain.ports.interfaces.PersonalServicePort;
import br.com.itau.casebackendjr.domain.ports.repositories.PersonalRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    PersonalServicePort PersonalService(PersonalRepositoryPort port){
        return new PersonalServiceImp(port);
    }
}
