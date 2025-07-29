package com.MarcelaEMariaLuiza.Syncro.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MarcelaEMariaLuiza.Syncro.DTO.LoginDTO;
import com.MarcelaEMariaLuiza.Syncro.DTO.LoginResponseDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Errors.EmailExistenteException;
import com.MarcelaEMariaLuiza.Syncro.Security.TokenService;
import com.MarcelaEMariaLuiza.Syncro.Services.AlunoService;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;

/**
 * Controlador REST para gerenciar as operações relacionadas a Alunos.
 * <p>
 * Expõe os endpoints HTTP para o registro (criação) e autenticação (login)
 * de alunos no sistema.
 * </p>
 *
 * @author Marcela, Maria Luiza
 * @version 1.0
 * @since 2025-07-28
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/aluno")
public class AlunoController {
    private final AlunoService alunoService;

    private final TokenService tokenService;

    /**
     * Endpoint para criar um novo aluno no sistema.
     * <p>
     * Recebe os dados do aluno no corpo da requisição e utiliza o {@link AlunoService}
     * para realizar a criação. Trata exceções específicas de negócio, como e-mail
     * já existente ou campos não preenchidos, retornando os status HTTP apropriados.
     * </p>
     *
     * @param aluno A entidade {@link Aluno} com os dados para o cadastro, recebida do corpo da requisição.
     * @return Um {@link ResponseEntity} com o status da operação e uma mensagem de sucesso ou erro.
     */
    @PostMapping("/create")
    @PermitAll
    public ResponseEntity<?> createAluno(@RequestBody Aluno aluno){
        try {
            Aluno alunoCriado = alunoService.createAluno(aluno);
            if(alunoCriado== null) return(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Algum dos dados inseridos está inválido"));
            return ResponseEntity.ok("Usuário criado");
        }catch (EmailExistenteException | CampoNaoPreenchidoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
         catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao criar o usuário");
        }
        
    }
    /**
     * Endpoint para autenticar um aluno e gerar um token JWT.
     * <p>
     * Recebe as credenciais de login, valida-as através do {@link AlunoService} e,
     * em caso de sucesso, gera um token de acesso com o {@link TokenService}.
     * </p>
     *
     * @param loginDTO O DTO {@link LoginDTO} com o e-mail e a senha do aluno.
     * @return Um {@link ResponseEntity} contendo um {@link LoginResponseDTO} com o token e e-mail
     * em caso de sucesso, ou uma mensagem de erro em caso de falha.
     */
    @PostMapping("/login")
    @PermitAll
    public ResponseEntity<?> Login(@RequestBody LoginDTO loginDTO){
        try {
            Aluno aluno = alunoService.login(loginDTO);
            String token = tokenService.generateToken(aluno);
            return ResponseEntity.ok(new LoginResponseDTO(aluno.getEmail(), token));
        }catch(UsernameNotFoundException | CampoNaoPreenchidoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao fazer o login");
        }
       
    }
    
}
 