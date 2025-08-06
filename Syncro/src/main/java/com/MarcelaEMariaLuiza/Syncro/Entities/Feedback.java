package com.MarcelaEMariaLuiza.Syncro.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa uma entidade Feedback no sistema.
 * <p>
 * Mapeada para a tabela "feedbacks", esta classe armazena o conteúdo de um
 * feedback, seu status de aprovação, e as associações com a Tarefa e o Aluno
 * correspondentes.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feedbacks")
public class Feedback {

    /**
     * Identificador único do feedback.
     * Gerado automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * O conteúdo textual do comentário de feedback.
     */
    @Column(columnDefinition = "TEXT")
    private String comentario;

    /**
     * Status de aprovação. {@code true} se a tarefa foi aprovada, {@code false}
     * caso contrário.
     * Não pode ser nulo.
     */
    @Column(nullable = false)
    private Boolean aprovado;

    /**
     * A tarefa à qual este feedback está associado.
     * A associação é obrigatória.
     */
    @ManyToOne
    @JoinColumn(name = "tarefa_id", nullable = false)
    private Tarefa tarefa;

    /**
     * O aluno autor do feedback.
     * 
     * 
     */
  
    @JoinColumn(name = "aluno_id")
    @ManyToOne
    private Aluno aluno;

    /**
     * Data e hora da última modificação do feedback.
     */
    private LocalDateTime modificadoEm;

    /**
     * Método de callback do JPA executado antes da primeira persistência.
     * Define o campo {@code modificadoEm} com a data e hora atuais.
     */
    @PrePersist
    private void prePersist() {
        this.modificadoEm = LocalDateTime.now();
    }

}