package br.com.sysmo.tarefa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "colaborador")
@SequenceGenerator(name = "colaborador_sequence", sequenceName = "colaborador_sequence", allocationSize = 1)
public class Colaborador {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "colaborador_sequence")
	private Long codigo;

	@Column(name = "nome", nullable = false)
	private String nome;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
