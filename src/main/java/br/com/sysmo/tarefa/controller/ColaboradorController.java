package br.com.sysmo.tarefa.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sysmo.tarefa.model.Colaborador;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/colaborador")
@Tag(name = "Colaboradores", description = "Endpoints para gerenciar colaboradores")
public interface ColaboradorController {

	@GetMapping
	@Operation(summary = "Busca Todos os Colaboradores", description = "Busca todos os colaboradores e retorna a lista de colaboradores.")
    public List<Colaborador> buscarListaColaborador();
	
	@PostMapping("/registrar")
	@Operation(summary = "Registra um Colaborador", description = "Utilizado para registrar um novo colaborador sem a necessidade de passar um codigo.")
    public ResponseEntity<Colaborador> registrar(@RequestBody Colaborador colaborador);
	
	@GetMapping("/{codigo}")
	@Operation(summary = "Busca Colaborador Individual", description = "Busca um colaborador pelo codigo e retorna.")
    public ResponseEntity<Colaborador> buscarColaborador(@RequestBody Long codigo);
	
	@DeleteMapping("/{codigo}")
	@Operation(summary = "Excluir Colaborador", description = "Exclui um colaborador pelo codigo.")
	public ResponseEntity<Void> excluirColaborador(@PathVariable Long codigo);
}
