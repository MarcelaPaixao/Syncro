package com.MarcelaEMariaLuiza.Syncro.Services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    /*@BeforeAll
    static void settingUpFunctions(){
        CreateGrupoDTO dto = new CreateGrupoDTO();
        dto.setNome("Syncro");
        dto.setProfessor("Vitor");
        dto.setMateria("PI I");
        Aluno criador = new Aluno();
    }
    void grupoNaoCriadoPorCampoNulo(){
        dto.setMembros(null);
        
        assertThrows(
            CampoNaoPreenchidoException.class,
            () -> grupoService.criarGrupo(dto, criador)
        );
    }*/
}
