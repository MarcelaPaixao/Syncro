package com.MarcelaEMariaLuiza.Syncro.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateFeedbackDTO {

    private Long id;

    private String comentario;

    private Boolean aprovado;

    private Long tarefaId;

    private Long alunoId;
}
