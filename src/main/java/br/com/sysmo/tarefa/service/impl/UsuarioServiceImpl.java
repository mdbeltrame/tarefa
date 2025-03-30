package br.com.sysmo.tarefa.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sysmo.tarefa.model.Usuario;
import br.com.sysmo.tarefa.repository.UsuarioRepository;
import br.com.sysmo.tarefa.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Optional<Usuario> autenticar(String email, String senha) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		if (usuario.isPresent() && bCryptPasswordEncoder.matches(senha, usuario.get().getSenha())) {
			return usuario;
		}
		return Optional.empty();
	}

	@Override
	public Usuario salvar(Usuario usuario) {
		usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}

}
