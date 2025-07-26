package com.MarcelaEMariaLuiza.Syncro.Controllers;

import com.MarcelaEMariaLuiza.Syncro.Errors.GrupoInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
