package com.MarcelaEMariaLuiza.Syncro.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.MarcelaEMariaLuiza.Syncro.Entities.Grupo;

import java.util.List;
import java.util.Optional;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long>{
    @Override
    Optional<Grupo> findById(Long id);
    //@Query("SELECT a FROM alunos JOIN FETCH a.grupos g  where g.id = :grupo.id ; ")
    //List<Long> findAlunosByGrupo(@Param("grupo_id") Long id);
}
