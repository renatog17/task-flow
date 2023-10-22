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

import br.com.renato.taskflow.controller.dto.lista.ReadListaDTO;
import br.com.renato.taskflow.controller.dto.quadro.CreateQuadroDTO;
import br.com.renato.taskflow.controller.dto.quadro.ReadQuadroDTO;
import br.com.renato.taskflow.controller.dto.quadro.UpdateQuadroDTO;
import br.com.renato.taskflow.controller.dto.tarefa.ReadTarefaDTO;
import br.com.renato.taskflow.domain.Quadro;
import br.com.renato.taskflow.domain.Tarefa;
import br.com.renato.taskflow.repository.ListaRepository;
import br.com.renato.taskflow.repository.QuadroRepository;
import br.com.renato.taskflow.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/quadros")
public class QuadroController {

	@Autowired
	QuadroRepository quadroRepository;
	@Autowired
	ListaRepository listaRepository;
	@Autowired
	TarefaRepository tarefaRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<?> createQuadro(@RequestBody @Valid CreateQuadroDTO novoQuadro,
			UriComponentsBuilder uriBuilder) {
		Quadro quadro = new Quadro(novoQuadro);
		quadroRepository.save(quadro);
		URI uri = uriBuilder.path("/tarefas/{id}").buildAndExpand(quadro.getId()).toUri();
		return ResponseEntity.created(uri).body(new ReadQuadroDTO(quadro));
	}

	@GetMapping("/lista")
	public ResponseEntity<Page<ReadQuadroDTO>> readQuadro(@RequestParam int page, @RequestParam int size,
			@RequestParam String sortBy) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		Page<ReadQuadroDTO> listaQuadros = quadroRepository.findAllByAtivoTrue(pageable).map(ReadQuadroDTO::new);
		return ResponseEntity.ok(listaQuadros);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> readQuadros(@PathVariable Long id) {
		Quadro quadro = quadroRepository.getReferenceById(id);
		return ResponseEntity.ok(new ReadQuadroDTO(quadro));
	}
	
	@GetMapping("/{id}/listas")
	public ResponseEntity<ReadQuadroDTO> readQuadroComListas(@PathVariable Long id){
		Quadro quadro = quadroRepository.getReferenceById(id);
		List<ReadListaDTO> listas = listaRepository.findAllByQuadro(quadro).stream().map(
				lista -> {
					
					List<ReadTarefaDTO> tarefas = tarefaRepository.findAllByLista(lista).stream().map(
							tarefa -> {
								ReadTarefaDTO readTarefaDTO = new ReadTarefaDTO(tarefa);
								return readTarefaDTO;
							}).toList();
					
					ReadListaDTO readListaDTO = new ReadListaDTO(lista, tarefas);
					return readListaDTO;
				}).toList();
		
		ReadQuadroDTO quadroComListas = new ReadQuadroDTO(quadro, listas);
		return ResponseEntity.ok(quadroComListas);
	}

	@PutMapping()
	@Transactional
	public ResponseEntity<?> updateQuadro(@RequestBody @Valid UpdateQuadroDTO updateQuadroDTO) {
		Quadro quadro = quadroRepository.getReferenceByIdAndAtivoTrue(updateQuadroDTO.id());
		if (quadro == null)
			throw new EntityNotFoundException();
		quadro.update(updateQuadroDTO);
		return ResponseEntity.ok(new ReadQuadroDTO(quadro));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteQuadro(@PathVariable Long id) {
		Quadro quadro = quadroRepository.getReferenceById(id);
		if (quadro.getAtivo()) {
			quadro.exclusaoLogica();
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
