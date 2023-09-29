package br.com.renato.taskflow.controller.dto.lista;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.renato.taskflow.controller.dto.serializer.ReadListaDTOSerializer;
import br.com.renato.taskflow.controller.dto.tarefa.ReadTarefaDTO;
import br.com.renato.taskflow.domain.Lista;

@JsonSerialize(using = ReadListaDTOSerializer.class)
public record ReadListaDTO (
		Long id,
		String titulo,
		Long quadroId,
		LocalDate dataCriacao,
		Optional<List<ReadTarefaDTO>> tarefas
		){

	public ReadListaDTO(Lista lista) {
		this(lista.getId(), lista.getTitulo(), lista.getQuadro().getId(), lista.getDataCriacao(), Optional.empty());
	}

	public ReadListaDTO(Lista lista, List<ReadTarefaDTO> tarefas) {
		this(lista.getId(), lista.getTitulo(), lista.getQuadro().getId(), lista.getDataCriacao(), Optional.ofNullable(tarefas));
	}
}
