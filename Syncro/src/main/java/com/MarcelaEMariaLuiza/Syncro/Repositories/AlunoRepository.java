package com.MarcelaEMariaLuiza.Syncro.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;

/**
 * Repositório para a entidade {@link Aluno}.
 * <p>
 * Esta interface gerencia as operações de banco de dados para os alunos,
 * estendendo {@link JpaRepository} para fornecer métodos CRUD padrão.
 */
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    /**
     * Busca um aluno pelo seu endereço de e-mail.
     *
     * @param email O e-mail do aluno a ser buscado.
     * @return O {@link Aluno} correspondente ao e-mail, ou {@code null} se não for
     *         encontrado.
     */
    Aluno findByEmail(String email);

    /**
     * Busca todos os alunos que pertencem a um grupo específico.
     *
     * @param idDoGrupo O ID do grupo cujos alunos serão listados.
     * @return Uma lista de {@link Aluno} que são membros do grupo especificado.
     */
    @Query("SELECT a FROM Aluno a JOIN a.grupos g WHERE g.id = :idDoGrupo")
    List<Aluno> FindByGrupos(@Param("idDoGrupo") Long idDoGrupo);

    @Query("SELECT COUNT (a) FROM Aluno a JOIN a.grupos g WHERE g.id = :grupoId")
    int countGrupoMembers(@Param("grupoId") Long grupoId);

}
