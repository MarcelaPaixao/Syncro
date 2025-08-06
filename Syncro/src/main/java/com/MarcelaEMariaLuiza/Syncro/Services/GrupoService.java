package com.MarcelaEMariaLuiza.Syncro.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MarcelaEMariaLuiza.Syncro.DTO.CreateGrupoDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.MarcelaEMariaLuiza.Syncro.Entities.Grupo;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Errors.GrupoInexistenteException;
import com.MarcelaEMariaLuiza.Syncro.Repositories.AlunoRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.GrupoRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.TarefaRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

/**
 * Classe de serviço para gerenciar a lógica de negócio relacionada a Grupos.
 * <p>
 * Responsável por operações como criação de novos grupos, associação de membros
 * e consulta de grupos por aluno, orquestrando as interações com os repositórios
 * de Grupo e Aluno.
 * </p>
 *
 * @author Marcela,Maria Luiza
 * @version 1.0
 * @since 2025-07-28
 */
@AllArgsConstructor

@Service
public class GrupoService {
    
    @Autowired
    private final GrupoRepository grupoRepository;

    @Autowired
    private final AlunoRepository alunoRepository;

    @Autowired
    private final TarefaRepository tarefaRepository;

    /**
     * Cria um novo grupo, associa o criador e adiciona os membros convidados.
     * <p>
     * A operação é executada dentro de uma única transação. O método valida os
     * dados de entrada, persiste o novo grupo, e em seguida, vincula o criador
     * e os demais membros (identificados por e-mail) ao grupo recém-criado.
     * </p>
     *
     * @param createGrupoDTO DTO com os dados para a criação do grupo, incluindo a lista de e-mails dos membros.
     * @param criador O objeto do aluno autenticado (principal) que está criando o grupo.
     * @return A entidade {@link Grupo} recém-criada e persistida.
     * @throws CampoNaoPreenchidoException Se campos essenciais como nome, professor, matéria ou a lista de membros não forem fornecidos.
     */
    @Transactional
    public Grupo criaGrupo(CreateGrupoDTO createGrupoDTO, Object criador){
        
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
        createGrupoDTO.getMembros().add(criador1.getEmail());
        for(String email: createGrupoDTO.getMembros()){
            Aluno aluno = alunoRepository.findByEmail(email);
            if(aluno==null) continue;
            aluno.adicionaGrupo(novoGrupo);

        }
        grupoRepository.save(novoGrupo);
        return novoGrupo;     
    }
    /**
     * Busca e retorna todos os grupos dos quais um aluno específico faz parte.
     *
     * @param alunoId O ID do aluno para o qual os grupos serão consultados.
     * @return Uma lista de DTOs ({@link CreateGrupoDTO}), cada um representando um grupo ao qual o aluno pertence.
     * @throws RuntimeException Se o ID do aluno fornecido não corresponder a um aluno existente.
     */
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
    @Transactional
    public float getProgressoGrupo(Long grupoId){
        
        Optional<Grupo> g = grupoRepository.findById(grupoId);
        if(g.isPresent() == false) throw new GrupoInexistenteException("Grupo Inválido");
        Grupo grupo = g.get();

        float tarefasTotais = tarefaRepository.qtdTarefasTotal(grupoId);
        float tarefasDone = tarefaRepository.qtdTarefasDone(grupoId);

        float progresso =(tarefasDone/tarefasTotais)*100;
        int progressoRetorno = Math.round(progresso);

        return progressoRetorno;
        
    }
}
