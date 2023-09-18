package br.com.renato.taskflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.taskflow.domain.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

	Tarefa findByTitulo(String titulo);
}
