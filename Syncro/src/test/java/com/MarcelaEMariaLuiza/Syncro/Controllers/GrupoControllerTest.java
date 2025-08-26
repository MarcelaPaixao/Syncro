package com.MarcelaEMariaLuiza.Syncro.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.MarcelaEMariaLuiza.Syncro.DTO.CreateGrupoDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.MarcelaEMariaLuiza.Syncro.Entities.Grupo;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Errors.GrupoInexistenteException;
import com.MarcelaEMariaLuiza.Syncro.Services.GrupoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.test.mock.mockito.MockBean;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.MarcelaEMariaLuiza.Syncro.Repositories.AlunoRepository;
import com.MarcelaEMariaLuiza.Syncro.Security.TokenService;
//@ActiveProfiles("test")

@SpringBootTest
@AutoConfigureMockMvc
public class GrupoControllerTest {
    
    private CreateGrupoDTO dto;
    @Mock
    private Aluno alunoMock;

    private Aluno criador;

    @MockBean
    private GrupoService grupoService;

    @MockBean
    private TokenService tokenService;
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private AlunoRepository alunoRepository;
    

    @Autowired 
    private ObjectMapper objectMapper;

    @Mock
    private Authentication mockAuthentication;

    @BeforeEach
    void setUp(){
        dto = new CreateGrupoDTO();
        dto.setNome("Syncro");
        dto.setProfessor("Vitor");
        dto.setMateria("PI I");
        dto.setMembros(new ArrayList<>(Collections.singletonList("membro1@email.com")));
        criador = new Aluno();
        criador.setEmail("fulano@email.com");
        criador.setId(2L);
    }
    @Test
    void grupoNaoCriadoPorCampoNulo()throws Exception{
        
        dto.setMembros(null);
         when(grupoService.criaGrupo(any(CreateGrupoDTO.class), any()))
            .thenThrow(new CampoNaoPreenchidoException("Campo(s) não foram preenchidos"));
        mockMvc.perform(post("/api/grupo/create")
            .with(user("fulano@email.com").roles("USER")) 
            .with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("Campo(s) não foram preenchidos"));
    }
    @Test
    void grupoCriadoComSucesso()throws Exception{
        
        Grupo grupoSalvo = new Grupo();
        grupoSalvo.setId(1L); 
        grupoSalvo.setNome("Syncro");
        grupoSalvo.setProfessor("Vitor");
        grupoSalvo.setMateria("PI I");

        when(grupoService.criaGrupo(any(CreateGrupoDTO.class), any()))
            .thenReturn(grupoSalvo);
        mockMvc.perform(post("/api/grupo/create")
            .with(user("fulano@email.com").roles("USER")) 
            .with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.nome").value("Syncro"))
        .andExpect(jsonPath("$.professor").value("Vitor"));
    }

     @Test
    void deveRetornarGrupoPorId() throws Exception {
      
        Aluno alunoLogado = new Aluno();
        alunoLogado.setEmail("usuario.logado@email.com");
        
        CreateGrupoDTO grupoTeste = new CreateGrupoDTO();
        grupoTeste.setId(1L);
        grupoTeste.setNome("Grupo de Teste");

        when(grupoService.getGrupo(1L)).thenReturn(grupoTeste);
        mockMvc.perform(get("/api/grupo/getGrupo/1")
            .with(csrf())
            .with(user("usuario.logado@email.com").roles("USER"))
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print()) 
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nome").value("Grupo de Teste"));
    }

    @Test
    void deveRetornarGrupoInvalidoPorId() throws Exception {
        when(grupoService.getGrupo(1L)).thenThrow(new RuntimeException());
        mockMvc.perform(get("/api/grupo/getGrupo/1")
            .with(csrf())
            .with(user(criador.getEmail()).roles("USER"))
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print()) 
            .andExpect(status().isInternalServerError());
    }   


    @Test
    void deveRetornar500ProgressoGrupoInexistente() throws Exception {

        when(grupoService.getProgressoGrupo(8L)).thenThrow(new GrupoInexistenteException("Grupo Inválido"));
        mockMvc.perform(get("/api/grupo/progresso/{id}", 8L) // Faz a chamada GET para /api/grupos/1/progresso
        .with(csrf())
        .with(user(criador.getEmail()).roles("USER")))
        .andExpect(status().isInternalServerError()); 
    } 
    @Test
    void deveRetornarProgressoGrupo() throws Exception {

        float progressoEsperado = 75.0f;
        when(grupoService.getProgressoGrupo(1L)).thenReturn(progressoEsperado);
        mockMvc.perform(get("/api/grupo/progresso/{id}", 1L) // Faz a chamada GET para /api/grupos/1/progresso
        .with(csrf())
        .with(user(criador.getEmail()).roles("USER")))
        .andExpect(status().isOk()) 
        .andExpect(content().string("75.0"));
    } 
}
