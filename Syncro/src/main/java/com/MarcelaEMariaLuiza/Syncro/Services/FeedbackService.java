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
