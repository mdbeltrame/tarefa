package br.com.sysmo.tarefa.service;

import br.com.sysmo.tarefa.model.Tarefa;

public interface TarefaService {

	public Tarefa salvar(Tarefa tarefa);
	
	public void excluir(Long codigo);
	
}
