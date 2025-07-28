package com.MarcelaEMariaLuiza.Syncro.Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa um Grupo de trabalho ou estudo no sistema.
 * <p>
 * Esta entidade é mapeada para a tabela "grupo" e agrupa alunos, tarefas e
 * informações gerais como matéria, professor e prazos.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "grupo")
@Entity
public class Grupo {

    /**
     * Identificador único do grupo.
     * Gerado automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    /**
     * Nome do grupo. Não pode ser nulo.
     */
    @Column(nullable = false)
    private String nome;

    /**
     * Nome do professor responsável pela matéria. Não pode ser nulo.
     */
    @Column(nullable = false)
    private String professor;

    /**
     * Matéria ou disciplina à qual o grupo está associado. Não pode ser nulo.
     */
    @Column(nullable = false)
    private String materia;

    /**
     * Data de prazo final para as atividades do grupo.
     * O formato é 'Ano-Mês-Dia'.
     */
    @Column(nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate prazo;

    /**
     * Data e hora em que o grupo foi modificado pela última vez.
     */
    private LocalDateTime modificadoEm;

    /**
     * Descrição textual sobre os objetivos ou tema do grupo.
     */
    @Column(columnDefinition = "TEXT")
    private String descricao;

    /**
     * Lista de alunos que fazem parte deste grupo.
     * A relação é gerenciada pela entidade Aluno.
     */
    @ManyToMany(mappedBy = "grupos")
    List<Aluno> alunos = new ArrayList<>();

    /**
     * Construtor para criar uma nova instância de Grupo com dados essenciais.
     *
     * @param nome      O nome do grupo.
     * @param professor O nome do professor.
     * @param materia   A matéria associada.
     * @param prazo     A data de prazo.
     * @param descricao A descrição do grupo.
     */
    public Grupo(String nome, String professor, String materia, LocalDate prazo, String descricao) {
        this.nome = nome;
        this.professor = professor;
        this.materia = materia;
        this.prazo = prazo;
        this.descricao = descricao;
    }

    /**
     * Lista de tarefas que pertencem a este grupo.
     */
    @OneToMany(mappedBy = "grupo")
    private List<Tarefa> tarefas;

    /**
     * Método de callback do JPA executado antes da primeira persistência.
     * Define o campo {@code modificadoEm} com a data e hora atuais.
     */
    @PrePersist
    private void prePersist() {
        this.modificadoEm = LocalDateTime.now();
    }

    /**
     * Adiciona uma nova tarefa à lista de tarefas deste grupo.
     *
     * @param tarefa A tarefa a ser adicionada.
     */
    public void adicionaNovaTarefa(Tarefa tarefa) {
        this.tarefas.add(tarefa);
    }
}
