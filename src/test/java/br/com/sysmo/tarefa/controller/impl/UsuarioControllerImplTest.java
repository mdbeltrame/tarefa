package br.com.sysmo.tarefa.controller.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
        usuarioMock.setEmail("mateus@gmail.com");
        usuarioMock.setSenha("123456789");

        when(usuarioRepository.findByEmail("mateus@gmail.com")).thenReturn(Optional.of(usuarioMock));
        when(usuarioService.salvar(any(Usuario.class))).thenReturn(usuarioMock);
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        when(usuarioRepository.existsById(99L)).thenReturn(false);
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
