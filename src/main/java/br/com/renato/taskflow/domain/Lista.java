package br.com.renato.taskflow.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.renato.taskflow.controller.dto.lista.CreateListaDTO;
import br.com.renato.taskflow.controller.dto.lista.UpdateListaDTO;
import br.com.renato.taskflow.controller.dto.tarefa.ReadTarefaDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity(name = "Lista")
@Table(name = "listas")
public class Lista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	@ManyToOne()
	private Quadro quadro;
	@OneToMany(mappedBy = "lista", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Tarefa> tarefas = new ArrayList<>();
	private Boolean ativo = true;
	private LocalDate dataCriacao;

	public Lista() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lista(String titulo, Quadro quadro) {
		super();
		this.titulo = titulo;
		this.quadro = quadro;
	}

	public Lista(@Valid CreateListaDTO novaLista) {
		Quadro quadro = new Quadro(novaLista.idQuadro());
		this.titulo = novaLista.titulo();
		this.quadro = quadro;
		this.dataCriacao = LocalDate.now();
	}

	public Lista(Long idLista) {
		this.id = idLista;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public Quadro getQuadro() {
		return quadro;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void exclusaoLogica() {
		this.ativo = false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lista other = (Lista) obj;
		return Objects.equals(id, other.id);
	}

	public void update(UpdateListaDTO updatelistaDto) {
		if (updatelistaDto.titulo() != null)
			this.titulo = updatelistaDto.titulo();
	}

}
