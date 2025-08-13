package com.MarcelaEMariaLuiza.Syncro.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/**
 * Classe central de configuração para o Spring Security.
 * <p>
 * Habilita a segurança web na aplicação e define a cadeia de filtros (filter chain)
 * que processa as requisições HTTP, determinando as regras de autorização e
 * integrando o filtro de validação de token JWT.
 * </p>
 *
 * @author Marcela, Maria Luiza
 * @version 1.0
 * @since 2025-07-28
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
   
    @Autowired
    private SecurityFilter securityFilter;

    /**
     * Define o bean da cadeia de filtros de segurança principal.
     * <p>
     * Esta configuração desabilita o CSRF, define a política de gerenciamento de sessão
     * como STATELESS (adequado para APIs com JWT), e configura as regras de autorização
     * para os endpoints HTTP. O filtro customizado {@link SecurityFilter} é adicionado
     * à cadeia para processar a autenticação via token.
     * </p>
     *
     * @param httpSecurity O objeto de configuração do Spring Security.
     * @return O objeto {@link SecurityFilterChain} construído.
     * @throws Exception se ocorrer um erro durante a configuração.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
        .cors(withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        
        .authorizeHttpRequests(registry -> {
            registry.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
            registry.requestMatchers("/api/aluno/create").permitAll();
            registry.requestMatchers("/api/aluno/login").permitAll();
            registry.anyRequest().authenticated();
        })

        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build(); // O build() finaliza a configuração
    }


    /**
     * Expõe o AuthenticationManager como um Bean para ser utilizado na aplicação.
     * <p>
     * Este bean é necessário para que o processo de autenticação do Spring Security
     * possa ser gerenciado e utilizado, por exemplo, em controllers de login.
     * </p>
     *
     * @param authenticationConfiguration A configuração de autenticação do Spring.
     * @return O {@link AuthenticationManager} gerenciado pelo Spring.
     * @throws Exception se não for possível obter o AuthenticationManager da configuração.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration ) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    }
    



