package com.MarcelaEMariaLuiza.Syncro.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAlunoDTO {
    private String senha;
    private String nome;
    private String email;
}
