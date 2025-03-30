package br.com.sysmo.tarefa.service;

import java.util.Optional;

import br.com.sysmo.tarefa.model.Usuario;

public interface UsuarioService {

    public Optional<Usuario> autenticar(String email, String senha);
	
	public Usuario salvar(Usuario usuario);
	
}
