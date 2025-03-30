package br.com.sysmo.tarefa.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tarefa")
@SequenceGenerator(name = "tarefa_sequence", sequenceName = "tarefa_sequence", allocationSize = 1)
public class Tarefa {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tarefa_sequence")
	private Long codigo;

	@Column(name = "titulo", nullable = false)
	private String titulo;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "data_criacao", nullable = false)
	private LocalDate dataCriacao;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
