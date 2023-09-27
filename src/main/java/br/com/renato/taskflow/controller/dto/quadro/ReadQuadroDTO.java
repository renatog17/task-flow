package br.com.renato.taskflow.controller.dto.quadro;

import java.time.LocalDate;

import br.com.renato.taskflow.domain.Quadro;

public record ReadQuadroDTO (
		Long id,
		String titulo,
		String descricao,
		LocalDate dataCriacao){

	public ReadQuadroDTO(Quadro quadro) {
		
		this(quadro.getId(), quadro.getTitulo(), quadro.getDescricao(), quadro.getDataCriacao());
	}
}
