package com.MarcelaEMariaLuiza.Syncro.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MarcelaEMariaLuiza.Syncro.Services.AlunoService;

import jakarta.annotation.security.PermitAll;

import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
@Controller
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
            return ResponseEntity.ok("Usuário criado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao criar o usuário");
        }
        
    }
}
