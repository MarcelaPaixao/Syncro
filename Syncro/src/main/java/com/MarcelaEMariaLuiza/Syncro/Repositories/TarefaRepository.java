package com.MarcelaEMariaLuiza.Syncro.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MarcelaEMariaLuiza.Syncro.Entities.Tarefa;

/**
 * Repositório para a entidade {@link Tarefa}.
 * <p>
 * Gerencia as operações de banco de dados para as tarefas,
 * estendendo {@link JpaRepository} para obter funcionalidades CRUD padrão.
 */
@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    /**
     * Busca todas as tarefas associadas a um grupo específico.
     *
     * @param grupoId O ID do grupo cujas tarefas serão buscadas.
     * @return Uma lista de {@link Tarefa} pertencentes ao grupo.
     */
    public List<Tarefa> findByGrupo_id(Long grupoId);

    /**
     * Busca todas as tarefas atribuídas a um aluno específico.
     *
     * @param alunoId O ID do aluno cujas tarefas serão buscadas.
     * @return Uma lista de {@link Tarefa} atribuídas ao aluno.
     */
    public List<Tarefa> findByAluno_id(Long alunoId);

    /**
     * Calcula a quantidade total de tarefas associadas a um grupo específico.
     *
     * @param grupoId O ID do grupo cujas tarefas serão contadas.
     * @return Um {@code int} representando o número total de tarefas no grupo,
     * independentemente do status.
     */

    @Query("SELECT COUNT (t) FROM Tarefa t where t.grupo.id = :grupoId")
    public int qtdTarefasTotal(@Param("grupoId") Long grupoId);
    
    /**
     * Calcula a quantidade de tarefas com o status 'DONE' (concluídas) em um grupo específico.
     *
     * @param grupoId O ID do grupo a ser verificado.
     * @return Um {@code int} representando o número de tarefas concluídas no grupo.
     */
    @Query("SELECT COUNT (t) FROM Tarefa t WHERE t.grupo.id = :grupoId AND t.status = DONE")
    public int qtdTarefasDone(@Param("grupoId") Long grupoId);
}
