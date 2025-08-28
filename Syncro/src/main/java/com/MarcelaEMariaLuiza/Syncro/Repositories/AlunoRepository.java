package com.MarcelaEMariaLuiza.Syncro.Repositories;

import java.util.List;
import java.util.Optional;

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
     /**
     * Conta o número total de alunos em um grupo específico.
     *
     * @param grupoId O ID do grupo para o qual o número de membros será contado.
     * @return Um {@code int} representando o número total de membros. Retorna 0 se o
     * grupo não existir ou não tiver membros.
     */
    @Query("SELECT COUNT (a) FROM Aluno a JOIN a.grupos g WHERE g.id = :grupoId")
    int countGrupoMembers(@Param("grupoId") Long grupoId);
    /**
     * Verifica se um aluno específico é membro de um grupo específico.
     * <p>
     * Este método é útil para validar se um relacionamento entre um aluno e um grupo existe.
     * </p>
     *
     * @param grupoId O ID do grupo a ser verificado.
     * @param id O ID do aluno a ser verificado.
     * @return Um {@link Optional}&lt;{@link Aluno}&gt; contendo o aluno se ele pertencer ao grupo,
     * ou um {@code Optional} vazio caso contrário.
     */
    @Query("SELECT a FROM Aluno a JOIN a.grupos g WHERE g.id = :grupoId AND a.id = :alunoId")
    Optional<Aluno> estaNoGrupoAluno(@Param("grupoId") Long grupoId, @Param("alunoId")Long id);

}
