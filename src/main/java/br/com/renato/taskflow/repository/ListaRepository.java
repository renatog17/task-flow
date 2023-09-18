package br.com.renato.taskflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.taskflow.domain.Lista;

public interface ListaRepository extends JpaRepository<Lista, Long>{

	Lista findByTitulo(String titulo);
}
