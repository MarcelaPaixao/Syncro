package com.MarcelaEMariaLuiza.Syncro.Repositories;

import java.util.List;

import com.MarcelaEMariaLuiza.Syncro.Entities.Feedback;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para a entidade {@link Feedback}.
 * <p>
 * Esta interface gerencia as operações de banco de dados para os feedbacks,
 * estendendo {@link JpaRepository} para fornecer métodos CRUD padrão.
 */
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    /**
     * Busca todos os feedbacks associados a uma tarefa específica.
     *
     * @param tarefaId O ID da tarefa cujos feedbacks serão buscados.
     * @return Uma lista de {@link Feedback} relacionados à tarefa.
     */
    List<Feedback> findByTarefa_id(Long tarefaId);

    /**
     * Busca todos os feedbacks associados a um aluno específico.
     *
     * @param alunoId O ID do aluno cujos feedbacks serão buscados.
     * @return Uma lista de {@link Feedback} relacionados ao aluno.
     */
    List<Feedback> findByAluno_id(Long alunoId);
}
