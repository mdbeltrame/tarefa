package br.com.sysmo.tarefa.controller.impl;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.sysmo.tarefa.model.Tarefa;
import br.com.sysmo.tarefa.repository.TarefaRepository;
import br.com.sysmo.tarefa.service.TarefaService;

@ExtendWith(SpringExtension.class)
public class TarefaControllerImplTest {

	
	@Mock
    private TarefaService tarefaService;

    @Mock
    private TarefaRepository tarefaRepository;
	
    @InjectMocks
    private TarefaControllerImpl tarefaControllerImpl;
    
    private Tarefa tarefaMock;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        tarefaMock = new Tarefa();
        tarefaMock.setCodigo(1L);
        tarefaMock.setTitulo("Minha Tarefa");
        tarefaMock.setDescricao("Descrição da Tarefa");
        tarefaMock.setDataCriacao(LocalDate.now());

        when(tarefaRepository.findAllByOrderByCodigoAsc()).thenReturn(List.of(tarefaMock));
        when(tarefaRepository.findByCodigo(1L)).thenReturn(Optional.of(tarefaMock));
        when(tarefaService.salvar(any(Tarefa.class))).thenReturn(tarefaMock);
        when(tarefaRepository.existsById(1L)).thenReturn(true);
        when(tarefaRepository.existsById(99L)).thenReturn(false);
        doNothing().when(tarefaService).excluir(1L);
    }

    @Test
    void buscarListaTarefa_DeveRetornarListaDeTarefas() {
        List<Tarefa> resultado = tarefaControllerImpl.buscarListaTarefa();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Minha Tarefa", resultado.get(0).getTitulo());
    }

    @Test
    void registrar_DeveSalvarETarefaERetornarOk() {
        ResponseEntity<Tarefa> resposta = tarefaControllerImpl.registrar(tarefaMock);

        assertNotNull(resposta);
        assertEquals(200, resposta.getStatusCode().value());
        assertEquals("Minha Tarefa", resposta.getBody().getTitulo());
    }

    @Test
    void buscarTarefa_DeveRetornarTarefa_QuandoExiste() {
        ResponseEntity<Tarefa> resposta = tarefaControllerImpl.buscarTarefa(1L);

        assertNotNull(resposta);
        assertEquals(200, resposta.getStatusCode().value());
        assertEquals("Minha Tarefa", resposta.getBody().getTitulo());
    }

    @Test
    void buscarTarefa_DeveRetornarNotFound_QuandoNaoExiste() {
        ResponseEntity<Tarefa> resposta = tarefaControllerImpl.buscarTarefa(99L);

        assertNotNull(resposta);
        assertEquals(404, resposta.getStatusCode().value());
    }

    @Test
    void excluirTarefa_DeveExcluirTarefaERetornarNoContent() {
        ResponseEntity<Void> resposta = tarefaControllerImpl.excluirTarefa(1L);

        assertEquals(204, resposta.getStatusCode().value());
        verify(tarefaService, times(1)).excluir(1L);
    }

    @Test
    void excluirTarefa_DeveRetornarNotFound_QuandoNaoExiste() {
        ResponseEntity<Void> resposta = tarefaControllerImpl.excluirTarefa(99L);

        assertEquals(404, resposta.getStatusCode().value());
        verify(tarefaService, never()).excluir(99L);
    }
    
}
