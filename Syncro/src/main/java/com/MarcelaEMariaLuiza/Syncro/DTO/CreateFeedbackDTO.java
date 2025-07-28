package com.MarcelaEMariaLuiza.Syncro.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) para a criação de um novo Feedback.
 * Carrega os dados essenciais recebidos da requisição para criar uma entidade
 * Feedback.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateFeedbackDTO {

    /**
     * O identificador único do feedback.
     * Geralmente nulo na criação e preenchido na resposta.
     */
    private Long id;

    /**
     * O conteúdo textual do feedback.
     */
    private String comentario;

    /**
     * Status de aprovação do feedback.
     * {@code true} se aprovado, {@code false} caso contrário.
     */
    private Boolean aprovado;

    /**
     * O ID da tarefa à qual este feedback está associado.
     */
    private Long tarefaId;

    /**
     * O ID do aluno que está recebendo o feedback.
     */
    private Long alunoId;
}
