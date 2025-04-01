package br.com.sysmo.tarefa.controller.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.sysmo.tarefa.model.Usuario;
import br.com.sysmo.tarefa.repository.UsuarioRepository;
import br.com.sysmo.tarefa.security.JwtUtil;
import br.com.sysmo.tarefa.service.UsuarioService;

@ExtendWith(SpringExtension.class)
public class UsuarioControllerImplTest {

	@Mock
	private UsuarioService usuarioService;

	@Mock
	private UsuarioRepository usuarioRepository;

	@Mock
	private JwtUtil jwtUtil;

	@InjectMocks
	private UsuarioControllerImpl usuarioControllerImpl;

	private Usuario usuarioMock;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		usuarioMock = new Usuario();
		usuarioMock.setCodigo(1L);
		usuarioMock.setNome("Mateus");
		usuarioMock.setEmail("mateus@email.com");
		usuarioMock.setSenha("senha123");
	}

	@Test
	void login_DeveRetornarToken_QuandoCredenciaisCorretas() {
		when(usuarioService.autenticar("mateus@email.com", "senha123")).thenReturn(Optional.of(usuarioMock));
		when(jwtUtil.generateToken("mateus@email.com")).thenReturn("token_gerado");

		ResponseEntity<Map<String, String>> resposta = usuarioControllerImpl.login(usuarioMock);

		assertNotNull(resposta);
		assertEquals(200, resposta.getStatusCode().value());
		assertEquals("Login bem-sucedido!", resposta.getBody().get("message"));
		assertEquals("token_gerado", resposta.getBody().get("token"));
	}

	@Test
	void login_DeveRetornarErro401_QuandoCredenciaisInvalidas() {
		when(usuarioService.autenticar("mateus@email.com", "senha123")).thenReturn(Optional.empty());

		ResponseEntity<Map<String, String>> resposta = usuarioControllerImpl.login(usuarioMock);

		assertNotNull(resposta);
		assertEquals(401, resposta.getStatusCode().value());
		assertEquals("Credenciais inválidas", resposta.getBody().get("message"));
		assertFalse(resposta.getBody().containsKey("token"));
	}

	@Test
	void registrar_DeveSalvarEUsuarioERetornarOk() {
		when(usuarioService.salvar(any(Usuario.class))).thenReturn(usuarioMock);

		ResponseEntity<Usuario> resposta = usuarioControllerImpl.registrar(usuarioMock);

		assertNotNull(resposta);
		assertEquals(200, resposta.getStatusCode().value());
		assertEquals(usuarioMock, resposta.getBody());
	}

}
