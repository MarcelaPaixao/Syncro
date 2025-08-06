package com.MarcelaEMariaLuiza.Syncro.DTO;

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
     * O nome do aluno designado para realizar a tarefa.
     */
    private String alunoNome;

}


