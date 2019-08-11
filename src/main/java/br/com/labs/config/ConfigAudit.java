package br.com.labs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import br.com.labs.model.Usuario;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ConfigAudit {

	@Bean
	public AuditorAware<Usuario> auditorAware() {
		return new SpringSecurityAuditorAware();
	}
}
