package com.MarcelaEMariaLuiza.Syncro.Services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.MarcelaEMariaLuiza.Syncro.DTO.CreateAlunoDTO;
import com.MarcelaEMariaLuiza.Syncro.DTO.CreateGrupoDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.MarcelaEMariaLuiza.Syncro.Entities.Grupo;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Errors.GrupoInexistenteException;
import com.MarcelaEMariaLuiza.Syncro.Repositories.AlunoRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.GrupoRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.TarefaRepository;

@ExtendWith(MockitoExtension.class)
public class GrupoServiceTest {
     
    @Mock
    private GrupoRepository grupoRepository;

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private TarefaRepository tarefaRepository;

    @InjectMocks
    private GrupoService grupoService;

    @Test
    void criaGrupo_ComSucesso(){
        CreateGrupoDTO dto = new CreateGrupoDTO();
        dto.setNome("Syncro");
        dto.setMateria("PI");
        dto.setProfessor("Vitor");
        dto.setMembros(new ArrayList<>(List.of("membro1@email.com")));

        Aluno criador = new Aluno();
        criador.setEmail("criador@email.com");
        criador.setId(1L);

        Aluno membro1 = new Aluno();
        membro1.setEmail("membro1@email.com");
        membro1.setId(2L);

        when(alunoRepository.findByEmail(criador.getEmail())).thenReturn(criador);
        when(alunoRepository.findByEmail(membro1.getEmail())).thenReturn(membro1);

        Grupo grupoCriado = grupoService.criaGrupo(dto, criador);

        verify(grupoRepository, times(1)).save(any(Grupo.class));
        ArgumentCaptor<Grupo> grupoCaptor = ArgumentCaptor.forClass(Grupo.class);
        verify(grupoRepository).save(grupoCaptor.capture());
        Grupo grupoSalvo = grupoCaptor.getValue();

        assertThat(grupoSalvo.getNome()).isEqualTo(grupoCriado.getNome());
        assertThat(grupoSalvo.getProfessor()).isEqualTo(grupoCriado.getProfessor());
    }

    @Test
    void criaGrupo_ComFalhaPorCampoVazio(){
        CreateGrupoDTO dto = new CreateGrupoDTO();
        dto.setNome("");
        dto.setMateria("PI");
        dto.setProfessor("Vitor");
        dto.setMembros(new ArrayList<>(List.of("membro1@email.com")));

        Aluno criador = new Aluno();
        
        assertThrows(CampoNaoPreenchidoException.class, () -> {
            grupoService.criaGrupo(dto, criador);
        });

        verify(grupoRepository, never()).save(any(Grupo.class));
    }

    @Test
    void getGruposAluno_QuandoAlunoExiste(){
        Long alunoId = 1L;
        Aluno aluno = new Aluno();
        aluno.setId(alunoId);
        
        Grupo grupo1 = new Grupo("Syncro", "Vitor", "PI", null, "");
        grupo1.setAlunos(List.of(aluno)); 

        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(aluno));
        when(grupoRepository.findByAlunoId(alunoId)).thenReturn(List.of(grupo1));

        List<CreateGrupoDTO> resultado = grupoService.getGruposAluno(alunoId);

        assertThat(resultado).isNotNull();
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNome()).isEqualTo(grupo1.getNome());
    }

    @Test
    void getGruposAluno_QuandoAlunoNaoExiste(){
        Long alunoIdInvalido = 99L;
        
        when(alunoRepository.findById(alunoIdInvalido)).thenReturn(Optional.empty());
        
        assertThrows(RuntimeException.class, () -> {
            grupoService.getGruposAluno(alunoIdInvalido);
        });
    }

    @Test
    void getGrupo_QuandoGrupoExiste(){
        Long grupoId = 1L;
        Aluno aluno = new Aluno();
        
        Grupo grupo1 = new Grupo("Syncro", "Vitor", "PI", null, "");
        grupo1.setAlunos(List.of(aluno)); 

        when(grupoRepository.findById(grupoId)).thenReturn(Optional.of(grupo1));

        CreateGrupoDTO resultado = grupoService.getGrupo(grupoId);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getNome()).isEqualTo(grupo1.getNome());
        assertThat(resultado.getProfessor()).isEqualTo(grupo1.getProfessor());
        assertThat(resultado.getMembros()).contains(aluno.getEmail());
    }

    @Test
    void getGrupo_QuandoGrupoNaoExiste(){
        Long grupoInvalidoId = 99L;

        when(grupoRepository.findById(grupoInvalidoId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            grupoService.getGrupo(grupoInvalidoId);
        });
    }

   @Test
   void getProgressoGrupo_QuandoGrupoExiste(){
        Long grupoId = 1L;
        Grupo grupo = new Grupo();
        
        when(grupoRepository.findById(grupoId)).thenReturn(Optional.of(grupo));
        // Simula que o grupo tem 10 tarefas no total
        when(tarefaRepository.qtdTarefasTotal(grupoId)).thenReturn(10);
        // Simula que 4 tarefas foram concluídas
        when(tarefaRepository.qtdTarefasDone(grupoId)).thenReturn(4);

        float progresso = grupoService.getProgressoGrupo(grupoId);

        assertThat(progresso).isEqualTo(40.0f);
   }

    @Test
    void getProgressoGrupo_QuandoGrupoNaoExiste(){
        Long grupoInvalidoId = 99L;

        when(grupoRepository.findById(grupoInvalidoId)).thenReturn(Optional.empty());

        assertThrows(GrupoInexistenteException.class, () -> {
            grupoService.getProgressoGrupo(grupoInvalidoId);
        });
    }
    
}
