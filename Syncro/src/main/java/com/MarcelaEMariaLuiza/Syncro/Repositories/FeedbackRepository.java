package com.MarcelaEMariaLuiza.Syncro.Repositories;

import java.util.List;

import com.MarcelaEMariaLuiza.Syncro.Entities.Feedback;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    
    List<Feedback> findByTarefa_id(Long tarefaId);

    List<Feedback> findByAluno_id(Long alunoId);
}
