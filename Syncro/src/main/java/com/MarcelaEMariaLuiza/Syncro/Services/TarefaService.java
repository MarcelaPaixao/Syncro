package com.MarcelaEMariaLuiza.Syncro.Services;

import com.MarcelaEMariaLuiza.Syncro.Errors.GrupoInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.MarcelaEMariaLuiza.Syncro.DTO.CreateTarefaDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Tarefa;
import com.MarcelaEMariaLuiza.Syncro.Entities.Grupo;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Repositories.GrupoRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.TarefaRepository;

import java.util.Optional;

@Service
public class TarefaService{
    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
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
        Optional<Grupo> novoGrupo = grupoRepository.findById(createTarefaDTO.getGrupoId());
        if(!novoGrupo.isPresent()) throw new GrupoInexistenteException("Grupo inválido");
        Grupo grupo = novoGrupo.get();
        tarefa.setGrupo(grupo);
        tarefaRepository.save(tarefa);
        grupo.adicionaNovaTarefa(tarefa);
        grupoRepository.save(grupo);
        return tarefa;
    
    }
}
