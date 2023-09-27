package br.com.renato.taskflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.taskflow.domain.Quadro;

public interface QuadroRepository extends JpaRepository<Quadro, Long>{

	Quadro findByTitulo(String titulo);
	List<Quadro> findAllByAtivoTrue();
	Quadro getReferenceByIdAndAtivoTrue(Long id);
}
