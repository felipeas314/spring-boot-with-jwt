package br.com.labs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.labs.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

}
