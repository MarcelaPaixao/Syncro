package com.MarcelaEMariaLuiza.Syncro.Entities;

import java.time.LocalDate;
import java.util.List;

import com.MarcelaEMariaLuiza.Syncro.enums.TarefaStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa uma entidade Tarefa no sistema.
 * <p>
 * Esta classe é mapeada para a tabela "tarefas" no banco de dados e contém
 * todos os detalhes sobre uma tarefa, incluindo sua descrição, prazo e
 * associações com outras entidades.
 */
@Entity
@Getter
@Setter
@Table(name = "tarefas")
public class Tarefa {

    /**
     * Identificador único da tarefa (Chave primária).
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Título da tarefa. Não pode ser nulo.
     */
    @Column(nullable = false)
    private String titulo;

    /**
     * Descrição detalhada da tarefa. Pode ser nulo.
     */
    @Column(nullable = true, columnDefinition = "TEXT")
    private String descricao;

    /**
     * Data limite para a conclusão da tarefa. Pode ser nulo.
     */
    @Column(nullable = true)
    private LocalDate prazo;

    /**
     * Lista de feedbacks associados a esta tarefa.
     */
    @OneToMany(mappedBy = "tarefa")
    private List<Feedback> feedbacks;

    /**
     * Link do Google Drive com arquivos pertinentes à tarefa.
     */
    @Column(nullable = true)
    private String linkDrive;

    /**
     * Link extra para material de apoio ou referência.
     */
    @Column(nullable = true)
    private String linkExtra;

    /**
     * O grupo ao qual esta tarefa pertence.
     * A associação é obrigatória.
     */
    @ManyToOne
    @JoinColumn(name = "grupo_id", nullable = false)
    private Grupo grupo;
    
    @Enumerated(EnumType.STRING)
    private TarefaStatus status;
    /**
     * O aluno designado para realizar esta tarefa.
     */
    @OneToOne
    private Aluno aluno;
}
