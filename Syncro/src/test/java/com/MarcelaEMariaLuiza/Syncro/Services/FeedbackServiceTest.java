package com.MarcelaEMariaLuiza.Syncro.Services;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.MarcelaEMariaLuiza.Syncro.DTO.CreateFeedbackDTO;
import com.MarcelaEMariaLuiza.Syncro.DTO.EditFeedbackDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.MarcelaEMariaLuiza.Syncro.Entities.Feedback;
import com.MarcelaEMariaLuiza.Syncro.Entities.Tarefa;
import com.MarcelaEMariaLuiza.Syncro.Errors.FeedbackInvalidoException;
import com.MarcelaEMariaLuiza.Syncro.Errors.FeedbackJaAprovado;
import com.MarcelaEMariaLuiza.Syncro.Repositories.AlunoRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.FeedbackRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.TarefaRepository;


@ExtendWith(MockitoExtension.class) 
public class FeedbackServiceTest {
    
    @Mock
    private  FeedbackRepository feedbackRepository;
    
    @Mock
    private  TarefaRepository tarefaRepository;
    
    @Mock
    private  AlunoRepository alunoRepository;

    @InjectMocks
    private FeedbackService feedbackService;

    @Test
    void criaFeedbackBemSucedido(){
        CreateFeedbackDTO feedback = new CreateFeedbackDTO();
        feedback.setAprovado(true);
        feedback.setComentario("Bom trabalho");
        feedback.setTarefaId(2L);
       
        
        Tarefa tarefa = new Tarefa();
        tarefa.setId(2L);
        when(tarefaRepository.findById(feedback.getTarefaId())).thenReturn(Optional.of(tarefa));
        when(feedbackRepository.FeedbackDado(2L, 1L)).thenReturn(0);
       
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno)); 
        feedback.setAlunoId(1L);

        
        when(feedbackRepository.save(any(Feedback.class))).thenAnswer(invocation -> invocation.getArgument(0));
        CreateFeedbackDTO feedbackCriado = feedbackService.createFeedback(feedback);
        assertNotNull(feedbackCriado);
        assertThat(feedbackCriado.getComentario()).isEqualTo(feedback.getComentario());
        assertThat(feedbackCriado.getAprovado()).isEqualTo(feedback.getAprovado());
    }
    @Test
    void naoCriaFeedbackDuplicado(){
        CreateFeedbackDTO feedback = new CreateFeedbackDTO();
        feedback.setAprovado(true);
        feedback.setComentario("Bom trabalho");
        feedback.setTarefaId(2L);
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        feedback.setAlunoId(1L);
        
        Tarefa tarefa = new Tarefa();
        tarefa.setId(2L);
        when(tarefaRepository.findById(feedback.getTarefaId())).thenReturn(Optional.of(tarefa));
        when(feedbackRepository.FeedbackDado(2L, 1L)).thenReturn(1);
        assertThrows( FeedbackInvalidoException.class, ()->{feedbackService.createFeedback(feedback);});
        
    
    }
    @Test
    void feedbackEditadoComSucesso(){
        EditFeedbackDTO feedback = new EditFeedbackDTO();
        feedback.setAprovado(false);
        feedback.setComentario("Bom trabalho");
        feedback.setId(3L);
        Feedback feedbackExistente = new Feedback();
        feedbackExistente.setAprovado(false);
        feedbackExistente.setComentario("Bom trabalho");
        feedbackExistente.setId(3L);
        when(feedbackRepository.findById(3L)).thenReturn(Optional.of(feedbackExistente));
        when(feedbackRepository.save(any(Feedback.class))).thenAnswer(invocation -> invocation.getArgument(0));
        feedbackService.EditaFeedback(feedback);
        ArgumentCaptor<Feedback> feedbackCaptor = ArgumentCaptor.forClass(Feedback.class);
        verify(feedbackRepository, times(1)).save(feedbackCaptor.capture());
    }

    @Test
    void feedbackNaoEditado(){ 
        Feedback feedbackExistente = new Feedback();
        feedbackExistente.setAprovado(true);
        feedbackExistente.setComentario("Bom trabalho");
        feedbackExistente.setId(3L);
        EditFeedbackDTO feedback = new EditFeedbackDTO();
        feedback.setAprovado(false);
        feedback.setComentario("Bom trabalho");
        feedback.setId(3L);
       
        when(feedbackRepository.findById(3L)).thenReturn(Optional.of(feedbackExistente));
        assertThrows( FeedbackJaAprovado.class, ()->{feedbackService.EditaFeedback(feedback);});
        ArgumentCaptor<Feedback> feedbackCaptor = ArgumentCaptor.forClass(Feedback.class);
        verify(feedbackRepository, times(0)).save(feedbackCaptor.capture());
    }
}
   
