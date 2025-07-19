package com.MarcelaEMariaLuiza.Syncro.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MarcelaEMariaLuiza.Syncro.Services.AlunoService;

import jakarta.annotation.security.PermitAll;

import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Errors.EmailExistenteException;
import com.MarcelaEMariaLuiza.Syncro.Requests.LoginRequest;
@RestController
@RequestMapping("/aluno")
public class AlunoController {
    private final AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService){
        this.alunoService = alunoService;
    }
    
    @PostMapping("/create")
    @PermitAll
    public ResponseEntity<?> createAluno(@RequestBody Aluno aluno){
        try {
            Aluno alunoCriado = alunoService.createAluno(aluno);
            if(alunoCriado== null) return(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Algum dos dados inseridos está inválido"));
            return ResponseEntity.ok("Usuário criado");
        }catch (EmailExistenteException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        catch(CampoNaoPreenchidoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
         catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao criar o usuário");
        }
        
    }

    @PostMapping("/login")
    @PermitAll
    public ResponseEntity<?> Login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok("Login feito");
    }
    
}
 