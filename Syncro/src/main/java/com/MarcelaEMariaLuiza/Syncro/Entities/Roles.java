package com.MarcelaEMariaLuiza.Syncro.Entities;

import lombok.Getter;

@Getter
public enum Roles {
    ALUNO("aluno");

    private String role;

    Roles(String role){
        this.role = role;
    }
}
