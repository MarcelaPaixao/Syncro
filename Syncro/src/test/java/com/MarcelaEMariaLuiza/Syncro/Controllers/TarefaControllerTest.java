package com.MarcelaEMariaLuiza.Syncro.Controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.MarcelaEMariaLuiza.Syncro.DTO.CreateTarefaDTO;
import com.MarcelaEMariaLuiza.Syncro.DTO.TarefaResponseDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Tarefa;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Errors.GrupoInexistenteException;
import com.MarcelaEMariaLuiza.Syncro.Services.AlunoService;
import com.MarcelaEMariaLuiza.Syncro.Services.TarefaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TarefaService tarefaService;

    @MockBean
    private AlunoService alunoService; 
    
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createTarefa_ComDadosValidos()throws Exception{
        CreateTarefaDTO dto = new CreateTarefaDTO();
        dto.setDescricao("Descrição Tarefa");
        dto.setTitulo("Nova Tarefa"); 
        dto.setId(1L); 
        dto.setGrupoId(2L); 
        dto.setAlunoId(3L); 
        
        when(tarefaService.createTarefa(any(CreateTarefaDTO.class))).thenReturn(new Tarefa());

        mockMvc.perform(post("/api/tarefa/create") 
                .with(user("fulano@email.com").roles("USER")) 
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON) 
                .content(objectMapper.writeValueAsString(dto))) 
                .andExpect(status().isOk()) 
                .andExpect(content().string("Tarefa gerada com sucesso")); 
    }

    @Test
    void createTarefa_ComCampoNaoPreenchido() throws Exception {
        CreateTarefaDTO dto = new CreateTarefaDTO(); 
        String mensagemDeErro = "Há campo(s) não preenchido(s)";

        when(tarefaService.createTarefa(any(CreateTarefaDTO.class)))
            .thenThrow(new CampoNaoPreenchidoException(mensagemDeErro));

        mockMvc.perform(post("/api/tarefa/create")
                .with(user("fulano@email.com").roles("USER")) 
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isConflict()) 
                .andExpect(content().string(mensagemDeErro)); 
    }

    @Test
    void createTarefa_ComGrupoInexistente() throws Exception {
        CreateTarefaDTO dto = new CreateTarefaDTO(); 
        String mensagemDeErro = "Grupo inexistente";

        when(tarefaService.createTarefa(any(CreateTarefaDTO.class)))
            .thenThrow(new GrupoInexistenteException(mensagemDeErro));

        mockMvc.perform(post("/api/tarefa/create")
                .with(user("fulano@email.com").roles("USER")) 
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isConflict()) 
                .andExpect(content().string(mensagemDeErro)); 
    }

    @Test
    void getTarefasPorGrupo_QuandoGrupoExiste() throws Exception {
        Long grupoId = 1L;
        
        CreateTarefaDTO tarefa1 = new CreateTarefaDTO();
        tarefa1.setTitulo("Tarefa 1");
        CreateTarefaDTO tarefa2 = new CreateTarefaDTO();
        tarefa2.setTitulo("Tarefa 2");
        
        List<CreateTarefaDTO> listaDeTarefas = List.of(tarefa1, tarefa2);
        
        when(tarefaService.getTarefasPorGrupo(grupoId)).thenReturn(listaDeTarefas);

        mockMvc.perform(get("/api/tarefa/get/grupo/{grupoId}", grupoId)
                .with(user("fulano@email.com").roles("USER")) 
                .with(csrf()))
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].titulo", is("Tarefa 1"))); 
    }

    @Test
    void getTarefasPorGrupo_QuandoServiceLancaExcecao() throws Exception {
        Long grupoId = 99L;
        when(tarefaService.getTarefasPorGrupo(grupoId)).thenThrow(new RuntimeException("Erro de banco de dados"));

        mockMvc.perform(get("/api/tarefa/get/grupo/{grupoId}", grupoId)
                .with(user("fulano@email.com").roles("USER")) 
                .with(csrf()))
                .andExpect(status().isOk()) 
                .andExpect(content().string("")); 
    }

    @Test
    void getTarefasPorAluno_QuandoAlunoExiste() throws Exception {
        Long alunoId = 1L;
        List<CreateTarefaDTO> listaDeTarefas = List.of(new CreateTarefaDTO(), new CreateTarefaDTO());
        
        when(tarefaService.getTarefasPorAluno(alunoId)).thenReturn(listaDeTarefas);

        mockMvc.perform(get("/api/tarefa/get/aluno/{alunoId}", alunoId)
                .with(user("fulano@email.com").roles("USER")) 
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getTarefasPorAluno_QuandoServiceLancaExcecao() throws Exception {
        Long alunoId = 99L;
        String mensagemErro = "Erro de banco de dados";
        
        when(tarefaService.getTarefasPorAluno(alunoId)).thenThrow(new RuntimeException(mensagemErro));

        mockMvc.perform(get("/api/tarefa/get/aluno/{alunoId}", alunoId)
            .with(user("fulano@email.com").roles("USER")) 
            .with(csrf()))
            .andExpect(status().isInternalServerError())
            .andExpect(content().string(mensagemErro));
    }

    @Test
    void editaTarefa_ComSucesso() throws Exception {
        CreateTarefaDTO dto = new CreateTarefaDTO();
        dto.setId(1L);
        dto.setTitulo("Tarefa 1");

        when(tarefaService.EditaTarefa(any(CreateTarefaDTO.class))).thenReturn(new Tarefa());

        mockMvc.perform(put("/api/tarefa/edita")
            .with(user("fulano@email.com").roles("USER")) 
            .with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andExpect(content().string("Tarefa editada com sucesso"));
    }

    @Test
    void editaTarefa_QuandoServiceLancaExcecao() throws Exception {
        CreateTarefaDTO dto = new CreateTarefaDTO();
        dto.setId(99L);
        String mensagemErro = "Erro ao salvar no banco de dados";

        when(tarefaService.EditaTarefa(any(CreateTarefaDTO.class)))
            .thenThrow(new RuntimeException(mensagemErro));

        mockMvc.perform(put("/api/tarefa/edita")
                .with(user("fulano@email.com").roles("USER")) 
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(mensagemErro));
    }

    @Test
    void getTarefa_QuandoTarefaExiste() throws Exception {
        Long tarefaId = 1L;
        CreateTarefaDTO tarefaDTO = new CreateTarefaDTO();
        tarefaDTO.setId(tarefaId);
        tarefaDTO.setTitulo("Tarefa 1");
        
        when(tarefaService.getTarefa(tarefaId)).thenReturn(tarefaDTO);

       mockMvc.perform(get("/api/tarefa/get/{tarefaId}", tarefaId)
                .with(user("fulano@email.com").roles("USER")) 
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.titulo", is("Tarefa 1")));
    }

    @Test
    void getTarefa_QuandoServiceLancaExcecao() throws Exception {
        Long tarefaId = 99L;
        String mensagemErro = "Erro de banco de dados";
        
        when(tarefaService.getTarefa(tarefaId)).thenThrow(new RuntimeException(mensagemErro));

        mockMvc.perform(get("/api/tarefa/get/{tarefaId}", tarefaId)
            .with(user("fulano@email.com").roles("USER")) 
            .with(csrf()))
            .andExpect(status().isInternalServerError())
            .andExpect(content().string(mensagemErro));
    }

    @Test
    void getTarefasAvaliarPorAluno_QuandoAlunoExiste() throws Exception {
        Long alunoId = 1L;
        
        TarefaResponseDTO tarefaParaAvaliar1 = new TarefaResponseDTO(); 
        tarefaParaAvaliar1.setTitulo("Tarefa para Avaliar 1");
        TarefaResponseDTO tarefaParaAvaliar2 = new TarefaResponseDTO();
        tarefaParaAvaliar2.setTitulo("Tarefa para Avaliar 2");

        List<TarefaResponseDTO> lista = List.of(tarefaParaAvaliar1, tarefaParaAvaliar2);
        
        when(tarefaService.getTarefasParaAvaliar(alunoId)).thenReturn(lista);

        mockMvc.perform(get("/api/tarefa/get/avalia/{alunoId}", alunoId)
                .with(user("fulano@email.com").roles("USER")) 
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].titulo", is("Tarefa para Avaliar 1")));
    }

    @Test
    void getTarefasAvaliarPorAluno_QuandoServiceLancaExcecao() throws Exception {
        Long alunoId = 1L;
        String mensagemErro = "Erro de banco de dados";
        
        when(tarefaService.getTarefasParaAvaliar(alunoId)).thenThrow(new RuntimeException(mensagemErro));

        mockMvc.perform(get("/api/tarefa/get/avalia/{alunoId}", alunoId)
            .with(user("fulano@email.com").roles("USER")) 
            .with(csrf()))
            .andExpect(status().isInternalServerError())
            .andExpect(content().string(mensagemErro));
    }

}

