package br.com.renato.taskflow.controller.dto.tarefa;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public record UpdateTarefaDTO(
		@NotNull
		Long id,
		String titulo,
		String descricao,
		@JsonFormat(pattern = "dd/MM/yyyy")
		LocalDate prazo) {

}
