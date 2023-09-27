package br.com.renato.taskflow.controller.dto.lista;

import br.com.renato.taskflow.domain.Lista;

public record ReadListaDTO (
		Long id,
		String titulo,
		Long quadroTitulo
		){

	public ReadListaDTO(Lista lista) {
		this(lista.getId(), lista.getTitulo(), lista.getQuadro().getId());
	}

}
