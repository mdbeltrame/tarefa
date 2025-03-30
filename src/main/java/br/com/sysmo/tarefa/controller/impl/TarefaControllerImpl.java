package br.com.sysmo.tarefa.controller.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sysmo.tarefa.controller.TarefaController;
import br.com.sysmo.tarefa.model.Tarefa;
import br.com.sysmo.tarefa.repository.TarefaRepository;
import br.com.sysmo.tarefa.service.TarefaService;

@RestController
public class TarefaControllerImpl implements TarefaController {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	private TarefaService tarefaService;

	@Override
	public List<Tarefa> buscarListaTarefa() {
		return tarefaRepository.findAllByOrderByCodigoAsc();
	}

	@Override
	public ResponseEntity<Tarefa> registrar(@RequestBody Tarefa tarefa) {

		if (tarefa.getDataCriacao() == null) {
			tarefa.setDataCriacao(LocalDate.now());
		}

		return ResponseEntity.ok(tarefaService.salvar(tarefa));
	}

	@Override
	public ResponseEntity<Tarefa> buscarTarefa(@PathVariable Long codigo) {
		return tarefaRepository.findByCodigo(codigo).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());

	}

	@Override
	public ResponseEntity<Void> excluirTarefa(@PathVariable Long codigo) {
		if (!tarefaRepository.existsById(codigo)) {
			return ResponseEntity.notFound().build();
		}

		tarefaService.excluir(codigo);
		;
		return ResponseEntity.noContent().build();
	}

}
