package br.com.labs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class LivroController {

	public LivroController() {

	}

	@GetMapping
	public ResponseEntity<String> index() {
		return ResponseEntity.ok("ok");
	}
}
