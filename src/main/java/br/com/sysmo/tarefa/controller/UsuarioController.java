package br.com.sysmo.tarefa.controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.sysmo.tarefa.model.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/usuario")
@Tag(name = "Usuários", description = "Endpoints para gerenciar usuários")
public interface UsuarioController {

	@PostMapping("/registrar")
	@Operation(summary = "Criar novo usuário", description = "Cria um novo usuário e retorna os dados.")
	public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario);

	@PostMapping("/login")
	@Operation(summary = "Efetua Login", description = "Efetua login e retorna o token JWT.")
	public ResponseEntity<Map<String, String>> login(@RequestBody Usuario usuario);

}
