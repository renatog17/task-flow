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

import br.com.renato.taskflow.controller.dto.quadro.CreateQuadroDTO;
import br.com.renato.taskflow.controller.dto.quadro.ReadQuadroDTO;
import br.com.renato.taskflow.controller.dto.quadro.UpdateQuadroDTO;
import br.com.renato.taskflow.controller.dto.tarefa.ReadTarefaDTO;
import br.com.renato.taskflow.domain.Quadro;
import br.com.renato.taskflow.domain.Tarefa;
import br.com.renato.taskflow.repository.QuadroRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/quadros")
public class QuadroController {

	@Autowired
	QuadroRepository quadroRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> createQuadro(@RequestBody @Valid CreateQuadroDTO novoQuadro, UriComponentsBuilder uriBuilder){
		Quadro quadro = new Quadro(novoQuadro);
		quadroRepository.save(quadro);
		URI uri = uriBuilder.path("/tarefas/{id}").buildAndExpand(quadro.getId()).toUri();
		return ResponseEntity.created(uri).body(novoQuadro);
	}
	
	@GetMapping("/lista")
	public ResponseEntity<?> readQuadro(){
		List<ReadQuadroDTO> listaQuadros = quadroRepository.findAll().stream().map(quadro ->{
			ReadQuadroDTO readQuadroDto = new ReadQuadroDTO(quadro);
			return readQuadroDto;
		}).toList();
		return ResponseEntity.ok(listaQuadros);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> readQuadros(@PathVariable Long id){
		Quadro quadro = quadroRepository.getReferenceById(id);
		return ResponseEntity.ok(new ReadQuadroDTO(quadro));
	}

	@PutMapping()
	@Transactional
	public ResponseEntity<?> updateQuadro(@RequestBody UpdateQuadroDTO updateQuadroDTO){
		Quadro quadro = quadroRepository.getReferenceById(updateQuadroDTO.id());
		quadro.update(updateQuadroDTO);
		return ResponseEntity.ok(new ReadQuadroDTO(quadro));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void deleteQuadro(@PathVariable Long id) {
		quadroRepository.deleteById(id);
	}
}
