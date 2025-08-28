package com.MarcelaEMariaLuiza.Syncro.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MarcelaEMariaLuiza.Syncro.DTO.CreateFeedbackDTO;
import com.MarcelaEMariaLuiza.Syncro.DTO.EditFeedbackDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.MarcelaEMariaLuiza.Syncro.Entities.Feedback;
import com.MarcelaEMariaLuiza.Syncro.Entities.Tarefa;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Errors.FeedbackInvalidoException;
import com.MarcelaEMariaLuiza.Syncro.Errors.FeedbackJaAprovado;
import com.MarcelaEMariaLuiza.Syncro.Errors.GrupoInexistenteException;
import com.MarcelaEMariaLuiza.Syncro.Repositories.AlunoRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.FeedbackRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.TarefaRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Classe de serviço responsável pela lógica de negócio relacionada a Feedbacks.
 * <p>
 * Gerencia a criação de novos feedbacks para as tarefas, validando os dados
 * e orquestrando a persistência através dos repositórios necessários.
 * </p>
 *
 * @author Marcela, Maria Luiza
 * @version 1.0
 * @since 2025-07-28
 */
@AllArgsConstructor
@NoArgsConstructor
@Service
public class FeedbackService {
    @Autowired
    private  FeedbackRepository feedbackRepository;
    @Autowired
    private  TarefaRepository tarefaRepository;
    @Autowired
    private  AlunoRepository alunoRepository;

    /**
     * Cria um novo feedback para uma tarefa, associado a um aluno.
     * <p>
     * Este método valida os dados do DTO, busca as entidades {@link Tarefa} e {@link Aluno}
     * correspondentes, constrói a nova entidade {@link Feedback} e a salva no banco de dados.
     * </p>
     *
     * @param createFeedbackDTO O DTO que contém os dados para a criação do feedback.
     * @return A entidade {@link Feedback} recém-criada e salva.
     * @throws CampoNaoPreenchidoException Se algum dos campos obrigatórios no DTO estiver vazio ou nulo.
     * @throws GrupoInexistenteException Se a tarefa ou o aluno associado não forem encontrados no banco de dados.
     */
    public CreateFeedbackDTO createFeedback(CreateFeedbackDTO createFeedbackDTO){
        
        if(createFeedbackDTO.getComentario().isEmpty() || createFeedbackDTO.getComentario() == null ||
                createFeedbackDTO.getAprovado() == null || createFeedbackDTO.getAlunoId() == null
                || createFeedbackDTO.getTarefaId() == null ){
            throw new CampoNaoPreenchidoException("Preencha os campos válidos");
        }

        Feedback feedback = new Feedback();
        feedback.setComentario(createFeedbackDTO.getComentario());
        feedback.setAprovado(createFeedbackDTO.getAprovado());
        Optional<Tarefa> novaTarefa = tarefaRepository.findById(createFeedbackDTO.getTarefaId());
        
        if(!novaTarefa.isPresent()) throw new GrupoInexistenteException("Tarefa inválida");
        if(feedbackRepository.FeedbackDado(createFeedbackDTO.getTarefaId(), createFeedbackDTO.getAlunoId())>0){
            throw new FeedbackInvalidoException("Já existe um feedback seu sobre essa tarefa. Tente editá-lo!");
        }
        Tarefa tarefa= novaTarefa.get();
        feedback.setTarefa(tarefa);
        Optional<Aluno> novoAluno = alunoRepository.findById(createFeedbackDTO.getAlunoId());
        if(!novoAluno.isPresent()) throw new GrupoInexistenteException("Aluno inválido");
        Aluno aluno = novoAluno.get();
        feedback.setAluno(aluno);
        feedbackRepository.save(feedback);
        createFeedbackDTO.setAlunoNome(aluno.getNome());
        return createFeedbackDTO;
    }
      /**
     * Edita um feedback existente.
     * <p>
     * Este método permite a alteração do comentário e do status de aprovação de um feedback.
     * Uma regra de negócio importante é que um feedback já marcado como "aprovado"
     * não pode ser modificado.
     * </p>
     *
     * @param editFeedbackDTO DTO contendo o ID do feedback e os novos dados a serem atualizados.
     * @throws CampoNaoPreenchidoException Se campos essenciais no DTO estiverem nulos ou vazios.
     * @throws FeedbackInvalidoException Se o feedback com o ID fornecido não for encontrado.
     * @throws FeedbackJaAprovado Se for feita uma tentativa de editar um feedback que já foi aprovado.
     */
    public void EditaFeedback(EditFeedbackDTO editFeedbackDTO){
        if(editFeedbackDTO.getComentario().isEmpty() || editFeedbackDTO.getComentario() == null ||
       editFeedbackDTO.getAprovado() == null || editFeedbackDTO.getId() == null){
            throw new CampoNaoPreenchidoException("Preencha os campos válidos");
}
        Optional <Feedback> f = feedbackRepository.findById(editFeedbackDTO.getId());
        if(!f.isPresent()) throw new FeedbackInvalidoException("Feedbak inválido");
        Feedback feedback = f.get();
        if(feedback.getAprovado()==false){
            feedback.setAprovado(editFeedbackDTO.getAprovado());
            feedback.setComentario(editFeedbackDTO.getComentario());
            feedbackRepository.save(feedback);
            
        }else{
            throw new FeedbackJaAprovado("Não é possível mudar o feedback após aprovado.");}
        
    }
/**
     * Busca todos os feedbacks associados a uma tarefa específica.
     *
     * @param tarefaId O ID da tarefa para a qual os feedbacks serão buscados.
     * @return Uma lista de DTOs ({@link CreateFeedbackDTO}) representando os feedbacks encontrados.
     * Retorna uma lista vazia se a tarefa não tiver feedbacks.
     */
public List<CreateFeedbackDTO> getFeedbacksTarefa(Long tarefaId){
    List <Feedback> feedbacks = feedbackRepository.findByTarefa_id(tarefaId);
    List <CreateFeedbackDTO> feedbackDTO = new ArrayList<>();
    for(Feedback f: feedbacks ){
            CreateFeedbackDTO f1 = new CreateFeedbackDTO();
            f1.setId(f.getId());
            f1.setComentario(f.getComentario());
            f1.setAprovado(f.getAprovado());
            f1.setAlunoId(f.getAluno().getId());
            f1.setAlunoNome(f.getAluno().getNome());
            feedbackDTO.add(f1);
       }
       return feedbackDTO
       ;
    
   }
   
}
