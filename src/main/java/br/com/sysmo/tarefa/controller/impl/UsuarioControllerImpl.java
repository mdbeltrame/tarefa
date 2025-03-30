package br.com.sysmo.tarefa.controller.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sysmo.tarefa.controller.UsuarioController;
import br.com.sysmo.tarefa.model.Usuario;
import br.com.sysmo.tarefa.security.JwtUtil;
import br.com.sysmo.tarefa.service.UsuarioService;

@RestController
public class UsuarioControllerImpl implements UsuarioController{

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private JwtUtil jwtUtil; 
	
	@Override
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.salvar(usuario));
    }
	
	@Override
    public ResponseEntity<Map<String, String>> login(@RequestBody Usuario usuario) {
        Optional<Usuario> autenticado = usuarioService.autenticar(usuario.getEmail(), usuario.getSenha());

        Map<String, String> response = new HashMap<>();
        if (autenticado.isPresent()) {
            String token = jwtUtil.generateToken(usuario.getEmail());

            response.put("message", "Login bem-sucedido!");
            response.put("token", token);

            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Credenciais inválidas");
            return ResponseEntity.status(401).body(response);
        }
    }
	
}
