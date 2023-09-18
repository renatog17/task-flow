package br.com.renato.taskflow.controller.dto.lista;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateListaDTO(
		@NotBlank
		String titulo,
		@NotNull
		Long idQuadro) {

}
