package com.MarcelaEMariaLuiza.Syncro.Repositories;
import org.springframework.stereotype.Repository;

import com.MarcelaEMariaLuiza.Syncro.Entities.Grupo;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long>{
    
}
