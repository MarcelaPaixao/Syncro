package com.MarcelaEMariaLuiza.Syncro.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.MarcelaEMariaLuiza.Syncro.Entities.Feedback;

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
    /**
     * Conta o número de feedbacks aprovados para uma tarefa, excluindo o feedback do próprio dono da tarefa.
     * <p>
     * Este método é útil para a regra de negócio que determina se uma tarefa pode ser concluída
     * com base na aprovação dos outros membros do grupo.
     * </p>
     *
     * @param tarefaId O ID da tarefa cujos feedbacks serão contados.
     * @param donoId O ID do aluno que é o dono da tarefa (para ser excluído da contagem).
     * @return Um {@code int} representando a quantidade de feedbacks aprovados pelos outros membros.
     */
    @Query("SELECT COUNT(f) FROM Feedback f WHERE f.tarefa.id = :tarefaId AND f.aluno.id != :donoId AND f.aprovado = TRUE")
    int countApprovedFeedbacks(@Param("tarefaId") Long tarefaId, @Param("donoId") Long donoId);
      /**
     * Verifica se um aluno específico já forneceu um feedback para uma tarefa específica.
     * <p>
     * O método retorna a contagem de feedbacks, que será {@code 1} se o feedback já foi dado
     * ou {@code 0} caso contrário.
     * </p>
     *
     * @param tarefaId O ID da tarefa a ser verificada.
     * @param alunoId O ID do aluno a ser verificado.
     * @return {@code 1} se o aluno já deu feedback, {@code 0} se não.
     */
    @Query("SELECT COUNT (f) from Feedback f Where f.tarefa.id = :tarefaId AND f.aluno.id = :alunoId")
    int FeedbackDado(@Param("tarefaId") Long tarefaId, @Param("alunoId") Long alunoId);

    
}
