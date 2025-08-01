package com.MarcelaEMariaLuiza.Syncro.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MarcelaEMariaLuiza.Syncro.DTO.CreateGrupoDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Grupo;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Services.GrupoService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Controlador REST para gerenciar as operações de Grupos.
 * <p>
 * Este controlador expõe os endpoints HTTP para a criação de novos grupos
 * e a consulta de grupos associados a um aluno específico.
 * </p>
 *
 * @author Marcela, Maria Luiza
 * @version 1.0
 * @since 2025-07-28
 */
@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/api/grupo")
public class GrupoController {
    @Autowired
    private GrupoService grupoService;
    /**
     * Endpoint para a criação de um novo grupo.
     * <p>
     * Recebe os dados do grupo a ser criado e utiliza o usuário autenticado,
     * injetado pelo Spring Security, como o criador do grupo. Delega a lógica
     * de criação para o {@link GrupoService}.
     * </p>
     *
     * @param createGrupoDTO DTO com os dados do grupo a ser criado.
     * @param authentication Objeto de autenticação injetado, contendo os dados do usuário logado.
     * @return Um {@link ResponseEntity} com a resposta da operação (sucesso ou erro).
     */
    @PostMapping("/create")
    public ResponseEntity<?> createGrupo(@RequestBody CreateGrupoDTO createGrupoDTO, Authentication authentication){
        try{
            Grupo novoGrupo = grupoService.criaGrupo(createGrupoDTO, authentication.getPrincipal());
            return ResponseEntity.ok("Grupo Criado");
        }catch(CampoNaoPreenchidoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        
        
        
    }
    /**
     * Endpoint para buscar todos os grupos de um aluno específico.
     *
     * @param alunoId O ID do aluno, recebido como uma variável no caminho (path variable).
     * @return Uma lista de {@link CreateGrupoDTO} representando os grupos do aluno, ou {@code null} em caso de erro.
     */
    @GetMapping("/get/{alunoId}")
    public List<CreateGrupoDTO> getGruposAluno(@PathVariable Long alunoId){
        try{
            System.out.println("entrou");
            List <CreateGrupoDTO> grupos = grupoService.getGruposAluno(alunoId);
            System.out.println(grupos);
            return grupos;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
            //throw  new ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        
        
        
    }
}
