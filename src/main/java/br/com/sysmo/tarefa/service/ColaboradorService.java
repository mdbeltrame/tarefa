package br.com.sysmo.tarefa.service;

import br.com.sysmo.tarefa.model.Colaborador;

public interface ColaboradorService {

	public Colaborador salvar(Colaborador colaborador);
	
	public void excluir(Long codigo);
}
