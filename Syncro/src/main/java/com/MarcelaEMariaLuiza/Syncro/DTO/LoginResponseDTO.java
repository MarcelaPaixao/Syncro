package com.MarcelaEMariaLuiza.Syncro.DTO;

/**
 * DTO (Data Transfer Object) que representa a resposta de um login
 * bem-sucedido.
 * Contém as informações essenciais para o cliente após a autenticação.
 *
 * @param nome  O nome do usuário que efetuou o login.
 * @param token O token de autenticação gerado para a sessão do usuário.
 */
public record LoginResponseDTO(String nome, String token) {

}
