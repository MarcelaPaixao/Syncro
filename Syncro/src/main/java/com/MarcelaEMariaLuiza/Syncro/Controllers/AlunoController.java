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
@RestController
@RequiredArgsConstructor
@RequestMapping("/aluno")
public class AlunoController {
    private final AlunoService alunoService;

    private final TokenService tokenService;

    
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
    @GetMapping("/test")
    public String testando(){
        return "yay";
    }
    
}
 