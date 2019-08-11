package br.com.labs.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.labs.model.Livro;
import br.com.labs.repository.LivroRepository;

@RestController
@RequestMapping("/livros")
public class LivroController {

	private LivroRepository livroRepository;

	public LivroController(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}

	@GetMapping
	public ResponseEntity<Page<Livro>> index(@PageableDefault(page = 0, size = 10) Pageable pageable) {
		return ResponseEntity.ok(livroRepository.findAll(pageable));
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Livro> create(@Valid @RequestBody Livro livro) {

		livroRepository.save(livro);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId())
				.toUri();

		return ResponseEntity.created(location).body(livro);
	}
}
