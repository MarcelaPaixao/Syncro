package com.MarcelaEMariaLuiza.Syncro.Entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="tarefas")
public class Tarefa {
   
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String titulo;
    
    @Column(nullable = true, columnDefinition="TEXT")
    private String descricao;

    @Column(nullable = true)
    private LocalDate prazo;
    
    @OneToMany(mappedBy = "tarefa")
    private List<Feedback> feedbacks;

    @Column(nullable = true)
    private String linkDrive;

    @Column(nullable = true)
    private String linkExtra;

    @ManyToOne
    @JoinColumn(name = "grupo_id",nullable=false )
    private Grupo grupo;
}
