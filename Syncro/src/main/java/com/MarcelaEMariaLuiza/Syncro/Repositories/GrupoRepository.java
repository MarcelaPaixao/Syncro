package com.MarcelaEMariaLuiza.Syncro.Repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MarcelaEMariaLuiza.Syncro.Entities.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long>{
    @Override
    Optional<Grupo> findById(Long id);

    @Query("SELECT g FROM Grupo g JOIN g.alunos a WHERE a.id = :alunoId")
    List <Grupo> findByAlunoId(@Param("alunoId") Long alunoId);

}
