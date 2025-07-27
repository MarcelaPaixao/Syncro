package com.MarcelaEMariaLuiza.Syncro.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.MarcelaEMariaLuiza.Syncro.DTO.CreateTarefaDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.MarcelaEMariaLuiza.Syncro.Entities.Grupo;
import com.MarcelaEMariaLuiza.Syncro.Entities.Tarefa;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Errors.GrupoInexistenteException;
import com.MarcelaEMariaLuiza.Syncro.Repositories.AlunoRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.GrupoRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.TarefaRepository;

@Service
public class TarefaService{
    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

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
        
           
        Optional<Aluno> novoAluno = alunoRepository.findById(createTarefaDTO.getAlunoId());
        if(novoGrupo.isPresent()){
            Aluno aluno  = novoAluno.get();
            tarefa.setAluno(aluno);
        }
        

        tarefaRepository.save(tarefa);
        grupo.adicionaNovaTarefa(tarefa);
        grupoRepository.save(grupo);
        //List<Aluno> a = grupoRepository.findByGrupos_Id(grupo.getId());
        return tarefa;
    
    }
   
    public List<CreateTarefaDTO> getTarefasPorGrupo(Long grupoId){
       Optional <Grupo> grupo = grupoRepository.findById(grupoId);
       if(!grupo.isPresent()) throw new RuntimeException("Grupo invalido");
       Grupo encontrado = grupo.get();
       List <Tarefa> tarefas = tarefaRepository.findByGrupo_id(grupoId);
       List<CreateTarefaDTO> tarefasFiltradas= new ArrayList<>();

       for(Tarefa t: tarefas){
        CreateTarefaDTO t1 = new CreateTarefaDTO();
        t1.setTitulo(t.getTitulo());
        t1.setDescricao(t.getDescricao());
        t1.setLinkDrive(t.getTitulo());
        t1.setLinkExtra(t.getLinkExtra());
        t1.setAlunoId(t.getAluno().getId());
        t1.setGrupoId(t.getGrupo().getId());
        t1.setId(t.getId());
        t1.setPrazo(t.getPrazo());
        tarefasFiltradas.add(t1);
       }
      return tarefasFiltradas;
    }

    public List<CreateTarefaDTO> getTarefasPorAluno(Long alunoId){
        Optional <Aluno> aluno = alunoRepository.findById(alunoId);
        if(!aluno.isPresent()) throw new RuntimeException("Aluno invalido");
        Aluno encontrado = aluno.get();
        List <Tarefa> tarefas = tarefaRepository.findByAluno_id(alunoId);
        List<CreateTarefaDTO> tarefasFiltradas= new ArrayList<>();
 
        for(Tarefa t: tarefas){
         CreateTarefaDTO t1 = new CreateTarefaDTO();
         t1.setTitulo(t.getTitulo());
         t1.setDescricao(t.getDescricao());
         t1.setLinkDrive(t.getTitulo());
         t1.setLinkExtra(t.getLinkExtra());
         t1.setAlunoId(t.getAluno().getId());
         t1.setGrupoId(t.getGrupo().getId());
         t1.setId(t.getId());
         t1.setPrazo(t.getPrazo());
         tarefasFiltradas.add(t1);
        }
       return tarefasFiltradas;
     }
}
