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

}
