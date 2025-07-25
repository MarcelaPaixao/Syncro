package com.MarcelaEMariaLuiza.Syncro.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MarcelaEMariaLuiza.Syncro.Entities.Tarefa;

@Repository
public interface  TarefaRepository extends JpaRepository<Tarefa, Long>{
    
}
