package com.MarcelaEMariaLuiza.Syncro.Entities;

import lombok.Getter;

/**
 * Enum que define os papéis (roles) de usuário no sistema.
 * <p>
 * Utilizado para controle de acesso e autorização.
 */
@Getter
public enum Roles {

    /**
     * Papel de Aluno. Representa um usuário padrão com permissões de estudante.
     * O valor associado "user" é o identificador interno para o papel de aluno.
     */
    ALUNO("user");

    /**
     * O valor textual do papel, usado em configurações de segurança.
     */
    private String role;

    /**
     * Construtor para associar um valor textual a cada constante do enum.
     *
     * @param role O nome do papel como uma String.
     */
    Roles(String role) {
        this.role = role;
    }
}
