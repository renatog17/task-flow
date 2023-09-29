package br.com.renato.taskflow.controller.dto.lista;

import jakarta.validation.constraints.NotNull;

public record UpdateListaDTO(
		@NotNull
		Long id, 
		String titulo) {

}
