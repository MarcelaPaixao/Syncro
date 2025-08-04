package com.MarcelaEMariaLuiza.Syncro.Entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa um Aluno no sistema, que também serve como usuário para
 * autenticação.
 * <p>
 * Esta entidade é mapeada para a tabela "alunos" e implementa a interface
 * {@link UserDetails} do Spring Security para integração com o sistema de
 * login.
 * Os métodos não implementados da interface retornam {@code true} por padrão.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "alunos")
public class Aluno implements UserDetails {

    /**
     * Identificador único do aluno. Gerado automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Nome do aluno. Não pode ser nulo.
     */
    @Column(nullable = false)
    private String nome;

    /**
     * E-mail do aluno. Usado como nome de usuário para login.
     * Deve ser único e não pode ser nulo.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Senha do aluno para autenticação.
     * Não pode ser nula.
     */
    @Column(nullable = false)
    private String senha;

    /**
     * Papel (role) do usuário, que define suas permissões. Por padrão, é ALUNO.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles role = Roles.ALUNO;

    /**
     * Lista de grupos dos quais o aluno participa.
     * A relação é Many-to-many e utiliza a tabela "alunosXgrupos".
     */
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(name = "alunosXgrupos", joinColumns = @JoinColumn(name = "id_aluno"), inverseJoinColumns = @JoinColumn(name = "id_grupo"))
    private List<Grupo> grupos = new ArrayList<>();

    /**
     * Adiciona um grupo à lista de grupos do aluno, caso ainda não tenha sido
     * adicionado.
     *
     * @param grupo O grupo a ser adicionado.
     */
    public void adicionaGrupo(Grupo grupo) {
        if (!this.grupos.contains(grupo))
            this.grupos.add(grupo);
    }

    /**
     * Retorna as permissões concedidas ao usuário. Para um aluno, é sempre
     * "ROLE_USER".
     *
     * @return Uma coleção com as permissões do usuário.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * Retorna a senha usada para autenticar o usuário.
     *
     * @return A senha do aluno.
     */
    @Override
    public String getPassword() {
        return senha;
    }

    /**
     * Retorna o nome de usuário usado para autenticar o usuário.
     *
     * @return O e-mail do aluno.
     */
    @Override
    public String getUsername() {
        return email;
    }

    public boolean estaEmGrupoAluno(Grupo grupo){
        return this.grupos.contains(grupo);
    } 
}
