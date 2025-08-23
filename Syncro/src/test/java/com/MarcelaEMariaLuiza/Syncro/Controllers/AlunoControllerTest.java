package com.MarcelaEMariaLuiza.Syncro.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.MarcelaEMariaLuiza.Syncro.Errors.EmailExistenteException;
import com.MarcelaEMariaLuiza.Syncro.DTO.CreateAlunoDTO;
import com.MarcelaEMariaLuiza.Syncro.DTO.CreateGrupoDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Repositories.AlunoRepository;
import com.MarcelaEMariaLuiza.Syncro.Security.TokenService;
import com.MarcelaEMariaLuiza.Syncro.Services.AlunoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.MarcelaEMariaLuiza.Syncro.Entities.Grupo;
import com.MarcelaEMariaLuiza.Syncro.Services.GrupoService;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest 
@AutoConfigureMockMvc 
@ActiveProfiles("test")
public class AlunoControllerTest {
    @MockBean
    private AlunoService alunoService;
    
    @MockBean
    private GrupoService grupoService;

    @MockBean
    private TokenService tokenService;
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private AlunoRepository alunoRepository;

    private CreateAlunoDTO alunoMock;

    @Autowired 
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp (){
        alunoMock = new CreateAlunoDTO();
        alunoMock.setNome("Ciclano");
        alunoMock.setEmail("ciclano@email.com");
        alunoMock.setSenha("12345");
    }
    @Test
    void AlunoCriadoComSucesso() throws Exception{
        Aluno alunoCadastrado = new Aluno();
        alunoCadastrado.setNome("Ciclano");
        alunoCadastrado.setEmail("ciclano@email.com");
        alunoCadastrado.setSenha("12345");
        alunoCadastrado.setId(1L);
        when(alunoService.createAluno(any(CreateAlunoDTO.class)))
            .thenReturn(alunoCadastrado);
         mockMvc.perform(post("/api/aluno/create")
         .with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(alunoMock)))
            .andExpect(status().isOk())
            .andExpect(content().string("Usuário criado"));
           
    }
    @Test
    void alunoNaoCriadoPorCampoNulo()throws Exception{
        
        alunoMock.setNome(null);
         when(alunoService.createAluno(any(CreateAlunoDTO.class)))
            .thenThrow(new CampoNaoPreenchidoException("Campo(s) não foram preenchidos"));
        mockMvc.perform(post("/api/aluno/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(alunoMock)))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("Campo(s) não foram preenchidos"));
    }
    @Test
    void alunoNaoCriadoPorEmailExistente()throws Exception{
        
        alunoMock.setNome(null);
         when(alunoService.createAluno(any(CreateAlunoDTO.class)))
            .thenThrow(new EmailExistenteException("O email informado já está cadastrado"));
        mockMvc.perform(post("/api/aluno/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(alunoMock)))
        .andExpect(status().isConflict())
        .andExpect(content().string("O email informado já está cadastrado"));
    }


    /*@Test
    void deveRetornarGruposDoUsuarioAutenticado() throws Exception {
      
        Aluno alunoLogado = new Aluno();
        alunoLogado.setEmail("usuario.logado@email.com");
        Grupo grupoTeste = new Grupo();
        grupoTeste.setNome("Grupo de Teste");
        List <Grupo> meusGrupos = Arrays.asList();

        when(grupoService.getGruposAluno((any(Aluno.class))).thenReturn(meusGrupos);

        // Act & Assert
        mockMvc.perform(get("api/aluno/{1L}")
            .with(csrf())
            .with(user("usuario.logado@email.com").roles("USER"))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].nome").value("Grupo de Teste"));
    } */

}
