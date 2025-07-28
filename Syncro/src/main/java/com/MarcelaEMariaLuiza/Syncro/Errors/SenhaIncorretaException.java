package com.MarcelaEMariaLuiza.Syncro.Errors;

/**
 * Exceção lançada durante o processo de autenticação quando a senha fornecida
 * não corresponde à senha armazenada.
 * <p>
 * Sendo uma {@code RuntimeException}, esta é uma exceção que sinaliza uma falha
 * de login devido a credenciais inválidas.
 */
public class SenhaIncorretaException extends RuntimeException {

    /**
     * Constrói uma nova exceção com a mensagem de detalhe especificada.
     *
     * @param message A mensagem de detalhe, informando sobre a falha na
     *                autenticação.
     */
    public SenhaIncorretaException(String message) {
        super(message);
    }
}
