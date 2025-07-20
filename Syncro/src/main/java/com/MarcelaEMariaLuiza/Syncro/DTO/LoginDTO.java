package com.MarcelaEMariaLuiza.Syncro.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String senha;
    
}
