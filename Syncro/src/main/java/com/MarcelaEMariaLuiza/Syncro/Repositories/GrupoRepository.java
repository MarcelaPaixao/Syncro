package com.MarcelaEMariaLuiza.Syncro.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MarcelaEMariaLuiza.Syncro.Entities.Grupo;

import java.util.Optional;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long>{
    @Override
    Optional<Grupo> findById(Long id);
}
