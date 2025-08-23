package com.MarcelaEMariaLuiza.Syncro.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.Collections;

import com.MarcelaEMariaLuiza.Syncro.DTO.CreateGrupoDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.MarcelaEMariaLuiza.Syncro.Entities.Grupo;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Services.GrupoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.MarcelaEMariaLuiza.Syncro.Repositories.AlunoRepository;
import com.MarcelaEMariaLuiza.Syncro.Security.TokenService;
@ActiveProfiles("test")
@WebMvcTest(GrupoController.class) 
public class GrupoControllerTest {
    
    private CreateGrupoDTO dto;
    private Aluno criador;

    @MockBean
    private GrupoService grupoService;

    @MockBean
    private TokenService tokenService;
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private AlunoRepository alunoRepository;
    

    @Autowired // O Spring também injeta um ObjectMapper para converter objetos para JSON
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp(){
        dto = new CreateGrupoDTO();
        dto.setNome("Syncro");
        dto.setProfessor("Vitor");
        dto.setMateria("PI I");
        dto.setMembros(new ArrayList<>(Collections.singletonList("membro1@email.com")));
        criador = new Aluno();
        criador.setEmail("fulano@email.com");
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
        dto.setMembros(new ArrayList<>(Collections.singletonList("membro1@email.com")));

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
}
