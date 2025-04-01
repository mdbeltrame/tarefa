package br.com.sysmo.tarefa.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.sysmo.tarefa.model.Usuario;
import br.com.sysmo.tarefa.repository.UsuarioRepository;

@ExtendWith(SpringExtension.class)
public class UsuarioServiceImplTest {

	@Mock
	private UsuarioRepository usuarioRepository;
	
	@Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@InjectMocks
	private UsuarioServiceImpl usuarioServiceImpl;

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
	    void autenticar_DeveRetornarUsuario_QuandoCredenciaisCorretas() {
	        when(usuarioRepository.findByEmail("mateus@email.com")).thenReturn(Optional.of(usuarioMock));
	        when(bCryptPasswordEncoder.matches("senha123", usuarioMock.getSenha())).thenReturn(true);

	        Optional<Usuario> resultado = usuarioServiceImpl.autenticar("mateus@email.com", "senha123");

	        assertTrue(resultado.isPresent());
	        assertEquals("mateus@email.com", resultado.get().getEmail());
	    }

	    @Test
	    void autenticar_DeveRetornarVazio_QuandoUsuarioNaoEncontrado() {
	        when(usuarioRepository.findByEmail("naoexiste@email.com")).thenReturn(Optional.empty());

	        Optional<Usuario> resultado = usuarioServiceImpl.autenticar("naoexiste@email.com", "senha123");

	        assertFalse(resultado.isPresent());
	    }

	    @Test
	    void autenticar_DeveRetornarVazio_QuandoSenhaIncorreta() {
	        when(usuarioRepository.findByEmail("mateus@email.com")).thenReturn(Optional.of(usuarioMock));
	        when(bCryptPasswordEncoder.matches("senhaErrada", usuarioMock.getSenha())).thenReturn(false);

	        Optional<Usuario> resultado = usuarioServiceImpl.autenticar("mateus@email.com", "senhaErrada");

	        assertFalse(resultado.isPresent());
	    }
	 
	@Test
	void salvar_DeveCriptografarSenhaESalvarUsuario() {
		when(bCryptPasswordEncoder.encode("senha123")).thenReturn("senhaCriptografada");
		when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioMock);

		Usuario resultado = usuarioServiceImpl.salvar(usuarioMock);

		assertNotNull(resultado);
		assertEquals("senhaCriptografada", resultado.getSenha());
		verify(usuarioRepository, times(1)).save(usuarioMock);
	}

}
