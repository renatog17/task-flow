package br.com.renato.taskflow.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.taskflow.domain.Lista;
import br.com.renato.taskflow.domain.Quadro;

public interface ListaRepository extends JpaRepository<Lista, Long>{

	Lista findByTitulo(String titulo);
	List<Lista> findAllByAtivoTrue();
	Lista getReferenceByIdAndAtivoTrue(Long id);
	Page<Lista> findAllByAtivoTrue(Pageable pageable);
	List<Lista> findAllByQuadro(Quadro quadro);
}
