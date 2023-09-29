package br.com.renato.taskflow.controller.dto.lista;

import java.time.LocalDate;

import br.com.renato.taskflow.domain.Lista;

public record ReadListaDTO (
		Long id,
		String titulo,
		Long quadroId,
		LocalDate dataCriacao
		){

	public ReadListaDTO(Lista lista) {
		this(lista.getId(), lista.getTitulo(), lista.getQuadro().getId(), lista.getDataCriacao());
	}

}
