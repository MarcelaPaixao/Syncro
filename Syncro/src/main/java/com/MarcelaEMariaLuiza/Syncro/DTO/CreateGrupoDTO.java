package com.MarcelaEMariaLuiza.Syncro.DTO;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateGrupoDTO{
  
private String nome;
   private String professor;
   private String materia;
   private String descricao;
   private LocalDateTime prazo;
   List <String> membros;
}
