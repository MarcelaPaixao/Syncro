package com.MarcelaEMariaLuiza.Syncro.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.MarcelaEMariaLuiza.Syncro.Entities.Tarefa;

@Repository
public interface  TarefaRepository extends JpaRepository<Tarefa, Long>{
    public List<Tarefa> findByGrupo_id(Long grupoId);

    public List<Tarefa> findByAluno_id(Long alunoId);
    
}
