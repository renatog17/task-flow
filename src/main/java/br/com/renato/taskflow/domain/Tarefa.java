package br.com.renato.taskflow.domain;

import java.time.LocalDate;
import java.util.Objects;

import br.com.renato.taskflow.controller.dto.tarefa.CreateTarefaDTO;
import br.com.renato.taskflow.controller.dto.tarefa.UpdateTarefaDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "Tarefa")
@Table(name = "tarefas")
public class Tarefa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String descricao;
	private LocalDate dataCriacao;
	private LocalDate prazo;
	@ManyToOne
	private Lista lista;

	public Tarefa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tarefa(String titulo, String descricao, LocalDate dataCriacao, LocalDate prazo, Lista lista) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataCriacao = dataCriacao;
		this.prazo = prazo;
		this.lista = lista;
	}

	public Tarefa(CreateTarefaDTO novaTarefa) {
		this.titulo = novaTarefa.titulo();
		this.descricao = novaTarefa.descricao();
		this.prazo = novaTarefa.prazo();
		this.dataCriacao = LocalDate.now();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public LocalDate getPrazo() {
		return prazo;
	}

	public Lista getLista() {
		return lista;
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
		Tarefa other = (Tarefa) obj;
		return Objects.equals(id, other.id);
	}

	public void update(UpdateTarefaDTO updateTarefaDto) {
		if(updateTarefaDto.titulo()!=null)
			this.titulo = updateTarefaDto.titulo();
		if(updateTarefaDto.descricao()!=null)
			this.descricao = updateTarefaDto.descricao();
		if(updateTarefaDto.prazo()!=null)
			this.prazo = updateTarefaDto.prazo();
		
	}
}
