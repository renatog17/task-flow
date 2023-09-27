package br.com.renato.taskflow.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.renato.taskflow.controller.dto.lista.CreateListaDTO;
import br.com.renato.taskflow.controller.dto.lista.ReadListaDTO;
import br.com.renato.taskflow.controller.dto.lista.UpdateListaDTO;
import br.com.renato.taskflow.domain.Lista;
import br.com.renato.taskflow.repository.ListaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/listas")
public class ListaController {

	@Autowired
	ListaRepository listaRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> createLista(@RequestBody @Valid CreateListaDTO novaLista, UriComponentsBuilder uriBuilder){
		Lista lista = new Lista(novaLista);
		listaRepository.save(lista);
		URI uri = uriBuilder.path("/listas/{id}").buildAndExpand (lista.getId()).toUri();
		return ResponseEntity.created(uri).body(novaLista);
	}
	
	@GetMapping("/lista")
	public ResponseEntity<?> readListas(){
		List<ReadListaDTO> listaListas = listaRepository.findAllByAtivoTrue().stream().map(lista ->{
			ReadListaDTO readListaDTO = new ReadListaDTO(lista);
			return readListaDTO;
		}).toList();
		return ResponseEntity.ok(listaListas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> readLista(@PathVariable Long id){
		Lista lista = listaRepository.getReferenceByIdAndAtivoTrue(id);
		return ResponseEntity.ok(new ReadListaDTO(lista));
	}

	@PutMapping()
	@Transactional
	public ResponseEntity<?> updateLista(@RequestBody UpdateListaDTO updatelistaDto){
		Lista lista = listaRepository.getReferenceByIdAndAtivoTrue(updatelistaDto.id());
		lista.update(updatelistaDto);
		return ResponseEntity.ok(new ReadListaDTO(lista));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void deleteTarefa(@PathVariable Long id) {
		Lista lista = listaRepository.getReferenceById(id);
		lista.exclusaoLogica();
	}
}
