package com.MarcelaEMariaLuiza.Syncro.Services;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.MarcelaEMariaLuiza.Syncro.DTO.AlunosResponseDTO;
import com.MarcelaEMariaLuiza.Syncro.DTO.CreateAlunoDTO;
import com.MarcelaEMariaLuiza.Syncro.DTO.LoginDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.MarcelaEMariaLuiza.Syncro.Entities.Grupo;
import com.MarcelaEMariaLuiza.Syncro.Errors.EmailExistenteException;
import com.MarcelaEMariaLuiza.Syncro.Errors.GrupoInexistenteException;
import com.MarcelaEMariaLuiza.Syncro.Errors.SenhaIncorretaException;
import com.MarcelaEMariaLuiza.Syncro.Repositories.AlunoRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.GrupoRepository;

@ExtendWith(MockitoExtension.class) 
public class AlunoServiceTest {
   
    @Mock
    private AlunoRepository alunoRepository;
   
    @Mock
    private GrupoRepository grupoRepository;

    @InjectMocks
    private AlunoService alunoService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void createAluno_ComDadosValidos(){
        CreateAlunoDTO dto = new CreateAlunoDTO();
        dto.setNome("Caramelo");
        dto.setEmail("melo@email.com");
        dto.setSenha("1234");

        when(alunoRepository.findByEmail(dto.getEmail())).thenReturn(null);
        when(alunoRepository.save(any(Aluno.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Aluno alunoCriado = alunoService.createAluno(dto);

        assertThat(alunoCriado).isNotNull();
        assertThat(alunoCriado.getNome()).isEqualTo(dto.getNome());
        assertThat(alunoCriado.getEmail()).isEqualTo(dto.getEmail());
        assertThat(alunoCriado.getSenha()).isNotEqualTo(dto.getSenha());
    }

    @Test
    void createAluno_ComEmailJaExistente(){
        CreateAlunoDTO dto = new CreateAlunoDTO();
        dto.setNome("Caramelo");
        dto.setEmail("melo@email.com");
        dto.setSenha("1234");

        when(alunoRepository.findByEmail(dto.getEmail())).thenReturn(new Aluno());

        assertThrows(EmailExistenteException.class, () ->  {
            alunoService.createAluno(dto);
        });

        verify(alunoRepository, never()).save(any(Aluno.class));
    }

    @Test
    void login_ComDadosCorretos(){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("melo@email.com");
        loginDTO.setSenha("1234");

        Aluno alunoExistente = new Aluno();
        alunoExistente.setEmail(loginDTO.getEmail());
        alunoExistente.setSenha(passwordEncoder.encode(loginDTO.getSenha()));

        when(alunoRepository.findByEmail(alunoExistente.getEmail())).thenReturn(alunoExistente);

        Aluno alunoLogado = alunoService.login(loginDTO);

        assertThat(alunoLogado).isNotNull();
        assertThat(alunoLogado.getEmail()).isEqualTo(loginDTO.getEmail());
    }

    @Test
    void login_ComEmailInexistente(){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("inexistente@email.com");
        loginDTO.setSenha("1234");

        when(alunoRepository.findByEmail(loginDTO.getEmail())).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            alunoService.login(loginDTO);
        });
    }

    @Test
    void login_ComSenhaIncorreta(){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("melo@email.com");
        loginDTO.setSenha("senhaIncorreta");

        Aluno alunoExistente = new Aluno();
        alunoExistente.setEmail(loginDTO.getEmail());
        alunoExistente.setSenha(passwordEncoder.encode("senhaCorreta"));

        when(alunoRepository.findByEmail(loginDTO.getEmail())).thenReturn(alunoExistente);

        assertThrows(SenhaIncorretaException.class, () -> {
            alunoService.login(loginDTO);
        });
    }

    @Test
    void getAlunosGrupo_QuandoGrupoExiste(){
        Long idGrupo = 1L;

        Grupo grupoMockado = new Grupo();
        grupoMockado.setId(idGrupo);

        Aluno aluno1 = new Aluno();
        aluno1.setId(10L);
        aluno1.setNome("Fulano");
        aluno1.setEmail("fulano@email.com");
        
        Aluno aluno2 = new Aluno();
        aluno2.setId(11L);
        aluno2.setNome("Ciclano");
        aluno2.setEmail("ciclano@email.com");

        List <Aluno> listaAlunos = List.of(aluno1, aluno2);

        when(grupoRepository.findById(idGrupo)).thenReturn(Optional.of(grupoMockado));
        when(alunoRepository.FindByGrupos(idGrupo)).thenReturn(listaAlunos);

        List <AlunosResponseDTO> resultado = alunoService.getAlunosGrupo(idGrupo);

        assertThat(resultado).isNotNull();
        assertThat(resultado.size()).isEqualTo(2);
        assertThat(resultado.get(0).getNome()).isEqualTo("Fulano");
        assertThat(resultado.get(1).getEmail()).isEqualTo("ciclano@email.com");
    }

    @Test
    void getAlunosGrupo_QuandoGrupoNaoExiste(){
        Long idGrupoInexistente = 99L;

        when(grupoRepository.findById(idGrupoInexistente)).thenReturn(Optional.empty());

        assertThrows(GrupoInexistenteException.class, () -> {
            alunoService.getAlunosGrupo(idGrupoInexistente);
        });

        verify(alunoRepository, never()).FindByGrupos(anyLong());

    }

}