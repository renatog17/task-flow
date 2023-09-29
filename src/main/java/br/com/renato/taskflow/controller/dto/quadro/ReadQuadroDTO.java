package br.com.renato.taskflow.controller.dto.quadro;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.renato.taskflow.controller.dto.lista.ReadListaDTO;
import br.com.renato.taskflow.controller.dto.serializer.ReadQuadroDTOSerializer;
import br.com.renato.taskflow.domain.Quadro;

@JsonSerialize(using = ReadQuadroDTOSerializer.class)
public record ReadQuadroDTO (
		Long id,
		String titulo,
		String descricao,
		LocalDate dataCriacao,
		Optional<List<ReadListaDTO>> listas){

	public ReadQuadroDTO(Quadro quadro) {
		this(quadro.getId(), quadro.getTitulo(), quadro.getDescricao(), quadro.getDataCriacao(), Optional.empty());
	}

	public ReadQuadroDTO(Quadro quadro, List<ReadListaDTO> listas) {
		this(quadro.getId(), quadro.getTitulo(), quadro.getDescricao(), quadro.getDataCriacao(), Optional.ofNullable(listas));
	}
}
