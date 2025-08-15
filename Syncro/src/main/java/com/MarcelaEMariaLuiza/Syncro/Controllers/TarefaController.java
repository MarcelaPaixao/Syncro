package com.MarcelaEMariaLuiza.Syncro.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MarcelaEMariaLuiza.Syncro.DTO.CreateTarefaDTO;
import com.MarcelaEMariaLuiza.Syncro.DTO.TarefaResponseDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Tarefa;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Errors.GrupoInexistenteException;
import com.MarcelaEMariaLuiza.Syncro.Services.TarefaService;
/**
 * Controlador REST para o gerenciamento de Tarefas.
 * <p>
 * Este controlador expõe os endpoints HTTP para criar novas tarefas e
 * consultar tarefas existentes, seja por grupo ou por aluno.
 * </p>
 *
 * @author Marcela, Maria Luiza
 * @version 1.0
 * @since 2025-07-28
 */
@RestController
@RequestMapping("/api/tarefa")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    /**
     * Endpoint para a criação de uma nova tarefa.
     * <p>
     * Recebe os dados da tarefa através de um DTO, delega a lógica de criação
     * para o {@link TarefaService} e trata exceções de negócio, como campos
     * não preenchidos ou entidades relacionadas inexistentes.
     * </p>
     *
     * @param createTarefaDTO DTO com os dados da tarefa a ser criada.
     * @return Um {@link ResponseEntity} com a resposta da operação (sucesso ou erro).
     */
    @PostMapping("/create")
    public ResponseEntity<?> createTarefa(@RequestBody CreateTarefaDTO createTarefaDTO){
       try{
        Tarefa tarefa = tarefaService.createTarefa(createTarefaDTO);
        return ResponseEntity.ok("Tarefa gerada com sucesso");

       }catch(CampoNaoPreenchidoException |  GrupoInexistenteException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
       }
       catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
       }
    }
    /**
     * Endpoint para buscar todas as tarefas de um grupo específico.
     *
     * @param grupoId O ID do grupo, recebido como uma variável no caminho (path variable).
     * @return Uma lista de {@link CreateTarefaDTO} representando as tarefas do grupo, ou {@code null} em caso de erro.
     */
    @GetMapping("/get/grupo/{grupoId}")
    public ResponseEntity<?> getTarefasPorGrupo(@PathVariable Long grupoId){
        try{
            List <CreateTarefaDTO> tarefas = tarefaService.getTarefasPorGrupo(grupoId);
            return ResponseEntity.ok(tarefas);
        }
        catch(Exception e){
            return null;
            
        }

}
    /**
     * Endpoint para buscar todas as tarefas de um aluno específico.
     *
     * @param alunoId O ID do aluno, recebido como uma variável no caminho (path variable).
     * @return Uma lista de {@link CreateTarefaDTO} representando as tarefas do aluno, ou {@code null} em caso de erro.
     */
@GetMapping("/get/aluno/{alunoId}")
    public ResponseEntity<?> getTarefasPorAluno(@PathVariable Long alunoId){
        try{
            List <CreateTarefaDTO> tarefas = tarefaService.getTarefasPorAluno(alunoId);
            return ResponseEntity.ok(tarefas);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
           
        }

}

@PutMapping("/edita")
    public ResponseEntity<?> editaTarefa(@RequestBody CreateTarefaDTO createTarefaDTO, Authentication authentication ){
        try{
          Tarefa tarefa = tarefaService.EditaTarefa(createTarefaDTO);
          return ResponseEntity.ok("Tarefa editada com sucesso");
          
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
@GetMapping("/get/avalia/{alunoId}")
    public ResponseEntity<?> getTarefasAvaliarPorAluno(@PathVariable Long alunoId){
        try{
            List <TarefaResponseDTO> tarefas = tarefaService.getTarefasParaAvaliar(alunoId);
            return ResponseEntity.ok(tarefas);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
           
        }

}

@GetMapping("/get/{tarefaId}")
public ResponseEntity<?> getTarefa(@PathVariable Long tarefaId){
    try{
        CreateTarefaDTO createTarefaDTO = tarefaService.getTarefa(tarefaId);
        return ResponseEntity.ok(createTarefaDTO);
    }catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
       
    }
}
}
