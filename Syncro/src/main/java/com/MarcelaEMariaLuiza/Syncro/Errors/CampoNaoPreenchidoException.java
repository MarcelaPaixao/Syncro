package com.MarcelaEMariaLuiza.Syncro.Errors;

/**
 * Exceção lançada quando um campo obrigatório não é preenchido.
 * <p>
 * Esta é uma exceção do tipo {@code RuntimeException}, usada para
 * indicar erros de validação de dados onde o cliente não forneceu toda a
 * informação necessária.
 */
public class CampoNaoPreenchidoException extends RuntimeException {

    /**
     * Constrói uma nova exceção com a mensagem de detalhe especificada.
     *
     * @param message A mensagem de detalhe, que informa qual campo não foi
     *                preenchido.
     */
    public CampoNaoPreenchidoException(String message) {
        super(message);
    }
}
