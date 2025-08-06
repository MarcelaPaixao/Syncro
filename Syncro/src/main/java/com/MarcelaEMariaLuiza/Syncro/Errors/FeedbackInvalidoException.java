package com.MarcelaEMariaLuiza.Syncro.Errors;

;

/**
 * Exceção lançada quando uma operação tenta acessar um feedback que não existe.
 * <p>
 * Como uma {@code RuntimeException}, esta exceção indica que um grupo inválido
 * foi fornecido em uma requisição.
 */
public class FeedbackInvalidoException extends RuntimeException {

    /**
     * Constrói uma nova exceção com a mensagem de detalhe especificada.
     *
     * @param message A mensagem de detalhe, que informa que o grupo não foi
     *                encontrado.
     */
    public FeedbackInvalidoException(String message) {
        super(message);
    }
}
