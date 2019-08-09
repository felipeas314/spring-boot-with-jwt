package br.com.labs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.labs.dto.TokenDTO;
import br.com.labs.model.LoginForm;
import br.com.labs.service.TokenService;

@RestController
@RequestMapping
public class AutenticacaoController {

	private TokenService tokenService;
	
	@Autowired
	private AuthenticationManager authManager;

	public AutenticacaoController(TokenService tokenService) {
		this.tokenService = tokenService;
	}
	
	public ResponseEntity<TokenDTO> autenticacao(@Valid @RequestBody LoginForm login){
		
		
		UsernamePasswordAuthenticationToken dadosLogin = login.converter();
		
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			return ResponseEntity.ok(new TokenDTO(token));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
