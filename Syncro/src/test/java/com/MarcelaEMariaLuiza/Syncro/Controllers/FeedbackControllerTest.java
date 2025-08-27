package com.MarcelaEMariaLuiza.Syncro.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.MarcelaEMariaLuiza.Syncro.DTO.CreateFeedbackDTO;
import com.MarcelaEMariaLuiza.Syncro.DTO.EditFeedbackDTO;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Services.FeedbackService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
public class FeedbackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeedbackService feedbackService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createFeedback_ComDadosValidos() throws Exception {
        CreateFeedbackDTO dtoEnviado = new CreateFeedbackDTO();
        dtoEnviado.setComentario("Ótimo trabalho!");
        dtoEnviado.setAprovado(true);
        
        when(feedbackService.createFeedback(any(CreateFeedbackDTO.class))).thenReturn(new CreateFeedbackDTO());

        mockMvc.perform(post("/api/feedback/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoEnviado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comentario", is("Ótimo trabalho!")));
    }

    @Test
    void createFeedback_QuandoServiceRetornaNull() throws Exception {
        CreateFeedbackDTO dto = new CreateFeedbackDTO();

        when(feedbackService.createFeedback(any(CreateFeedbackDTO.class))).thenReturn(null);

        mockMvc.perform(post("/api/feedback/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Algum dos dados inseridos está inválido"));
    }

    @Test
    void createFeedback_ComCampoNaoPreenchido() throws Exception {
        CreateFeedbackDTO dto = new CreateFeedbackDTO();
        String mensagemErro = "O campo de comentário é obrigatório";
        when(feedbackService.createFeedback(any(CreateFeedbackDTO.class)))
            .thenThrow(new CampoNaoPreenchidoException(mensagemErro));

        mockMvc.perform(post("/api/feedback/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isConflict())
                .andExpect(content().string(mensagemErro));
    }

    @Test
    void editaFeedback_ComDadosValidos() throws Exception {
        EditFeedbackDTO dto = new EditFeedbackDTO();
        dto.setId(1L);
        dto.setComentario("Comentário atualizado.");
        
        doNothing().when(feedbackService).EditaFeedback(any(EditFeedbackDTO.class));

        mockMvc.perform(put("/api/feedback/edita")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comentario", is("Comentário atualizado.")));
    }

    @Test
    void editaFeedback_QuandoServiceLancaExcecao() throws Exception {
        EditFeedbackDTO dto = new EditFeedbackDTO();
        String mensagemErro = "Erro ao editar";
        
        doThrow(new RuntimeException(mensagemErro))
            .when(feedbackService).EditaFeedback(any(EditFeedbackDTO.class));
            
        mockMvc.perform(put("/api/feedback/edita")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(mensagemErro));
    }

    @Test
    void getFeedbacksTarefa_QuandoExistemFeedbacks() throws Exception {
        Long tarefaId = 1L;
        CreateFeedbackDTO feedback1 = new CreateFeedbackDTO();
        feedback1.setComentario("Feedback 1");
        CreateFeedbackDTO feedback2 = new CreateFeedbackDTO();
        feedback2.setComentario("Feedback 2");
        List<CreateFeedbackDTO> lista = List.of(feedback1, feedback2);

        when(feedbackService.getFeedbacksTarefa(tarefaId)).thenReturn(lista);

        mockMvc.perform(get("/api/feedback/get/{tarefaId}", tarefaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].comentario", is("Feedback 1")));
    }
    
    @Test
    void getFeedbacksTarefa_QuandoServiceLancaExcecao() throws Exception {
        Long tarefaId = 1L;
        String mensagemErro = "Tarefa não encontrada";
        when(feedbackService.getFeedbacksTarefa(tarefaId)).thenThrow(new RuntimeException(mensagemErro));

        mockMvc.perform(get("/api/feedback/get/{tarefaId}", tarefaId))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(mensagemErro));
    }

}
