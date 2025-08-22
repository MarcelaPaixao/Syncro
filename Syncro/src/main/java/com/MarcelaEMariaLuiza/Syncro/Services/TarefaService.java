package com.MarcelaEMariaLuiza.Syncro.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.MarcelaEMariaLuiza.Syncro.DTO.CreateTarefaDTO;
import com.MarcelaEMariaLuiza.Syncro.DTO.TarefaResponseDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.MarcelaEMariaLuiza.Syncro.Entities.Grupo;
import com.MarcelaEMariaLuiza.Syncro.Entities.Tarefa;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Errors.GrupoInexistenteException;
import com.MarcelaEMariaLuiza.Syncro.Repositories.AlunoRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.FeedbackRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.GrupoRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.TarefaRepository;
import com.MarcelaEMariaLuiza.Syncro.enums.TarefaStatus;

/**
 * Classe de serviço responsável pela lógica de negócio das tarefas.
 * <p>
 * Centraliza as operações de criação e consulta de tarefas, interagindo
 * com os repositórios de Tarefa, Grupo e Aluno para realizar as operações
 * necessárias no banco de dados.
 * </p>
 *
 * @author Marcela, Maria Luiza
 * @version 1.0
 * @since 2025-07-28
 */
@Service
public class TarefaService{
    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired 
    private FeedbackRepository feedbackRepository;

    /**
     * Cria uma nova tarefa e a associa a um grupo e a um aluno.
     * <p>
     * Valida os dados de entrada, mapeia o DTO para uma entidade Tarefa,
     * associa ao grupo e aluno correspondentes e persiste a nova tarefa
     * no banco de dados.
     * </p>
     *
     * @param createTarefaDTO DTO contendo os dados para a criação da tarefa.
     * @return A entidade {@link Tarefa} recém-criada e salva.
     * @throws CampoNaoPreenchidoException Se campos obrigatórios como título ou ID do grupo não forem fornecidos.
     * @throws GrupoInexistenteException   Se o ID do grupo fornecido no DTO não corresponder a um grupo existente.
     */
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
        tarefa.setLinkExtra(createTarefaDTO.getLinkExtra());
        tarefa.setStatus(TarefaStatus.TODO);
        
        Optional<Grupo> novoGrupo = grupoRepository.findById(createTarefaDTO.getGrupoId());
        if(!novoGrupo.isPresent()) throw new GrupoInexistenteException("Grupo inválido");
        Grupo grupo = novoGrupo.get();
        tarefa.setGrupo(grupo);
        
           
        Optional<Aluno> novoAluno = alunoRepository.findById(createTarefaDTO.getAlunoId());
        if(novoGrupo.isPresent()){
            Aluno aluno  = novoAluno.get();
            if(!aluno.estaEmGrupoAluno(grupo)) return null;
            tarefa.setAluno(aluno);
        }
        

        tarefaRepository.save(tarefa);
        grupo.adicionaNovaTarefa(tarefa);
        grupoRepository.save(grupo);
        return tarefa;
    
    }

    /**
     * Busca todas as tarefas associadas a um grupo específico.
     *
     * @param grupoId O ID do grupo para o qual as tarefas serão buscadas.
     * @return Uma lista de DTOs ({@link CreateTarefaDTO}) representando as tarefas do grupo.
     * @throws RuntimeException Se o ID do grupo fornecido for inválido ou não existir.
     */
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
        t1.setStatus(t.getStatus());
        tarefasFiltradas.add(t1);
       }
      return tarefasFiltradas;
    }

    /**
     * Busca todas as tarefas associadas a um aluno específico.
     *
     * @param alunoId O ID do aluno para o qual as tarefas serão buscadas.
     * @return Uma lista de DTOs ({@link CreateTarefaDTO}) representando as tarefas do aluno.
     * @throws RuntimeException Se o ID do aluno fornecido for inválido ou não existir.
     */
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

    public Tarefa EditaTarefa(CreateTarefaDTO createTarefaDTO){
        
       
        if(createTarefaDTO.getTitulo().isEmpty() || createTarefaDTO.getTitulo() == null 
        || createTarefaDTO.getId() == null || createTarefaDTO.getGrupoId() == null || createTarefaDTO.getStatus()==null){
            throw new IllegalArgumentException("Dados inválidos. Tarefa não encontrada");
        }
        Optional<Tarefa> t = tarefaRepository.findById(createTarefaDTO.getId());
        if(!t.isPresent()) throw new RuntimeException("Tarefa inexistente");
        Tarefa tarefa = t.get(); 

        if(createTarefaDTO.getStatus() != TarefaStatus.DONE){
            tarefa.setStatus(createTarefaDTO.getStatus());
        }else{
            int numMembros = alunoRepository.countGrupoMembers(createTarefaDTO.getGrupoId());

            int feedbacksAprovados = feedbackRepository.countApprovedFeedbacks(tarefa.getId(), tarefa.getAluno().getId());
            
            float porcentagem = (feedbacksAprovados/(numMembros -1))*100;
            if(porcentagem>70){
                tarefa.setStatus(TarefaStatus.DONE);
            }
        }

        tarefa.setTitulo(createTarefaDTO.getTitulo());
        tarefa.setDescricao(createTarefaDTO.getDescricao());
        tarefa.setLinkDrive(createTarefaDTO.getLinkDrive());
        tarefa.setLinkExtra(createTarefaDTO.getLinkExtra());
        tarefa.setPrazo(createTarefaDTO.getPrazo());
        
        Optional <Aluno> aluno = alunoRepository.estaNoGrupoAluno(createTarefaDTO.getGrupoId(), createTarefaDTO.getAlunoId());
        if(aluno.isPresent()){
            tarefa.setAluno(aluno.get());
        }
        tarefaRepository.save(tarefa);
        return null;
    }

    public List<TarefaResponseDTO> getTarefasParaAvaliar(Long alunoId){
        List<Grupo> grupo = grupoRepository.findByAlunoId(alunoId);
        List <Tarefa> tarefasGrupo = new ArrayList<>();
        for(Grupo g: grupo){
            tarefasGrupo.addAll(tarefaRepository.findByGrupo_id(g.getId()));
        }
        List <TarefaResponseDTO> tarefasParaAvaliacao = new ArrayList<>();
        for(Tarefa t: tarefasGrupo){
            if((t.getAluno().getId() != alunoId) && (t.getStatus()==TarefaStatus.REVIEW)){
                if(feedbackRepository.FeedbackDado(alunoId, t.getId())==0){
                    TarefaResponseDTO response = new TarefaResponseDTO();
                    response.setId(t.getId());
                    response.setTitulo(t.getTitulo());
                    response.setDescricao(t.getDescricao());
                    response.setAlunoNome(t.getAluno().getNome());
                    tarefasParaAvaliacao.add(response);
                }
            }
        }
        return tarefasParaAvaliacao;
    }

    public CreateTarefaDTO getTarefa(Long tarefaId) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(tarefaId);
        if(!tarefa.isPresent()) throw new RuntimeException("Tarefa inexistente");

        Tarefa t = tarefa.get();
        CreateTarefaDTO response = new CreateTarefaDTO();
        response.setId(t.getId());
        response.setTitulo(t.getTitulo());
        response.setDescricao(t.getDescricao());
        response.setLinkDrive(t.getTitulo());
        response.setLinkExtra(t.getLinkExtra());
        response.setPrazo(t.getPrazo());
        response.setStatus(t.getStatus());
        response.setAlunoId(t.getAluno().getId());
        response.setGrupoId(t.getGrupo().getId());
        return response;
    }
}
