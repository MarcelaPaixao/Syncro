package com.MarcelaEMariaLuiza.Syncro.Entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="grupo")
@Entity
public class Grupo {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long Id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String professor;

    @Column(nullable = false)
    private String materia;
    
    private LocalDateTime prazo;

    private LocalDateTime modificadoEm;
   
    @Column(columnDefinition="TEXT")
    private String descricao;

    @ManyToMany(mappedBy="grupos")
    List <Aluno> alunos;
    public Grupo( String nome,  String professor, String materia,LocalDateTime prazo, String descricao, List <Aluno>membros){
        this.nome = nome;
        this.professor = professor;
        this.materia = materia;
        this.prazo = prazo;
        this.descricao = descricao;
        this.alunos= membros;

    }
    @PrePersist
    private void prePersist(){
        this.modificadoEm = LocalDateTime.now();
    }
}
