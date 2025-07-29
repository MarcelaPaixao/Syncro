package com.MarcelaEMariaLuiza.Syncro.Services;

import com.MarcelaEMariaLuiza.Syncro.DTO.CreateFeedbackDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.MarcelaEMariaLuiza.Syncro.Entities.Feedback;
import com.MarcelaEMariaLuiza.Syncro.Entities.Tarefa;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Errors.GrupoInexistenteException;
import com.MarcelaEMariaLuiza.Syncro.Repositories.AlunoRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.FeedbackRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.TarefaRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

/**
 * Classe de serviço responsável pela lógica de negócio relacionada a Feedbacks.
 * <p>
 * Gerencia a criação de novos feedbacks para as tarefas, validando os dados
 * e orquestrando a persistência através dos repositórios necessários.
 * </p>
 *
 * @author Marcela & Maria Luiza
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
    public Feedback createFeedback(@RequestBody CreateFeedbackDTO createFeedbackDTO){
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
        Tarefa tarefa= novaTarefa.get();
        feedback.setTarefa(tarefa);
        Optional<Aluno> novoAluno = alunoRepository.findById(createFeedbackDTO.getAlunoId());
        if(!novoAluno.isPresent()) throw new GrupoInexistenteException("Tarefa inválida");
        Aluno aluno = novoAluno.get();
        feedback.setAluno(aluno);
        feedbackRepository.save(feedback);
        return feedback;
    }
}
