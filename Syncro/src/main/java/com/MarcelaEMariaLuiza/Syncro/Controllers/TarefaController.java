package com.MarcelaEMariaLuiza.Syncro.Controllers;

import com.MarcelaEMariaLuiza.Syncro.Errors.GrupoInexistenteException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MarcelaEMariaLuiza.Syncro.DTO.CreateGrupoDTO;
import com.MarcelaEMariaLuiza.Syncro.DTO.CreateTarefaDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Tarefa;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Services.TarefaService;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;
    
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
    @GetMapping("/get/grupo/{grupoId}")
    public List<CreateTarefaDTO> getTarefasPorGrupo(@PathVariable Long grupoId){
        try{
            List <CreateTarefaDTO> tarefas = tarefaService.getTarefasPorGrupo(grupoId);
            return tarefas;
        }
        catch(Exception e){
            return null;
            
        }

}
@GetMapping("/get/aluno/{alunoId}")
    public List<CreateTarefaDTO> getTarefasPorAluno(@PathVariable Long alunoId){
        try{
            List <CreateTarefaDTO> tarefas = tarefaService.getTarefasPorAluno(alunoId);
            return tarefas;
        }
        catch(Exception e){
            return null;
           
        }

}
}
