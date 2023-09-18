package br.com.renato.taskflow.controller.dto.tarefa;

import java.time.LocalDate;

import br.com.renato.taskflow.domain.Tarefa;

public record ReadTarefaDTO (String titulo,
		String descricao,
		LocalDate prazo,
		LocalDate dataCriacao){

	public ReadTarefaDTO(Tarefa tarefa) {
		this(tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getPrazo(), tarefa.getDataCriacao());
	}

}
