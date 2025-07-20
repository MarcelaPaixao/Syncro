package com.MarcelaEMariaLuiza.Syncro.Entities;

import lombok.Getter;

@Getter
public enum Roles {
    ALUNO("user");

    private String role;

    Roles(String role){
        this.role = role;
    }
}
