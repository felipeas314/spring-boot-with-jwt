package br.com.labs.config;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.labs.model.Usuario;

public class SpringSecurityAuditorAware implements AuditorAware<Usuario> {

	@Override
	public Optional<Usuario> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}
		

		return Optional.of((Usuario) authentication.getPrincipal());
	}

}
