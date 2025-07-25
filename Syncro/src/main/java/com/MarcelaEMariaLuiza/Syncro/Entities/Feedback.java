package com.MarcelaEMariaLuiza.Syncro.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="feedbacks")
public class Feedback {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

    @Column(columnDefinition="TEXT")
    private String comentario;

    @Column(nullable=false)
    private Boolean aprovado;
    
    @ManyToOne
    @JoinColumn(name="tarefa_id", nullable=false)
    private Tarefa tarefa;
   
    @MapsId
    @JoinColumn(name = "aluno_id")
    @OneToOne
    private Aluno aluno;

    private LocalDateTime modificadoEm;

    @PrePersist
    private void prePersist(){
        this.modificadoEm = LocalDateTime.now();
    }

}