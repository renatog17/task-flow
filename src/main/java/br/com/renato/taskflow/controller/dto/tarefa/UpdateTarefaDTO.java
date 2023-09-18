package br.com.renato.taskflow.controller.dto.tarefa;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record UpdateTarefaDTO(String titulo,
		String descricao,
		@JsonFormat(pattern = "dd/MM/yyyy")
		LocalDate prazo) {

}
