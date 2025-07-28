package com.MarcelaEMariaLuiza.Syncro.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) para encapsular as credenciais de login.
 * Utilizado para receber os dados de e-mail e senha durante o processo de
 * autenticação.
 */
@Getter
@Setter
public class LoginDTO {

    /**
     * O e-mail do usuário.
     * Deve ser um endereço de e-mail bem formatado e não pode ser nulo.
     */
    @NotNull
    @Email
    private String email;

    /**
     * A senha do usuário.
     * Não pode ser nula.
     */
    @NotNull
    private String senha;

}
