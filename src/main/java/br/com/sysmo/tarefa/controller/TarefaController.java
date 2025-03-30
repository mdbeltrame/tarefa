package br.com.sysmo.tarefa.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sysmo.tarefa.model.Tarefa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/tarefa")
@Tag(name = "Tarefas", description = "Endpoints para gerenciar tarefas")
public interface TarefaController {

	@GetMapping
	@Operation(summary = "Busca Todas as Tarefas", description = "Busca todas as tarefas e retorna a lista de tarefas.")
    public List<Tarefa> buscarListaTarefa();
	
	@PostMapping("/registrar")
	@Operation(summary = "Registra/Editar uma Tarefas", description = "Utilizado para registrar uma nova tarefa sem a necessidade de passar um codigo, utilizado para editar uma tarefa passando o codigo e por fim retorna a mesma.")
    public ResponseEntity<Tarefa> registrar(@RequestBody Tarefa tarefa);
	
	@GetMapping("/{codigo}")
	@Operation(summary = "Busca Tarefa Individual", description = "Busca uma tarefa pelo codigo e retorna.")
    public ResponseEntity<Tarefa> buscarTarefa(@RequestBody Long codigo);
	
	@DeleteMapping("/{codigo}")
	@Operation(summary = "Excluir Tarefa", description = "Exclui uma tarefa pelo codigo.")
	public ResponseEntity<Void> excluirTarefa(@PathVariable Long codigo);
}
