package com.MarcelaEMariaLuiza.Syncro.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MarcelaEMariaLuiza.Syncro.DTO.CreateFeedbackDTO;
import com.MarcelaEMariaLuiza.Syncro.DTO.EditFeedbackDTO;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Services.FeedbackService;
/**
 * Controlador REST para as operações relacionadas a Feedbacks.
 * <p>
 * Este controlador expõe os endpoints HTTP para a criação de feedbacks,
 * atuando como a camada de entrada para as requisições web e delegando
 * a lógica de negócio para o {@link FeedbackService}.
 * </p>
 *
 * @author Marcela, Maria Luiza
 * @version 1.0
 * @since 2025-07-28
 */
@RequestMapping("/api/feedback")
@RestController
public class FeedbackController {

    /**
     * Injeção da camada de serviço de Feedback.
     */
    public final FeedbackService feedbackService;

    /**
     * Construtor para injeção de dependência do FeedbackService pelo Spring.
     *
     * @param feedbackService O serviço que contém a lógica de negócio para feedbacks.
     */
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }
    /**
     * Endpoint para criar um novo feedback para uma tarefa.
     * <p>
     * Recebe os dados do feedback no corpo da requisição e utiliza o
     * {@link FeedbackService} para processar a criação. Realiza o tratamento
     * de exceções para retornar respostas HTTP adequadas.
     * </p>
     *
     * @param createFeedbackDTO O DTO contendo os dados necessários para criar o feedback.
     * @return Um {@link ResponseEntity} com status 200 (OK) e uma mensagem de sucesso,
     * ou um status de erro (409 CONFLICT, 500 INTERNAL_SERVER_ERROR) com uma mensagem descritiva.
     */
    @PostMapping("/create")
    public ResponseEntity<?> createFeedback (@RequestBody CreateFeedbackDTO createFeedbackDTO){
        try{
            CreateFeedbackDTO feedback = feedbackService.createFeedback(createFeedbackDTO);
            if(feedback== null) return(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Algum dos dados inseridos está inválido"));
            return ResponseEntity.ok(createFeedbackDTO);
        }catch (CampoNaoPreenchidoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PutMapping("/edita")
    public ResponseEntity<?> editaFeedback(@RequestBody EditFeedbackDTO editFeedbackDTO){
        try{
            feedbackService.EditaFeedback(editFeedbackDTO);
            return ResponseEntity.ok(editFeedbackDTO);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/get/{tarefaId}")
    public ResponseEntity<?> getFeedbacksTarefa(@PathVariable Long tarefaId){
        try{
           List<CreateFeedbackDTO> feedbacks = feedbackService.getFeedbacksTarefa(tarefaId);
           return ResponseEntity.ok(feedbacks);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        
        }
    }
}
