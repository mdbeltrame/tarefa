package br.com.sysmo.tarefa.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sysmo.tarefa.controller.ColaboradorController;
import br.com.sysmo.tarefa.model.Colaborador;
import br.com.sysmo.tarefa.repository.ColaboradorRepository;
import br.com.sysmo.tarefa.service.ColaboradorService;

@RestController
public class ColaboradorControllerImpl implements ColaboradorController {

	@Autowired
	private ColaboradorService colaboradorService;
	
	@Autowired
	private ColaboradorRepository colaboradorRepository; 

	@Override
	public List<Colaborador> buscarListaColaborador() {
		return colaboradorRepository.findAllByOrderByCodigoAsc();
	}
	
	@Override
	public ResponseEntity<Colaborador> registrar(@RequestBody Colaborador colaborador) {
		return ResponseEntity.ok(colaboradorService.salvar(colaborador));
	}

	@Override
	public ResponseEntity<Colaborador> buscarColaborador(@PathVariable Long codigo) {
		return colaboradorRepository.findByCodigo(codigo).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());

	}

	@Override
	public ResponseEntity<Void> excluirColaborador(@PathVariable Long codigo) {
		if (!colaboradorRepository.existsById(codigo)) {
			return ResponseEntity.notFound().build();
		}

		colaboradorService.excluir(codigo);
		;
		return ResponseEntity.noContent().build();
	}
	
}
