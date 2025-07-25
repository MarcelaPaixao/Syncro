package com.MarcelaEMariaLuiza.Syncro.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.MarcelaEMariaLuiza.Syncro.DTO.CreateTarefaDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Tarefa;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Repositories.GrupoRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.TarefaRepository;

@Service
public class TarefaService{

    private TarefaRepository tarefaRepository;

    private GrupoRepository grupoRepository;

    public Tarefa createTarefa(@RequestBody CreateTarefaDTO createTarefaDTO){
       
        if(createTarefaDTO.getTitulo().isEmpty() || createTarefaDTO.getTitulo() == null 
         || createTarefaDTO.getGrupoId() == null){
            throw new CampoNaoPreenchidoException("Preencha os campos válidos");
        }

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(createTarefaDTO.getTitulo());
        tarefa.setDescricao(createTarefaDTO.getDescricao());
        tarefa.setPrazo(createTarefaDTO.getPrazo());
        tarefa.setLinkDrive(createTarefaDTO.getLinkDrive());
        tarefa.setLinkDrive(createTarefaDTO.getLinkExtra());
        //Grupo grupo = grupoRepository.findById(createTarefaDTO.getGrupoId());

        return tarefa;
    
    }
}
