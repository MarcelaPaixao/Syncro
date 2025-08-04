package com.MarcelaEMariaLuiza.Syncro.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) para representar os dados de uma Tarefa em
 * respostas da API.
 * Esta classe é usada para formatar e enviar informações de uma ou mais tarefas
 * para o cliente.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

 public class TarefaResponseDTO {
    
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

}


