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

import br.com.renato.taskflow.controller.dto.tarefa.CreateTarefaDTO;
import br.com.renato.taskflow.controller.dto.tarefa.ReadTarefaDTO;
import br.com.renato.taskflow.controller.dto.tarefa.UpdateTarefaDTO;
import br.com.renato.taskflow.domain.Tarefa;
import br.com.renato.taskflow.repository.TarefaRepository;
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
	public ResponseEntity<?> readTarefa(){
		List<ReadTarefaDTO> listaTarefas = tarefaRepository.findAll().stream().map(tarefa ->{
			ReadTarefaDTO readTarefaDto = new ReadTarefaDTO(tarefa);
			return readTarefaDto;
		}).toList();
		return ResponseEntity.ok(listaTarefas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> readTarefas(@PathVariable Long id){
		Tarefa tarefa = tarefaRepository.getReferenceById(id);
		return ResponseEntity.ok(new ReadTarefaDTO(tarefa));
	}

	@PutMapping()
	@Transactional
	public ResponseEntity<?> updateTarefa(@RequestBody UpdateTarefaDTO updateTarefaDto){
		Tarefa tarefa = tarefaRepository.findByTitulo(updateTarefaDto.titulo());
		tarefa.update(updateTarefaDto);
		return ResponseEntity.ok(new ReadTarefaDTO(tarefa));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void deleteTarefa(@PathVariable Long id) {
		tarefaRepository.deleteById(id);
	}
}
