package com.MarcelaEMariaLuiza.Syncro.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Aluno findByEmail(String email);

    @Query("SELECT a FROM Aluno a JOIN a.grupos g WHERE g.id = :idDoGrupo")
    List <Aluno> FindByGrupos(@Param("idDoGrupo") Long idDoGrupo);

   

} 
