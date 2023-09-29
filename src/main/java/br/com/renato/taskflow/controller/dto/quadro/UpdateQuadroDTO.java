package br.com.renato.taskflow.controller.dto.quadro;

import jakarta.validation.constraints.NotNull;

public record UpdateQuadroDTO(
		@NotNull
		Long id,
		String titulo,
		String descricao) {

}
