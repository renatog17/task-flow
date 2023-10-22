package br.com.renato.taskflow.controller;

import java.net.URI;

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

import br.com.renato.taskflow.controller.dto.tarefa.CreateTarefaDTO;
import br.com.renato.taskflow.controller.dto.tarefa.ReadTarefaDTO;
import br.com.renato.taskflow.controller.dto.tarefa.UpdateTarefaDTO;
import br.com.renato.taskflow.domain.Tarefa;
import br.com.renato.taskflow.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

	@Autowired
	TarefaRepository tarefaRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> createTarefa(@RequestBody @Valid CreateTarefaDTO novaTarefa, UriComponentsBuilder uriBuilder){
		Tarefa tarefa = new Tarefa(novaTarefa);
		tarefaRepository.save(tarefa);
		URI uri = uriBuilder.path("/tarefas/{id}").buildAndExpand(tarefa.getId()).toUri();
		return ResponseEntity.created(uri).body(novaTarefa);
	}
	
	@GetMapping("/lista")
	public ResponseEntity<Page<ReadTarefaDTO>> readTarefa(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy){
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		Page<ReadTarefaDTO> tarefas = tarefaRepository.findAllByAtivoTrue(pageable).map(ReadTarefaDTO::new);
		return ResponseEntity.ok(tarefas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> readTarefas(@PathVariable Long id){
		Tarefa tarefa = tarefaRepository.getReferenceByIdAndAtivoTrue(id);
		if(tarefa==null)
			throw new EntityNotFoundException();
			
		return ResponseEntity.ok(new ReadTarefaDTO(tarefa));
	}

	@PutMapping()
	@Transactional
	public ResponseEntity<?> updateTarefa(@RequestBody @Valid UpdateTarefaDTO updateTarefaDto){
		Tarefa tarefa = tarefaRepository.getReferenceByIdAndAtivoTrue(updateTarefaDto.id());
		if(tarefa==null)
			throw new EntityNotFoundException();
		tarefa.update(updateTarefaDto);
		return ResponseEntity.ok(new ReadTarefaDTO(tarefa));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteTarefa(@PathVariable Long id) {
		Tarefa tarefa = tarefaRepository.getReferenceById(id);
		if(tarefa.getAtivo()) {
			tarefa.exclusaoLogica();
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
