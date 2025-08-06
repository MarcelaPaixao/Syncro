package com.MarcelaEMariaLuiza.Syncro.DTO;

import java.time.LocalDate;

import com.MarcelaEMariaLuiza.Syncro.enums.TarefaStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* DTO (Data Transfer Object) para a criação de uma nova Tarefa.
 * Agrupa os dados necessários enviados em uma requisição para criar uma entidade Tarefa.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTarefaDTO {

    /**
     * O identificador único da tarefa.
     * Geralmente nulo durante a criação.
     */
    private Long Id;

    /**
     * O título ou nome da tarefa.
     */
    private String titulo;

    /**
     * A descrição detalhada do que deve ser feito na tarefa.
     */
    private String descricao;

    /**
     * A data limite para a conclusão da tarefa.
     */
    private LocalDate prazo;

    /**
     * Link para um arquivo ou pasta no Google Drive relacionado à tarefa.
     */
    private String linkDrive;

    /**
     * Um link adicional para referência ou material de apoio.
     */
    private String linkExtra;

    /**
     * O ID do grupo ao qual esta tarefa pertence.
     */
    private Long grupoId;

    /**
     * O ID do aluno designado para realizar a tarefa.
     */
    private Long alunoId;

    @Enumerated(EnumType.STRING)
    private TarefaStatus status;

}
