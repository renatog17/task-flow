package br.com.renato.taskflow.controller.dto.quadro;

import jakarta.validation.constraints.NotBlank;

public record CreateQuadroDTO(
		@NotBlank String titulo, 
		String descricao) {
}
