package br.com.renato.taskflow.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.renato.taskflow.controller.dto.lista.CreateListaDTO;
import br.com.renato.taskflow.controller.dto.lista.ReadListaDTO;
import br.com.renato.taskflow.controller.dto.lista.UpdateListaDTO;
import br.com.renato.taskflow.controller.dto.tarefa.ReadTarefaDTO;
import br.com.renato.taskflow.domain.Lista;
import br.com.renato.taskflow.repository.ListaRepository;
import br.com.renato.taskflow.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/listas")
public class ListaController {

	@Autowired
	ListaRepository listaRepository;
	
	@Autowired
	TarefaRepository tarefaRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> createLista(@RequestBody @Valid CreateListaDTO novaLista, UriComponentsBuilder uriBuilder){
		Lista lista = new Lista(novaLista);
		listaRepository.save(lista);
		URI uri = uriBuilder.path("/listas/{id}").buildAndExpand (lista.getId()).toUri();
		return ResponseEntity.created(uri).body(novaLista);
	}
	
	@GetMapping("/lista")
	public ResponseEntity<Page<ReadListaDTO>> readListas(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy){
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		Page<ReadListaDTO> listaListas = listaRepository.findAllByAtivoTrue(pageable).map(ReadListaDTO::new);
		return ResponseEntity.ok(listaListas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> readLista(@PathVariable Long id){
		Lista lista = listaRepository.getReferenceByIdAndAtivoTrue(id);
		if(lista==null)
			throw new EntityNotFoundException();
		return ResponseEntity.ok(new ReadListaDTO(lista));
	}
	
	@GetMapping("/{id}/tarefas")
	public ResponseEntity<ReadListaDTO> readListaETarefas(@PathVariable Long id){
		Lista lista = listaRepository.getReferenceById(id);
		
		List<ReadTarefaDTO> tarefas = tarefaRepository.findAllByLista(lista).stream().map(
				tarefa -> {
					ReadTarefaDTO readTarefaDTO = new ReadTarefaDTO(tarefa);
					return readTarefaDTO;
				}).toList();
		
		ReadListaDTO listaComTarefas = new ReadListaDTO(lista, tarefas);
		return ResponseEntity.ok(listaComTarefas);
	}
	
	@PutMapping()
	@Transactional
	public ResponseEntity<?> updateLista(@RequestBody UpdateListaDTO updatelistaDto){
		Lista lista = listaRepository.getReferenceByIdAndAtivoTrue(updatelistaDto.id());
		if(lista==null)
			throw new EntityNotFoundException();
		lista.update(updatelistaDto);
		return ResponseEntity.ok(new ReadListaDTO(lista));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteTarefa(@PathVariable Long id) {
		Lista lista = listaRepository.getReferenceById(id);
		if(lista.getAtivo()) {
			lista.exclusaoLogica();
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
