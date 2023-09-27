package br.com.renato.taskflow.controller.dto.tarefa;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTarefaDTO(
		@NotBlank
		String titulo,
		String descricao,
		@JsonFormat(pattern = "dd/MM/yyyy")
		LocalDate prazo,
		@NotNull
		Long idLista) {

}
