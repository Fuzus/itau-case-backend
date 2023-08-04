package br.com.itau.casebackendjr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CaseBackendJrApplication {

	private static Logger logger = LoggerFactory.getLogger(CaseBackendJrApplication.class);
	public static void main(String[] args) {
		logger.info("Iniciando aplicação");
		SpringApplication.run(CaseBackendJrApplication.class, args);
		logger.info("Aplicação pronta para receber requisições");
	}

}
