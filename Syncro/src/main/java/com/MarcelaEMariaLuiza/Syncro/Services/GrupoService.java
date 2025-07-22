package com.MarcelaEMariaLuiza.Syncro.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.MarcelaEMariaLuiza.Syncro.DTO.CreateGrupoDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.MarcelaEMariaLuiza.Syncro.Entities.Grupo;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Repositories.AlunoRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.GrupoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Service
public class GrupoService {
    
    @Autowired
    private final GrupoRepository grupoRepository;

    @Autowired
    private final AlunoRepository alunoRepository;
    
    public Grupo criaGrupo(@RequestBody CreateGrupoDTO createGrupoDTO, Object criador){
        
        Aluno criador1 = (Aluno)criador;
        if(createGrupoDTO.getNome().isEmpty() || createGrupoDTO.getNome() == null ||
        createGrupoDTO.getProfessor().isEmpty() || createGrupoDTO.getProfessor() == null ||
        createGrupoDTO.getMateria().isEmpty() || createGrupoDTO.getMateria() == null ||
        createGrupoDTO.getMembros().isEmpty() || createGrupoDTO.getMembros() == null 
        || createGrupoDTO.getMembros().size()<=0){

            
            throw new CampoNaoPreenchidoException("Campo(s) não foram preenchidos");
        }

        Grupo novoGrupo = new Grupo(createGrupoDTO.getNome(), createGrupoDTO.getProfessor(), createGrupoDTO.getMateria(), createGrupoDTO.getPrazo(),
        createGrupoDTO.getDescricao());
        List <Aluno> alunos = new ArrayList<>();
        grupoRepository.save(novoGrupo);
        criador1.adicionaGrupo(novoGrupo);
        alunoRepository.save(criador1);
        for(String email: createGrupoDTO.getMembros()){
            Aluno aluno = alunoRepository.findByEmail(email);
            if(aluno==null) continue;
            aluno.adicionaGrupo(novoGrupo);
            alunoRepository.save(aluno);

        }
        return novoGrupo;     
    }
    
}
