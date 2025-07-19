package com.MarcelaEMariaLuiza.Syncro.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import com.MarcelaEMariaLuiza.Syncro.Services.AlunoService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private AlunoService alunoService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
        .csrf(AbstractHttpConfigurer::disable)
        .formLogin(httpForm->{
            httpForm.loginPage("/aluno/login").permitAll();
        })
        .authorizeHttpRequests(registry->{
        registry.requestMatchers("/aluno/create").permitAll();
        registry.anyRequest().authenticated();
    })
        .build();
    }
}
