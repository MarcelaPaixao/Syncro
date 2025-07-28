package com.MarcelaEMariaLuiza.Syncro.Errors;

/**
 * Exceção lançada ao tentar cadastrar um usuário com um e-mail que já existe no
 * sistema.
 * <p>
 * Sendo uma {@code RuntimeException}, esta é uma exceção que sinaliza uma
 * violação de uma regra de negócio (unicidade de e-mail).
 */
public class EmailExistenteException extends RuntimeException {

    /**
     * Constrói uma nova exceção com a mensagem de detalhe especificada.
     *
     * @param message A mensagem de detalhe, indicando que o e-mail já está em uso.
     */
    public EmailExistenteException(String message) {
        super(message);
    }

}
