package br.com.sysmo.tarefa.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.sysmo.tarefa.model.Tarefa;
import br.com.sysmo.tarefa.repository.TarefaRepository;

@ExtendWith(SpringExtension.class)
public class TarefaServiceImplTest {

	@Mock
	private TarefaRepository tarefaRepository;
	
	@InjectMocks
	private TarefaServiceImpl tarefaServiceImpl;
	
	private Tarefa tarefaMock;
	
	@BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        tarefaMock = new Tarefa();
        tarefaMock.setCodigo(1L);
        tarefaMock.setTitulo("Minha Tarefa");
        tarefaMock.setDescricao("Descrição da Tarefa");
        tarefaMock.setDataCriacao(LocalDate.now());
    }
	
	@Test
    void salvar_DeveSalvarETarefa() {
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefaMock);

        Tarefa resultado = tarefaServiceImpl.salvar(tarefaMock);

        assertNotNull(resultado);
        assertEquals("Minha Tarefa", resultado.getTitulo());
    }

    @Test
    void excluir_DeveChamarRepositoryDeleteById() {
        doNothing().when(tarefaRepository).deleteById(1L);

        tarefaServiceImpl.excluir(1L);

        verify(tarefaRepository, times(1)).deleteById(1L);
    }
	
}
