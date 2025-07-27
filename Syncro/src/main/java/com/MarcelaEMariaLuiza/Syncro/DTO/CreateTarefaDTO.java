package com.MarcelaEMariaLuiza.Syncro.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTarefaDTO {
    
    private Long Id;

    private String titulo;

    private String descricao;

    private LocalDate prazo;

    private String linkDrive;

    private String linkExtra;

    private Long grupoId;

    private Long alunoId;


}
