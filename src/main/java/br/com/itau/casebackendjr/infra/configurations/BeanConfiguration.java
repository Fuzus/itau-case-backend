package br.com.itau.casebackendjr.infra.configurations;

import br.com.itau.casebackendjr.domain.adapters.services.RegisterServiceImp;
import br.com.itau.casebackendjr.domain.ports.interfaces.RegisterServicePort;
import br.com.itau.casebackendjr.domain.ports.repositories.RegisterRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    RegisterServicePort PersonalService(RegisterRepositoryPort port){
        return new RegisterServiceImp(port);
    }
}
