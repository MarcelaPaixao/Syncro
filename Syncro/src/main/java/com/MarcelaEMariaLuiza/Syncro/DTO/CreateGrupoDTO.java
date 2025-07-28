package com.MarcelaEMariaLuiza.Syncro.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) para a criação de um novo Grupo.
 * Contém as informações necessárias para registrar um grupo no sistema.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateGrupoDTO {

   /**
    * O identificador único do grupo.
    * Geralmente nulo durante a criação.
    */
   private Long Id;

   /**
    * O nome do grupo.
    */
   private String nome;

   /**
    * O nome do professor responsável pelo grupo ou matéria.
    */
   private String professor;

   /**
    * A matéria ou disciplina à qual o grupo pertence.
    */
   private String materia;

   /**
    * Descrição dos objetivos ou tema do grupo.
    */
   private String descricao;

   /**
    * A data de prazo para a conclusão das atividades do grupo.
    */
   private LocalDate prazo;

   /**
    * Lista com os nomes ou identificadores dos membros a serem adicionados ao
    * grupo.
    */
   List<String> membros;
}
