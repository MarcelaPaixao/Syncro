package com.MarcelaEMariaLuiza.Syncro.Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.MarcelaEMariaLuiza.Syncro.DTO.CreateTarefaDTO;
import com.MarcelaEMariaLuiza.Syncro.DTO.TarefaResponseDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.MarcelaEMariaLuiza.Syncro.Entities.Grupo;
import com.MarcelaEMariaLuiza.Syncro.Entities.Tarefa;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Errors.GrupoInexistenteException;
import com.MarcelaEMariaLuiza.Syncro.Repositories.AlunoRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.FeedbackRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.GrupoRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.TarefaRepository;
import com.MarcelaEMariaLuiza.Syncro.enums.TarefaStatus;

@ExtendWith(MockitoExtension.class)
class TarefaServiceTest {

    @Mock
    private TarefaRepository tarefaRepository;
    @Mock
    private GrupoRepository grupoRepository;
    @Mock
    private AlunoRepository alunoRepository;
    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private TarefaService tarefaService;

    private Aluno aluno;
    private Grupo grupo;
    private Tarefa tarefa;
    private CreateTarefaDTO createTarefaDTO;

    @BeforeEach
    void setUp() {
        grupo = new Grupo();
        grupo.setId(1L);
        grupo.setDescricao("Grupo de Teste");
        grupo.setTarefas(new ArrayList<>()); 
        aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("Aluno Teste");

        tarefa = new Tarefa();
        tarefa.setId(1L);
        tarefa.setTitulo("Tarefa Teste");
        tarefa.setDescricao("Descrição da Tarefa");
        tarefa.setStatus(TarefaStatus.TODO);
        tarefa.setGrupo(grupo);
        tarefa.setAluno(aluno);
        
        createTarefaDTO = new CreateTarefaDTO();
        createTarefaDTO.setTitulo("Tarefa Teste");
        createTarefaDTO.setDescricao("Descrição DTO");
        createTarefaDTO.setGrupoId(1L);
        createTarefaDTO.setAlunoId(1L);
        createTarefaDTO.setId(1L);
        createTarefaDTO.setStatus(TarefaStatus.TODO);
    }

    @Test
  
    void CreateTarefaSuccesso() {
        when(grupoRepository.findById(1L)).thenReturn(Optional.of(grupo));
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        aluno.adicionaGrupo(grupo); 
        
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefa);
        when(grupoRepository.save(any(Grupo.class))).thenReturn(grupo);

    
        Tarefa result = tarefaService.createTarefa(createTarefaDTO);
        assertNotNull(result);
        assertEquals("Tarefa Teste", result.getTitulo());
        verify(tarefaRepository, times(1)).save(any(Tarefa.class));
        verify(grupoRepository, times(1)).save(grupo);
    }
    
    @Test
    void testCreateTarefa_ThrowsExceptionWhenTitleIsEmpty() {
    
        createTarefaDTO.setTitulo("");
        assertThrows(CampoNaoPreenchidoException.class, () -> {
            tarefaService.createTarefa(createTarefaDTO);
        });
    }

    @Test
    void GetTarefasPorGrupoSuccesso() {
        
        when(grupoRepository.findById(1L)).thenReturn(Optional.of(grupo));
        when(tarefaRepository.findByGrupo_id(1L)).thenReturn(Collections.singletonList(tarefa));

        List<CreateTarefaDTO> result = tarefaService.getTarefasPorGrupo(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Tarefa Teste", result.get(0).getTitulo());
    }


    @Test
    void EditaTarefa() {
     
        createTarefaDTO.setStatus(TarefaStatus.DOING);
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(alunoRepository.estaNoGrupoAluno(1L, 1L)).thenReturn(Optional.of(aluno));

      
        tarefaService.EditaTarefa(createTarefaDTO);
        verify(tarefaRepository, times(1)).save(any(Tarefa.class));
        assertEquals(TarefaStatus.DOING, tarefa.getStatus());
    }

   
    void EditaTarefaMudaParaDone() {
      
        createTarefaDTO.setStatus(TarefaStatus.DONE);
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(alunoRepository.countGrupoMembers(1L)).thenReturn(4); 
        when(feedbackRepository.countApprovedFeedbacks(1L, 1L)).thenReturn(3);

        tarefaService.EditaTarefa(createTarefaDTO);

        verify(tarefaRepository, times(1)).save(tarefa);
        assertEquals(TarefaStatus.DONE, tarefa.getStatus());
    }

    @Test
    @DisplayName("Não deve alterar status para DONE com feedbacks insuficientes")
    void testEditaTarefaNaoMudaParaDone() {
    
        tarefa.setStatus(TarefaStatus.REVIEW); 
        createTarefaDTO.setStatus(TarefaStatus.DONE);
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(alunoRepository.countGrupoMembers(1L)).thenReturn(5); 
        when(feedbackRepository.countApprovedFeedbacks(1L, 1L)).thenReturn(2); 

        tarefaService.EditaTarefa(createTarefaDTO);
        verify(tarefaRepository, times(1)).save(tarefa);
        assertEquals(TarefaStatus.REVIEW, tarefa.getStatus()); 
    }
    @Test
    void GetTarefasParaAvaliarSuccesso() {
       
        Long avaliadorId = 2L;
        Aluno avaliador = new Aluno();
        avaliador.setId(avaliadorId);
        Tarefa tarefaParaAvaliar = new Tarefa();
        tarefaParaAvaliar.setId(2L);
        tarefaParaAvaliar.setTitulo("Outra Tarefa");
        tarefaParaAvaliar.setDescricao("Para Avaliar");
        tarefaParaAvaliar.setGrupo(grupo);
        tarefaParaAvaliar.setAluno(aluno); 
        tarefaParaAvaliar.setStatus(TarefaStatus.REVIEW);

        when(grupoRepository.findByAlunoId(avaliadorId)).thenReturn(Collections.singletonList(grupo));
        when(tarefaRepository.findByGrupo_id(grupo.getId())).thenReturn(Collections.singletonList(tarefaParaAvaliar));
        when(feedbackRepository.FeedbackDado(avaliadorId, tarefaParaAvaliar.getId())).thenReturn(0); 

        List<TarefaResponseDTO> result = tarefaService.getTarefasParaAvaliar(avaliadorId);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Outra Tarefa", result.get(0).getTitulo());
    }
    
    @Test
    void testGetTarefasParaAvaliarSoRetornarEmReview() {
      
        Long avaliadorId = 2L;
        tarefa.setStatus(TarefaStatus.DOING);

        when(grupoRepository.findByAlunoId(avaliadorId)).thenReturn(Collections.singletonList(grupo));
        when(tarefaRepository.findByGrupo_id(grupo.getId())).thenReturn(Collections.singletonList(tarefa));
       
        List<TarefaResponseDTO> result = tarefaService.getTarefasParaAvaliar(avaliadorId);
        assertTrue(result.isEmpty());
    }

    @Test

    void GetTarefaSuccesso() {
      
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        CreateTarefaDTO result = tarefaService.getTarefa(1L);
        assertNotNull(result);
        assertEquals(tarefa.getId(), result.getId());
        assertEquals(tarefa.getTitulo(), result.getTitulo());
    }

    @Test
    void testGetTarefaMalSucedida() {
        when(tarefaRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            tarefaService.getTarefa(99L);
        });
    }
}