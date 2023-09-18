package br.com.renato.taskflow.controller.dto.lista;

import br.com.renato.taskflow.domain.Lista;

public record ReadListaDTO (String titulo,
		String quadroTitulo
		){

	public ReadListaDTO(Lista lista) {
		this(lista.getTitulo(), lista.getQuadro().getTitulo());
	}

}
