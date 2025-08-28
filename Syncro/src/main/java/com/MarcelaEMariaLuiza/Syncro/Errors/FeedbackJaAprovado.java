package com.MarcelaEMariaLuiza.Syncro.Errors;
/**
 * Exceção lançada ao tentar modificar um feedback que já foi aprovado.
 * <p>
 * Esta é uma exceção não verificada (unchecked), pois estende {@link RuntimeException}.
 * Ela sinaliza um erro de lógica de negócio que impede a alteração de um
 * feedback após sua aprovação final.
 * </p>
 *
 * 
 */
public class FeedbackJaAprovado extends RuntimeException {
     /**
     * Constrói uma nova exceção {@code FeedbackJaAprovado} com a mensagem de detalhe especificada.
     *
     * @param message a mensagem de detalhe (que é salva para recuperação posterior pelo método {@link #getMessage()}).
     */
    public FeedbackJaAprovado(String message){
        super(message);
    }
}
