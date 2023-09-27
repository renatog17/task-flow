package br.com.renato.taskflow.controller.dto.tarefa;

import java.time.LocalDate;

import br.com.renato.taskflow.domain.Tarefa;

public record ReadTarefaDTO (
		Long id,
		String titulo,
		String descricao,
		LocalDate prazo,
		LocalDate dataCriacao,
		Long listaId){

	public ReadTarefaDTO(Tarefa tarefa) {
		this(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getPrazo(), tarefa.getDataCriacao(), tarefa.getLista().getId());
	}

}
