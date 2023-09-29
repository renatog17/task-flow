package br.com.renato.taskflow.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.taskflow.domain.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

	Tarefa findByTitulo(String titulo);
	List<Tarefa> findAllByAtivoTrue();
	Tarefa getReferenceByIdAndAtivoTrue(Long id);
	Page<Tarefa> findAllByAtivoTrue(Pageable paginacao);
}
