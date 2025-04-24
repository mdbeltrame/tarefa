package br.com.sysmo.tarefa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sysmo.tarefa.model.Colaborador;
import br.com.sysmo.tarefa.repository.ColaboradorRepository;
import br.com.sysmo.tarefa.service.ColaboradorService;

@Service
public class ColaboradorServiceImpl implements ColaboradorService{

	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	@Override
	public Colaborador salvar(Colaborador colaborador) {
        return colaboradorRepository.save(colaborador);
    }
	
	@Override
	public void excluir(Long codigo) {
		colaboradorRepository.deleteById(codigo);
    }
}
