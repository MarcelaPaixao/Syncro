package com.MarcelaEMariaLuiza.Syncro.Requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String senha;
    
}
