package com.MarcelaEMariaLuiza.Syncro.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EditFeedbackDTO {
    
    private Long id;

    /**
     * O conteúdo textual do feedback.
     */
    private String comentario;

    /**
     * Status de aprovação do feedback.
     * {@code true} se aprovado, {@code false} caso contrário.
     */
    private Boolean aprovado;

}
