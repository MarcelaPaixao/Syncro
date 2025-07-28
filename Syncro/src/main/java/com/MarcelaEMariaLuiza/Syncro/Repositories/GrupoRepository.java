package com.MarcelaEMariaLuiza.Syncro.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MarcelaEMariaLuiza.Syncro.Entities.Grupo;

/**
 * Repositório para a entidade {@link Grupo}.
 * <p>
 * Gerencia as operações de banco de dados para os grupos, estendendo
 * {@link JpaRepository} para obter funcionalidades CRUD padrão.
 */
@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    /**
     * Busca um grupo pelo seu ID.
     * <p>
     * Sobrescreve o método padrão para garantir o retorno de um {@link Optional},
     * tratando de forma segura os casos em que o grupo não é encontrado.
     *
     * @param id O ID do grupo a ser buscado.
     * @return um {@link Optional} contendo o {@link Grupo} se encontrado, ou um
     *         {@link Optional} vazio caso contrário.
     */
    @Override
    Optional<Grupo> findById(Long id);

    /**
     * Busca todos os grupos aos quais um aluno específico pertence.
     *
     * @param alunoId O ID do aluno.
     * @return Uma lista de {@link Grupo} dos quais o aluno é membro.
     */
    @Query("SELECT g FROM Grupo g JOIN g.alunos a WHERE a.id = :alunoId")
    List<Grupo> findByAlunoId(@Param("alunoId") Long alunoId);

}
