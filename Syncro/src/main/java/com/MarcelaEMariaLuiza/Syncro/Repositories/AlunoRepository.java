package com.MarcelaEMariaLuiza.Syncro.Repositories;

import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    
} 
