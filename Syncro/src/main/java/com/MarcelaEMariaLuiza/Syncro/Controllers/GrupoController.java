package com.MarcelaEMariaLuiza.Syncro.Controllers;


import org.hibernate.engine.spi.EntityEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.MarcelaEMariaLuiza.Syncro.DTO.CreateGrupoDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Grupo;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Services.GrupoService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/grupo")
public class GrupoController {
    @Autowired
    private GrupoService grupoService;
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
}
