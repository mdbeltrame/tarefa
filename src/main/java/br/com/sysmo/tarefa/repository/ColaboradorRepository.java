package br.com.sysmo.tarefa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sysmo.tarefa.model.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>{

	public List<Colaborador> findAllByOrderByCodigoAsc();
	public Optional<Colaborador> findByCodigo(Long codigo);
	
}
