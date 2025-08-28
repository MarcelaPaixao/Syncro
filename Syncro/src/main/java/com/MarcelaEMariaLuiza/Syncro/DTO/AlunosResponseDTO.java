package com.MarcelaEMariaLuiza.Syncro.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * DTO (Data Transfer Object) para encapsular os dados de um aluno enviados em respostas da API.
 * <p>
 * Esta classe é usada para transferir informações essenciais e seguras sobre um
 * aluno do servidor para o cliente, evitando a exposição direta da entidade do banco de dados.
 * </p>
 *
 */
public class AlunosResponseDTO {
    /**
     * O ID único do aluno.
     */
    private Long id;
     /**
     * nome do aluno.
     */
    private String nome;
    /**
     * email único do aluno.
     */
    private String email;
}
