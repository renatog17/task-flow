package br.com.renato.taskflow.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.renato.taskflow.controller.dto.quadro.CreateQuadroDTO;
import br.com.renato.taskflow.controller.dto.quadro.UpdateQuadroDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity(name = "Quadro")
@Table(name = "quadros")
public class Quadro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String descricao;
	private LocalDate dataCriacao;
	@OneToMany(mappedBy = "quadro")
	private List<Lista> listas = new ArrayList<Lista>();
	private Boolean ativo = true;

	public Quadro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Quadro(String titulo, String descricao, List<Lista> listas) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataCriacao = LocalDate.now();
		this.listas = listas;
	}

	public Quadro(@Valid CreateQuadroDTO novoQuadro) {
		this(novoQuadro.titulo(), novoQuadro.descricao(), null);
	}

	public Quadro(Long idQuadro) {
		this.id = idQuadro;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public List<Lista> getListas() {
		return listas;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	public Boolean getAtivo() {
		return ativo;
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
		Quadro other = (Quadro) obj;
		return Objects.equals(id, other.id);
	}

	public void update(UpdateQuadroDTO updateQuadroDTO) {
		if (updateQuadroDTO.titulo() != null)
			this.titulo = updateQuadroDTO.titulo();
		if (updateQuadroDTO.descricao() != null)
			this.descricao = updateQuadroDTO.descricao();

	}

}
