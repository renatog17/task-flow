package br.com.renato.taskflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.taskflow.domain.Quadro;

public interface QuadroRepository extends JpaRepository<Quadro, Long>{

	Quadro findByTitulo(String titulo);
}
