package br.com.sysmo.tarefa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sysmo.tarefa.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

	public List<Tarefa> findAllByOrderByCodigoAsc();
	public Optional<Tarefa> findByCodigo(Long codigo);
}
