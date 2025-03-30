package br.com.sysmo.tarefa.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.sysmo.tarefa")
@EnableJpaRepositories(basePackages = "br.com.sysmo.tarefa.repository")
@EntityScan(basePackages = "br.com.sysmo.tarefa.model")
public class TarefaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TarefaApplication.class, args);
	}

}
