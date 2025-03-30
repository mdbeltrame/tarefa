package br.com.sysmo.tarefa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sysmo.tarefa.model.Tarefa;
import br.com.sysmo.tarefa.repository.TarefaRepository;
import br.com.sysmo.tarefa.service.TarefaService;

@Service
public class TarefaServiceImpl implements TarefaService{

	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Override
	public Tarefa salvar(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }
	
	@Override
	public void excluir(Long codigo) {
        tarefaRepository.deleteById(codigo);
    }
	
}
