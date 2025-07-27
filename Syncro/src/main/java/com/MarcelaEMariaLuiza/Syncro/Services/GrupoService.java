package com.MarcelaEMariaLuiza.Syncro.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.MarcelaEMariaLuiza.Syncro.DTO.CreateGrupoDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.MarcelaEMariaLuiza.Syncro.Entities.Grupo;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Repositories.AlunoRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.GrupoRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor

@Service
public class GrupoService {
    
    @Autowired
    private final GrupoRepository grupoRepository;

    @Autowired
    private final AlunoRepository alunoRepository;
    
    @Transactional
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

        }
        return novoGrupo;     
    }
   
    public List<CreateGrupoDTO> getGruposAluno(Long alunoId){
       Optional <Aluno> aluno = alunoRepository.findById(alunoId);
       if(!aluno.isPresent()) throw new RuntimeException("Usuário invalido");
       Aluno encontrado = aluno.get();
       List <Grupo> g = grupoRepository.findByAlunoId(encontrado.getId());
       List <CreateGrupoDTO> grupos = new ArrayList();
       
       for(Grupo grupo: g ){
            CreateGrupoDTO g1 = new CreateGrupoDTO();
            g1.setId(grupo.getId());
            g1.setNome(grupo.getNome());
            g1.setDescricao(grupo.getDescricao());
            g1.setMateria(grupo.getMateria());
            g1.setPrazo(grupo.getPrazo());
            g1.setProfessor(grupo.getProfessor());
            List <String> membros = new ArrayList<>();
            for(Aluno a: grupo.getAlunos()){
                membros.add(a.getEmail());
            }
            g1.setMembros(membros);
            grupos.add(g1);
       }
     return grupos;
    }
}
